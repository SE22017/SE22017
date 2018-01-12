package test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class ProcessCURD {
	//获取流程引擎
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
@Test
public void deplay(){
	//获取仓库的服务实例
	Deployment deployment=	processEngine.getRepositoryService()
				 .createDeployment()
				 .name("请假")//添加部署的显示别名
				 .addClasspathResource("Activiti/activitiEmployeeProcess.bpmn")//从类路径下添加定义的规则文件
				 .addClasspathResource("Activiti/activitiEmployeeProcess.png")//从类路径下添加定义的图片
				 .deploy();//完成部署
	System.out.println(deployment.getName()+"----"+deployment.getId());
	
}
@Test
//删除定义流程（根据定义的key删除，删除key相同的版本不同的流程）
public void deleteProcess(){
	//查询所有定义的流程定义信息
	List<ProcessDefinition> list =processEngine.getRepositoryService()
				                  .createProcessDefinitionQuery()
				                  .processDefinitionKey("zuTaskProcess")
				                  .list();
	//循环删除
	for (ProcessDefinition pd:list){
		processEngine.getRepositoryService()
					 .deleteDeployment(pd.getDeploymentId(), true);
	}
	System.out.println("删除成功！");
	
	
}
@Test
public void startProcess(){
	//启动流程，
	//使用流程定义的key启动流程实例，默认会按照最新版本启动流程实例。
	ProcessInstance pi=processEngine.getRuntimeService()//获取正在执行得service
									.startProcessInstanceByKey("zuTaskProcess");//使用流程定义的key启动流程实例，默认会按照最新版本启动流程实例
	System.out.println(pi.getId()+"---"+pi.getActivityId()+"++"+pi.getName());
}
@Test
public void queryMyTask(){
	//指定任务办理者
	String assignee="组长1";
	//查询任务列表
	List<Task> list=processEngine.getTaskService()//获取服务
								 .createTaskQuery()//创建查询任务的对象
								 .taskAssignee(assignee)//指定个人任务班里人
								 .orderByTaskCreateTime().desc()//按照任务创建时间升序排列
								 .list();
	for(Task task:list){
		System.out.println(task.getAssignee()+"++"+task.getId());
		
	}
}
@Test
public void completeTask(){
	//指定任务id
	String taskId="165012";
	//完成任务
	processEngine.getTaskService().complete(taskId);
	System.out.println("完成任务");
	
}
@Test
public void queryProcessDefition(){
	//获取查询的仓库服务对象
	List<ProcessDefinition> pList=processEngine.getRepositoryService()
												.createProcessDefinitionQuery()//创建查询
												.orderByProcessDefinitionVersion().asc()//添加查询的条件
												.list();//返回结果集
	for(ProcessDefinition processDefinition:pList){
		
		System.out.println(processDefinition.getName()+"++"+processDefinition.getVersion());
	}
}
@Test
public void viewImage() throws IOException{
	String deploymentId="1";
	//从仓库中查询要显示的图片,获取图片资源名称
	List<String> names=processEngine.getRepositoryService()
									.getDeploymentResourceNames(deploymentId);
	//定义图片名称
	String imageName=null;
	for(String name:names){
		System.out.println("name="+name);
		if(name.indexOf(".png")>=0){
			imageName=name;
		}

	}
	System.out.println("imgaName="+imageName);								
	if(imageName!=null){
		//将图片生成到F盘目录下
		File file=new File("f:/"+imageName);
		//通过部署ID和文件名称获得文件的输入流，，获取图片文件的输入流
		InputStream in=processEngine.getRepositoryService()
									.getResourceAsStream(deploymentId, imageName);
		//将输入流的图片写到F盘下
		FileUtils.copyInputStreamToFile(in, file);
		
	}
}
@Test
public void deplayNew(){
	InputStream inoutStream=this.getClass().getResourceAsStream("/Activiti/zuTaskProcess.bpmn");
	InputStream inoutStreampng=this.getClass().getResourceAsStream("/Activiti/zuTaskProcess.png");
	Deployment deployment=	processEngine.getRepositoryService()
					 .createDeployment()
					 .name("组任务")
					 .addInputStream("zuTaskProcess.bpmn", inoutStream)//设置资源名称
					 .addInputStream("zuTaskProcess.png", inoutStreampng)//设置资源名称
					 .deploy();//完成部署
	System.out.println(deployment.getId()+"+++"+deployment.getName());
}
//查询组任务列表
@Test
public void findGroupList(){
	String userId="组长1";
	List<Task> list=processEngine.getTaskService()
								 .createTaskQuery()
								 .taskCandidateUser(userId)
								 .list();
	for(Task task:list){
		System.out.println(task.getId()+"..."+task.getName()+"..."+task.getAssignee());
		
	}
}
//查询组任务成员
@Test
public void findGroupUser(){
	//根据任务编号进行查询
	String taskId="137504";
	//根据运行实例进行查询
	String intenceId="125001";
	List<IdentityLink> list=processEngine.getTaskService()
										 .getIdentityLinksForTask(taskId);
	/*List<IdentityLink> list=processEngine.getRuntimeService()
										  .getIdentityLinksForProcessInstance(intenceId);*/
	for(IdentityLink il:list){
		System.out.println(il.getTaskId()+"..."+il.getUserId());
		
	}
}
//将组任务分配到个人，拾取任务，由一个人去完成
@Test
public void claim(){
	String taskId="125004";
	String userId="李四";
	processEngine.getTaskService().claim(taskId, userId);
}
//向组任务添加成员
@Test
public void addUser(){
	String taskId="137504";
	String user="小明";
	processEngine.getTaskService().addCandidateUser(taskId, user);
}
//向组任务删除成员
@Test
public void delUser(){
	String taskId="137504";
	String user="小明";
	processEngine.getTaskService().deleteCandidateUser(taskId, user);
	
}
}
