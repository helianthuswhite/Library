package com.library.book;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ShowBook extends JFrame {
	
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String DB_URI="jdbc:sqlserver://127.0.0.1:1433;database=userData";
	private static String DB_USER="user";
	private static String DB_PWD="user123";
	
	private String ID;
	private String NAME;
	private  String NUM;
	private String TIME;
	public static String account [] = new String[1000];
	public static String id[] = new String[1000];
	public static String name[] = new String[1000];
	public static String time[] = new String[1000];
	
	private int i = 0;
	
	 public static DefaultTableModel tableModel;   //表格模型对象
	 public static  JTable table;
	 String[] columnNames = {"数量","名称","编号","借阅时间"};

	private JPanel contentPane;

	static {try {
		Class.forName(DRIVER);
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}}

	/**
	 * Create the frame.
	 */
	public ShowBook(String userName) {
		setBounds(420, 320, 450, 300);
		setTitle("已借书籍");
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		/*
		 *创建表格
		 */
		tableModel = new DefaultTableModel(null,columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
		scrollPane.setViewportView(table);
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false); //不可整列移动 
        table.getTableHeader().setResizingAllowed(false);//不可编辑
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);
		
		contentPane.add(scrollPane,BorderLayout.CENTER);
		
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
		    conn = DriverManager.getConnection(DB_URI,DB_USER,DB_PWD);
//		    ps = conn.prepareStatement("SELECT userName FROM userData WHERE account =?");
//		    ps.setString(1,MainWindow.txtabcdef.getText().toString());
//		    rs = ps.executeQuery();
//		    if(rs.next()){
//		    	userName = rs.getString(1);
//		    }
//		    ps.close();
//		    rs = null;
		    
		    stmt=conn.createStatement();
		    rs=stmt.executeQuery("select * from bookof"+userName);
//		    遍历将数据库内容打印到表格
		    while(rs.next()){
		    	this.NUM = rs.getString(1);
		    	this.NAME = rs.getString(2);
		    	this.ID = rs.getString(3);
		    	this.TIME = rs.getString(4);
		    	
		    	account[i] = this.NUM;
		    	id[i] = this.ID;
		    	name[i] = this.NAME;
		    	time[i] = this.TIME;
		    	i++;
//		    	添加一行在表格
		    	tableModel.addRow(new Object[]  {this.NUM,this.NAME,this.ID,this.TIME});
		    }	} catch (Exception e) {
				e.printStackTrace();
//				数据库的关闭
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
