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
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import db.PosUse;
import sale.DealCancel;
import sale.Payment_1;
import sale.Payment_3;
import sale.Payment_4;
import sale.SaleBtn;
import sale.SalesInputService;
import sale.ViewSalesInput;
import stat.ViewStatButtons;
import stat.ViewStatDay;
import stat.ViewStatMonth;
import stat.ViewStatProduct;
import stat.ViewStatYear;
import stock.StockBtn;
import stock.StockMonitor;
import stock.StockPopupChange;
import stock.StockPopupIn;
import stock.StockPopupSearch;
import calc.CalcBtn;
import calc.CalcService;
import calc.PCalc;
import calc.Recepit;
import db.Connect_DB;

import account.AccountBtn;
import account.SignUp;
import account.SignUpChange;
import account.ViewAccount;

public class MainFrame extends JFrame implements ActionListener, Runnable{// ���������� 
	
	
	
	SignUp signUp = new SignUp();//ȸ������
	SignUpChange signUpChange = new SignUpChange();//ȸ������
	StockPopupIn stockpopupin = new StockPopupIn();
	StockPopupChange stockpopupchange = new StockPopupChange();
	StockPopupSearch stockpopupsearch = new StockPopupSearch();
	StockBtn stockbtn;
	public CalcBtn calcbtn;
	AccountBtn accountbtn;
	ViewStatButtons statbtn;
	
	
	StockMonitor stockmonitor = new StockMonitor();
	public ViewAccount viewAccount = new ViewAccount();
	//public Recepit recepit = new Recepit();
	public PCalc pCalc = new PCalc();
	public Recepit recepit = new Recepit();//���꿵����
	
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
	public JButton mBtnexit;//���α׷������ư
	
	ViewStatProduct v1 = new ViewStatProduct(); // ��ǰ�� �г�
	ViewStatYear v2 = new ViewStatYear(); // ������ �г�
	ViewStatMonth v3 = new ViewStatMonth(); // ���� �г�
	ViewStatDay v4 = new ViewStatDay(); // �Ϻ� �г�
	
	
	CalcService calcService = new CalcService(this);
	public SaleBtn salebtn = new SaleBtn();
	
	
	
//	��ǰ���� ��ư ���� ���� Ȯ��
	boolean isHold = false;
//	������ ��ǰ���� PosDto��ü���� ������ ����
	
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
		statbtn = new ViewStatButtons();
		accountbtn = new AccountBtn();
		calcbtn= new CalcBtn();
		
		
		setFont(new Font("���� ���",Font.BOLD,20));
		setTitle("pos");
		//setVisible(true);
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
		pMainBtn.setBounds(1157,50,163,674);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);
		
		
		
		mBtnSale = new JButton("�Ǹ�");
		mBtnSale.setBounds(0, 0, 163, 90);
		pMainBtn.add(mBtnSale);
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		
		mBtnInven = new JButton("���");
		mBtnInven.setBounds(0, 90, 163, 90);
		pMainBtn.add(mBtnInven);
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("���� ���", Font.BOLD, 20));
		
		mBtnCalc = new JButton("����");
		mBtnCalc.setBackground(new Color(99, 166, 166));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnCalc.setBounds(0, 180, 163, 90);
		pMainBtn.add(mBtnCalc);
		
		mBtnStat = new JButton("���");
		mBtnStat.setBackground(new Color(28, 94, 94));
		mBtnStat.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(0, 270, 163, 90);
		pMainBtn.add(mBtnStat);
		
		mBtnAccount = new JButton("��������");
		mBtnAccount.setBackground(new Color(99, 166, 166));
		mBtnAccount.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setBounds(0, 360, 163, 90);
		pMainBtn.add(mBtnAccount);
		
		logout = new JButton("�α׾ƿ�");
		logout.setBounds(0, 450, 163, 90);
		pMainBtn.add(logout);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("���� ���", Font.BOLD, 20));
		
		mBtnexit = new JButton("����");
		mBtnexit.setForeground(Color.WHITE);
		mBtnexit.setFont(new Font("���� ���", Font.BOLD, 20));
		mBtnexit.setBackground(Color.RED);
		mBtnexit.setBounds(0, 539, 163, 90);
		pMainBtn.add(mBtnexit);
		
		pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1157, 552);
		contentPane.add(pMonitor);
		pMonitor.setLayout(monitor);
		
		
		//////////////////////////////////
//		������гο� ī�巹�̾ƿ��ְ� ��ȭ�� �г� ��ü�����Ͽ� �߰�
		pMonitor.add("ViewSalesInput", viewSalesInput);
		pMonitor.add(stockmonitor, "Stock");
		pMonitor.add("ViewAccount", viewAccount);
		pMonitor.add("PCalc",pCalc);

		viewAccount.setLayout(null);
		//pMonitor.add(stat,"stat");
		pMonitor.add("viewStatProduct", v1);
		pMonitor.add("viewStatYear", v2);
		pMonitor.add("viewStatMonth", v3);
		pMonitor.add("viewStatDay", v4);
		
		
		pFBtn = new JPanel();
		pFBtn.setBackground(Color.WHITE);
		pFBtn.setBounds(0, 601, 1157, 123);
		contentPane.add(pFBtn);
		pFBtn.setLayout(btn);
		
		
        //////////////////////////////////
//		��ɹ�ư�г� ī�� ����ɹ�ư �г� ��ü������ �߰�
		pFBtn.add(salebtn, "salebtn");
		pFBtn.add(stockbtn, "Stockbtn");
		pFBtn.add(statbtn, "Statbtn");
		pFBtn.add(calcbtn, "Calcbtn");
		pFBtn.add(accountbtn, "accountbtn");
		
		
		
		// ���α�ɹ�ư
		mBtnInven.addActionListener(this);
		mBtnSale.addActionListener(this);
		mBtnCalc.addActionListener(calcService);
		//mBtnCalc.addActionListener(this);
		mBtnStat.addActionListener(this);
		mBtnAccount.addActionListener(this);
		logout.addActionListener(this);
		mBtnexit.addActionListener(this);
		
		
		
		//������ɹ�ư(���� ��� ������)
		accountbtn.AccountSignUp.addActionListener(this);
		accountbtn.AccountChange.addActionListener(this);
		accountbtn.AccountDelete.addActionListener(this);
		
		
		//����-ȸ�����
		signUp.name.addActionListener(this);
		signUp.id.addActionListener(this);
		signUp.email.addActionListener(this);
		signUp.passwordField.addActionListener(this);
		signUp.passwordField_Check.addActionListener(this);
		signUp.btnIdCheckButton.addActionListener(this);
		signUp.No.addActionListener(this);
		signUp.txtIDCheck.addActionListener(this);
		signUp.Yes.addActionListener(this);
		
		//����-ȸ������
		signUpChange.name.addActionListener(this);
		//signUpChange.id.addActionListener(this);
		signUpChange.email.addActionListener(this);
		signUpChange.passwordField.addActionListener(this);
		signUpChange.passwordField_Check.addActionListener(this);
		//signUpChange.btnIdCheckButton.addActionListener(this);
		signUpChange.No.addActionListener(this);
		signUpChange.txtIDCheck.addActionListener(this);
		signUpChange.Yes.addActionListener(this);
		
		// ��� ��ɹ�ư
		stockbtn.stockSearch.addActionListener(this);
		stockbtn.stockIn.addActionListener(this);
		stockbtn.stockChg.addActionListener(this);
		stockbtn.stockevery.addActionListener(this);
		stockbtn.stockdelete.addActionListener(this);
		
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
		stockpopupin.InproductTf.addActionListener(this);
		stockpopupin.PcntTf.addActionListener(this);
		stockpopupin.PcategoryTf.addActionListener(this);
		stockpopupin.PprovideTf.addActionListener(this);
		
		stockpopupin.pcodeInput.addActionListener(this);
		stockpopupin.inproductInput.addActionListener(this);
		stockpopupin.pcntInput.addActionListener(this);
		stockpopupin.pcategoryInput.addActionListener(this);
		stockpopupin.pprovideInput.addActionListener(this);
		
		// ��� ��ɹ�ư
		statbtn.sBtnDay.addActionListener(this);
		statbtn.sBtnMonth.addActionListener(this);
		statbtn.sBtnProduct.addActionListener(this);
		statbtn.sBtnYear.addActionListener(this);
		
		
		//�Ǹ� ��� ������
		salebtn.sBtnCancel.addActionListener(salesInputService);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcancel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);
		
		//���긮����
		calcbtn.cBtnCalc.addActionListener(calcService);
		
		
		//�����ɹ�ư������
		pCalc.btnCalc_0.addActionListener(calcService);
		pCalc.btnCalc_1.addActionListener(calcService);
		pCalc.btnCalc_2.addActionListener(calcService);
		pCalc.btnCalc_3.addActionListener(calcService);
		pCalc.btnCalc_4.addActionListener(calcService);
		pCalc.btnCalc_5.addActionListener(calcService);
		pCalc.btnCalc_6.addActionListener(calcService);
		pCalc.btnCalc_7.addActionListener(calcService);
		pCalc.btnCalc_8.addActionListener(calcService);
		pCalc.btnCalc_9.addActionListener(calcService);
		pCalc.btnCalc_00.addActionListener(calcService);
		pCalc.btnCalc_del.addActionListener(calcService);
		pCalc.btnCalc_C.addActionListener(calcService);
		//pCalc.btnCalc_Apply.addActionListener(calcService);
		
		
		
		//���꿵����������
		recepit.printRec.addActionListener(calcService);
		recepit.Calc.addActionListener(calcService);
		recepit.Date_1.addActionListener(calcService);
		recepit.Admin_1.addActionListener(calcService);
		recepit.Cash_1.addActionListener(calcService);
		recepit.lbCashChec_1.addActionListener(calcService);
		recepit.lbCashRes_1.addActionListener(calcService);
		
		
//		����1 �̺�Ʈ ���
		//payment_1.cbP1Cooperation.addActionListener(salesInputService);
		payment_1.btnP1Apply.addActionListener(salesInputService);
		payment_1.btnP1Before.addActionListener(salesInputService);
		payment_1.btnP1Next.addActionListener(salesInputService);
		
		
//		����3 �̺�Ʈ ���
		//payment_3.btnP3Before.addActionListener(salesInputService);
		payment_3.btnP3Cancel.addActionListener(salesInputService);
		payment_3.btnP3Input.addActionListener(salesInputService);
		payment_3.btnP3Next.addActionListener(salesInputService);
		payment_3.JcomboBoxPay.addActionListener(salesInputService);
		payment_3.JcomboBoxPay.addItemListener(salesInputService);
		
//		���� 4 �̺�Ʈ ���

		payment_4.btnP4PrintReceipt.addActionListener(salesInputService);
		payment_4.btnP4Payment.addActionListener(salesInputService);

		dealCancel.btnRefund.addActionListener(salesInputService);

		viewSalesInput.code_input.addKeyListener(salesInputService);
		viewSalesInput.product_name_input.addKeyListener(salesInputService);
		
		
		thread = new Thread(this);
		thread.start();

	}//���������Ӹ޼ҵ� ��
	
	/*
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
	*/
	

	@Override
	public void actionPerformed(ActionEvent e) { //�̺�Ʈó�� 
		Object ob = e.getSource();
		Object obb =e.getActionCommand();
		
		//���α�ɵ�
		if(ob==mBtnSale) {//�ǸŰ���
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		} else if (ob == mBtnStat) {//���
			monitor.show(pMonitor, "viewStatProduct");
			btn.show(pFBtn, "Statbtn");
		} else if (ob == mBtnInven) {//������
			monitor.show(pMonitor, "Stock");
			btn.show(pFBtn, "Stockbtn");
			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
			stockmonitor.showMon(connect_db.StockAll());
		} else if (ob == mBtnAccount) {//��������
			
			if(connect_db.rank.equals("����")) {
				monitor.show(pMonitor, "ViewAccount");
				btn.show(pFBtn, "accountbtn");
			}
			else {
				JOptionPane.showMessageDialog(this, "���常 ������ �� �ֽ��ϴ�.", "���ٿ���", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//JOptionPane.showMessageDialog(this, "������ ��ǰ�� �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
			//return;	
		    

		} else if (ob == logout) {
			if (JOptionPane.showConfirmDialog(this, "�α׾ƿ��Ͻðڽ��ϱ�?", "�α׾ƿ�",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				Login login= new Login();
				//dispose();
				this.setVisible(false);
				login.setVisible(true);
			}
		} else if (ob == mBtnexit) {
			if (JOptionPane.showConfirmDialog(this, "���α׷��� �����Ͻðڽ��ϱ�?", "���α׷�����",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				System.exit(0);
			}
		}
		/*
		else if (ob == mBtnCalc) {//����
			monitor.show(pMonitor, "Recepit");
			btn.show(pFBtn, "Calcbtn");
		}
		*/

		
		//���α�ɵ�
		
		
		//���������� ��ɵ�
		
		else if(ob==accountbtn.AccountSignUp) {//�����߰�
			signUp.setVisible(true);
		}else if(ob==accountbtn.AccountChange) {//��������
				
			/*
			int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());

			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "������ ��� �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
			String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
			String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
			String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);
			*/
			int tmp = viewAccount.table.convertRowIndexToModel(viewAccount.table.getSelectedRow());

			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "������ ������ �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			//������ �� ����
			String tmp1 = (String) viewAccount.model.getValueAt(tmp, 0);//�̸�
			String tmp2 = (String) viewAccount.model.getValueAt(tmp, 1);//���̵�
			String tmp3 = (String) viewAccount.model.getValueAt(tmp, 2);//��й�ȣ
			String tmp4 = (String) viewAccount.model.getValueAt(tmp, 3);//�̸���
			String tmp5 = (String) viewAccount.model.getValueAt(tmp, 4);//����			
			
			//�����������̱�
			signUpChange.setVisible(true);
			//�����������ֱ�
			signUpChange.name.setText(tmp1);//�̸�
			signUpChange.txtIDCheck.setText(tmp2);//���̵�
			signUpChange.passwordField.setText(tmp3);//��й�ȣ
			signUpChange.email.setText(tmp4);//�̸���
			
		}else if(ob==accountbtn.AccountDelete) {//��������
			
			int tmp = viewAccount.table.convertRowIndexToModel(viewAccount.table.getSelectedRow());
			
			//������ �� ����
			String tmp1 = (String) viewAccount.model.getValueAt(tmp, 0);//�̸�
			String tmp2 = (String) viewAccount.model.getValueAt(tmp, 1);//���̵�
			String tmp3 = (String) viewAccount.model.getValueAt(tmp, 2);//��й�ȣ
			String tmp4 = (String) viewAccount.model.getValueAt(tmp, 3);//�̸���
			String tmp5 = (String) viewAccount.model.getValueAt(tmp, 4);//����
			
			if(tmp5.equals("����")) {
				JOptionPane.showMessageDialog(this, "������ ������ �� �����ϴ�.", "��������", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "������ ������ �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}


			/*
			System.out.println(productCode);
			int rrr;
			int rrrr =1;
			if (JOptionPane.showConfirmDialog(this, "������ ��ǰ�� �����Ͻðڽ��ϱ�?", "����Ȯ��",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				rrr = connect_db.stockdelete(productCode);//DB�����ؼ� ����
				rrrr=rrr;
			}
			
			if (rrrr == 0) {
				JOptionPane.showMessageDialog(this, "�̹� �Ǹ��ϰ� �ִ� ��ǰ�� ������ �� �����ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			*/
			int rrr;
			int rrrr =1;
			if (JOptionPane.showConfirmDialog(this, "������ ������ �����Ͻðڽ��ϱ�?", "����Ȯ��",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				rrr = connect_db.accountdelete(tmp2);//DB�����ؼ� ����
				rrrr=rrr;
			}
			
			if (rrrr == 0) {
				JOptionPane.showMessageDialog(this, "�ǸŸ� �߰ų� ������ �� ������ ������ �� �����ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
	        //������ ȭ�� ����
	        viewAccount.clearRows(viewAccount.model.getRowCount(), viewAccount.model);
	        viewAccount.showMon(connect_db.AccountAll());
			
			
			
		}
		
		else if(ob==signUp.btnIdCheckButton) {//�ߺ�Ȯ�� ��������
			
			Vector<PosUse> results;
			PosUse result = connect_db.OverlapId(signUp.id.getText().trim());
			results = new Vector<PosUse>();
			results.add(result);
			
			
			//if(result.getCheckID().trim().isEmpty() || result.getCheckID().trim().equals("")) {
			if(result.getCheckID()==null)
			{
				signUp.txtIDCheck.setText(signUp.id.getText());
			}
			else {
				JOptionPane.showMessageDialog(signUp, "�̹� ��ϵ� ���̵� �ֽ��ϴ�.", "�ߺ� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		else if(ob==signUp.No) {//����-��ҹ�ư ��������
			signUpclear();
			signUp.setVisible(false);
		}
		else if(ob==signUp.Yes) {//����-ȸ�����Թ�ư ��������
			signupProcess();//ȸ��������������
		}
		
		
		else if(ob==signUpChange.No) {//��������-��ҹ�ư ��������
			signUpclear();
			signUpChange.setVisible(false);
		}
		else if(ob==signUpChange.Yes) {//��������-ȸ��������ư ��������
			signupChangeProcess();

		}
		
		

		/*
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
		}*/	
		
		// ��� ���� ��ɵ�
				else if (ob == stockbtn.stockevery) {//
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					stockmonitor.showMon(connect_db.StockAll());
				} else if (ob == stockbtn.stockSearch) {
					stockpopupsearch.setVisible(true);
				} else if (ob == stockbtn.stockChg) {
					// �����Ұ� ������� ��ȿ���˻� getSelectedRow��ߵ�

					int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());

					if (tmp < 0) {
						JOptionPane.showMessageDialog(this, "������ ��� �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
					String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
					String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
					String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);

					stockpopupchange.selecItem.setText("��ǰ�ڵ� : " + tmp1 + " ��ǰ�� : " + tmp2 + " ���� : " + tmp3 + " ���� : " + tmp4);
					stockpopupchange.setVisible(true);
				} else if (ob == stockbtn.stockIn) {
					stockpopupin.setVisible(true);
				} else if (ob == stockpopupsearch.SearchCode || ob == stockpopupsearch.sCbtn) {
					// �����ȸ �ڵ�� �˻��ϱ�
					// ��ȿ�� �˻� �ϱ� ��� products���̺� �����ϴ� �ڵ�θ� �˻�������

					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);//ȭ�����

					String scode = stockpopupsearch.SearchCode.getText();

					stockmonitor.showMon(connect_db.StockSearchCode(scode));

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
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);//ȭ�����

					String sname = stockpopupsearch.SearchName.getText();

					stockmonitor.showMon(connect_db.StockSearchName(sname));

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
					int count = Integer.parseInt(stockpopupchange.ChgCountTf.getText().trim());//������ ���� ����.

					int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());
					String productCode = (String) stockmonitor.tmodel.getValueAt(tmp, 0);

					connect_db.StockChange(count, productCode);

					//������ ȭ�� ����
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					stockmonitor.showMon(connect_db.StockAll());
					
					stockpopupchange.ChgCountTf.setText("");
					stockpopupchange.selecItem.setText("");
					stockpopupchange.setVisible(false);
				} else if (ob == stockpopupin.inCancel) {
					// �԰�â ��ҹ�ư ���빰 �ʱ�ȭ�ϰ� ����
					stockpopupin.setVisible(false);
					stockpopupin.InproductTf.setText("");
					stockpopupin.PcodeTf.setText("");
					stockpopupin.PcntTf.setText("");
					stockpopupin.PcategoryTf.setText("");
					stockpopupin.PprovideTf.setText("");
					
					stockpopupin.pcodeResult.setText("");
					stockpopupin.pcntResult.setText("");
					stockpopupin.inproductResult.setText("");
					stockpopupin.pcategoryResult.setText("");
					stockpopupin.pprovideResult.setText("");
					
				} else if (ob == stockpopupin.PcodeTf || ob == stockpopupin.pcodeInput) {
					// �ڵ� �Է��ϸ� �Է����ڵ� ���� ǥ��
					stockpopupin.pcodeResult.setText(stockpopupin.PcodeTf.getText());

				} else if (ob == stockpopupin.InproductTf || ob == stockpopupin.inproductInput) {
					// ��ǰ �Է��ϸ� �Է��ѻ�ǰ ǥ��
					stockpopupin.inproductResult.setText(stockpopupin.InproductTf.getText());

				} else if (ob == stockpopupin.PcntTf || ob == stockpopupin.pcntInput) {
					// ���� �Է��ϸ� �Է��� ���� ǥ��
					stockpopupin.pcntResult.setText(stockpopupin.PcntTf.getText());

				} else if (ob == stockpopupin.PcategoryTf || ob == stockpopupin.pcategoryInput) {
					// ���� �Է��ϸ� �Է��� ���� ǥ��
					stockpopupin.pcategoryResult.setText(stockpopupin.PcategoryTf.getText());

				} else if (ob == stockpopupin.PprovideTf || ob == stockpopupin.pprovideInput) {
					// ������ �Է��ϸ� �Է��� ������ ǥ��
					stockpopupin.pprovideResult.setText(stockpopupin.PprovideTf.getText());

				} else if (ob == stockpopupin.inOk) {
					// ���� 5������ pcodeResult, inproductResult, pcntResult, pcategoryResult, pprovideResult�� ���ڰ� �޾Ƽ� in��
					if (isNumber(stockpopupin.pcntResult.getText())) {

						if (stockpopupin.pcodeResult.getText().isEmpty() || stockpopupin.pcodeResult.getText().trim().equals("")
								|| stockpopupin.inproductResult.getText().trim().isEmpty()
								|| stockpopupin.inproductResult.getText().trim().equals("")
								|| stockpopupin.pcntResult.getText().trim().isEmpty()
								|| stockpopupin.pcntResult.getText().trim().equals("")
								|| stockpopupin.pcategoryResult.getText().trim().isEmpty()
								|| stockpopupin.pcategoryResult.getText().trim().equals("")
								|| stockpopupin.pprovideResult.getText().trim().isEmpty()
								|| stockpopupin.pprovideResult.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(this, "��ĭ�� �ֽ��ϴ�!", "��� ����", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							String pcode = stockpopupin.pcodeResult.getText().trim();//��ǰ�ڵ�
							String indt = stockpopupin.inproductResult.getText().trim();//��ǰ��
							int pcnt = Integer.parseInt(stockpopupin.pcntResult.getText().trim());//����
							String pcategory = stockpopupin.pcategoryResult.getText().trim();//����
							String pprovide = stockpopupin.pprovideResult.getText().trim();//������
							
							

							boolean namecheck = connect_db.stockname(indt);
							if(namecheck==true) {
								JOptionPane.showMessageDialog(this, "???�̹� ��ϵ� ��ǰ�� �ֽ��ϴ�.", "��� ����", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							
							
							int rr=connect_db.StockIn(pcode,indt,pcnt,pcategory,pprovide);//mysql����(��ǰ���̺� ���ֱ�)
							if (rr == 0) {
								JOptionPane.showMessageDialog(this, "�̸���ħ�̹� ��ϵ� ��ǰ�� �ֽ��ϴ�.", "��� ����", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							

							
							/*
							if(connect_db.stockname(indt)==true) {
								JOptionPane.showMessageDialog(this, "???�̹� ��ϵ� ��ǰ�� �ֽ��ϴ�.", "��� ����", JOptionPane.ERROR_MESSAGE);
								return;
							}
							*/
							
							
							
							
							
							
							
							
							
							
							
							
							/*

							int rr = stockdao.StockIn(pcode, indt, pcnt);
							if (rr == 0) {
								JOptionPane.showMessageDialog(this, "������ �԰�� ������ �ֽ��ϴ�!", "��� ����", JOptionPane.ERROR_MESSAGE);
								return;
							}
                            */
							stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
							stockmonitor.showMon(connect_db.StockAll());
							
							stockpopupin.InproductTf.setText("");
							stockpopupin.PcodeTf.setText("");
							stockpopupin.PcntTf.setText("");
							stockpopupin.PcategoryTf.setText("");
							stockpopupin.PprovideTf.setText("");
							
							stockpopupin.pcodeResult.setText("");
							stockpopupin.pcntResult.setText("");
							stockpopupin.inproductResult.setText("");
							stockpopupin.pcategoryResult.setText("");
							stockpopupin.pprovideResult.setText("");
							stockpopupin.setVisible(false);
						}

					} else {
						JOptionPane.showMessageDialog(this, "������ ���ڷ� ���ּ���!", "��� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
		
				} else if(ob == stockbtn.stockdelete) {
					
					int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());

					if (tmp < 0) {
						JOptionPane.showMessageDialog(this, "������ ��ǰ�� �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					String productCode = (String) stockmonitor.tmodel.getValueAt(tmp, 0);//��ǰ�ڵ�
					String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);//�̸�
					String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);//����
					String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);//����
					
					System.out.println(productCode);
					int rrr;
					int rrrr =1;
					if (JOptionPane.showConfirmDialog(this, "������ ��ǰ�� �����Ͻðڽ��ϱ�?", "����Ȯ��",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						rrr = connect_db.stockdelete(productCode);//DB�����ؼ� ����
						rrrr=rrr;
					}
					
					if (rrrr == 0) {
						JOptionPane.showMessageDialog(this, "�̹� �Ǹ��ϰ� �ִ� ��ǰ�� ������ �� �����ϴ�.", "���� ����", JOptionPane.ERROR_MESSAGE);
						return;
					}
					

					//������ ȭ�� ����
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					stockmonitor.showMon(connect_db.StockAll());

				}
		
		        //��� ����� ��ư��
				else if (ob == statbtn.sBtnProduct) {
					monitor.show(pMonitor, "viewStatProduct");
				} else if (ob == statbtn.sBtnYear) {
					monitor.show(pMonitor, "viewStatYear");
				} else if (ob == statbtn.sBtnMonth) {
					monitor.show(pMonitor, "viewStatMonth");
				} else if (ob == statbtn.sBtnDay) {
					monitor.show(pMonitor, "viewStatDay");
				}
		
		
	}
	/*
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
		
		
		//salesDao.updateMembership(sellId); // ����� : ����� ����Ʈ ����
		//salesDao.updateMoney(sellId); // ���� update : ���� ����׸�ŭ ����
		//salesDao.updateStock(list); // ��� update : ���� ������ŭ ��� ������ ���ϱ�
		//salesDao.deletehistory_d(sellId);
		//salesDao.deletehisotry(sellId);
		

		JOptionPane.showMessageDialog(dealCancel, "ȯ�� ó���� �Ϸ�Ǿ����ϴ�.", "ȯ�� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
		dealCancel.dispose();
	}*/
	
	// <���̺� ���� ��� ����> �޼ҵ�
	public static void tableCellCenter(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // ����Ʈ���̺��������� ����
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // �������� ���������� CENTER��

		TableColumnModel tcm = t.getColumnModel(); // ������ ���̺��� �÷����� ������

		// ��ü ���� ����
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
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
	
	
	public void signupProcess(){//ȸ����������
		/*
		|| stockpopupin.inproductResult.getText().trim().isEmpty()
		|| stockpopupin.inproductResult.getText().trim().equals("")
		*/
		if (signUp.name.getText().trim().equals("") || /*signUp.id.getText().trim().equals("") || */
			signUp.email.getText().trim().equals("") || String.valueOf(signUp.passwordField.getPassword()).trim().equals("") || 
			String.valueOf(signUp.passwordField_Check.getPassword()).trim().equals("") ||
			signUp.name.getText().trim().isEmpty() || /*signUp.id.getText().trim().isEmpty() || */
			signUp.email.getText().trim().isEmpty() || String.valueOf(signUp.passwordField.getPassword()).trim().isEmpty() || 
			String.valueOf(signUp.passwordField_Check.getPassword()).trim().isEmpty()
			) {
			JOptionPane.showMessageDialog(signUp, "��ĭ�� �Է����ּ���.", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			return;
		    }
		else if(signUp.txtIDCheck.getText().trim().equals("") || signUp.txtIDCheck.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(signUp, "�ߺ�Ȯ�� ��ư�� �����ּ���.", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(String.valueOf(signUp.passwordField.getPassword()).trim().equals(String.valueOf(signUp.passwordField_Check.getPassword()).trim() )==false) {
			JOptionPane.showMessageDialog(signUp, "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else {
			
			connect_db.AccountIn(signUp.txtIDCheck.getText().trim(), String.valueOf(signUp.passwordField.getPassword()).trim(), signUp.name.getText().trim(), signUp.email.getText().trim() , "����");
			
			//������ ȭ�� ����
			viewAccount.clearRows(viewAccount.model.getRowCount(), viewAccount.model);
			viewAccount.showMon(connect_db.AccountAll());
			
			/*
			//������ ȭ�� ����
			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
			stockmonitor.showMon(connect_db.StockAll());
			*/

			signUpclear();
			signUp.setVisible(false);
			
			
			
		}
		
		
		
		
	}
	
	public void signupChangeProcess() {
		
		    if (signUpChange.name.getText().trim().equals("") || /*signUpChange.id.getText().trim().equals("") || */
				signUpChange.email.getText().trim().equals("") || String.valueOf(signUpChange.passwordField.getPassword()).trim().equals("") || 
				String.valueOf(signUpChange.passwordField_Check.getPassword()).trim().equals("") ||
				signUpChange.name.getText().trim().isEmpty() || /*signUpChange.id.getText().trim().isEmpty() || */
				signUpChange.email.getText().trim().isEmpty() || String.valueOf(signUpChange.passwordField.getPassword()).trim().isEmpty() || 
				String.valueOf(signUpChange.passwordField_Check.getPassword()).trim().isEmpty()
				) {
				JOptionPane.showMessageDialog(signUpChange, "��ĭ�� �Է����ּ���.", "�Է� ����", JOptionPane.ERROR_MESSAGE);
				return;
			    }
			else if(String.valueOf(signUpChange.passwordField.getPassword()).trim().equals(String.valueOf(signUpChange.passwordField_Check.getPassword()).trim() )==false) {
				JOptionPane.showMessageDialog(signUpChange, "��й�ȣ�� ��й�ȣ Ȯ���� ��ġ���� �ʽ��ϴ�.", "�Է� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				String ID=signUpChange.txtIDCheck.getText().trim();
				String PW=String.valueOf(signUpChange.passwordField.getPassword()).trim();
				String user_name=signUpChange.name.getText().trim();
				String user_email=signUpChange.email.getText().trim();
				//String.valueOf(signUp.passwordField.getPassword()).trim()
				connect_db.dbsignUpChange(ID, PW, user_name, user_email);
				
				
		
		        //������ ȭ�� ����
		        viewAccount.clearRows(viewAccount.model.getRowCount(), viewAccount.model);
		        viewAccount.showMon(connect_db.AccountAll());
		
		        signUpchangeclear();
		        signUpChange.setVisible(false);
			}
	}
	

	public void signUpclear() {//ȸ������âŬ����
		signUp.name.setText(null);
		signUp.id.setText(null);
		signUp.email.setText(null);
		signUp.txtIDCheck.setText(null);
		signUp.passwordField.setText(null);
		signUp.passwordField_Check.setText(null);
	}
	
	public void signUpchangeclear() {
		signUpChange.name.setText(null);
		signUpChange.email.setText(null);
		signUpChange.txtIDCheck.setText(null);
		signUpChange.passwordField.setText(null);
		signUpChange.passwordField_Check.setText(null);
	}
}
