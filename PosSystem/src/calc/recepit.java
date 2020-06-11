package calc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class recepit extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recepit frame = new recepit();
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
	public recepit() {
		setTitle("Á¤»ê¿µ¼öÁõ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 688);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("³¯Â¥");
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lblNewLabel.setBackground(new Color(224, 255, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(54, 78, 140, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("°ü¸®ÀÚ");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lblNewLabel_1.setBackground(new Color(224, 255, 255));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(54, 158, 140, 45);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("ÇÕ°è±Ý¾×");
		lblNewLabel_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lblNewLabel_2.setBackground(new Color(224, 255, 255));
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(54, 238, 140, 45);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("±Ý°íÀüÃ¼±Ý¾×");
		lblNewLabel_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lblNewLabel_3.setBackground(new Color(224, 255, 255));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(54, 318, 140, 45);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Â÷ÀÌ±Ý¾×");
		lblNewLabel_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
		lblNewLabel_4.setBackground(new Color(224, 255, 255));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(54, 398, 140, 45);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lblNewLabel_5.setBackground(new Color(224, 255, 255));
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(248, 78, 140, 45);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lblNewLabel_6.setBackground(new Color(224, 255, 255));
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setBounds(248, 158, 140, 45);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lblNewLabel_7.setBackground(new Color(224, 255, 255));
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(248, 238, 140, 45);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lblNewLabel_8.setBackground(new Color(224, 255, 255));
		lblNewLabel_8.setOpaque(true);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setBounds(248, 318, 140, 45);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		lblNewLabel_9.setBackground(new Color(224, 255, 255));
		lblNewLabel_9.setOpaque(true);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(248, 398, 140, 45);
		contentPane.add(lblNewLabel_9);
		
		JButton btnNewButton = new JButton("Á¤»ê¿µ¼öÁõ Ãâ·Â");
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setBounds(44, 503, 160, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Á¤»ê");
		btnNewButton_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		btnNewButton_1.setBackground(SystemColor.window);
		btnNewButton_1.setBounds(241, 503, 160, 50);
		contentPane.add(btnNewButton_1);
	}
}
