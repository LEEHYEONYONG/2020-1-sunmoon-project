package sale;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Payment_4 extends JFrame{//영수증 출력 GUI
	
	private JPanel contentPane;
	final JPanel Payment_4 = new JPanel();
	public JButton btnP4Payment;
	public JButton btnP4PrintReceipt;
	public JTextArea taP4details;
	
	/*
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Payment_4 frame = new Payment_4();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
  }
  */
	
	
	public Payment_4() {
		setTitle("영수증 출력");
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(Payment_4, BorderLayout.CENTER);
		Payment_4.setLayout(null);
		
		
	}

}
