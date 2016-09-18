package com.basic.util.mysql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.basic.model.base.ReturnData;
import com.basic.util.PropertiesUtils;
/**
 * 创建数据库并导入sql文件
 * 
 * @author qiushi.zhou  
 * @date 2016年9月1日 上午8:54:06
 */
public class DatabaseCreatorMysql{
	
	private static Logger logger = LoggerFactory.getLogger(DatabaseCreatorMysql.class);
	
	private static String mysqlDriver = PropertiesUtils.getPropertyByKey("jdbc.properties", "jdbc.driver");
	private static String url = PropertiesUtils.getPropertyByKey("jdbc.properties", "jdbc.url");
	private static String username = PropertiesUtils.getPropertyByKey("jdbc.properties", "jdbc.username");
	private static String password = PropertiesUtils.getPropertyByKey("jdbc.properties", "jdbc.password");
	
	Connection conn = null;
	
	/**
	 * 创建数据库并导入数据
	 * 
	 * @param 
	 * @return String
	 */
	public String databaseCreateAndImportMysql(String databaseName,String sqlFilePath){
		String result = null;
		String databaseSql = "create database " +databaseName;
		if(this.createDatabase(databaseSql).equals(ReturnData.SUCCESS_CODE)){
			result = this.initializeDatabase(databaseName, sqlFilePath);	
		}
		return result;
	}
	/**
	 * 删除数据库
	 * 
	 * @param 
	 * @return String
	 */
	public String dropDatabase(String databaseName){
		String databaseSql = "drop database "+databaseName;
		return this.createDatabase(databaseSql);
	}
	
	/** 
	 * 数据库操作
	 * 
	 * @param 
	 * @return String
	 */
	@Transactional
	public String createDatabase(String databaseSql){
		Statement smt = null;
		String returnResult = ReturnData.FAIL_CODE;
		try{
			Class.forName(mysqlDriver);
			
			try{
				conn = DriverManager.getConnection(url,username,password);
				if(conn !=null){
					smt = conn.createStatement();
					smt.executeUpdate(databaseSql);
					returnResult = ReturnData.SUCCESS_CODE;
				}
			}catch(Exception e2){
				logger.error("数据库操作失败：[{}]",e2);
			}
		}catch(Exception e){
			logger.error("加载mysql驱动失败：[{}]",e);
		}finally{
			try {
				conn.close();
				smt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returnResult;
	}
	
	/** 
	 * 导入sql文件
	 * 
	 * @param 
	 * @return void
	 */
	@Transactional
	public String initializeDatabase(String databaseName, String sqlFilePath) {
		String returnResult = ReturnData.SUCCESS_CODE;
		try {

			StringBuffer sb = new StringBuffer("cmd.exe /C mysql -u");
			sb.append(username);

			if (password != null && !password.equalsIgnoreCase(""))
				sb.append(" -p").append(password);

			sb.append(" ").append(databaseName).append("<")
					.append(sqlFilePath);
			java.lang.Runtime.getRuntime().exec(sb.toString());

		} catch (IOException e) {
			returnResult = ReturnData.FAIL_CODE;
			logger.error("导入sql文件失败:[{}]",e);
		}
		return returnResult;

	} 
	 
	 public static void main(String[] args){
		 
		 System.out.println(password);
//		 for(int i=0; i< 1000; i++){
//			 String databaseName = "database_"+i;
//			 String sqlFilePath = "D:/root.sql";
//			 new DatabaseCreatorMysql().databaseCreateAndImportMysql(databaseName, sqlFilePath);
//			 System.out.println("创建完成数据库："+databaseName+"\r\n");
//			 try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			 new DatabaseCreatorMysql().dropDatabase(databaseName);
//			 System.out.println("删除数据库："+databaseName+"\r\n");
//		 }
		 
		}
}
