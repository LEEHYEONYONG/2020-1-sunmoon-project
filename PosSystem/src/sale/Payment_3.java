package sale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Payment_3 extends JFrame {//결제화면 GUI

	JPanel contentPane;
	public JPanel payment_3 = new JPanel();
	public JTextField tfP3SM;
	public JTextField tfP3CardP;
	public JLabel lbP3FinalPay;
	public JLabel lbP3Payment;
	public JTextField tfP3CashP;
	public JLabel lbP3PointView;
	public JLabel lbP3FinalPayView;
	public JLabel lbP3PaymentView;

	public JButton btnP3Before;
	public JButton btnP3Next;
	public JButton btnP3Cancel;
	public JButton btnP3Input;
	
	
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				Payment_3 frame = new Payment_3();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
  }


	
	
	public Payment_3() {
		setTitle("결제 금액 확인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(payment_3, BorderLayout.CENTER);
		payment_3.setLayout(null);
		
		tfP3SM = new JTextField();
		tfP3SM.setEditable(false);
		tfP3SM.setHorizontalAlignment(SwingConstants.CENTER);
		tfP3SM.setBounds(150, 20, 300, 30);
		payment_3.add(tfP3SM);
		tfP3SM.setColumns(10);
		
		btnP3Input = new JButton("입력완료");
		btnP3Input.setBounds(369, 89, 140, 90);
		payment_3.add(btnP3Input);
		
		tfP3CardP = new JTextField();
		tfP3CardP.setBounds(153, 89, 150, 40);
		payment_3.add(tfP3CardP);
		tfP3CardP.setColumns(10);
		
		btnP3Before = new JButton("이전으로");
		btnP3Before.setBounds(12, 341, 150, 50);
		payment_3.add(btnP3Before);
		
		btnP3Next = new JButton("결 제");
		btnP3Next.setForeground(Color.RED);
		btnP3Next.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		btnP3Next.setBounds(412, 341, 150, 50);
		payment_3.add(btnP3Next);
		
		btnP3Cancel = new JButton("거래취소");
		btnP3Cancel.setBounds(212, 341, 150, 50);
		payment_3.add(btnP3Cancel);
		
		lbP3FinalPay = new JLabel("결제 될 금액");
		lbP3FinalPay.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3FinalPay.setBounds(397, 227, 150, 40);
		payment_3.add(lbP3FinalPay);
		
		lbP3Payment = new JLabel("결제 총 금액");
		lbP3Payment.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3Payment.setBounds(37, 227, 150, 40);
		payment_3.add(lbP3Payment);
		
		tfP3CashP = new JTextField();
		tfP3CashP.setColumns(10);
		tfP3CashP.setBounds(153, 139, 150, 40);
		payment_3.add(tfP3CashP);
		
		JLabel label = new JLabel("카드 받은 금액");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 89, 129, 40);
		payment_3.add(label);
		
		JLabel label_1 = new JLabel("현금 받은 금액");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 139, 129, 40);
		payment_3.add(label_1);
		
		lbP3PaymentView = new JLabel("");
		lbP3PaymentView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP3PaymentView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3PaymentView.setBounds(37, 277, 150, 40);
		payment_3.add(lbP3PaymentView);
		
		
		lbP3FinalPayView = new JLabel("");
		lbP3FinalPayView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3FinalPayView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP3FinalPayView.setBounds(397, 277, 150, 40);
		payment_3.add(lbP3FinalPayView);
	    }
	
	    
	
	
	
	}
	