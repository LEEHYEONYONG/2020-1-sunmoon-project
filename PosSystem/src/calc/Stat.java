package calc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Stat extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
     
	/*
	
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stat frame = new Stat();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	*/
	
	public Stat() {
		setTitle("Á¤»ê");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton.setBounds(345, 34, 77, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_1.setBounds(423, 34, 77, 42);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("3");
		btnNewButton_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(501, 34, 77, 42);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("4");
		btnNewButton_3.setBackground(SystemColor.window);
		btnNewButton_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_3.setBounds(345, 77, 77, 42);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("5");
		btnNewButton_4.setBackground(SystemColor.window);
		btnNewButton_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_4.setBounds(423, 77, 77, 42);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("6");
		btnNewButton_5.setBackground(SystemColor.window);
		btnNewButton_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_5.setBounds(501, 77, 77, 42);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("7");
		btnNewButton_6.setBackground(SystemColor.window);
		btnNewButton_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_6.setBounds(345, 119, 77, 42);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("8");
		btnNewButton_7.setBackground(SystemColor.window);
		btnNewButton_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_7.setBounds(423, 119, 77, 42);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("9");
		btnNewButton_8.setBackground(SystemColor.window);
		btnNewButton_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_8.setBounds(501, 119, 77, 42);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("0");
		btnNewButton_9.setBackground(SystemColor.window);
		btnNewButton_9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_9.setBounds(345, 163, 155, 42);
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("00");
		btnNewButton_10.setBackground(SystemColor.window);
		btnNewButton_10.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_10.setBounds(501, 163, 77, 42);
		contentPane.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("¡ç");
		btnNewButton_11.setBackground(SystemColor.window);
		btnNewButton_11.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_11.setBounds(579, 34, 77, 42);
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("C");
		btnNewButton_12.setBackground(SystemColor.window);
		btnNewButton_12.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_12.setBounds(579, 77, 77, 42);
		contentPane.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton("Enter");
		btnNewButton_13.setBackground(SystemColor.window);
		btnNewButton_13.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 17));
		btnNewButton_13.setBounds(579, 119, 77, 86);
		contentPane.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton("ÆÇ¸Å°ü¸®");
		btnNewButton_14.setBackground(SystemColor.window);
		btnNewButton_14.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		btnNewButton_14.setBounds(720, 10, 276, 68);
		contentPane.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton("»óÇ°°ü¸®");
		btnNewButton_15.setBackground(SystemColor.window);
		btnNewButton_15.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		btnNewButton_15.setBounds(720, 99, 276, 68);
		contentPane.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton("Á¤»ê");
		btnNewButton_16.setBackground(SystemColor.window);
		btnNewButton_16.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		btnNewButton_16.setBounds(720, 185, 276, 68);
		contentPane.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton("Åë°è");
		btnNewButton_17.setBackground(SystemColor.window);
		btnNewButton_17.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		btnNewButton_17.setBounds(720, 274, 276, 68);
		contentPane.add(btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton("°èÁ¤°ü¸®");
		btnNewButton_18.setBackground(SystemColor.window);
		btnNewButton_18.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		btnNewButton_18.setBounds(720, 360, 276, 68);
		contentPane.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton("·Î±×¾Æ¿ô");
		btnNewButton_19.setBackground(SystemColor.window);
		btnNewButton_19.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		btnNewButton_19.setBounds(720, 446, 276, 68);
		contentPane.add(btnNewButton_19);
		
		JButton btnNewButton_20 = new JButton("Á¤»ê³»¿ª");
		btnNewButton_20.setBackground(SystemColor.window);
		btnNewButton_20.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		btnNewButton_20.setBounds(651, 572, 237, 68);
		contentPane.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton("Á¤»ê");
		btnNewButton_21.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnNewButton_21.setBackground(SystemColor.window);
		btnNewButton_21.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		btnNewButton_21.setBounds(372, 572, 237, 68);
		contentPane.add(btnNewButton_21);
		
		textField = new JTextField();
		textField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("±ÇÁ¾");
		textField.setForeground(SystemColor.window);
		textField.setBackground(new Color(0, 102, 255));
		textField.setBounds(25, 34, 87, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("¼ö·®");
		textField_1.setBackground(new Color(0, 102, 255));
		textField_1.setForeground(SystemColor.window);
		textField_1.setBounds(113, 34, 87, 35);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setText("±Ý¾×");
		textField_2.setForeground(SystemColor.window);
		textField_2.setBackground(new Color(0, 102, 255));
		textField_2.setBounds(201, 34, 87, 35);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("50000");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel.setBackground(new Color(0, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(25, 71, 87, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("10000");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(25, 108, 87, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("5000");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_2.setBackground(new Color(0, 255, 255));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(25, 145, 87, 35);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("1000");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_3.setBackground(new Color(224, 255, 255));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(25, 182, 87, 35);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("500");
		lblNewLabel_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_4.setBackground(new Color(0, 255, 255));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(25, 219, 87, 35);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("100");
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_5.setBackground(new Color(224, 255, 255));
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(25, 256, 87, 35);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("50");
		lblNewLabel_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_6.setBackground(new Color(0, 255, 255));
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(25, 293, 87, 35);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("10");
		lblNewLabel_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_7.setBackground(new Color(224, 255, 255));
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(25, 330, 87, 35);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("ÇÕ°è±Ý¾×");
		lblNewLabel_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		lblNewLabel_8.setForeground(SystemColor.window);
		lblNewLabel_8.setBackground(new Color(138, 43, 226));
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(25, 367, 87, 291);
		contentPane.add(lblNewLabel_8);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane.setBackground(new Color(0, 255, 255));
		textPane.setBounds(113, 71, 87, 35);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_1.setBackground(new Color(224, 255, 255));
		textPane_1.setBounds(113, 108, 87, 35);
		contentPane.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_2.setBackground(new Color(0, 255, 255));
		textPane_2.setBounds(113, 145, 87, 35);
		contentPane.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_3.setBackground(new Color(224, 255, 255));
		textPane_3.setBounds(113, 182, 87, 35);
		contentPane.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_4.setBackground(new Color(0, 255, 255));
		textPane_4.setBounds(113, 219, 87, 35);
		contentPane.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_5.setBackground(new Color(224, 255, 255));
		textPane_5.setBounds(113, 256, 87, 35);
		contentPane.add(textPane_5);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_6.setBackground(new Color(0, 255, 255));
		textPane_6.setBounds(113, 293, 87, 35);
		contentPane.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		textPane_7.setBackground(new Color(224, 255, 255));
		textPane_7.setBounds(113, 330, 87, 35);
		contentPane.add(textPane_7);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setOpaque(true);
		lblNewLabel_9.setBackground(new Color(0, 255, 255));
		lblNewLabel_9.setBounds(201, 71, 87, 35);
		contentPane.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_10.setBackground(new Color(224, 255, 255));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setOpaque(true);
		lblNewLabel_10.setBounds(201, 108, 87, 35);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_11.setBackground(new Color(0, 255, 255));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setOpaque(true);
		lblNewLabel_11.setBounds(201, 145, 87, 35);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_12.setBackground(new Color(224, 255, 255));
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setOpaque(true);
		lblNewLabel_12.setBounds(201, 182, 87, 35);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_13.setBackground(new Color(0, 255, 255));
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_13.setOpaque(true);
		lblNewLabel_13.setBounds(201, 219, 87, 35);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_14.setBackground(new Color(224, 255, 255));
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setOpaque(true);
		lblNewLabel_14.setBounds(201, 256, 87, 35);
		contentPane.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_15.setBackground(new Color(0, 255, 255));
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_15.setOpaque(true);
		lblNewLabel_15.setBounds(201, 293, 87, 35);
		contentPane.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_16.setBackground(new Color(224, 255, 255));
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16.setOpaque(true);
		lblNewLabel_16.setBounds(201, 330, 87, 35);
		contentPane.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("");
		lblNewLabel_17.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_17.setForeground(SystemColor.window);
		lblNewLabel_17.setBackground(new Color(138, 43, 226));
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_17.setOpaque(true);
		lblNewLabel_17.setBounds(201, 367, 87, 291);
		contentPane.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("");
		lblNewLabel_18.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel_18.setForeground(SystemColor.window);
		lblNewLabel_18.setBackground(new Color(138, 43, 226));
		lblNewLabel_18.setOpaque(true);
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_18.setBounds(113, 367, 87, 291);
		contentPane.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("ÀüÃ¼±Ý¾×");
		lblNewLabel_19.setToolTipText("");
		lblNewLabel_19.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel_19.setOpaque(true);
		lblNewLabel_19.setBackground(new Color(224, 255, 255));
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_19.setBounds(345, 229, 155, 211);
		contentPane.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("Â÷ÀÌ±Ý¾×");
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_20.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel_20.setBackground(new Color(0, 255, 255));
		lblNewLabel_20.setOpaque(true);
		lblNewLabel_20.setBounds(345, 443, 155, 40);
		contentPane.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setBackground(new Color(224, 255, 255));
		lblNewLabel_21.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_21.setOpaque(true);
		lblNewLabel_21.setBounds(501, 229, 155, 211);
		contentPane.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("");
		lblNewLabel_22.setBackground(new Color(0, 255, 255));
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_22.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 24));
		lblNewLabel_22.setOpaque(true);
		lblNewLabel_22.setBounds(501, 443, 155, 40);
		contentPane.add(lblNewLabel_22);
	}
}