package com.cn.swm.controller.activiti;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cn.swm.model.User;
import com.cn.swm.model.activiti.ActivitiRtn;
import com.cn.swm.model.activiti.PageInfo;
import com.cn.swm.model.leave.MyTask;
import com.cn.swm.utils.DateJsonValueProcessor;
import com.cn.swm.utils.ResponseUtil;
import com.cn.swm.utils.StringUtil;

@Controller
@RequestMapping("/activti")
public class ActController {
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private TaskService taskService;
	@Resource
	private HistoryService historyService;
	@Autowired  
    ProcessEngineFactoryBean processEngine;  
	@Resource  
	ProcessEngineConfiguration processEngineConfiguration;  
	@Resource
	private RuntimeService runtimeService;
	/**
	 * 获取所有流程部署
	 * @param response
	 * @param request
	 * @throws Exception
	 * @date 2017年9月20日上午8:59:41
	 * @author sunshijian
	 */
	@RequestMapping("/deployPage")
	public void deployPage(HttpServletResponse response,HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("offset");//页码
 		String rows = request.getParameter("limit");//页面大小
 		String s_name=setSearchText(request.getParameter("search_name"));
		if(s_name==null){
			s_name="";
		}
		PageInfo pageInfo=new PageInfo();
		
		Integer sizePage=Integer.parseInt(rows);
		
		String pageIndex = page;
		if (pageIndex == null || pageIndex == "") {
				pageIndex = "1";
		}
		pageInfo.setPageSize(sizePage);
		pageInfo.setPageIndex((Integer.parseInt(pageIndex)));
		//根据名称查询的数量
		long deployCount=repositoryService.createDeploymentQuery().deploymentNameLike("%"+s_name+"%")
				.count();
		List<Deployment> deployList=repositoryService.createDeploymentQuery()//获取服务
				.orderByDeploymenTime().desc()  //根据创建时间降序
				.deploymentNameLike("%"+s_name+"%")   //根据传入的参数进行查询
				.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
		
		
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"resources"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(deployList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", deployCount);
		ResponseUtil.write(response, result);
	
	}
	/**
	 * 
	 * @param searchText
	 * @return
	 * @date 2017年9月20日上午9:05:16
	 * @author sunshijian
	 */
	 public String setSearchText(String searchText) {  
	      try {  
	    	  searchText = new String(searchText.getBytes("ISO8859-1"), "UTF-8");}  
	      catch (UnsupportedEncodingException e) {  
	    	  e.printStackTrace();  
	      }
	      return searchText;
	  }  
	/**
	 * 新增部署流程
	 * @param response
	 * @param deployFile
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2017年9月20日上午9:05:48
	 * @author sunshijian
	 */
	@RequestMapping("/addDeploy")
	public String addDeploy(HttpServletResponse response,MultipartFile deployFile,HttpServletRequest request) throws Exception{
		
		String name = request.getParameter("deployName");
		repositoryService.createDeployment() 
		.name(name)	
		.addZipInputStream(new ZipInputStream(deployFile.getInputStream()))
		.deploy();
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 删除部署流程
	 * @param response
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 * @date 2017年9月20日上午9:07:23
	 * @author sunshijian
	 */
	@RequestMapping("/delDeploy")
	public String delDeploy(HttpServletResponse response,String ids, HttpServletRequest request) throws Exception{
		//String id = request.getParameter("ids");
		String[] idsStr=ids.split(",");
		for(String str:idsStr){
			repositoryService.deleteDeployment(str, true);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * 根据流程部署获取流程图
	 * @param deploymentId
	 * @param diagramResourceName
	 * @param response
	 * @return
	 * @throws Exception
	 * @date 2017年9月20日上午9:07:55
	 * @author sunshijian
	 */
	@RequestMapping("/showView")
	public String showView(String deploymentId,String diagramResourceName,HttpServletResponse response)throws Exception{
		
		//从仓库中查询要显示的图片,获取图片资源名称
		List<String> names=repositoryService
										.getDeploymentResourceNames(deploymentId);
		//定义图片名称
		String imageName=null;
		for(String name:names){
			if(name.indexOf(".png")>=0){
				imageName=name;
			}
		}
		InputStream inputStream=repositoryService.getResourceAsStream(deploymentId, imageName);
		OutputStream out=response.getOutputStream();
		for(int b=-1;(b=inputStream.read())!=-1;){
			out.write(b);
		}
		out.close();
		inputStream.close();
		return null;
	}
	/**
	 * 流程为角色获取任务的人员查询待办任务
	 * @param response
	 * @param request
	 * @throws Exception
	 * @date 2017年9月20日上午9:11:03
	 * @author sunshijian
	 */
	@RequestMapping("/taskPage")
	public void taskPage(HttpServletResponse response,HttpServletRequest request ) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("offset");//页码
 		String rows = request.getParameter("limit");//页面大小
 		String s_name=setSearchText(request.getParameter("search_name"));
		if(s_name==null){
			s_name="";
		}
		PageInfo pageInfo=new PageInfo();
		Integer sizePage=Integer.parseInt(rows);
		String pageIndex = page;
		if (pageIndex == null || pageIndex == "") {
				pageIndex = "1";
		}
		pageInfo.setPageSize(sizePage);
		pageInfo.setPageIndex((Integer.parseInt(pageIndex)));
	    HttpSession session = request.getSession();      
	    User user=(User)session.getAttribute("user");
		String userId = user.getName();
		long  taskCount = taskService
		.createTaskQuery()
		.taskCandidateUser(userId)
		.count();
		
		List<Task> list = taskService
		.createTaskQuery()
		.taskCandidateUser(userId)
		.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
		List<MyTask> MyTaskList=new ArrayList<MyTask>();
		for(Task t:list){
			MyTask myTask=new MyTask();
			myTask.setId(t.getId());
			myTask.setName(t.getName());
			myTask.setCreateTime(t.getCreateTime());
			myTask.setUser(t.getAssignee());
			MyTaskList.add(myTask);
		}

		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"resources"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(MyTaskList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", taskCount);
		ResponseUtil.write(response, result);
		
	}
	/**
	 * 获取待办任务
	 * @param response
	 * @param request
	 * @param assignee
	 * @date 2017年9月20日上午9:51:05
	 * @author sunshijian
	 */
	public void queryMyTask(HttpServletResponse response,HttpServletRequest request ,String assignee){
		//查询任务列表
		List<Task> list=taskService
								 .createTaskQuery()//创建查询任务的对象
								 .taskAssignee(assignee)//指定个人任务班里人
								 .orderByTaskCreateTime().desc()//按照任务创建时间升序排列
								 .list();
		for(Task task:list){
			System.out.println(task.getAssignee()+"++"+task.getId());
			
		}
	}
	/**
	 * 获取正在进行的流程图
	 * @param request
	 * @param response
	 * @throws IOException
	 * @date 2017年9月20日上午9:18:38
	 * @author sunshijian
	 */
	@RequestMapping("/processImg")  
    public void queryProPlan(HttpServletRequest request,HttpServletResponse response) throws IOException{  
        String processInstanceId = request.getParameter("ProcessInstanceId");  
        //获取历史流程实例  
        HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();  
        //获取流程图  
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());  
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();  
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);  
  
        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();  
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());  
  
        List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();  
        //高亮环节id集合  
        List<String> highLightedActivitis = new ArrayList<String>();  
          
        //高亮线路id集合  
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);  
  
        for(HistoricActivityInstance tempActivity : highLightedActivitList){  
            String activityId = tempActivity.getActivityId();  
            highLightedActivitis.add(activityId);  
        }  
  
        //中文显示的是口口口，设置字体就好了  
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体",null,processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0);  
        //单独返回流程图，不高亮显示  
        //InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);  
        // 输出资源内容到相应对象  
        byte[] b = new byte[1024];  
        int len;  
        while ((len = imageStream.read(b, 0, 1024)) != -1) {  
            response.getOutputStream().write(b, 0, len);  
        }  
	          
	    }  
     /**
      * 获取高亮显示的线进行显示
      * @param processDefinitionEntity
      * @param historicActivityInstances
      * @return
      * @date 2017年9月20日上午9:20:35
      * @author sunshijian
      */
	    private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,List<HistoricActivityInstance> historicActivityInstances) {  
	          
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId  
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历  
            ActivityImpl activityImpl = processDefinitionEntity  
            .findActivity(historicActivityInstances.get(i)  
            .getActivityId());// 得到节点定义的详细信息  
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点  
            ActivityImpl sameActivityImpl1 = processDefinitionEntity  
            .findActivity(historicActivityInstances.get(i + 1)  
            .getActivityId());  
            // 将后面第一个节点放在时间相同节点的集合里  
            sameStartTimeNodes.add(sameActivityImpl1);  
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {  
                HistoricActivityInstance activityImpl1 = historicActivityInstances  
                .get(j);// 后续第一个节点  
                HistoricActivityInstance activityImpl2 = historicActivityInstances  
                .get(j + 1);// 后续第二个节点  
                if (activityImpl1.getStartTime().equals(  
                    activityImpl2.getStartTime())) {  
                    // 如果第一个节点和第二个节点开始时间相同保存  
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity  
                    .findActivity(activityImpl2.getActivityId());  
                    sameStartTimeNodes.add(sameActivityImpl2);  
                } else {  
                    // 有不相同跳出循环  
                    break;  
                }  
            }  
            List<PvmTransition> pvmTransitions = activityImpl  
                    .getOutgoingTransitions();// 取出节点的所有出去的线  
            for (PvmTransition pvmTransition : pvmTransitions) {  
                // 对所有的线进行遍历  
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition  
                        .getDestination();  
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示  
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {  
                    highFlows.add(pvmTransition.getId());  
                }  
            }  
        }  
        return highFlows;  
	}  
	/**
	 * 获取流程执行历史
	 * @param response
	 * @param id
	 * @date 2017年9月20日上午9:22:34
	 * @author sunshijian
	 */
    @RequestMapping("/findHisList")  
    public void findHisActivitiList(HttpServletResponse response,String id){  
        String processInstanceId = id;  
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()  
        .processInstanceId(processInstanceId) 
        .list();  
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		result.put("data", jsonArray);
		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	    }  
    /**
     * 转办流程
     * @param taskId
     * @param userCode
     * @date 2017年9月20日上午9:24:13
     * @author sunshijian
     */
    @RequestMapping("/transferAssignee")  
  	public void transferAssignee(String taskId, String userCode) {  
      taskService.setAssignee(taskId, userCode);  
    } 
    /**
     * 终止流程
     * @param taskId
     * @param response
     * @return
     * @throws Exception
     * @date 2017年9月20日上午9:30:02
     * @author sunshijian
     */
    @RequestMapping("/endProcess") 
    public String endProcess(String taskId,HttpServletResponse response) throws Exception {  
    	ActivityImpl endActivity = findActivitiImpl(taskId, "end");  
        commitProcess(taskId, null, endActivity.getId());  
        JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
    } 
    
    /**
     * 根据当前任务ID，查询可以驳回的任务节点 
     * @param taskId
     * @param response
     * @throws Exception
     * @date 2017年9月20日上午9:30:53
     * @author sunshijian
     */
    @RequestMapping("/rollerlist") 
    public void findBackAvtivity(String taskId,HttpServletResponse response) throws Exception {  
		List<ActivityImpl> rtnList = null;  
        rtnList = iteratorBackActivity(taskId, findActivitiImpl(taskId,  
                    null), new ArrayList<ActivityImpl>(),  
                    new ArrayList<ActivityImpl>());  
       
        List<ActivityImpl> list= reverList(rtnList);  //反向显示list顺序
        List<ActivitiRtn> rtnlist=new ArrayList<>();
        for (ActivityImpl activiti:list){
        	ActivitiRtn actrtn=new ActivitiRtn();
        	actrtn.setStr1(activiti.getProperties().get("name"));
        	actrtn.setStr2(activiti.getId());
        	rtnlist.add(actrtn);
        }
       for(int i=0;i<rtnlist.size();i++){
    	   System.out.println("*******");
           System.out.println(rtnlist.get(i).getStr1());
           System.out.println(rtnlist.get(i).getStr2());
       }
        JSONObject result=new JSONObject();
		result.put("rows", rtnlist);
		result.put("total", rtnlist.size());
	    ResponseUtil.write(response, result);
 
    }  
   /**
    * 驳回流程
    * @param taskId
    * @param activityId
    * @param variables
    * @throws Exception
    * @date 2017年9月20日上午9:26:57
    * @author sunshijian
    */
    @RequestMapping("/backProcess")  
    public void backProcess(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        if (StringUtil.isEmpty(activityId)) {  
            throw new Exception("驳回目标节点ID为空！");  
        }  
        // 查询本节点发起的会签任务，并结束  
        List<Task> tasks = taskService.createTaskQuery().taskTenantId(taskId)
                .taskDescription("jointProcess").list();  
        for (Task task : tasks) {  
            commitProcess(task.getId(), null, null);  
        }  
        // 查找所有并行任务节点，同时驳回  
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(  
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());  
        for (Task task : taskList) {  
            commitProcess(task.getId(), variables, activityId);  
        }  
    }  
    /**
     * 根据任务ID和节点ID获取活动节点 
     * 如果为null或""，则默认查询当前活动节点
     * 如果为"end"，则查询结束节点 
     * @param taskId
     * @param activityId
     * @return
     * @throws Exception
     * @date 2017年9月20日上午9:32:13
     * @author sunshijian
     */
    private ActivityImpl findActivitiImpl(String taskId, String activityId) throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);  
        // 获取当前活动节点ID  
        if (StringUtil.isEmpty(activityId)) {  
            activityId = findTaskById(taskId).getTaskDefinitionKey();  
        }  
        // 根据流程定义，获取该流程实例的结束节点  
        if (activityId.toUpperCase().equals("END")) {  
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {  
                List<PvmTransition> pvmTransitionList = activityImpl  
                        .getOutgoingTransitions();  
                if (pvmTransitionList.isEmpty()) {  
                    return activityImpl;  
                }  
            }  
        }  
        // 根据节点ID，获取对应的活动节点  
        ActivityImpl activityImpl =  processDefinition 
                .findActivity(activityId);  
  
        return activityImpl;  
    }   
  /**
   * 根据任务ID获得任务实例 
   * @param taskId
   * @return
   * @throws Exception
   * @date 2017年9月20日上午9:33:41
   * @author sunshijian
   */
    private TaskEntity findTaskById(String taskId) throws Exception {  
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(  
                taskId).singleResult();  
        if (task == null) {  
            throw new Exception("任务实例未找到!");  
        }  
        return task;  
    }  
   /**
    * 根据任务ID获取流程定义  
    * @param taskId
    * @return
    * @throws Exception
    * @date 2017年9月20日上午9:33:55
    * @author sunshijian
    */
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(  
            String taskId) throws Exception {  
        // 取得流程定义  
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService  
                .getProcessDefinition(findTaskById(taskId).getProcessDefinitionId());  
  
        if (processDefinition == null) {  
            throw new Exception("流程定义未找到!");  
        }  
  
        return processDefinition;  
    } 
   
    /**
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点 
     * @param taskId 当前任务ID 
     * @param currActivity  当前活动节点 
     * @param rtnList  存储回退节点集合 
     * @param tempList 存储回退节点集合 
     * @return  临时存储节点集合（存储一次迭代过程中的同级userTask节点） 
     * @throws Exception
     * @date 2017年9月20日上午9:34:30
     * @author sunshijian
     */
    private List<ActivityImpl> iteratorBackActivity(String taskId,  
            ActivityImpl currActivity, List<ActivityImpl> rtnList,  
            List<ActivityImpl> tempList) throws Exception {  
        // 查询流程定义，生成流程树结构  
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);  
        // 当前节点的流入来源  
        List<PvmTransition> incomingTransitions = currActivity  
                .getIncomingTransitions();  
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点  
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();  
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点  
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();  
        // 遍历当前节点所有流入路径  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            ActivityImpl activityImpl = transitionImpl.getSource();  
            String type = (String) activityImpl.getProperty("type");  
            /** 
             * 并行节点配置要求：<br> 
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束) 
             */  
            if ("parallelGateway".equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId  
                        .lastIndexOf("_") + 1);  
                if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归  
                    return rtnList;  
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                    parallelGateways.add(activityImpl);  
                }  
            } else if ("startEvent".equals(type)) {// 开始节点，停止递归  
                return rtnList;  
            } else if ("userTask".equals(type)) {// 用户任务  
                tempList.add(activityImpl);  
            } else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点  
                currActivity = transitionImpl.getSource();  
                exclusiveGateways.add(currActivity);  
            }  
        }  
  
        /** 
         * 迭代条件分支集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : exclusiveGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
  
        /** 
         * 迭代并行集合，查询对应的userTask节点 
         */  
        for (ActivityImpl activityImpl : parallelGateways) {  
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);  
        }  
  
        /** 
         * 根据同级userTask集合，过滤最近发生的节点 
         */  
        currActivity = filterNewestActivity(processInstance, tempList);  
        if (currActivity != null) {  
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID  
            String id = findParallelGatewayId(currActivity);  
            if (StringUtil.isEmpty(id)) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点  
                rtnList.add(currActivity);  
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点  
                currActivity = findActivitiImpl(taskId, id);  
            }  
  
            // 清空本次迭代临时集合  
            tempList.clear();  
            // 执行下次迭代  
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);  
        }  
        return rtnList;  
    }  
    /**
     * 根据任务ID获取对应的流程实例 
     * @param taskId
     * @return
     * @throws Exception
     * @date 2017年9月20日上午9:35:53
     * @author sunshijian
     */
    private ProcessInstance findProcessInstanceByTaskId(String taskId)  
            throws Exception {  
        // 找到流程实例  
        ProcessInstance processInstance = runtimeService  
                .createProcessInstanceQuery().processInstanceId(  
                        findTaskById(taskId).getProcessInstanceId())  
                .singleResult();  
        if (processInstance == null) {  
            throw new Exception("流程实例未找到!");  
        }  
        return processInstance;  
    }  
   
    /**
     * 根据流入任务集合，查询最近一次的流入任务节点 
     * @param processInstance
     * @param tempList
     * @return
     * @date 2017年9月20日上午9:36:12
     * @author sunshijian
     */
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,  
            List<ActivityImpl> tempList) {  
        while (tempList.size() > 0) {  
            ActivityImpl activity_1 = tempList.get(0);  
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(  
                    processInstance, activity_1.getId());  
            if (activityInstance_1 == null) {  
                tempList.remove(activity_1);  
                continue;  
            }  
  
            if (tempList.size() > 1) {  
                ActivityImpl activity_2 = tempList.get(1);  
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(  
                        processInstance, activity_2.getId());  
                if (activityInstance_2 == null) {  
                    tempList.remove(activity_2);  
                    continue;  
                }  
  
                if (activityInstance_1.getEndTime().before(  
                        activityInstance_2.getEndTime())) {  
                    tempList.remove(activity_1);  
                } else {  
                    tempList.remove(activity_2);  
                }  
            } else {  
                break;  
            }  
        }  
        if (tempList.size() > 0) {  
            return tempList.get(0);  
        }  
        return null;  
    }  

    /**
     * 查询指定任务节点的最新记录 
     * @param processInstance
     * @param activityId
     * @return
     * @date 2017年9月20日上午9:36:33
     * @author sunshijian
     */
    private HistoricActivityInstance findHistoricUserTask(  
            ProcessInstance processInstance, String activityId) {  
        HistoricActivityInstance rtnVal = null;  
        // 查询当前流程实例审批结束的历史节点  
        List<HistoricActivityInstance> historicActivityInstances = historyService  
                .createHistoricActivityInstanceQuery().activityType("userTask")  
                .processInstanceId(processInstance.getId()).activityId(  
                        activityId).finished()  
                .orderByHistoricActivityInstanceEndTime().desc().list();  
        if (historicActivityInstances.size() > 0) {  
            rtnVal = historicActivityInstances.get(0);  
        }  
  
        return rtnVal;  
    }  
  
    /**
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID 
     * @param activityImpl
     * @return
     * @date 2017年9月20日上午9:37:00
     * @author sunshijian
     */
    private String findParallelGatewayId(ActivityImpl activityImpl) {  
        List<PvmTransition> incomingTransitions = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : incomingTransitions) {  
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;  
            activityImpl = transitionImpl.getDestination();  
            String type = (String) activityImpl.getProperty("type");  
            if ("parallelGateway".equals(type)) {// 并行路线  
                String gatewayId = activityImpl.getId();  
                String gatewayType = gatewayId.substring(gatewayId  
                        .lastIndexOf("_") + 1);  
                if ("END".equals(gatewayType.toUpperCase())) {  
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))  
                            + "_start";  
                }  
            }  
        }  
        return null;  
    }  
   
    /**
     * 反向排序list集合，便于驳回节点按顺序显示 
     * @param list
     * @return
     * @date 2017年9月20日上午9:37:24
     * @author sunshijian
     */
    private List<ActivityImpl> reverList(List<ActivityImpl> list) {  
        List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();  
        // 由于迭代出现重复数据，排除重复  
        for (int i = list.size(); i > 0; i--) {  
            if (!rtnList.contains(list.get(i - 1)))  
                rtnList.add(list.get(i - 1));  
        }  
        return rtnList;  
    }  
   
    /**
     * 流程提交操作
     * @param taskId 当前任务ID 
     * @param variables 流程变量 
     * @param activityId  流程转向执行任务节点ID,此参数为空默认为提交操作            
     * @throws Exception
     * @date 2017年9月20日上午9:37:43
     * @author sunshijian
     */
    private void commitProcess(String taskId, Map<String, Object> variables,String activityId) throws Exception {  
        if (variables == null) {  
            variables = new HashMap<String, Object>();  
        }  
        // 跳转节点为空，默认提交操作  
        if (StringUtil.isEmpty(activityId)) {  
            taskService.complete(taskId, variables);  
        } else {// 流程转向操作  
            turnTransition(taskId, activityId, variables);  
        }  
    }  
   
    /**
     * 流程转向操作 
     * @param taskId
     * @param activityId
     * @param variables
     * @throws Exception
     * @date 2017年9月20日上午9:39:42
     * @author sunshijian
     */
    private void turnTransition(String taskId, String activityId,  
            Map<String, Object> variables) throws Exception {  
        // 当前节点  
        ActivityImpl currActivity = findActivitiImpl(taskId, null);  
        // 清空当前流向  
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);  
  
        // 创建新流向  
        TransitionImpl newTransition = currActivity.createOutgoingTransition();  
        // 目标节点  
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);  
        // 设置新流向的目标节点  
        newTransition.setDestination(pointActivity);  
  
        // 执行转向任务  
        taskService.complete(taskId, variables);  
        // 删除目标节点新流入  
        pointActivity.getIncomingTransitions().remove(newTransition);  
  
        // 还原以前流向  
        restoreTransition(currActivity, oriPvmTransitionList);  
    }  
   
    /**
     * 清空指定活动节点流向 
     * @param activityImpl
     * @return
     * @date 2017年9月20日上午9:40:14
     * @author sunshijian
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {  
        // 存储当前节点所有流向临时变量  
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();  
        // 获取当前节点所有流向，存储到临时变量，然后清空  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        for (PvmTransition pvmTransition : pvmTransitionList) {  
            oriPvmTransitionList.add(pvmTransition);  
        }  
        pvmTransitionList.clear();  
  
        return oriPvmTransitionList;  
    } 
   
    /**
     * 还原指定活动节点流向 
     * @param activityImpl
     * @param oriPvmTransitionList
     * @date 2017年9月20日上午9:40:38
     * @author sunshijian
     */
    private void restoreTransition(ActivityImpl activityImpl,  
            List<PvmTransition> oriPvmTransitionList) {  
        // 清空现有流向  
        List<PvmTransition> pvmTransitionList = activityImpl  
                .getOutgoingTransitions();  
        pvmTransitionList.clear();  
        // 还原以前流向  
        for (PvmTransition pvmTransition : oriPvmTransitionList) {  
            pvmTransitionList.add(pvmTransition);  
        }  
    }  
    /**
     * 挂起一个流程（测试）
     * @param processInstanceId
     * @date 2017年9月20日上午9:40:56
     * @author sunshijian
     */
    @RequestMapping("/SuspendProcessInstance")
    public void SuspendProcessInstance(String processInstanceId){
        //根据一个流程实例的id挂起该流程实例
    	processInstanceId="200001";
        runtimeService.suspendProcessInstanceById(processInstanceId);
        
    }
    /**
     * 根据流程实例ID和任务key值查询所有同级任务集合 
     * @param processInstanceId
     * @param key
     * @return
     * @date 2017年9月20日上午9:41:26
     * @author sunshijian
     */
    private List<Task> findTaskListByKey(String processInstanceId, String key) {  
        return taskService.createTaskQuery().processInstanceId(  
                processInstanceId).taskDefinitionKey(key).list();  
    }  
    
}
