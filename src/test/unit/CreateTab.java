//package test;
//
//import org.activiti.engine.ProcessEngine;
//import org.activiti.engine.ProcessEngineConfiguration;
//import org.junit.Test;
//
//public class CreateTab {
//	/**使用配置文件创建工作流需要的23张表*/
//	@Test
//	public void createTable_2(){
//
//		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")	//
//									.buildProcessEngine();
//		System.out.println("processEngine:"+processEngine);
//	}
//}
package test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class CreateTab {
	/**使用配置文件创建工作流需要的23张表*/
	@Test
	public void createTable_2(){

		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")	//
				.buildProcessEngine();
		System.out.println("processEngine:"+processEngine);
	}
	@Test
	public void testCreateTable() {
		// 引擎配置
		ProcessEngineConfiguration pec=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		pec.setJdbcDriver("com.mysql.jdbc.Driver");
		pec.setJdbcUrl("jdbc:mysql://localhost:3306/learn?useUnicode=true&characterEncoding=utf8");
		pec.setJdbcUsername("root");
		pec.setJdbcPassword("123456");
		/**
		 * DB_SCHEMA_UPDATE_FALSE 不能自动创建表，需要表存在
		 * create-drop 先删除表再创建表
		 * DB_SCHEMA_UPDATE_TRUE 如何表不存在，自动创建和更新表
		 */
		pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		// 获取流程引擎对象
		ProcessEngine processEngine=pec.buildProcessEngine();
	}

}

