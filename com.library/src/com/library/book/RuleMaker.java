package com.library.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class RuleMaker extends JFrame {

	private JPanel contentPane;
	private JTextField txtLegallyBorrowTime;
	private JTextField txtLegallyBorrowNumber;
	private JTextField txtPunishPerday;
	private JTextField txtDamageCompensation;

	@SuppressWarnings("unused")
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;database=rule_test";

	private static String DB_USER = "sa";

	private static String DB_PWD = "sa123";

	private static String SEL_SQL = "SELECT * FROM Table_rule";

	@SuppressWarnings("unused")
	private static String INS_SQL = "INSERT INTO Table_rule(id,legallyBorrowTime,legallyBorrowNumber,punishPerday,damageCompensation) VALUES (1,?,?,?,?)";

	private static String UPD_SQL = "UPDATE Table_rule SET legallyBorrowTime=?,legallyBorrowNumber=?,punishPerday=1,damageCompensation=? WHERE ID=?";

	private int lbt = 0;
	private int lbn = 0;
	private float pp = 0;
	private float dc = 0;

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;


	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public RuleMaker() throws Exception {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		stmt = conn.prepareStatement(SEL_SQL);
		rs = stmt.executeQuery();
		while (rs.next()) {
			lbt = rs.getInt(2);
			lbn = rs.getInt(3);
			pp = rs.getFloat(4);
			dc = rs.getFloat(5);
		}
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}

		setTitle("\u501F\u9605\u89C4\u5219\u5B9A\u5236");
		setResizable(false);
		setBounds(420, 320, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtLegallyBorrowTime = new JTextField();
		txtLegallyBorrowTime.setBounds(123, 34, 200, 26);
		txtLegallyBorrowTime.setText(lbt + "");
		contentPane.add(txtLegallyBorrowTime);
		txtLegallyBorrowTime.setColumns(10);

		txtLegallyBorrowNumber = new JTextField();
		txtLegallyBorrowNumber.setColumns(10);
		txtLegallyBorrowNumber.setBounds(123, 83, 200, 26);
		txtLegallyBorrowNumber.setText(lbn + "");
		contentPane.add(txtLegallyBorrowNumber);

		txtPunishPerday = new JTextField();
		txtPunishPerday.setColumns(10);
		txtPunishPerday.setBounds(123, 97, 200, 26);
		txtPunishPerday.setText(pp + "");
//		contentPane.add(txtPunishPerday);

		txtDamageCompensation = new JTextField();
		txtDamageCompensation.setColumns(10);
		txtDamageCompensation.setBounds(123, 130, 200, 26);
		txtDamageCompensation.setText(dc + "");
		contentPane.add(txtDamageCompensation);

		JButton btnMakeRule = new JButton("\u63D0\u4EA4");
		btnMakeRule.setBounds(123, 186, 131, 53);
		contentPane.add(btnMakeRule);

		label = new JLabel("\u5408\u6CD5\u501F\u9605\u65F6\u95F4\uFF1A");
		label.setBounds(10, 39, 103, 15);
		contentPane.add(label);

		label_1 = new JLabel("\u5408\u6CD5\u501F\u9605\u6570\u91CF\uFF1A");
		label_1.setBounds(10, 88, 103, 15);
		contentPane.add(label_1);

		label_2 = new JLabel("\u635F\u574F\u8D54\u507F\u6BD4\u4F8B\uFF1A");
		label_2.setBounds(10, 135, 103, 15);
		contentPane.add(label_2);
		
		label_3 = new JLabel("天");
		label_3.setBounds(328,40,103,15);
		contentPane.add(label_3);
		
	
		btnMakeRule.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				makeRule();
			}
		});

	}
	
	public void makeRule(){
		lbt = Integer.parseInt(txtLegallyBorrowTime.getText()
				.toString());
		lbn = Integer.parseInt(txtLegallyBorrowNumber.getText()
				.toString());
		pp = Float.parseFloat(txtPunishPerday.getText().toString());
		dc = Float.parseFloat(txtPunishPerday.getText().toString());
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			stmt = conn.prepareStatement(UPD_SQL);
			stmt.setInt(1, lbt);
			stmt.setInt(2, lbn);
			// stmt.setFloat(3, pp);
			stmt.setFloat(3, dc);
			stmt.setInt(4, 1);
			stmt.addBatch();
			stmt.executeBatch();
			stmt.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "修改成功");
	}

}
