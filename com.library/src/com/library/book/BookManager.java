package com.library.book;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class BookManager extends JFrame {

	private JPanel contentPane;
    public BookAdder ab = null;
    public RuleMaker mr  = null;
    public BookQuerier qb = null;

	/**
	 * Create the frame.
	 */
	public BookManager() {
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setResizable(false);
		setVisible(true);
		setBounds(420,320, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAddBook = new JButton("\u65B0\u4E66\u4E0A\u67B6");
		btnAddBook.setBounds(31, 37, 173, 78);
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ab = new BookAdder();
				ab.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAddBook);
		
		JButton btnMakeRule = new JButton("\u501F\u9605\u89C4\u5219\u5B9A\u5236");
		btnMakeRule.setBounds(234, 37, 173, 78);
		btnMakeRule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				    mr = new RuleMaker();
					mr.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnMakeRule);
		
		JButton btnQueryBook = new JButton("\u56FE\u4E66\u67E5\u8BE2");
		btnQueryBook.setBounds(31, 150, 376, 78);
		btnQueryBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					qb = new BookQuerier();
					qb.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnQueryBook);
	}
}
