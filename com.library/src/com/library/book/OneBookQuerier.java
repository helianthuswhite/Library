package com.library.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class OneBookQuerier extends JFrame {

	@SuppressWarnings("unused")
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;database=book";
	
	private static String DB_URL_USER = "jdbc:sqlserver://127.0.0.1:1433;database=userData";
	
	private static String DB_URL_RULE = "jdbc:sqlserver://127.0.0.1:1433;database=rule_test";

	private static String DB_USER = "sa";

	private static String DB_PWD = "sa123";

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	private static String DEL_SQL1 = "DELETE FROM book_";
	private static String DEL_SQL2 = " WHERE id=";
	@SuppressWarnings("unused")
	private static String UPD_SQL1 = "UPDATE　book_";
	@SuppressWarnings("unused")
	private static String UPD_SQL = "SET lendSituation=?, brokenSituation=? WHERE id=?";
	private static String SEL_SQL = "SELECT * FROM book_";

	private JPanel panel;

	private String[][] tableValues = new String[100][7];
	private String[] columnNames = { "编号", "书名", "价格", "借出情况", "破损情况", "借书人" ,"借书时间"};

	private JTable table;
	private DefaultTableModel tableModel;
	private JLabel lblNewLabel;
	private JLabel label;
	private JTextField txtId;
	private JTextField txtName;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField txtLendSituation;
	private JTextField txtBrokenSituation;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnReturn;
	private JTextField txtUserName;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public OneBookQuerier(int bookNum) {
		setTitle("\u67E5\u8BE2\u5355\u672C\u56FE\u4E66");
		setResizable(false);
		setBounds(420, 320, 450, 450);
		getContentPane().setLayout(null);

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			stmt = conn.prepareStatement(SEL_SQL + bookNum);
			rs = stmt.executeQuery();
			for (int i = 0; rs.next() ; i++) {
				tableValues[i][0] = rs.getInt(1) + "";
				tableValues[i][1] = rs.getString(2);
				tableValues[i][2] = rs.getFloat(3) + "";
				if (rs.getBoolean(4)) {
					tableValues[i][3] = "是";
				} else {
					tableValues[i][3] = "否";
				}
				if (rs.getBoolean(5)) {
					tableValues[i][4] = "是";
				} else {
					tableValues[i][4] = "否";
				}
				tableValues[i][5] = rs.getString(6);
				if(rs.getDate(7) != null){
					tableValues[i][6] = rs.getDate(7).toString();
				}

				

			}
			closeDataBase(conn, stmt);
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		tableModel = new DefaultTableModel(tableValues, columnNames);
		table = new JTable();
		table.setModel(tableModel);

		// 使table中内容居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		JScrollPane scrollPane = new JScrollPane(table); // 支持滚动
		scrollPane.setBounds(0, 10, 440, 292);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		table.setRowSorter(new TableRowSorter(tableModel));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		panel = new JPanel();
		panel.setBounds(0, 302, 444, 119);
		getContentPane().add(panel);
		panel.setLayout(null);

		lblNewLabel = new JLabel("\u7F16\u53F7\uFF1A");
		lblNewLabel.setBounds(23, 20, 54, 15);
//		panel.add(lblNewLabel);

		label = new JLabel("\u4E66\u540D\uFF1A");
		label.setBounds(23, 45, 54, 15);
//		panel.add(label);

		label_1 = new JLabel("\u501F\u51FA\u60C5\u51B5\uFF1A");
		label_1.setBounds(198, 20, 82, 15);
//		panel.add(label_1);

		label_2 = new JLabel("\u7834\u635F\u60C5\u51B5\uFF1A");
		label_2.setBounds(34, 25, 82, 15);
		panel.add(label_2);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(80, 20, 108, 15);
//		panel.add(txtId);
		txtId.setColumns(10);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setColumns(10);
		txtName.setBounds(80, 45, 108, 15);
//		panel.add(txtName);

		txtLendSituation = new JTextField();
		txtLendSituation.setEditable(false);
		txtLendSituation.setColumns(10);
		txtLendSituation.setBounds(290, 20, 108, 15);
		
		JLabel label_3 = new JLabel("\u501F\u4E66\u4EBA\uFF1A");
		label_3.setBounds(34, 70, 82, 15);
		panel.add(label_3);
//		panel.add(txtLendSituation);

		txtBrokenSituation = new JTextField();
		txtBrokenSituation.setColumns(10);
		txtBrokenSituation.setBounds(111, 25, 82, 15);
		panel.add(txtBrokenSituation);

		btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.setBounds(224, 17, 93, 30);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateOneBook(bookNum);
			}
		});
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(111, 70, 82, 15);
		panel.add(txtUserName);
		panel.add(btnUpdate);

		btnDelete = new JButton("\u5220\u9664");
		btnDelete.setBounds(327, 17, 93, 30);
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				deleteOneBook(bookNum);
			}
		});
		panel.add(btnDelete);
		
		JButton btnLendOut = new JButton("\u501F\u51FA");
		btnLendOut.setBounds(224, 62, 93, 30);
		btnLendOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//修改书的信息，将书借出，即修改单本书的借出情况为是，修改借书人的名字
				
				//修改借书人的信息，借到书，借书的数量+1，借的书 书号 书名 单本书编号
				try {
					lendoutOneBook(bookNum);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnLendOut);
		
		btnReturn = new JButton("\u8FD8\u4E66");
		btnReturn.setBounds(327, 62, 93, 30);
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//将书收回 修改单本书的借出情况为是  修改借书人的名字
				//修改借书人的信息，
				try {
					returnOneBook(bookNum);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		panel.add(btnReturn);

		table.addMouseListener(new MouseAdapter() { // 鼠标事件
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow(); // 获得选中行索引
				if(tableModel.getValueAt(selectedRow, 0) != null){
					Object oa = tableModel.getValueAt(selectedRow, 0);
					Object ob = tableModel.getValueAt(selectedRow, 1);
					Object oc = tableModel.getValueAt(selectedRow, 3);
					Object od = tableModel.getValueAt(selectedRow, 4);
					Object oe = tableModel.getValueAt(selectedRow, 5);

					txtId.setText(oa.toString()); // 给文本框赋值
					txtName.setText(ob.toString());
					txtLendSituation.setText(oc.toString());
					txtBrokenSituation.setText(od.toString());
					txtUserName.setText(oe.toString());
					
					if(txtLendSituation.getText().toString().equals("是")){
						btnDelete.setEnabled(false);
					}
					else{
						btnDelete.setEnabled(true);
					}
					
					if(txtUserName.getText().toString().equals("")){
						btnLendOut.setEnabled(true);
						btnReturn.setEnabled(false);
						
					}
					else{
						btnLendOut.setEnabled(false);
						btnReturn.setEnabled(true);
					}
				}
				
				

			}
		});

	}

	
	
	
	
	
	
	//修改书的信息，将书借出，即修改单本书的借出情况为是，修改借书人的名字
	
	//修改借书人的信息，借到书，借书的数量+1，借的书 书号 书名 单本书编号
	@SuppressWarnings("unused")
	public void lendoutOneBook(int bookNum) throws SQLException{
		int selectedRow = table.getSelectedRow();
		if(selectedRow != -1){
			int indexId = Integer.parseInt(txtId.getText().toString());
			String indexBookName = txtName.getText().toString();
			String indexUserName = txtUserName.getText().toString();
			Date date = new Date();
			DateFormat df1 = DateFormat.getDateInstance();
			tableModel.setValueAt("是", selectedRow, 3);
			tableModel.setValueAt(txtUserName.getText().toString(), selectedRow, 5);
			tableModel.setValueAt(df1.format(date), selectedRow, 6);
			
			
			
			
			int index = Integer.parseInt(txtId.getText().toString());
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("UPDATE book_" + bookNum
					+ " SET lendSituation=1, userName=?, lendTime=? WHERE id=?");
			stmt.setString(1, indexUserName);
//			stmt.setDate(2, (java.sql.Date) date);
			stmt.setDate(2, new java.sql.Date(date.getTime()));
			stmt.setInt(3, indexId);
			stmt.addBatch();
			stmt.executeBatch();
			closeDataBase(conn, stmt);
			
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("SELECT muchBooks FROM UserData WHERE userName='"+indexUserName + "'");
			rs = stmt.executeQuery();
			int muchBooks = 0;
			while(rs.next()){
				muchBooks = rs.getInt(1) + 1;
			}
			closeDataBase(conn, stmt);
			
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("UPDATE UserData SET muchBooks =? WHERE userName =?");
			stmt.setInt(1, muchBooks);
			stmt.setString(2, indexUserName);
			stmt.addBatch();
			stmt.executeBatch();
			closeDataBase(conn, stmt);
			
			
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("INSERT INTO bookOf"+indexUserName+"(bookNum, bookName, bookId,lendTime) VALUES(?,?,?,?)");
			stmt.setInt(1, bookNum);
			stmt.setString(2, indexBookName);
			stmt.setInt(3, indexId);
			stmt.setDate(4, new java.sql.Date(date.getTime()));
			stmt.execute();
			closeDataBase(conn, stmt);
			
			JOptionPane.showMessageDialog(null, "借出成功！借书人："+indexUserName);
			
		}
	}
	
	
	//归还单本书  修改单本书的借出情况为否  借书人的名字为空
	//修改借书人的信息  借书的数量-1  借的书 去掉对应行
	@SuppressWarnings("unused")
	public void returnOneBook(int bookNum) throws SQLException{
		int selectedRow = table.getSelectedRow();
		if(selectedRow != -1){
			
			tableModel.setValueAt("否", selectedRow, 3);
			tableModel.setValueAt("", selectedRow, 5);
			tableModel.setValueAt("", selectedRow, 6);
			
			
			int indexId = Integer.parseInt(txtId.getText().toString());
			String indexBookName = txtName.getText().toString();
			String indexUserName = txtUserName.getText().toString();
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("UPDATE book_" + bookNum
					+ " SET lendSituation=0, userName=?,lendTime=NULL WHERE id=?");
			stmt.setString(1, "");
			stmt.setInt(2, indexId);
			stmt.addBatch();
			stmt.executeBatch();
//			stmt.execute();
			closeDataBase(conn, stmt);
			
			
			
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("SELECT muchBooks FROM UserData WHERE userName='"+indexUserName + "'");
			rs = stmt.executeQuery();
			int muchBooks = 0;
			while(rs.next()){
				muchBooks = rs.getInt(1) - 1;
				if(muchBooks < 0){
					muchBooks = 0;
				}
			}
			closeDataBase(conn, stmt);
			
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("UPDATE UserData SET muchBooks =? WHERE userName =?");
			stmt.setInt(1, muchBooks);
			stmt.setString(2, indexUserName);
			stmt.addBatch();
			stmt.executeBatch();
			closeDataBase(conn, stmt);
			
			Date date1 = new Date();
			Calendar cal1 = Calendar.getInstance();//借书的时间
			Calendar cal2 = Calendar.getInstance();//现在的时间
			cal2.setTime(date1);
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("SELECT lendTime FROM bookOf"+indexUserName+" WHERE bookNum=?");
			stmt.setInt(1, bookNum);
			rs = stmt.executeQuery();
			while(rs.next()){
				date1 = rs.getDate(1);
			}
			cal1.setTime(date1);
			long time1 = cal1.getTimeInMillis();
			long time2 = cal2.getTimeInMillis();
			
			long diff = time2 - time1;
			long diffDays = diff/(24*60*60*1000);//借书的时间
			closeDataBase(conn, stmt);
			
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			stmt = conn.prepareStatement("SELECT brokenSituation FROM book_"+bookNum+" WHERE id=?");
			stmt.setInt(1, indexId);
			rs = stmt.executeQuery();
			int ifDamaged = 0;
			if(rs.next()){
				ifDamaged = rs.getInt(1);
			}
			if(ifDamaged == 1){
				conn = DriverManager.getConnection(DB_URL_RULE, DB_USER, DB_PWD);
				//"SELECT * FROM Table_rule"
				stmt = conn.prepareStatement("SELECT * FROM Table_rule WHERE id=1");
				rs = stmt.executeQuery();
				float dc = 0;//损坏赔偿比例
				if(rs.next()){
					dc = rs.getFloat(5);
				}
				closeDataBase(conn, stmt);
				
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				stmt = conn.prepareStatement("SELECT price FROM Table_book WHERE num="+bookNum);
				rs = stmt.executeQuery();
				float price = 0;
				if(rs.next()){
					price = rs.getFloat(1);
				}
				closeDataBase(conn, stmt);
				int damage = (int) (price * dc);
				JOptionPane.showMessageDialog(null, "借书"+diffDays+"天，损坏赔偿"+damage+"元，还书成功！");
			}
			else if(ifDamaged == 0){
				JOptionPane.showMessageDialog(null, "借书"+diffDays+"天，还书成功！");
			}
			
	
			conn = DriverManager.getConnection(DB_URL_USER, DB_USER, DB_PWD);
			//INSERT INTO Table_book(name,num,price,quantity) VALUES (?,?,?,?)
			stmt = conn.prepareStatement("DELETE FROM bookOf"+indexUserName+" WHERE bookNum=? AND bookId=?");
			stmt.setInt(1, bookNum);
			stmt.setInt(2, indexId);
			stmt.execute();
			closeDataBase(conn, stmt);
					
		}
		
	}
	
	public void deleteOneBook(int bookNum) {
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		if (selectedRow != -1) // 存在选中行
		{
			tableModel.removeRow(selectedRow); // 删除行
			// int index =
			// Integer.parseInt(tableModel.getValueAt(selectedRow,
			// 0).toString());
			int index = Integer.parseInt(txtId.getText().toString());
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				stmt = conn.prepareStatement(DEL_SQL1 + bookNum + DEL_SQL2
						+ index);
				stmt.execute();

				// 获得该本书的数量
				stmt = conn
						.prepareStatement("SELECT quantity FROM Table_book WHERE num="
								+ bookNum);
				rs = stmt.executeQuery();
				int quantity = 0;
				if (rs.next()) {
					quantity = rs.getInt(1) - 1;
				}
				if (stmt != null) {
					stmt.close();
				}

				stmt = conn.prepareStatement("UPDATE Table_book SET quantity="
						+ quantity + " WHERE num=" + bookNum);
				stmt.execute();
				closeDataBase(conn, stmt);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		JOptionPane.showMessageDialog(null, "删除成功！");

	}

	public void updateOneBook(int bookNum) {
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		if (selectedRow != -1) // 是否存在选中行
		{
			// 修改指定的值：

			int index = Integer.parseInt(txtId.getText().toString());
			int index1 = 0;
			int index2 = 0;

			tableModel.setValueAt(txtLendSituation.getText(), selectedRow, 3);
			tableModel.setValueAt(txtBrokenSituation.getText(), selectedRow, 4);

			if (txtLendSituation.getText().toString().equals("是")) {
				index1 = 1;
			} else {
				index1 = 0;
			}
			if (txtBrokenSituation.getText().toString().equals("是")) {
				index2 = 1;
			} else {
				index2 = 0;
			}

			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
				stmt = conn.prepareStatement("UPDATE book_" + bookNum
						+ " SET lendSituation=?,brokenSituation=? WHERE id=?");
				stmt.setInt(1, index1);
				stmt.setInt(2, index2);
				stmt.setInt(3, index);
				stmt.addBatch();
				stmt.executeBatch();
				closeDataBase(conn, stmt);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		JOptionPane.showMessageDialog(null, "修改成功！");
	}
	

	public void closeDataBase(Connection conn, PreparedStatement stmt) throws SQLException{
		if(conn != null){
			conn.close();
			conn = null;
		}
		if(stmt != null){
			stmt.close();
			stmt = null;
		}
	}
}
