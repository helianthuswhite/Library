package com.library.usermanage;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class UserFrame extends JFrame{
	
	 public static DefaultTableModel tableModel;   //表格模型对象
	 public static  JTable table;
	String[] columnNames = {"序号","用户名","账号"};
	public static int a = 1;
	
	public UserFrame(UserButtons userButtons, BottomButtons bottomButtons, 
			JudgeText text_2) {
	
		setTitle("\u7528\u6237\u7BA1\u7406");
		setBounds(350, 250, 579, 442);
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
		/*
		 * 边界布局依次创建Panel
		 */
		getContentPane().add(scrollPane,BorderLayout.CENTER);
		this.add(userButtons,BorderLayout.NORTH);
		this.add(bottomButtons,BorderLayout.SOUTH);
		this.setVisible(true);
		this.setResizable(false);
		
		
	}
}
