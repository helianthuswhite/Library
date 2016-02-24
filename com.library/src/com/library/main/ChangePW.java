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
public class ChangePW extends JFrame {

	private JPanel contentPane;
	public static JTextField textField;

	public ChangePW(int i) {
		setTitle("修改密码");
		setBounds(420, 320, 450, 95);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u65B0\u5BC6\u7801:");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(35, 10, 70, 30);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(115, 10, 177, 30);
		contentPane.add(textField);
		
		JButton button = new JButton("\u63D0\u4EA4");
		button.setBounds(315, 10, 91, 30);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(i==2){
					new ChangePwAd();
				}else if( i ==1){
					new ChangePwUser();
				}
				JOptionPane.showMessageDialog(null,
						"修改成功!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}
}
