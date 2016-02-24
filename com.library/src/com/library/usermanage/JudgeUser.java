package com.library.usermanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.library.main.MainWindow;

public class JudgeUser {
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=userData";
	private static String DB_USER="user";
	private static String DB_PWD="user123";
	public static String ID;
	public static String NAME;
	public static  String NUM;
	public static String HBOOK;
	public static String account [] = new String[1000];
	public static String id[] = new String[1000];
	public static String name[] = new String[1000];
	public static String hbooks[] = new String[1000];
	
	
	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}
	
	public JudgeUser(){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
		    conn = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
		    stmt=conn.prepareStatement("select * from userData where account =?");
		    stmt.setString(1, MainWindow.txtabcdef.getText().toString());
		    rs=stmt.executeQuery();
//		    遍历将数据库内容打印到表格
		    while(rs.next()){
		    	JudgeUser.ID = rs.getString(1);
		    	JudgeUser.NAME = rs.getString(2);
		    	JudgeUser.NUM = rs.getString(3);
		    	JudgeUser.HBOOK = rs.getString(5);
		    }
		} catch (Exception e) {
			e.printStackTrace();
//			数据库的关闭
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
				if(rs!=null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
