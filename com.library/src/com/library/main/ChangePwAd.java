package com.library.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ChangePwAd {
	
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=managedata";
	private static String DB_USER="sa";
	private static String DB_PWD="sa123";
	
	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}
	
	public ChangePwAd(){
		Connection conn = null;
		Statement stmt = null;
		try {
		    conn = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
		    stmt=conn.createStatement();
		    stmt.executeUpdate("UPDATE ManageData SET password ='"
		    		+ ""+ChangePW.textField.getText().toString()+"' where account = 'Admin123'");
		} catch (Exception e) {
			e.printStackTrace();
//			���ݿ�Ĺر�
		}finally{
			try{
				if(conn!=null)
					conn.close();
				if(stmt!=null)
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
