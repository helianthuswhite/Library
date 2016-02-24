package com.library.usermanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DeleteUser {

	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=userData";
	private static String DB_USER="user";
	private static String DB_PWD="user123";
	
	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}
	
	public DeleteUser(){
		while(UserFrame.table.getRowCount()==1){
			UserFrame.tableModel.removeRow(0);
		}
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		try {
		    conn = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
		    stmt=conn.createStatement();
		    String sqlString="delete from userdata where id=" + JudgeText.ID  +" ";
		    stmt.executeUpdate(sqlString);
		    
		    ps = conn.prepareStatement("DROP TABLE bookOf"+JudgeText.NAME);
		    ps.execute();
		    
		    
			} catch (Exception e) {
				e.printStackTrace();
//				数据库的关闭
			}finally{
				try{
					if(conn!=null)
						conn.close();
					if(stmt!=null)
						stmt.close();
					if(ps != null){
						ps.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}
}
