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

import sale.SaleBtn;
import sale.SalesInputService;
import sale.ViewSalesInput;

public class MainFrame extends JFrame implements ActionListener{
	
	
	public CardLayout monitor;
	public CardLayout btn;
	Thread thread;
	
	public JButton logout;
	
	private JPanel contentPane;
	private JPanel pMainBtn;
	public JPanel pMonitor;
	public JButton mBtnSale;//판매버튼
	public JButton mBtnInven;//재고버튼
	public JButton mBtnCalc;//정산버튼
	public JButton mBtnStat;//통계버튼
	public JButton mBtnAccount;//계정버튼
	public JPanel pFBtn;
	
	public SaleBtn salebtn = new SaleBtn();
	
	public ViewSalesInput viewSalesInput = new ViewSalesInput();
	public SalesInputService salesInputService = new SalesInputService(this);
	

	public MainFrame(){
		monitor = new CardLayout();
		btn = new CardLayout();
		
		
		setFont(new Font("맑은 고딕",Font.BOLD,20));
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
		
		
		
		mBtnSale = new JButton("판매");
		mBtnSale.setBounds(0, 0, 163, 125);
		pMainBtn.add(mBtnSale);
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		
		mBtnInven = new JButton("재고");
		mBtnInven.setBounds(0, 125, 163, 125);
		pMainBtn.add(mBtnInven);
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		mBtnCalc = new JButton("정산");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnCalc.setBounds(0, 250, 163, 125);
		pMainBtn.add(mBtnCalc);
		
		mBtnStat = new JButton("통계");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(0, 375, 163, 125);
		pMainBtn.add(mBtnStat);
		
		logout = new JButton("로그아웃");
		logout.setBounds(0, 499, 163, 125);
		pMainBtn.add(logout);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
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
		
		
		
		//판매 기능 리스너
		salebtn.sBtnPdHold.addActionListener(this);
		salebtn.sBtnCancel.addActionListener(this);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcancel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);
		
		
		
		

		
		
		
		
	}
	
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
	public void actionPerformed(ActionEvent e) { //이벤트처리 
		Object ob = e.getSource();
		Object obb =e.getActionCommand();
		
		if(ob==mBtnSale) {
			mBtnSale.setBackground(new Color(255, 69, 0));
			mBtnAccount.setBackground(new Color(28, 94, 94));
			mBtnCalc.setBackground(new Color(28, 94, 94));
			mBtnInven.setBackground(new Color(28, 94, 94));
			mBtnStat.setBackground(new Color(99, 166, 166));
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		}
		
		
	}

}
