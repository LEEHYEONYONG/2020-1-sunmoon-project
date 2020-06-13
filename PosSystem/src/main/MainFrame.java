package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import db.PosDto;
import sale.DealCancel;
import sale.Payment_1;
import sale.Payment_3;
import sale.Payment_4;
import sale.SaleBtn;
import sale.SalesInputService;
import sale.ViewSalesInput;
import stock.StockBtn;
import stock.StockMonitor;
import stock.StockPopupChange;
import stock.StockPopupIn;
import stock.StockPopupSearch;
import calc.CalcBtn;
import calc.CalcService;
import db.Connect_DB;

public class MainFrame extends JFrame implements ActionListener, Runnable{// ���������� 
	
	StockPopupIn stockpopupin = new StockPopupIn();
	StockPopupChange stockpopupchange = new StockPopupChange();
	StockPopupSearch stockpopupsearch = new StockPopupSearch();
	StockBtn stockbtn;
	StockMonitor stockmonitor = new StockMonitor();
	//Stat stat;
	public CardLayout monitor;
	public CardLayout btn;
	Thread thread;
	
	public JButton logout;
	
	private JPanel contentPane;
	private JPanel pMainBtn;
	public JPanel pMonitor;
	private JPanel pStatusBar;
	private JLabel titleLabel;
	private JLabel dateLabel;
	public JButton mBtnSale;//�ǸŹ�ư
	public JButton mBtnInven;//����ư
	public JButton mBtnCalc;//�����ư
	public JButton mBtnStat;//����ư
	public JButton mBtnAccount;//������ư
	public JPanel pFBtn;
	
	
	CalcService calcService = new CalcService(this);
	public SaleBtn salebtn = new SaleBtn();
	public CalcBtn calcbtn = new CalcBtn();
	
//	��ǰ���� ��ư ���� ���� Ȯ��
	boolean isHold = false;
//	������ ��ǰ���� PosDto��ü���� ������ ����
	Vector<PosDto> hodingProductList = null;
	
	public ViewSalesInput viewSalesInput = new ViewSalesInput();
	public SalesInputService salesInputService = new SalesInputService(this);
	
	//�����ͺ��̽� �ʵ� �߰�
    public Connect_DB connect_db = new Connect_DB();
	
	// ������ �������â ������
	public DealCancel dealCancel = new DealCancel();
	public Payment_1 payment_1 = new Payment_1();
	public Payment_3 payment_3 = new Payment_3();
	public Payment_4 payment_4 = new Payment_4();
	

	public MainFrame(){
		monitor = new CardLayout();
		btn = new CardLayout();
		stockbtn = new StockBtn();
		
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
		
		pStatusBar = new JPanel();
		pStatusBar.setBackground(new Color(0, 0, 128));
		pStatusBar.setBounds(0, 0, 1320, 51);
		contentPane.add(pStatusBar);
		pStatusBar.setLayout(null);
		
		titleLabel = new JLabel("Pos");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 128));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 25));
		titleLabel.setBounds(540, 10, 241, 31);
		pStatusBar.add(titleLabel);
		
		dateLabel = new JLabel();
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("���� ���", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);
		
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
//		������гο� ī�巹�̾ƿ��ְ� ��ȭ�� �г� ��ü�����Ͽ� �߰�
		pMonitor.add("ViewSalesInput", viewSalesInput);
		pMonitor.add(stockmonitor, "Stock");
		//pMonitor.add(stat,"stat");
		
		pFBtn = new JPanel();
		pFBtn.setBackground(Color.WHITE);
		pFBtn.setBounds(0, 601, 1157, 123);
		contentPane.add(pFBtn);
		pFBtn.setLayout(btn);
		salebtn.sBtnAcancel.setLocation(352, 28);
		salebtn.sBtnPay.setLocation(528, 28);
		salebtn.sBtnCancel.setLocation(704, 28);
		
		
        //////////////////////////////////
//		��ɹ�ư�г� ī�� ����ɹ�ư �г� ��ü������ �߰�
		pFBtn.add(salebtn, "salebtn");
		pFBtn.add(stockbtn, "Stockbtn");
		//pFBtn.add(statbtn, "Statbtn");
		//pFBtn.add(accbtn, "Accbtn");
		pFBtn.add(calcbtn, "Calcbtn");
		
		
		
		// ���α�ɹ�ư
		mBtnInven.addActionListener(this);
		mBtnSale.addActionListener(this);
		mBtnCalc.addActionListener(calcService);
		mBtnStat.addActionListener(this);
		
		
		// ��� ��ɹ�ư
		stockbtn.stockSearch.addActionListener(this);
		stockbtn.stockIn.addActionListener(this);
		stockbtn.stockChg.addActionListener(this);
		stockbtn.stockevery.addActionListener(this);
		
		// ���-�˻� �˾� �׼Ǹ�����
		stockpopupsearch.sCbtn.addActionListener(this);
		stockpopupsearch.SearchCode.addActionListener(this);
		stockpopupsearch.searchCancel.addActionListener(this);
		stockpopupsearch.SearchName.addActionListener(this);
		stockpopupsearch.sNbtn.addActionListener(this);

		// ���-���� �˾� �׼Ǹ�����
		stockpopupchange.chgOk.addActionListener(this);
		stockpopupchange.chgCancel.addActionListener(this);
		stockpopupchange.ChgCountTf.addActionListener(this);
		
		// ���-���� �˾� �׼Ǹ����� ( �԰��ư)
		stockpopupin.inOk.addActionListener(this);
		stockpopupin.inCancel.addActionListener(this);
		stockpopupin.PcodeTf.addActionListener(this);
		stockpopupin.IndateTf.addActionListener(this);
		stockpopupin.PcntTf.addActionListener(this);
		stockpopupin.pcodeInput.addActionListener(this);
		stockpopupin.indateInput.addActionListener(this);
		stockpopupin.pcntInput.addActionListener(this);
		
		
		//�Ǹ� ��� ������
		salebtn.sBtnCancel.addActionListener(this);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcancel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);
		
		//���긮����
		
		
//		����1 �̺�Ʈ ���
		//payment_1.cbP1Cooperation.addActionListener(salesInputService);
		payment_1.btnP1Apply.addActionListener(salesInputService);
		payment_1.btnP1Before.addActionListener(salesInputService);
		payment_1.btnP1Next.addActionListener(salesInputService);
		
		
//		����3 �̺�Ʈ ���
		payment_3.btnP3Before.addActionListener(salesInputService);
		payment_3.btnP3Cancel.addActionListener(salesInputService);
		payment_3.btnP3Input.addActionListener(salesInputService);
		payment_3.btnP3Next.addActionListener(salesInputService);
		payment_3.JcomboBoxPay.addActionListener(salesInputService);
		payment_3.JcomboBoxPay.addItemListener(salesInputService);
		
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
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		} else if (ob == mBtnStat) {//���
			
		} else if (ob == mBtnInven) {//������
			monitor.show(pMonitor, "Stock");
			btn.show(pFBtn, "Stockbtn");
		} /*
		  else if (ob == mBtnCalc) {//����
			//monitor.show(pMonitor, "Stat");
			//btn.show(pFBtn, "Calcbtn");
		} */
		
		//���α�ɵ�
		
		//�ǸŰ����� ��ɵ�
		else if(ob == salebtn.sBtnCancel) {//ȯ��
			dealCancel.setVisible(true);
		} else if (ob == dealCancel.btnRefund) {
			int choose = JOptionPane.showConfirmDialog(dealCancel, "ȯ�� ������ �����Ͻðڽ��ϱ�?", "ȯ��",
					JOptionPane.OK_CANCEL_OPTION);
			if (choose == 0) {
				refundProcess();
			}
			dealCancel.Sell_id.setText("");
		}	
		
		// ��� ���� ��ɵ�
				else if (ob == stockbtn.stockevery) {
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					//stockmonitor.showMon(stockdao.StockAll());
				} else if (ob == stockbtn.stockSearch) {
					stockpopupsearch.setVisible(true);
				} else if (ob == stockbtn.stockChg) {
					// �����Ұ� ������� ��ȿ���˻� getSelectedRow��ߵ�

					int tmp = stockmonitor.StockTable.convertRowIndexToModel(stockmonitor.StockTable.getSelectedRow());

					if (tmp < 0) {
						JOptionPane.showMessageDialog(this, "������ ��� �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
					String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
					String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
					String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);

					stockpopupchange.selecItem.setText("��ǰ�ڵ� : " + tmp1 + " �԰��� : " + tmp2 + " ��ǰ�� : " + tmp3 + " ���� : " + tmp4);
					stockpopupchange.setVisible(true);
				} else if (ob == stockbtn.stockIn) {
					stockpopupin.setVisible(true);
				} else if (ob == stockpopupsearch.SearchCode || ob == stockpopupsearch.sCbtn) {
					// �����ȸ �ڵ�� �˻��ϱ�
					// ��ȿ�� �˻� �ϱ� ��� products���̺� �����ϴ� �ڵ�θ� �˻�������

					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

					String scode = stockpopupsearch.SearchCode.getText();

					//stockmonitor.showMon(stockdao.StockSearchCode(scode));

					stockpopupsearch.setVisible(false);
					stockpopupsearch.SearchCode.setText("");
					stockpopupsearch.SearchName.setText("");
				} else if (ob == stockpopupsearch.searchCancel) {
					// �����ȸ ��ҹ�ư
					stockpopupsearch.setVisible(false);
					stockpopupsearch.SearchCode.setText("");
					stockpopupsearch.SearchName.setText("");
				} else if (ob == stockpopupsearch.SearchName || ob == stockpopupsearch.sNbtn) {
					// �����ȸ �̸����� �˻��ϱ�
					// ���������� �����ϴ� �̸��ΰ� �ƴѰ�
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

					String sname = stockpopupsearch.SearchName.getText();

					//stockmonitor.showMon(stockdao.StockSearchName(sname));

					stockpopupsearch.setVisible(false);
					stockpopupsearch.SearchCode.setText("");
					stockpopupsearch.SearchName.setText("");
				} else if (ob == stockpopupchange.chgCancel) {
					// ������ ��ҹ�ư
					stockpopupchange.setVisible(false);
					stockpopupchange.ChgCountTf.setText("");
					stockpopupchange.selecItem.setText("");
				} else if (ob == stockpopupchange.chgOk || ob == stockpopupchange.ChgCountTf) {
					// ������ Ȯ�ι�ư, ���������� ������ �Ŀ� �ٽ� ������ �������� ���̺� ���� �Ѹ�
					if (stockpopupchange.ChgCountTf.getText().trim().isEmpty()
							|| stockpopupchange.ChgCountTf.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(this, "������ �Է����ּ���!", "�Է� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!isNumber(stockpopupchange.ChgCountTf.getText().trim())) {
						JOptionPane.showMessageDialog(this, "���ڸ� �Է����ּ���!", "�Է� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
					int count = Integer.parseInt(stockpopupchange.ChgCountTf.getText().trim());

					int tmp = stockmonitor.StockTable.convertRowIndexToModel(stockmonitor.StockTable.getSelectedRow());
					String productCode = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
					String inDate = (String) stockmonitor.tmodel.getValueAt(tmp, 1);

					//stockdao.StockChange(count, productCode, inDate);

					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

					//stockmonitor.showMon(stockdao.StockAll());
					stockpopupchange.ChgCountTf.setText("");
					stockpopupchange.selecItem.setText("");
					stockpopupchange.setVisible(false);
				} else if (ob == stockpopupin.inCancel) {
					// �԰�â ��ҹ�ư ���빰 �ʱ�ȭ�ϰ� ����
					stockpopupin.setVisible(false);
					stockpopupin.IndateTf.setText("");
					stockpopupin.PcodeTf.setText("");
					stockpopupin.PcntTf.setText("");
					stockpopupin.pcodeResult.setText("");
					stockpopupin.pcntResult.setText("");
					stockpopupin.indateResult.setText("");
				} else if (ob == stockpopupin.PcodeTf || ob == stockpopupin.pcodeInput) {
					// �ڵ� �Է��ϸ� �Է����ڵ� ���� ǥ��
					stockpopupin.pcodeResult.setText(stockpopupin.PcodeTf.getText());

				} else if (ob == stockpopupin.IndateTf || ob == stockpopupin.indateInput) {
					// �԰����� �Է��ϸ� �Է��ѳ�¥ ǥ��
					stockpopupin.indateResult.setText(stockpopupin.IndateTf.getText());

				} else if (ob == stockpopupin.PcntTf || ob == stockpopupin.pcntInput) {
					// ���� �Է��ϸ� �Է��� ���� ǥ��
					stockpopupin.pcntResult.setText(stockpopupin.PcntTf.getText());

				} else if (ob == stockpopupin.inOk) {
					// ���� 3������ pcodeResult, indateResult, pcntResult�� ���ڰ� �޾Ƽ� in��
					if (isNumber(stockpopupin.pcntResult.getText())) {

						if (stockpopupin.pcodeResult.getText().isEmpty() || stockpopupin.pcodeResult.getText().trim().equals("")
								|| stockpopupin.indateResult.getText().trim().isEmpty()
								|| stockpopupin.indateResult.getText().trim().equals("")
								|| stockpopupin.pcntResult.getText().trim().isEmpty()
								|| stockpopupin.pcntResult.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(this, "��ĭ�� �ֽ��ϴ�!", "��� ����", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							String pcode = stockpopupin.pcodeResult.getText().trim();
							String indt = stockpopupin.indateResult.getText().trim();
							int pcnt = Integer.parseInt(stockpopupin.pcntResult.getText().trim());
							/*

							int rr = stockdao.StockIn(pcode, indt, pcnt);
							if (rr == 0) {
								JOptionPane.showMessageDialog(this, "������ �԰�� ������ �ֽ��ϴ�!", "��� ����", JOptionPane.ERROR_MESSAGE);
								return;
							}
                            */
							stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

							//stockmonitor.showMon(stockdao.StockAll());
							stockpopupin.IndateTf.setText("");
							stockpopupin.PcodeTf.setText("");
							stockpopupin.PcntTf.setText("");
							stockpopupin.pcodeResult.setText("");
							stockpopupin.pcntResult.setText("");
							stockpopupin.indateResult.setText("");
							stockpopupin.setVisible(false);
						}

					} else {
						JOptionPane.showMessageDialog(this, "������ ���ڷ� ���ּ���!", "��� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
		
				}
		
		
		
		
		
	}
	
	private void refundProcess() {
		String sellId = null;
		sellId = dealCancel.Sell_id.getText().trim();

		if (sellId.contentEquals("")) {
			JOptionPane.showMessageDialog(dealCancel, "�ŷ� ��ȣ�� �Է����ּ���.", "ȯ�� ��ȸ ����", JOptionPane.WARNING_MESSAGE);
			return;
		}

//		receipt.setVisible(true);
//		receipt.refundDetail.append("��ҿ�����/n" + "�Ǹ��ڵ� : \t" );

		Vector<PosDto> list = new Vector<PosDto>();
		//list = salesDao.selectUpdateStock(sellId);

		System.out.println(list.toString());

		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(dealCancel, "�ŷ� ��ȣ�� �ùٸ��� �ʽ��ϴ�.", "ȯ�� ��ȸ ����", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		/*
		salesDao.updateMembership(sellId); // ����� : ����� ����Ʈ ����
		salesDao.updateMoney(sellId); // ���� update : ���� ����׸�ŭ ����
		salesDao.updateStock(list); // ��� update : ���� ������ŭ ��� ������ ���ϱ�
		salesDao.deletehistory_d(sellId);
		salesDao.deletehisotry(sellId);
		*/

		JOptionPane.showMessageDialog(dealCancel, "ȯ�� ó���� �Ϸ�Ǿ����ϴ�.", "ȯ�� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		dealCancel.dispose();
	}

	private static boolean isNumber(String str) {
		boolean flag = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			int num = str.charAt(i) - 48;
			if (num < 0 || num > 9) {
				flag = false;
				break;
			}
		}

		return flag;
	}
	
	@Override
	public void run() {
		while (true) {
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date d = new Date();
			String str = df.format(d);

			try {
				thread.sleep(1000);
				dateLabel.setText(str);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
//	         System.out.println("����ð� :: " + str);
//	         idLabel.setText();
//	         dateLabel.setText(str);
		}
		
	}

}
