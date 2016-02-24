package com.library.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JudgeLoad {
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI_1="jdbc:sqlserver://127.0.0.1:1433;database=userdata";
	private static String DB_URI_2="jdbc:sqlserver://127.0.0.1:1433;database=managedata";
	private static String DB_USER="sa";
	private static String DB_PWD="sa123";
	private String inputPassword = String.valueOf(MainWindow.textField.getPassword()); 
	private  String NUM;
	private String PASSWORD;
	public static String account_1 [] = new String[1000];
	public static String password_1[] = new String[1000];
	public static String account_2 [] = new String[1000];
	public static String password_2[] = new String[1000];
	protected int a = 0;
	
	private int i = 0;
	
	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}
	
	public JudgeLoad(){
		/*
		 * 连接数据库managedata,判断管理员登录是否正确
		 */
		Connection conn_2 = null;
		Statement stmt_2 = null;
		ResultSet rs_2 = null;
		try {
			conn_2 = DriverManager.getConnection(DB_URI_2,DB_USER,DB_PWD);
			stmt_2 = conn_2.createStatement();
			rs_2 = stmt_2.executeQuery("select * from managedata");
		    while(rs_2.next()){
		    	this.NUM = rs_2.getString(2);
		    	this.PASSWORD = rs_2.getString(3);
		    	account_2[i] = this.NUM;
		    	password_2[i] = this.PASSWORD;
		    	i++;
		    }
		    for(int i = 0;i<account_2.length;i++){
		    	if(MainWindow.txtabcdef.getText().equals(account_2[i])
		    			&&inputPassword.equals(password_2[i])){
		    		a =2;
		    		break;
		    	}
		    }
}
		catch (Exception e) {
			e.printStackTrace();
//			数据库的关闭
		}finally{
			try{
				if(conn_2!=null)
					conn_2.close();
				if(stmt_2!=null)
					stmt_2.close();
				if(rs_2!=null)
					rs_2.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		/*****************************************************************************************
		 * 
		 * 连接数据库userdata，判断用户登录是否正确
		 */
		Connection conn_1 = null;
		Statement stmt_1 = null;
		ResultSet rs_1 = null;
		try {
			conn_1 = DriverManager.getConnection(DB_URI_1,DB_USER,DB_PWD);
			stmt_1 = conn_1.createStatement();
			rs_1 = stmt_1.executeQuery("select * from userdata");
		    while(rs_1.next()){
		    	this.NUM = rs_1.getString(3);
		    	this.PASSWORD = rs_1.getString(4);
		    	account_1[i] = this.NUM;
		    	password_1[i] = this.PASSWORD;
		    	i++;
		    }
		    for(int i = 0;i<account_1.length;i++){
		    	if(MainWindow.txtabcdef.getText().equals(account_1[i])
		    			&&inputPassword.equals(password_1[i])){
		    		a =1;
		    		break;
		    	}	else if(a!=2&&i == account_1.length-1) {
		    		JOptionPane.showMessageDialog(null,
		    				"用户名或者密码错误!", "系统信息", JOptionPane.ERROR_MESSAGE);
		    		break;
		    	}
		    }
}
		catch (Exception e) {
			e.printStackTrace();
//			数据库的关闭
		}finally{
			try{
				if(conn_1!=null)
					conn_1.close();
				if(stmt_1!=null)
					stmt_1.close();
				if(rs_1!=null)
					rs_1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
