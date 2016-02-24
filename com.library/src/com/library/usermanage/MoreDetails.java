package com.library.usermanage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.library.book.ShowBook;

@SuppressWarnings("serial")
public class MoreDetails extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static ShowBook sb;
	
	/**
	 * Create the frame.
	 */
	public MoreDetails() {
		setBounds(420, 320, 450, 300);
		setTitle("详细信息");
		setVisible(true);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("\u5E8F\u53F7\uFF1A");
		lblNewLabel_1.setBounds(33, 10, 96, 32);
		lblNewLabel_1.setFont(new Font("楷体", Font.BOLD, 30));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_2.setBounds(33, 60, 128, 32);
		lblNewLabel_2.setFont(new Font("楷体", Font.BOLD, 30));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u8D26\u53F7\uFF1A");
		lblNewLabel_3.setBounds(33, 114, 96, 32);
		lblNewLabel_3.setFont(new Font("楷体", Font.BOLD, 30));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("\u4E66\u7C4D\uFF1A");
		lblNewLabel.setBounds(33, 217, 96, 32);
		lblNewLabel.setFont(new Font("楷体", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		JTextField Field_1 = new JTextField(JudgeText.ID);
		Field_1.setBackground(Color.DARK_GRAY);
		Field_1.setForeground(Color.ORANGE);
		Field_1.setFont(new Font("楷体", Font.BOLD, 25));
		Field_1.setBounds(181,10,216,32);
		Field_1.setEditable(false);
		contentPane.add(Field_1);
		
		textField = new JTextField(JudgeText.NAME);
		textField.setForeground(Color.ORANGE);
		textField.setFont(new Font("楷体", Font.BOLD, 25));
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(181, 60, 216, 32);
		textField.setEditable(false);
		contentPane.add(textField);
		
		textField_1 = new JTextField(JudgeText.NUM);
		textField_1.setForeground(Color.ORANGE);
		textField_1.setFont(new Font("楷体", Font.BOLD, 25));
		textField_1.setBackground(Color.DARK_GRAY);
		textField_1.setBounds(181, 114, 216, 32);
		textField_1.setEditable(false);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField(JudgeText.HBOOK);
		textField_2.setForeground(Color.ORANGE);
		textField_2.setFont(new Font("楷体", Font.BOLD, 25));
		textField_2.setBackground(Color.DARK_GRAY);
		textField_2.setBounds(181, 167, 216, 32);
		textField_2.setEditable(false);
		contentPane.add(textField_2);
		
		JLabel label = new JLabel("\u6570\u91CF\uFF1A");
		label.setFont(new Font("楷体", Font.BOLD, 30));
		label.setBounds(33, 165, 96, 32);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u4E66\u7C4D");
		btnNewButton.setBounds(181, 215, 216, 34);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
                        sb = new ShowBook(JudgeText.NAME);				
			}
		});
	}
}
