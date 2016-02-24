package com.library.usermanage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class UserButtons extends JPanel{
	
	public static JTextField textField_1;
	public static JTextPane txtInf;

	public UserButtons(){
		Init();
	}

	private void Init() {
		
		this.setLayout(new FlowLayout());
//		创建“用户查询”按扭
		JButton btnNewButton = new JButton("\u7528\u6237\u67E5\u8BE2");
		this.add(btnNewButton);
		
//		按钮监听事件
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new JudgeText().SetText();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		/*
		 * 输入文本框的创建
		 */
			textField_1 = new JTextField(20);
			textField_1.setText("                  请输入用户信息                 ");
			this.add(textField_1);
			/*
	    	鼠标点击文本提示内容消失
			鼠标点击事件*/
			textField_1.addMouseListener(new MouseListener(){
				@Override
				public void mouseClicked(MouseEvent e) {
					textField_1.setText("");
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
				}
				
				@Override
			public void mouseEntered(MouseEvent e) {
			}
			
				@Override
				public void mouseExited(MouseEvent e) {
			}
		});
			
			/*
			 * 提示信息的创建
			 */
				txtInf = new JTextPane();
				txtInf.setText("点击查询所需用户");
				txtInf.setForeground(Color.RED);
				txtInf.setBackground(getBackground());
				txtInf.setEditable(false);
				this.add(txtInf );
	}
	}
