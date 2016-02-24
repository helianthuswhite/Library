package com.library.main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Rigeste extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;
	public static JTextField textField_1;
	public static JTextField textField_2;

	

	/**
	 * Create the frame.
	 */
	public Rigeste() {
		setBounds(420, 320, 450, 300);
		setTitle("用户注册");
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		 * 用户名
		 */
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D:");
		lblNewLabel.setBounds(68, 22, 70, 30);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(148, 22, 177, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		/*
		 * 账号
		 */
		JLabel label = new JLabel("\u8D26\u53F7:");
		label.setBounds(68, 79, 70, 30);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 82, 177, 30);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		/*
		 * 密码
		 */
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setBounds(68, 137, 70, 30);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 140, 177, 30);
		textField_2.setColumns(10);
		contentPane.add(textField_2);
		
		/*
		 * 注册按钮
		 */
		JButton button = new JButton("提交");
		button.setBounds(182, 212, 91, 30);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
                 new NewUser();			
                 JOptionPane.showMessageDialog(null,
 						"注册成功!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		contentPane.add(button);
	}
}
