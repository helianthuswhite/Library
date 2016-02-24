package com.library.userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.library.usermanage.BottomButtons;
import com.library.usermanage.JudgeText;
import com.library.usermanage.UserButtons;
import com.library.usermanage.UserFrame;

public class UserData {
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=userData";
	private static String DB_USER="user";
	private static String DB_PWD="user123";
	private String ID;
	private String NAME;
	private  String NUM;
	private int HBOOK;
	public static String account [] = new String[1000];
	public static String id[] = new String[1000];
	public static String name[] = new String[1000];
	public static int hbooks[] = new int[1000];
	
	private int i = 0;
	
	public static UserFrame uf = null;
	
	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}

	/*
	 * 数据库的连接以及数据的打印
	 */
	public UserData(UserButtons userButtons, BottomButtons bottomButtons, 
			JudgeText text_2) {
//		新建UserFrame对象
		uf  = new UserFrame(userButtons,bottomButtons,text_2);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    conn = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
		    stmt=conn.createStatement();
		    rs=stmt.executeQuery("select * from userData");
//		    遍历将数据库内容打印到表格
		    while(rs.next()){
		    	this.ID = rs.getString(1);
		    	this.NAME = rs.getString(2);
		    	this.NUM = rs.getString(3);
		    	this.HBOOK = rs.getInt(5);
		    	
		    	account[i] = this.NUM;
		    	id[i] = this.ID;
		    	name[i] = this.NAME;
		    	hbooks[i] = this.HBOOK;
		    	i++;
//		    	添加一行在表格
		    	UserFrame.tableModel.addRow(new Object[]  {this.ID,this.NAME,this.NUM});
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
