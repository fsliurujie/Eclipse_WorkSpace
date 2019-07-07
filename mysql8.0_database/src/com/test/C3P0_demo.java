package com.test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://192.168.150.128:3306/testdb";
		String DB_USERNAME = "lrjmysql"; 
		String DB_PASSWORD = "Qwer.1234";
		
		//创建连接池实例
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
        	//设置连接池连接数据库所需的驱动
			ds.setDriverClass(DB_DRIVER);
			//设置连接数据库的URL
			ds.setJdbcUrl(DB_URL);
			//设置接连数据库的用户名
			ds.setUser(DB_USERNAME);
			//设置连接数据库的密码
			ds.setPassword(DB_PASSWORD);
			//设置连接池的最大连接数
			ds.setMaxPoolSize(40);
			//设置连接池的最小连接数
			ds.setMinPoolSize(10);
			//设置连接池的初始连接数
			ds.setInitialPoolSize(10);
			//设置连接池的缓存Statement的最大数
			ds.setMaxStatements(180);
			//以上也可以通过新建一个配置文件，命名必须为c3p0-config.xml,
			//必须放在src目录下，c3p0包会默认加载src目录下的c3p0-config.xml文件。来实现相关设置
			//可参考 https://blog.csdn.net/qq_42035966/article/details/81332343
			
			//获取数据库连接
			Connection conn = ds.getConnection();
			System.out.println("获取数据库连接成功");
			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}

}
