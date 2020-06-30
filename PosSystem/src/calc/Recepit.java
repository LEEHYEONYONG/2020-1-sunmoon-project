package calc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.MainFrame;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridLayout;

public class Recepit extends JFrame {
	
	public JPanel contentPane;
	public JLabel Date;
	public JLabel Admin;
	public JLabel Cash;
	public JLabel lbCashChec;
	public JLabel lbCashRes;
	public JTextField Date_1;//³¯Â¥
	public JTextField Admin_1;//°ü¸®ÀÚ
	public JTextField Cash_1;//Çö±Ý¸ÅÃâ
	public JTextField lbCashChec_1;//±Ý°íÀüÃ¼±Ý¾×
	public JTextField lbCashRes_1;//Â÷ÀÌ±Ý¾×
	public JButton printRec;//Á¤»ê¹öÆ°
	public JButton Calc;//Ãë¼Ò
	public JPanel panelCheck;
	
    /*
	/**
	 * Launch the application.
	 */
	
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recepit frame = new Recepit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Recepit() {
		setResizable(false);
		setTitle("Á¤»ê¿µ¼öÁõ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 688);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		printRec = new JButton("Á¤»ê¿µ¼öÁõ Ãâ·Â");
		printRec.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		printRec.setBackground(SystemColor.window);
		printRec.setBounds(44, 503, 160, 50);
		/*
		printRec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¿µ¼öÁõÀ» Ãâ·ÂÇÕ´Ï´Ù.");
				
				MainFrame drawing = new MainFrame();
				
				dispose();
			}
		});
		*/
		contentPane.add(printRec);
		
		Calc = new JButton("È®ÀÎ");
		Calc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Calc.setBackground(SystemColor.window);
		Calc.setBounds(298, 503, 160, 50);
		/*
		Calc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Á¤»ê ¿Ï·á");
				
				MainFrame drawing = new MainFrame();
				
				dispose();
			}
		});
		*/
		contentPane.add(Calc);
		
		
		panelCheck = new JPanel();
		panelCheck.setBackground(Color.WHITE);
		panelCheck.setBounds(12, 10, 470, 459);
		contentPane.add(panelCheck);
		panelCheck.setLayout(null);
		
		Date = new JLabel("³¯Â¥");
		Date.setBounds(26, 52, 140, 43);
		panelCheck.add(Date);
		Date.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		Date.setBackground(new Color(224, 255, 255));
		Date.setOpaque(true);
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		
		Date_1 = new JTextField("");
		Date_1.setBounds(206, 53, 252, 45);
		panelCheck.add(Date_1);
		Date_1.setEditable(false);
		Date_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Date_1.setBackground(new Color(224, 255, 255));
		Date_1.setOpaque(true);
		Date_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		Admin = new JLabel("°ü¸®ÀÚ");
		Admin.setBounds(26, 120, 140, 45);
		panelCheck.add(Admin);
		Admin.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		Admin.setBackground(new Color(224, 255, 255));
		Admin.setOpaque(true);
		Admin.setHorizontalAlignment(SwingConstants.CENTER);
		
		Admin_1 = new JTextField("");
		Admin_1.setBounds(206, 122, 252, 45);
		panelCheck.add(Admin_1);
		Admin_1.setEditable(false);
		Admin_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Admin_1.setBackground(new Color(224, 255, 255));
		Admin_1.setOpaque(true);
		Admin_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		Cash = new JLabel("\uD604\uAE08\uB9E4\uCD9C");
		Cash.setBounds(26, 193, 140, 45);
		panelCheck.add(Cash);
		Cash.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		Cash.setBackground(new Color(224, 255, 255));
		Cash.setOpaque(true);
		Cash.setHorizontalAlignment(SwingConstants.CENTER);
		
		Cash_1 = new JTextField("");
		Cash_1.setBounds(206, 195, 252, 45);
		panelCheck.add(Cash_1);
		Cash_1.setEditable(false);
		Cash_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Cash_1.setBackground(new Color(224, 255, 255));
		Cash_1.setOpaque(true);
		Cash_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbCashChec = new JLabel("±Ý°íÀüÃ¼±Ý¾×");
		lbCashChec.setBounds(26, 269, 140, 45);
		panelCheck.add(lbCashChec);
		lbCashChec.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lbCashChec.setBackground(new Color(224, 255, 255));
		lbCashChec.setOpaque(true);
		lbCashChec.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbCashChec_1 = new JTextField("");
		lbCashChec_1.setBounds(206, 269, 252, 45);
		panelCheck.add(lbCashChec_1);
		lbCashChec_1.setEditable(false);
		lbCashChec_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lbCashChec_1.setBackground(new Color(224, 255, 255));
		lbCashChec_1.setOpaque(true);
		lbCashChec_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbCashRes = new JLabel("Â÷ÀÌ±Ý¾×");
		lbCashRes.setBounds(26, 343, 140, 45);
		panelCheck.add(lbCashRes);
		lbCashRes.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lbCashRes.setBackground(new Color(224, 255, 255));
		lbCashRes.setOpaque(true);
		lbCashRes.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbCashRes_1 = new JTextField("");
		lbCashRes_1.setBounds(206, 345, 252, 45);
		panelCheck.add(lbCashRes_1);
		lbCashRes_1.setEditable(false);
		lbCashRes_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lbCashRes_1.setBackground(new Color(224, 255, 255));
		lbCashRes_1.setOpaque(true);
		lbCashRes_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	}
}
