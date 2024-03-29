package com.test;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

public class PreparedStatementTest
{
	private String driver;
	private String url;
	private String user;
	private String pass;
	public void initParam(String paramFile)throws Exception
	{
		// 使用Properties类来加载属性文件
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
		// 加载驱动
		Class.forName(driver);
	}
	public void insertUseStatement()throws Exception
	{
		long start = System.currentTimeMillis();  //时间戳，毫秒
		try(
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(url
				, user , pass);
			// 使用Connection来创建一个Statment对象
			Statement stmt = conn.createStatement())
		{
			// 需要使用100条SQL语句来插入100条记录
			for (int i = 0; i < 100 ; i++ )
			{
				stmt.executeUpdate("insert into jdbc_test (jdbc_name,jdbc_desc) values("
					+ " '姓名" + i + "', '描述')");
			}
			System.out.println("使用Statement费时:"
				+ (System.currentTimeMillis() - start));
		}
	}
	public void insertUsePrepare()throws Exception
	{
		long start = System.currentTimeMillis();
		try(
			// 获取数据库连接
			Connection conn = DriverManager.getConnection(url
				, user , pass);
			// 使用Connection来创建一个PreparedStatement对象
			PreparedStatement pstmt = conn.prepareStatement(
				"insert into jdbc_test (jdbc_name,jdbc_desc) values(?,'描述')"))

		{
			// 100次为PreparedStatement的参数设值，就可以插入100条记录
			for (int i = 0; i < 100 ; i++ )
			{	
				//设置第一个?的类型及值，如果不清楚参数的类型，可以用setObject()方法，由PrepareStatement来负责类型转换
				pstmt.setString(1 , "姓名" + i);    
				pstmt.executeUpdate();
			}
			System.out.println("使用PreparedStatement费时:"
				+ (System.currentTimeMillis() - start));
		}
	}
	public static void main(String[] args) throws Exception
	{
		PreparedStatementTest pt = new PreparedStatementTest();
		pt.initParam("mysql.ini");
		pt.insertUseStatement();
		pt.insertUsePrepare();
	}
}
