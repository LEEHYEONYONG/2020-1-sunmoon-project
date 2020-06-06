package sale;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Payment_1 extends JFrame {
	
	JPanel contentPane;
	final JPanel Payment_1 = new JPanel();
	public JTextField tfP1SM;
	public 	JTextField tfP1BeforePrice;
	public JTextField tfP1Afterprice;
	public JButton btnP1Before;
	public JButton btnP1Next;
	public JLabel lbP1Afterprice;
	
	/*
	Payment_3 payment_3 = new Payment_3();
	Payment_4 payment_4 = new Payment_4();
	*/
	
	public JButton btnP1Apply;
	private JTextField txtP1Check;
	
	
	
	/*
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_1 frame = new Payment_1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	public Payment_1() {
		setTitle("할인");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(Payment_1, BorderLayout.CENTER);
		Payment_1.setLayout(null);
		
		tfP1SM = new JTextField();
		tfP1SM.setEditable(false);
		tfP1SM.setHorizontalAlignment(SwingConstants.CENTER);
		tfP1SM.setBounds(150, 20, 300, 30);
		Payment_1.add(tfP1SM);
		tfP1SM.setColumns(10);

		btnP1Apply = new JButton("적용");
		btnP1Apply.setBounds(350, 100, 150, 50);
		Payment_1.add(btnP1Apply);

		tfP1BeforePrice = new JTextField();
		tfP1BeforePrice.setHorizontalAlignment(SwingConstants.CENTER);
		tfP1BeforePrice.setFont(new Font("굴림", Font.BOLD, 18));
		tfP1BeforePrice.setEditable(false);
		tfP1BeforePrice.setBounds(100, 220, 150, 50);
		Payment_1.add(tfP1BeforePrice);
		tfP1BeforePrice.setColumns(10);

		tfP1Afterprice = new JTextField();
		tfP1Afterprice.setHorizontalAlignment(SwingConstants.CENTER);
		tfP1Afterprice.setEditable(false);
		tfP1Afterprice.setColumns(10);
		tfP1Afterprice.setBounds(350, 220, 150, 50);
		Payment_1.add(tfP1Afterprice);

		btnP1Before = new JButton("거래취소");
		btnP1Before.setBounds(100, 310, 150, 50);
		Payment_1.add(btnP1Before);

		btnP1Next = new JButton("다음으로");
		btnP1Next.setBounds(350, 310, 150, 50);
		Payment_1.add(btnP1Next);

		JLabel lbP1Beforeprice = new JLabel("할인적용 전 가격");
		lbP1Beforeprice.setHorizontalAlignment(SwingConstants.CENTER);
		lbP1Beforeprice.setBounds(100, 160, 150, 50);
		Payment_1.add(lbP1Beforeprice);

		lbP1Afterprice = new JLabel("할인적용 후 가격");
		lbP1Afterprice.setHorizontalAlignment(SwingConstants.CENTER);
		lbP1Afterprice.setBounds(350, 160, 150, 50);
		Payment_1.add(lbP1Afterprice);
		
		txtP1Check = new JTextField();
		txtP1Check.setBounds(100, 100, 238, 50);
		Payment_1.add(txtP1Check);
		txtP1Check.setColumns(10);
		
		
	}
}
