package com.test;

import java.util.*;
import java.io.*;
import java.sql.*;

public class ExecuteDML {
	private String driver;
	private String url;
	private String user;
	private String pass;

	public void initParam(String paramFile) throws Exception {
		// 使用Properties类来加载属性文件
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
	}

	public int dmlData(String sql) throws Exception {
		// 加载驱动
		Class.forName(driver);
		try (
				// 获取数据库连接
				Connection conn = DriverManager.getConnection(url, user, pass);
				// 使用Connection来创建一个Statment对象
				Statement stmt = conn.createStatement()) {
			// 执行DML,返回受影响的记录条数
			return stmt.executeUpdate(sql);
		}
	}

	public static void main(String[] args) throws Exception {
		ExecuteDML ed = new ExecuteDML();
		ed.initParam("mysql.ini");
		int result = ed.dmlData("insert into jdbc_test(jdbc_name,jdbc_desc)" + "values (\"lrj\",\"test_insert\");");
		System.out.println("--系统中共有" + result + "条记录受影响--");

		result = ed.dmlData("update jdbc_test set jdbc_desc=\"test_update\"" + "where jdbc_name=\"lrj\";");
		System.out.println("--系统中共有" + result + "条记录受影响--");
		
		result = ed.dmlData("delete from jdbc_test " + "where jdbc_id=2;");
		System.out.println("--系统中共有" + result + "条记录受影响--");
	}
}
