package com.library.user.operate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class userBookQuerier extends JFrame {
	
	@SuppressWarnings("unused")
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;database=book";

	private static String DB_USER = "sa";

	private static String DB_PWD = "sa123";

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	@SuppressWarnings("unused")
	private static String DEL_SQL = "DELETE FROM Table_book WHERE num=";
	@SuppressWarnings("unused")
	private static String DEL_TABLE_SQL = "DROP TABLE book_";

	private static String SEL_SQL1 = "SELECT * FROM Table_book WHERE name LIKE";
	private static String SEL_SQL2 = "SELECT * FROM Table_book WHERE num =";

	@SuppressWarnings("unused")
	private JPanel contentPane;
	private JPanel panel1;
	@SuppressWarnings("unused")
	private JPanel panel2;
	private JTextField txtBookName;
	private JTextField txtBookNum;

	@SuppressWarnings("unused")
	private JScrollPane scrollPane;

	private JTable table;
	private DefaultTableModel tableModel;

	private String[][] tableValues = new String[100][4];
	private String[] columnNames = { "书号", "书名", "价格", "数量" };
	private JLabel label;
	private JLabel label_1;
	@SuppressWarnings("unused")
	private JButton btnDeleteBook;
	private JButton btnQueryOneBook;

	@SuppressWarnings("unused")
	private int BookNum = 0;




	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public userBookQuerier() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
		setResizable(false);
		setVisible(true);
		setTitle("图书查询");
		setBounds(420, 320, 450, 450);
		getContentPane().setLayout(null);

		panel1 = new JPanel();
		panel1.setBounds(0, 0, 450, 82);
		getContentPane().add(panel1);
		panel1.setLayout(null);

		txtBookName = new JTextField();
		txtBookName.setBounds(99, 10, 200, 21);
		panel1.add(txtBookName);
		txtBookName.setColumns(10);

		txtBookNum = new JTextField();
		txtBookNum.setColumns(10);
		txtBookNum.setBounds(99, 45, 200, 21);
		panel1.add(txtBookNum);

		label = new JLabel("\u4E66\u540D\uFF1A");
		label.setBounds(47, 13, 42, 15);
		panel1.add(label);

		label_1 = new JLabel("\u4E66\u53F7\uFF1A");
		label_1.setBounds(47, 48, 42, 15);
		panel1.add(label_1);

		JButton btnQueryBookName = new JButton("\u67E5\u8BE2");
		btnQueryBookName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = txtBookName.getText().toString();
				String txt1 = "'%" + txt + "%'";
				queryBookName(SEL_SQL1 + txt1);
			}
		});
		btnQueryBookName.setBounds(309, 9, 93, 23);
		panel1.add(btnQueryBookName);

		JButton btnQueryBookNum = new JButton("\u67E5\u8BE2");
		btnQueryBookNum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = txtBookNum.getText().toString();
				queryBookName(SEL_SQL2 + txt);

			}
		});
		btnQueryBookNum.setBounds(309, 43, 93, 23);
		panel1.add(btnQueryBookNum);

		tableModel = new DefaultTableModel(tableValues, columnNames);
		table = new JTable();
		table.setModel(tableModel);

		// 使table中内容居中显示
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.CENTER);
		table.setDefaultRenderer(Object.class, r);

		JScrollPane scrollPane = new JScrollPane(table); // 支持滚动
		scrollPane.setBounds(0, 92, 440, 250);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		table.setRowSorter(new TableRowSorter(tableModel));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		table.addMouseListener(new MouseAdapter() { // 鼠标事件
			@SuppressWarnings("unused")
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow(); // 获得选中行索引
				if(tableModel.getValueAt(selectedRow, 0) != null){
					Object oa = tableModel.getValueAt(selectedRow, 0);
					Object ob = tableModel.getValueAt(selectedRow, 1);
					Object oc = tableModel.getValueAt(selectedRow, 2);
					Object od = tableModel.getValueAt(selectedRow, 3);
					BookNum = Integer.parseInt(oa.toString());
				}

			}
		});

//		// 删除图书按钮
//		btnDeleteBook = new JButton("\u56FE\u4E66\u4E0B\u67B6");
//		btnDeleteBook.setBounds(67, 363, 93, 48);
//		getContentPane().add(btnDeleteBook);
//		btnDeleteBook.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				int selectedRow = table.getSelectedRow();// 获得选中行的索引
//				if (selectedRow != -1) // 是否存在选中行
//				{
//
//					int index = Integer.parseInt(tableModel.getValueAt(
//							selectedRow, 0).toString());
//					tableModel.removeRow(selectedRow); // 删除行
//
//					try {
//						deleteBookRow(DEL_SQL + index);
//						deleteOneBookTable(DEL_TABLE_SQL + index);
//						JOptionPane.showMessageDialog(null, "删除成功！");
//
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//
//				}
//			}
//		});
		// 查询单本图书按钮
		btnQueryOneBook = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		btnQueryOneBook.setBounds(107, 363, 217, 48);
		btnQueryOneBook.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				queryOneBook();

			}
		});
		getContentPane().add(btnQueryOneBook);
	}
	
	
	
	public void queryOneBook() {
		int selectedRow = table.getSelectedRow();// 获得选中行的索引
		if (selectedRow != -1) // 是否存在选中行
		{

			int bookNum = Integer.parseInt(tableModel
					.getValueAt(selectedRow, 0).toString());
			userOneBookQuerier uqob = new userOneBookQuerier(bookNum);
			uqob.setVisible(true);
			System.out.println("点击单本图书查询");
		}
	}

	public void deleteOneBookTable(String SQL) throws SQLException {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		// stmt = conn.prepareStatement(DEL_TABLE_SQL + index);
		stmt = conn.prepareStatement(SQL);
		stmt.execute();
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
	}

	public void deleteBookRow(String SQL) throws SQLException {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		// stmt = conn.prepareStatement(DEL_SQL + index);
		stmt = conn.prepareStatement(SQL);
		stmt.execute();
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
	}

	// 清除表格信息
	public void clearTable() {
		for (int i = 0; tableValues[i][0] != null; i++) {
			tableModel.setValueAt("", i, 0);
			tableModel.setValueAt("", i, 1);
			tableModel.setValueAt("", i, 2);
			tableModel.setValueAt("", i, 3);
			tableValues[i][0] = null;
			tableValues[i][1] = null;
			tableValues[i][2] = null;
			tableValues[i][3] = null;
		}
	}

	public void queryBookName(String SEL_SQL) {
		clearTable();

		// String txt = txtBookName.getText().toString();
		// String txt1 = "'%" + txt + "%'" ;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			// stmt = conn.prepareStatement(SEL_SQL1+txt1);
			stmt = conn.prepareStatement(SEL_SQL);
			rs = stmt.executeQuery();
			for (int i = 0; rs.next(); i++) {
				tableValues[i][1] = rs.getString(2);
				tableValues[i][0] = rs.getInt(3) + "";
				tableValues[i][2] = rs.getFloat(4) + "";
				tableValues[i][3] = rs.getInt(5) + "";
				tableModel.setValueAt(tableValues[i][0], i, 0);
				tableModel.setValueAt(tableValues[i][1], i, 1);
				tableModel.setValueAt(tableValues[i][2], i, 2);
				tableModel.setValueAt(tableValues[i][3], i, 3);
			}
			if (conn != null) {
				conn.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
