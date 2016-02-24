package com.library.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BookAdder extends JFrame {

	private JPanel contentPane;
	private JTextField txtBookNum;
	private JTextField txtBookPrice;
	private JTextField txtBookName;
	private JTextField txtBookQuantity;

	@SuppressWarnings("unused")
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;database=book";

	private static String DB_USER = "sa";

	private static String DB_PWD = "sa123";

	private static String INS_SQL_1 = "INSERT INTO Table_book(name,num,price,quantity) VALUES (?,?,?,?)";

	// 插入到book_(num)
	@SuppressWarnings("unused")
	private static String INS_SQL_2 = "INSERT INTO ?(id, name, price, lendSituation, brokenSituation) VALUES(?,?,?,0,0)";

	// 表名为 book_(num)
	@SuppressWarnings("unused")
	private static String CRT_SQL = "CREATE TABLE ?(id int, name nvarchar(20), price float , lendSituation int, brokenSituation int)";

	private String bname = null;
	private int bnum = 0;
	private float bp = 0;
	private int bq = 0;

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;


	/**
	 * Create the frame.
	 */
	public BookAdder() {
		setTitle("\u65B0\u4E66\u4E0A\u67B6");
		this.setResizable(false);
		setBounds(420, 320, 304, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtBookName = new JTextField();
		txtBookName.setColumns(10);
		txtBookName.setBounds(101, 30, 132, 26);
		contentPane.add(txtBookName);

		txtBookNum = new JTextField();
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(101, 66, 132, 26);
		contentPane.add(txtBookNum);

		txtBookPrice = new JTextField();
		txtBookPrice.setColumns(10);
		txtBookPrice.setBounds(101, 102, 132, 26);
		contentPane.add(txtBookPrice);

		txtBookQuantity = new JTextField();
		txtBookQuantity.setColumns(10);
		txtBookQuantity.setBounds(101, 138, 132, 26);
		contentPane.add(txtBookQuantity);

		JButton btnAddBook = new JButton("\u63D0\u4EA4");
		btnAddBook.setBounds(101, 194, 93, 41);
		btnAddBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				addBook();//添加新书动作
			}
		});
		contentPane.add(btnAddBook);

		label = new JLabel("\u4E66\u540D\uFF1A");
		label.setBounds(40, 36, 54, 15);
		contentPane.add(label);

		label_1 = new JLabel("\u4E66\u53F7\uFF1A");
		label_1.setBounds(40, 69, 54, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("\u4EF7\u683C\uFF1A");
		label_2.setBounds(40, 105, 54, 15);
		contentPane.add(label_2);

		label_3 = new JLabel("\u6570\u91CF\uFF1A");
		label_3.setBounds(40, 145, 54, 15);
		contentPane.add(label_3);
	}
	
	
	
	public void addBook(){
		bname = txtBookName.getText().toString();
		bnum = Integer.parseInt(txtBookNum.getText().toString());
		bp = Float.parseFloat(txtBookPrice.getText().toString());
		bq = Integer.parseInt(txtBookQuantity.getText().toString());

		try {
			addNewBookToTable();
			createNewOneBookTable();
			initializeNewOneBookTable();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "添加成功！");
	}
	
	
	/*
	 * 添加新书到Table_book表格
	 */
	public void addNewBookToTable() throws Exception {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		stmt = conn.prepareStatement(INS_SQL_1);
		stmt.setString(1, bname);
		stmt.setInt(2, bnum);
		stmt.setFloat(3, bp);
		stmt.setInt(4, bq);
		stmt.execute();
		closeDataBase(conn, stmt);
	}

	public void createNewOneBookTable() throws Exception {
		String create_sql = "CREATE TABLE "
				+ "book_"
				+ bnum
				+ "(id int, name nvarchar(20), price float , lendSituation int, brokenSituation int, userName nvarchar(20), lendTime date)";
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		stmt = conn.prepareStatement(create_sql);
		stmt.execute();
		closeDataBase(conn, stmt);
	}

	/*
	 * 初始化新建的表格
	 */
	public void initializeNewOneBookTable() throws Exception {
		String insert_sql = "INSERT INTO "
				+ "book_"
				+ bnum
				+ "(id, name, price, lendSituation, brokenSituation, userName, lendTime) VALUES(?,?,?,0,0,'',NULL)";
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		stmt = conn.prepareStatement(insert_sql);
		for (int i = 1; i <= bq; i++) {
			stmt.setInt(1, i);
			stmt.setString(2, bname);
			stmt.setFloat(3, bp);
			stmt.execute();
		}
		closeDataBase(conn, stmt);
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
