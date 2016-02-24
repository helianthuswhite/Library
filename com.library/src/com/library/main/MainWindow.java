package com.library.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.library.book.BookManager;
import com.library.user.operate.userBookQuerier;
import com.library.usermanage.Details;
import com.library.usermanage.JudgeUser;
import com.library.usermanage.UserManage;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	public static  JTextField txtabcdef;
	public static  JPasswordField textField;
    private  JudgeLoad jud;
    public BookManager bm =null;
    public ChangePW cpw =null;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 900, 600);
		setTitle("图书管理系统测试版");
		setResizable(false);
		
	
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		/*
		 * 用户操作
		 */
		JMenu mnNewMenu = new JMenu("  \u7528\u6237\u64CD\u4F5C");
		menuBar.add(mnNewMenu);
		mnNewMenu.setEnabled(false);
		
		
		/*
		 * 管理员操作
		 */
		JMenu menu = new JMenu("\u7BA1\u7406\u5458\u64CD\u4F5C");
		menuBar.add(menu);
		menu.setEnabled(false);
		
		
		/*
		 * 图书管理
		 */
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u56FE\u4E66\u7BA1\u7406");
		menu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 bm = new BookManager();
			}
			});
		
		/*
		 * 用户管理
		 */
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u7528\u6237\u7BA1\u7406");
		menu.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UserManage();
			}
			});
		
		
		/*
		 * 版本信息
		 */
		JMenu menu_1 = new JMenu("\u7248\u672C\u4FE1\u606F");
		menuBar.add(menu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u7248\u672C\u4FE1\u606F");
		menu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Version();
			}
			});
		
		/*
		 * 登陆PANEL
		 */
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(630, 60, 256, 400);
		panel.setOpaque(false);
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setBorder(BorderFactory.createEtchedBorder() );
		/*
		 * 添加边框
		 */
		
		/*
		 * 添加背景图片
		 */
		 ImageIcon img = new ImageIcon("Graphics/bg.jpg");//这是背景图片  
		 JLabel imgLabel = new JLabel(img);//将背景图放在标签里。  
		   
		 this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		 //注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。  
		 imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置  
		 contentPane.setLayout(new BorderLayout());  
		 ((JPanel)contentPane).setOpaque(false);
		panel.setLayout(null);
		
		
		JLabel jlabel_1 = new JLabel();
		jlabel_1.setBounds(10, 132, 62, 41);
		jlabel_1.setFont(new Font("宋体", Font.BOLD, 20));
		jlabel_1.setText("账号");
		panel.add(jlabel_1);
		
		/*
		 * 账号输入
		 */
		txtabcdef = new JTextField();
		txtabcdef.setBounds(72, 132, 158, 31);
		txtabcdef.setFont(new Font("宋体", Font.PLAIN, 15));
		panel.add(txtabcdef);
		txtabcdef.setColumns(10);
		
		
		/*
		 * 密码输入
		 */
		textField = new JPasswordField();
		textField.setBounds(72, 190, 158, 31);
		textField.setFont(new Font("宋体", Font.PLAIN, 15));
		textField.setColumns(10);
		panel.add(textField);
		
		/*
		 * 密码
		 */
		JLabel label = new JLabel();
		label.setBounds(10, 183, 62, 41);
		label.setText("\u5BC6\u7801");
		label.setFont(new Font("宋体", Font.BOLD, 20));
		panel.add(label);
		
		/*
		 * 登陆button
		 */
		JButton btnSdadada = new JButton();
		btnSdadada.setBounds(65, 254, 75, 31);
		btnSdadada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  jud = new JudgeLoad();
				if(jud.a == 1){
					panel.setVisible(false);
					mnNewMenu.setEnabled(true);
					menu.setEnabled(false);
					JOptionPane.showMessageDialog(null,
							"登录成功!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}else if(jud.a == 2){
					panel.setVisible(false);
					menu.setEnabled(true);
					mnNewMenu.setEnabled(false);
					JOptionPane.showMessageDialog(null,
							"登录成功!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				}else{
					menu.setEnabled(false);
					mnNewMenu.setEnabled(false);
				}
			}
		});
		btnSdadada.setText("\u767B\u9646");
		panel.add(btnSdadada);
		
		/*
		 * 修改密码
		 */
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menu.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new ChangePW(2);
			}
		});
		
		/*
		 * 管理员退出登录
		 */
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		menu.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jud.a = 0;
				JOptionPane.showMessageDialog(null,
						"退出成功!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				menu.setEnabled(false);
				mnNewMenu.setEnabled(false);
				panel.setVisible(true);
			}
		});
		
		/*
		 * 个人信息
		 */
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("\u4E2A\u4EBA\u4FE1\u606F");
		mnNewMenu.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new JudgeUser();
				new Details();
			}
		});
		
		/*
		 * 图书查询
		 */
		JMenuItem menuItem = new JMenuItem("\u56FE\u4E66\u67E5\u8BE2");
		mnNewMenu.add(menuItem);
		menuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
                      new userBookQuerier();				
			}
		});
		
		
		/*
		 * 用户修改密码
		 */
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		mnNewMenu.add(mntmNewMenuItem_7);
		
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 cpw = new ChangePW(1);
			}
		});
		
		/*
		 * 用户退出登录
		 */
		
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("\u9000\u51FA\u767B\u5F55");
		mnNewMenu.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jud.a = 0;
				JOptionPane.showMessageDialog(null,
						"退出成功!", "系统信息", JOptionPane.INFORMATION_MESSAGE);
				menu.setEnabled(false);
				mnNewMenu.setEnabled(false);
				panel.setVisible(true);
			}
		});
		
		/*
		 * 注册Button
		 */
		JButton button = new JButton();
		button.setBounds(165, 254, 75, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Rigeste();
			}
		});
		button.setText("\u6CE8\u518C");
		panel.add(button);
		
		/*
		 * 欢迎词
		 */
		JLabel label_1 = new JLabel();
		label_1.setBounds(20, 10, 230, 41);
		label_1.setForeground(Color.RED);
		label_1.setText("\u6B22\u8FCE\u4F7F\u7528\u672C\u7CFB\u7EDF");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 30));
		panel.add(label_1);
		
		/*
		 * 提示词
		 */
		JLabel label_2 = new JLabel();
		label_2.setBounds(44, 257, 390, 106);
		label_2.setText("\u5982\u6709\u4EFB\u4F55\u7591\u95EE\uFF0C\u8BF7\u54A8\u8BE2\u7BA1\u7406\u5458");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		panel.add(label_2);
	}
}
