package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select_demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 驱动类，如果mysql是5.0及以下版本为 com.mysql.jdbc.Driver
		String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		// 需要注意MySQL8.0和低版本不同的地方：DB_DRIVER要写com.mysql.cj.jdbc.Driver，并且DB_URL后面要加上useSSL和serverTimezone
		String DB_URL = "jdbc:mysql://192.168.150.128:3306/testdb"; // 也可以加上?useSSL=true&serverTimezone=Asia/Shanghai&characterEncoding=UTF-8
		String DB_USERNAME = "lrjmysql"; // 8.0 不能直接用root
		String DB_PASSWORD = "Qwer.1234";
		// 上面这些信息可以写到配置文件里，通过读文件的方式读取，这样上生产就只改配置文件而不用改源码了
//		/* 数据库操作分4步 
//		 * 1.加载驱动 
//		 * 2.获取连接
//		 * 3.通过Connection对象创建Statement对象
//		 *   createStatement()            :创建基本的Statement对象，大批量操作时，一般不用
//		 *   prepareStatement(String sql) :根据传入的SQL语句创建预编译的Statement对象，使用?占位，效率比Statement要高，还能防SQL注入，一般用这个。
//		 *   prepareCall(String sql)      :根据传入的SQL语句创建CallableStatement对象，一般用来调存储过程
//		 * 4.使用Statement执行SQL语句
//		 *   execute()       :可以执行任何SQL语句，但比较麻烦，返回boolean值，如果为true则表示有ResultSet
//		 *   executeUpdate() :主要用于执行DML和DDL语句，执行DML语句返回受影响的行数，执行DDL语句返回0
//		 *   executeQuery()  :只能执行查询语句，执行后返回代表查询结果的ResultSet对象
//		 * 5.关闭相关资源，如 Connection Statement ResultSet
//		 */

		try {
			Class.forName(DB_DRIVER);
			System.out.println("数据库加载成功!");
			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			System.out.println("获取数据库连接句柄成功! 获取连接对象:" + connection);
			Statement stmt = connection.createStatement();

			// stmt.execute("USE `testdb`"); //连接里已经指定了用testdb，这里这句可以省略
			// 查询例子
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM user_info");
			while (resultSet.next()) {
				System.out.println(resultSet.getInt("userid") + "\t" + resultSet.getString("username") + "\t"
						+ resultSet.getString("passwd") + "\t" + resultSet.getInt("age") + "\t"
						+ resultSet.getString("address"));
				// 或者可以这样取
				System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3)
						+ "\t" + resultSet.getInt(4) + "\t" + resultSet.getString(5));
			}

			resultSet.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			System.out.println("捕获异常");
		}

	}

}
