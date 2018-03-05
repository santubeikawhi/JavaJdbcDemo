package com.jl.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCallableStatementsDemo {
	//静态参数数据库连接URL
	static final String DB_URL = "jdbc:mysql://localhost:3306";
	//数据库账号
	static final String USER = "root";
	//数据库账号密码
	static final String PASS = "admin";
	
	public static void main(String args[]){
		Connection conn = null;
		//存储过程调用声明
		CallableStatement cstmt = null;
		try {
			//初始化驱动程序，打开与数据库的通信
			Class.forName("com.mysql.jdbc.Driver");
			//数据库物理连接
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//创建数据库语句
			System.out.println("Creating CallableStatement...");
		    
		    //数据库查询语句
		    String sql = "{call sys.sqlAll()}";
		    cstmt = conn.prepareCall(sql);
		    ResultSet rs = cstmt.executeQuery();
		    while(rs.next()){
		    	String custId  = rs.getString("cust_id");
		        String name = rs.getString("name_");
		        int first = rs.getInt("age_");
		        System.out.println("---用户信息---");
		        System.out.println("用户Id："+custId);
		        System.out.println("用户名："+name);
		        System.out.println("用户年龄："+first);
		        System.out.println("-----------");
		    }
		    //清理环境资源
		    rs.close();
		    cstmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			System.out.println(se.getSQLState());
		}finally{
			try {
				if(cstmt != null){
					cstmt.close();
				}
			} catch (SQLException se2) {
				// TODO Auto-generated catch block
				se2.printStackTrace();
			}
			try {
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
