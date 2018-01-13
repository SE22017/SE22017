package test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;


public class ProcessInsts {
	//获取流程引擎
	ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
	//设置变量
	@Test
	public void setVariable(){
		//获取执行的service
		TaskService taskService=processEngine.getTaskService();
		//指定办理人
		String assignee="张三";
		//流程实例id
		String intanceId="15001";
		Task task=taskService.createTaskQuery()
							 .taskAssignee(assignee)
							 .processInstanceId(intanceId)
							 .singleResult();
		/**变量中存放基本的数据类型*/
		taskService.setVariable(task.getId(), "请假人", "张物理");
		taskService.setVariable(task.getId(), "请假天数", 6);
		taskService.setVariable(task.getId(), "请假日期", new Date());
		/**变量存放javabean对象，前提是javabean实现Serializable接口*/
		/*Person p=new Person();
		p.setId("225");
		p.setName("翠湖");
		taskService.setVariable(task.getId(), "人员信息", p);*/
	}
	@Test
	public void getVariable(){
		//获取执行的service
		TaskService taskService=processEngine.getTaskService();
		//指定办理人
		String assignee="李四";
		//流程实例id
		String intanceId="15001";
		Task task=taskService.createTaskQuery()
						 	 .taskAssignee(assignee)
							 .processInstanceId(intanceId)
							 .singleResult();
		String namevalue=(String)taskService.getVariable(task.getId(), "请假人");
		int intday=(Integer)taskService.getVariable(task.getId(), "请假天数");
		Date date=(Date)taskService.getVariable(task.getId(), "请假日期");
		System.out.println(namevalue+"    "+intday+"   "+date);
		
		/*Person p=(Person)taskService.getVariable(task.getId(), "人员信息");
		System.out.println(p.getId()+"   "+p.getName());*/
	}
	/*@Test
	public void findMyTaskList(){
		String user="张三";
		List<Task> list=processEngine.getTaskService()
									 .createTaskQuery()
									 .taskAssignee(user)
									 .list();
		for(Task task:list){
			System.out.println(task.getId());
			System.out.println(task.getName());
			System.out.println(task.getAssignee());
			System.out.println(task.getExecutionId());
		}
	}*/
	@Test
	public void findMyTaskList(){
		String userId = "组长1";
		List<Task> list = processEngine.getTaskService()//
		                .createTaskQuery()//
		                .taskAssignee(userId)//指定个人任务查询
		                .list();
		for(Task task:list ){
			System.out.println("id="+task.getId());
			System.out.println("name="+task.getName());
			System.out.println("assinee="+task.getAssignee());
			System.out.println("assinee="+task.getCreateTime());
			System.out.println("executionId="+task.getExecutionId());
			
		}
	}
	@Test
	public void completeTask(){
		String taskId="82504";
		Map<String, Object> variables=new HashMap<String, Object>();
		variables.put("amout", 400);
		processEngine.getTaskService()
					.complete(taskId, variables);
		System.out.println("完成任务");
	}
	@Test
	public void runStep(){
		//查询是否有一个执行对象在**状态
		Execution e=processEngine.getRuntimeService()
								 .createExecutionQuery()
								 .processInstanceId("90001")
								 .activityId("分发")
								 .singleResult();
		//设置流程变量
		//Map<String ,Object> map=new HashMap<String ,Object>();
		//map.put("当日销售", 1000);
		//流程向后执行一步，，当前任务已经执行结束，，使用signal是流程往下执行一步，
		//processEngine.getRuntimeService()
		//			 .signal(e.getId(), map);
		//获取流程变量、
		int num=(Integer) processEngine.getRuntimeService()
									   .getVariable(e.getId(), "当日销售");
		System.out.println(num);
		processEngine.getRuntimeService()
					 .signal(e.getId());
	}
	@Test
	//查询路程状态
	public void processStatus(){
		ProcessInstance pi=processEngine.getRuntimeService()
					 .createProcessInstanceQuery()
					 .processInstanceId("90001")
					 .singleResult();
		if(pi==null){
			System.out.println("执行完成");
		}
	}
	@Test
	public void taskGroup(){
		String userId="组长1";
		List<Task> taskList=processEngine.getTaskService()
				  .createTaskQuery()
				  .taskCandidateGroup(userId)
				  .list();
		for(Task task:taskList){
			System.out.println("id="+task.getId());
			System.out.println("name="+task.getName());
			System.out.println("assinee="+task.getAssignee());
			System.out.println("assinee="+task.getCreateTime());
			System.out.println("executionId="+task.getExecutionId());	
			
		}
		
	}
	
	@Test
	public void test55(){
		String userId = "组长1";//小张，小李可以查询结果，小王不可以，因为他不是部门经理角色
		List list = processEngine.getTaskService()//
		.createTaskQuery()//
		.taskCandidateUser(userId)//指定组任务查询
		.list();
		System.out.println(list);
	}
	@Test
	public void test888(){
	
		System.out.println(UUID.randomUUID().toString());
	}
}
