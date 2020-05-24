package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sale.DealCancel;
import sale.Payment_3;
import sale.Payment_4;
import sale.SaleBtn;
import sale.SalesInputService;
import sale.ViewSalesInput;

public class MainFrame extends JFrame implements ActionListener, Runnable{// ���������� 
	
	
	public CardLayout monitor;
	public CardLayout btn;
	Thread thread;
	
	public JButton logout;
	
	private JPanel contentPane;
	private JPanel pMainBtn;
	public JPanel pMonitor;
	public JButton mBtnSale;//�ǸŹ�ư
	public JButton mBtnInven;//����ư
	public JButton mBtnCalc;//�����ư
	public JButton mBtnStat;//����ư
	public JButton mBtnAccount;//������ư
	public JPanel pFBtn;
	
	public SaleBtn salebtn = new SaleBtn();
	
	public ViewSalesInput viewSalesInput = new ViewSalesInput();
	public SalesInputService salesInputService = new SalesInputService(this);
	
	// ������ �������â ������
	public DealCancel dealCancel = new DealCancel();
	public Payment_3 payment_3 = new Payment_3();
	public Payment_4 payment_4 = new Payment_4();
	

	public MainFrame(){
		monitor = new CardLayout();
		btn = new CardLayout();
		
		
		setFont(new Font("���� ���",Font.BOLD,20));
		setTitle("pos");
		setVisible(true);
		setResizable(false);
		//setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1326,753);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255,255,255));
		pMainBtn.setBounds(1157,50,163,625);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);
		
		
		
		mBtnSale = new JButton("�Ǹ�");
		mBtnSale.setBounds(0, 0, 163, 125);
		pMainBtn.add(mBtnSale);
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		
		mBtnInven = new JButton("���");
		mBtnInven.setBounds(0, 125, 163, 125);
		pMainBtn.add(mBtnInven);
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("���� ���", Font.BOLD, 20));
		
		mBtnCalc = new JButton("����");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnCalc.setBounds(0, 250, 163, 125);
		pMainBtn.add(mBtnCalc);
		
		mBtnStat = new JButton("���");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(0, 375, 163, 125);
		pMainBtn.add(mBtnStat);
		
		logout = new JButton("�α׾ƿ�");
		logout.setBounds(0, 499, 163, 125);
		pMainBtn.add(logout);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("���� ���", Font.BOLD, 20));
		
		pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1157, 552);
		contentPane.add(pMonitor);
		pMonitor.setLayout(monitor);
		
		
		//////////////////////////////////
		pMonitor.add("ViewSalesInput", viewSalesInput);
		
		
		pFBtn = new JPanel();
		pFBtn.setBackground(Color.WHITE);
		pFBtn.setBounds(0, 601, 1157, 123);
		contentPane.add(pFBtn);
		pFBtn.setLayout(btn);
		
		
        //////////////////////////////////
		pFBtn.add(salebtn, "salebtn");
		
		
		
		//�Ǹ� ��� ������
		salebtn.sBtnPdHold.addActionListener(this);
		salebtn.sBtnCancel.addActionListener(this);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcancel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);
		
//		����3 �̺�Ʈ ���
		payment_3.btnP3Before.addActionListener(this);
		payment_3.btnP3Cancel.addActionListener(this);
		payment_3.btnP3Input.addActionListener(salesInputService);
		payment_3.btnP3Next.addActionListener(salesInputService);
		
//		���� 4 �̺�Ʈ ���

		payment_4.btnP4PrintReceipt.addActionListener(this);
		payment_4.btnP4Payment.addActionListener(salesInputService);

		dealCancel.btnRefund.addActionListener(this);

		viewSalesInput.code_input.addKeyListener(salesInputService);
		viewSalesInput.product_name_input.addKeyListener(salesInputService);
		
		
		thread = new Thread(this);
		thread.start();

	}//���������Ӹ޼ҵ� ��
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame mainFrame = new MainFrame();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { //�̺�Ʈó�� 
		Object ob = e.getSource();
		Object obb =e.getActionCommand();
		
		//���α�ɵ�
		if(ob==mBtnSale) {//�ǸŰ���
			mBtnSale.setBackground(new Color(255, 69, 0));
			mBtnAccount.setBackground(new Color(28, 94, 94));
			mBtnCalc.setBackground(new Color(28, 94, 94));
			mBtnInven.setBackground(new Color(28, 94, 94));
			mBtnStat.setBackground(new Color(99, 166, 166));
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		} else if (ob == mBtnStat) {//���
			
		} else if (ob == mBtnInven) {//������
			
		} else if (ob == mBtnCalc) {
			
		}
		
		//���α�ɵ�
		
		//�ǸŰ����� ��ɵ�
		else if(ob == salebtn.sBtnCancel) {//ȯ��
			dealCancel.setVisible(true);
		}
		else if (ob == salebtn.sBtnPdHold) {//��ǰ����
			
		} else if (ob == payment_3.btnP3Before) {
			payment_4.setVisible(false);
			payment_3.setVisible(false);

		} else if (ob == payment_3.btnP3Cancel) {
			payment_3.setVisible(false);
		}
		
		
		
		
		
		
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
