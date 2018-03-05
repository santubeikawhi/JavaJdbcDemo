package com.jl.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcStatementsDemo {
	//��̬�������ݿ�����URL
	static final String DB_URL = "jdbc:mysql://localhost:3306";
	//���ݿ��˺�
	static final String USER = "root";
	//���ݿ��˺�����
	static final String PASS = "admin";
	
	public static void main(String args[]){
		Connection conn = null;
		Statement stmt = null;
		try {
			//��ʼ���������򣬴������ݿ��ͨ��
			Class.forName("com.mysql.jdbc.Driver");
			//���ݿ���������
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//�������ݿ����
			System.out.println("Creating statement...");
		    stmt = conn.createStatement();
		    
		    //���ݿ��ѯ���
		    String sql = "SELECT cust_id, name_, age_ FROM sys.customer";
		    ResultSet rs = stmt.executeQuery(sql);
		    while(rs.next()){
		    	String custId  = rs.getString("cust_id");
		        String name = rs.getString("name_");
		        int first = rs.getInt("age_");
		        System.out.println("---�û���Ϣ---");
		        System.out.println("�û�Id��"+custId);
		        System.out.println("�û�����"+name);
		        System.out.println("�û����䣺"+first);
		        System.out.println("-----------");
		    }
		    //��������Դ
		    rs.close();
		    stmt.close();
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
				if(stmt != null){
					stmt.close();
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
