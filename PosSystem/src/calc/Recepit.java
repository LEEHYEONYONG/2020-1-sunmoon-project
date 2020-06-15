package calc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

public class Recepit extends JFrame {
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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
	}

	/**
	 * Create the frame.
	 */
	public Recepit() {
		setTitle("Á¤»ê¿µ¼öÁõ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 688);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Date = new JLabel("³¯Â¥");
		Date.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		Date.setBackground(new Color(224, 255, 255));
		Date.setOpaque(true);
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		Date.setBounds(54, 78, 140, 45);
		contentPane.add(Date);
		
		JLabel Admin = new JLabel("°ü¸®ÀÚ");
		Admin.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		Admin.setBackground(new Color(224, 255, 255));
		Admin.setOpaque(true);
		Admin.setHorizontalAlignment(SwingConstants.CENTER);
		Admin.setBounds(54, 158, 140, 45);
		contentPane.add(Admin);
		
		JLabel Cash = new JLabel("\uD604\uAE08\uB9E4\uCD9C");
		Cash.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		Cash.setBackground(new Color(224, 255, 255));
		Cash.setOpaque(true);
		Cash.setHorizontalAlignment(SwingConstants.CENTER);
		Cash.setBounds(54, 238, 140, 45);
		contentPane.add(Cash);
		
		JLabel lbCashChec = new JLabel("±Ý°íÀüÃ¼±Ý¾×");
		lbCashChec.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lbCashChec.setBackground(new Color(224, 255, 255));
		lbCashChec.setOpaque(true);
		lbCashChec.setHorizontalAlignment(SwingConstants.CENTER);
		lbCashChec.setBounds(54, 318, 140, 45);
		contentPane.add(lbCashChec);
		
		JLabel lbCashRes = new JLabel("Â÷ÀÌ±Ý¾×");
		lbCashRes.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lbCashRes.setBackground(new Color(224, 255, 255));
		lbCashRes.setOpaque(true);
		lbCashRes.setHorizontalAlignment(SwingConstants.CENTER);
		lbCashRes.setBounds(54, 398, 140, 45);
		contentPane.add(lbCashRes);
		
		JLabel Date_1 = new JLabel("");
		Date_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Date_1.setBackground(new Color(224, 255, 255));
		Date_1.setOpaque(true);
		Date_1.setHorizontalAlignment(SwingConstants.CENTER);
		Date_1.setBounds(248, 78, 140, 45);
		contentPane.add(Date_1);
		
		JLabel Admin_1 = new JLabel("");
		Admin_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Admin_1.setBackground(new Color(224, 255, 255));
		Admin_1.setOpaque(true);
		Admin_1.setHorizontalAlignment(SwingConstants.CENTER);
		Admin_1.setBounds(248, 158, 140, 45);
		contentPane.add(Admin_1);
		
		JLabel Cash_1 = new JLabel("");
		Cash_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Cash_1.setBackground(new Color(224, 255, 255));
		Cash_1.setOpaque(true);
		Cash_1.setHorizontalAlignment(SwingConstants.CENTER);
		Cash_1.setBounds(248, 238, 140, 45);
		contentPane.add(Cash_1);
		
		JLabel lbCashChec_1 = new JLabel("");
		lbCashChec_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lbCashChec_1.setBackground(new Color(224, 255, 255));
		lbCashChec_1.setOpaque(true);
		lbCashChec_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbCashChec_1.setBounds(248, 318, 140, 45);
		contentPane.add(lbCashChec_1);
		
		JLabel lbCashRes_1 = new JLabel("");
		lbCashRes_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lbCashRes_1.setBackground(new Color(224, 255, 255));
		lbCashRes_1.setOpaque(true);
		lbCashRes_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbCashRes_1.setBounds(248, 398, 140, 45);
		contentPane.add(lbCashRes_1);
		
		JButton printRec = new JButton("Á¤»ê¿µ¼öÁõ Ãâ·Â");
		printRec.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		printRec.setBackground(SystemColor.window);
		printRec.setBounds(44, 503, 160, 50);
		printRec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "¿µ¼öÁõÀ» Ãâ·ÂÇÕ´Ï´Ù.");
				
				MainFrame drawing = new MainFrame();
				
				dispose();
			}
		});
		contentPane.add(printRec);
		
		JButton Calc = new JButton("Á¤»ê");
		Calc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		Calc.setBackground(SystemColor.window);
		Calc.setBounds(241, 503, 160, 50);
		Calc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Á¤»ê ¿Ï·á");
				
				MainFrame drawing = new MainFrame();
				
				dispose();
			}
		});
		contentPane.add(Calc);
		
		
	}


}
