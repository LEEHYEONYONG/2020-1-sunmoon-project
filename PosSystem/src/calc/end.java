package calc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JButton;


public class end extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					end frame = new end();
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
	public end() {
		setTitle("Á¤»ê³»¿ª");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 640);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Á¤»ê³»¿ª");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(new Color(238, 232, 170));
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(145, 30, 100, 40);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(253, 245, 230));
		textArea.setBounds(57, 100, 270, 380);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("¿µ¼öÁõÃâ·Â");
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		btnNewButton.setBounds(30, 500, 130, 64);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("È®ÀÎ");
		btnNewButton_1.setBackground(SystemColor.window);
		btnNewButton_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		btnNewButton_1.setBounds(230, 500, 130, 64);
		contentPane.add(btnNewButton_1);
	}
}

