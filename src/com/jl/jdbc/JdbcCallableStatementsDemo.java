package com.jl.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCallableStatementsDemo {
	//��̬�������ݿ�����URL
	static final String DB_URL = "jdbc:mysql://localhost:3306";
	//���ݿ��˺�
	static final String USER = "root";
	//���ݿ��˺�����
	static final String PASS = "admin";
	
	public static void main(String args[]){
		Connection conn = null;
		//�洢���̵�������
		CallableStatement cstmt = null;
		try {
			//��ʼ���������򣬴������ݿ��ͨ��
			Class.forName("com.mysql.jdbc.Driver");
			//���ݿ���������
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			//�������ݿ����
			System.out.println("Creating CallableStatement...");
		    
		    //���ݿ��ѯ���
		    String sql = "{call sys.sqlAll()}";
		    cstmt = conn.prepareCall(sql);
		    ResultSet rs = cstmt.executeQuery();
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
