package com.library.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class NewUser {
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=userData";
	private static String DB_USER="user";
	private static String DB_PWD="user123";
	private String userName;
	private String account;
	private String password;
	
	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}

	public NewUser(){
		userName = Rigeste.textField.getText();
		account = Rigeste.textField_1.getText();
		password = Rigeste.textField_2.getText();
		
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
		    conn = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
		    ps = conn.prepareStatement("INSERT INTO UserData(userName,account,password,muchBooks) "
		    		+ "VALUES(?,?,?,0) ");
		    ps.setString(1, userName);
		    ps.setString(2, account);
		    ps.setString(3, password);
		    ps.execute();
		    ps = conn.prepareStatement("CREATE TABLE bookOf"+userName
		    		+"(bookNum int, bookName nvarchar(50), bookId int,lendTime date)");
		    ps.execute();
		}catch (Exception e) {
			e.printStackTrace();
//			数据库的关闭
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(rs!=null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
