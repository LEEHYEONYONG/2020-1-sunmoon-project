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

public class MainFrame extends JFrame implements ActionListener, Runnable{// 메인프레임 
	
	
	
	SignUp signUp = new SignUp();//회원가입
	SignUpChange signUpChange = new SignUpChange();//회원수정
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
	public Recepit recepit = new Recepit();//정산영수증
	
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
	
	public JButton mBtnSale;//판매버튼
	public JButton mBtnInven;//재고버튼
	public JButton mBtnCalc;//정산버튼
	public JButton mBtnStat;//통계버튼
	public JButton mBtnAccount;//계정버튼
	public JPanel pFBtn;
	public JButton mBtnexit;//프로그램종료버튼
	
	ViewStatProduct v1 = new ViewStatProduct(); // 상품별 패널
	ViewStatYear v2 = new ViewStatYear(); // 연도별 패널
	ViewStatMonth v3 = new ViewStatMonth(); // 월별 패널
	ViewStatDay v4 = new ViewStatDay(); // 일별 패널
	
	
	CalcService calcService = new CalcService(this);
	public SaleBtn salebtn = new SaleBtn();
	
	
	
//	상품보류 버튼 눌림 여부 확인
	boolean isHold = false;
//	보류한 상품들의 PosDto객체들을 저장할 벡터
	
	public ViewSalesInput viewSalesInput = new ViewSalesInput();
	public SalesInputService salesInputService = new SalesInputService(this);
	
	//데이터베이스 필드 추가
    public Connect_DB connect_db = new Connect_DB();
	
	// 결제및 결제취소창 프레임
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
		
		
		setFont(new Font("맑은 고딕",Font.BOLD,20));
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
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		titleLabel.setBounds(540, 10, 241, 31);
		pStatusBar.add(titleLabel);
		
		dateLabel = new JLabel();
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);
		
		pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255,255,255));
		pMainBtn.setBounds(1157,50,163,674);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);
		
		
		
		mBtnSale = new JButton("판매");
		mBtnSale.setBounds(0, 0, 163, 90);
		pMainBtn.add(mBtnSale);
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		
		mBtnInven = new JButton("재고");
		mBtnInven.setBounds(0, 90, 163, 90);
		pMainBtn.add(mBtnInven);
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		mBtnCalc = new JButton("정산");
		mBtnCalc.setBackground(new Color(99, 166, 166));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnCalc.setBounds(0, 180, 163, 90);
		pMainBtn.add(mBtnCalc);
		
		mBtnStat = new JButton("통계");
		mBtnStat.setBackground(new Color(28, 94, 94));
		mBtnStat.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(0, 270, 163, 90);
		pMainBtn.add(mBtnStat);
		
		mBtnAccount = new JButton("계정관리");
		mBtnAccount.setBackground(new Color(99, 166, 166));
		mBtnAccount.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setBounds(0, 360, 163, 90);
		pMainBtn.add(mBtnAccount);
		
		logout = new JButton("로그아웃");
		logout.setBounds(0, 450, 163, 90);
		pMainBtn.add(logout);
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		mBtnexit = new JButton("종료");
		mBtnexit.setForeground(Color.WHITE);
		mBtnexit.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnexit.setBackground(Color.RED);
		mBtnexit.setBounds(0, 539, 163, 90);
		pMainBtn.add(mBtnexit);
		
		pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1157, 552);
		contentPane.add(pMonitor);
		pMonitor.setLayout(monitor);
		
		
		//////////////////////////////////
//		모니터패널에 카드레이아웃주고 각화면 패널 객체생성하여 추가
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
//		기능버튼패널 카드 각기능버튼 패널 객체생성후 추가
		pFBtn.add(salebtn, "salebtn");
		pFBtn.add(stockbtn, "Stockbtn");
		pFBtn.add(statbtn, "Statbtn");
		pFBtn.add(calcbtn, "Calcbtn");
		pFBtn.add(accountbtn, "accountbtn");
		
		
		
		// 메인기능버튼
		mBtnInven.addActionListener(this);
		mBtnSale.addActionListener(this);
		mBtnCalc.addActionListener(calcService);
		//mBtnCalc.addActionListener(this);
		mBtnStat.addActionListener(this);
		mBtnAccount.addActionListener(this);
		logout.addActionListener(this);
		mBtnexit.addActionListener(this);
		
		
		
		//계정기능버튼(계정 기능 리스너)
		accountbtn.AccountSignUp.addActionListener(this);
		accountbtn.AccountChange.addActionListener(this);
		accountbtn.AccountDelete.addActionListener(this);
		
		
		//계정-회원등록
		signUp.name.addActionListener(this);
		signUp.id.addActionListener(this);
		signUp.email.addActionListener(this);
		signUp.passwordField.addActionListener(this);
		signUp.passwordField_Check.addActionListener(this);
		signUp.btnIdCheckButton.addActionListener(this);
		signUp.No.addActionListener(this);
		signUp.txtIDCheck.addActionListener(this);
		signUp.Yes.addActionListener(this);
		
		//계정-회원수정
		signUpChange.name.addActionListener(this);
		//signUpChange.id.addActionListener(this);
		signUpChange.email.addActionListener(this);
		signUpChange.passwordField.addActionListener(this);
		signUpChange.passwordField_Check.addActionListener(this);
		//signUpChange.btnIdCheckButton.addActionListener(this);
		signUpChange.No.addActionListener(this);
		signUpChange.txtIDCheck.addActionListener(this);
		signUpChange.Yes.addActionListener(this);
		
		// 재고 기능버튼
		stockbtn.stockSearch.addActionListener(this);
		stockbtn.stockIn.addActionListener(this);
		stockbtn.stockChg.addActionListener(this);
		stockbtn.stockevery.addActionListener(this);
		stockbtn.stockdelete.addActionListener(this);
		
		// 재고-검색 팝업 액션리스너
		stockpopupsearch.sCbtn.addActionListener(this);
		stockpopupsearch.SearchCode.addActionListener(this);
		stockpopupsearch.searchCancel.addActionListener(this);
		stockpopupsearch.SearchName.addActionListener(this);
		stockpopupsearch.sNbtn.addActionListener(this);

		// 재고-수정 팝업 액션리스너
		stockpopupchange.chgOk.addActionListener(this);
		stockpopupchange.chgCancel.addActionListener(this);
		stockpopupchange.ChgCountTf.addActionListener(this);
		
		// 재고-삽입 팝업 액션리스너 ( 입고버튼)
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
		
		// 통계 기능버튼
		statbtn.sBtnDay.addActionListener(this);
		statbtn.sBtnMonth.addActionListener(this);
		statbtn.sBtnProduct.addActionListener(this);
		statbtn.sBtnYear.addActionListener(this);
		
		
		//판매 기능 리스너
		salebtn.sBtnCancel.addActionListener(salesInputService);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcancel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);
		
		//정산리스너
		calcbtn.cBtnCalc.addActionListener(calcService);
		
		
		//정산기능버튼리스너
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
		
		
		
		//정산영수증리스너
		recepit.printRec.addActionListener(calcService);
		recepit.Calc.addActionListener(calcService);
		recepit.Date_1.addActionListener(calcService);
		recepit.Admin_1.addActionListener(calcService);
		recepit.Cash_1.addActionListener(calcService);
		recepit.lbCashChec_1.addActionListener(calcService);
		recepit.lbCashRes_1.addActionListener(calcService);
		
		
//		결제1 이벤트 등록
		//payment_1.cbP1Cooperation.addActionListener(salesInputService);
		payment_1.btnP1Apply.addActionListener(salesInputService);
		payment_1.btnP1Before.addActionListener(salesInputService);
		payment_1.btnP1Next.addActionListener(salesInputService);
		
		
//		결제3 이벤트 등록
		//payment_3.btnP3Before.addActionListener(salesInputService);
		payment_3.btnP3Cancel.addActionListener(salesInputService);
		payment_3.btnP3Input.addActionListener(salesInputService);
		payment_3.btnP3Next.addActionListener(salesInputService);
		payment_3.JcomboBoxPay.addActionListener(salesInputService);
		payment_3.JcomboBoxPay.addItemListener(salesInputService);
		
//		결제 4 이벤트 등록

		payment_4.btnP4PrintReceipt.addActionListener(salesInputService);
		payment_4.btnP4Payment.addActionListener(salesInputService);

		dealCancel.btnRefund.addActionListener(salesInputService);

		viewSalesInput.code_input.addKeyListener(salesInputService);
		viewSalesInput.product_name_input.addKeyListener(salesInputService);
		
		
		thread = new Thread(this);
		thread.start();

	}//메인프레임메소드 끝
	
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
	public void actionPerformed(ActionEvent e) { //이벤트처리 
		Object ob = e.getSource();
		Object obb =e.getActionCommand();
		
		//메인기능들
		if(ob==mBtnSale) {//판매관리
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		} else if (ob == mBtnStat) {//통계
			monitor.show(pMonitor, "viewStatProduct");
			btn.show(pFBtn, "Statbtn");
		} else if (ob == mBtnInven) {//재고관리
			monitor.show(pMonitor, "Stock");
			btn.show(pFBtn, "Stockbtn");
			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
			stockmonitor.showMon(connect_db.StockAll());
		} else if (ob == mBtnAccount) {//계정관리
			
			if(connect_db.rank.equals("점장")) {
				monitor.show(pMonitor, "ViewAccount");
				btn.show(pFBtn, "accountbtn");
			}
			else {
				JOptionPane.showMessageDialog(this, "점장만 접근할 수 있습니다.", "접근오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			//JOptionPane.showMessageDialog(this, "삭제할 상품를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
			//return;	
		    

		} else if (ob == logout) {
			if (JOptionPane.showConfirmDialog(this, "로그아웃하시겠습니까?", "로그아웃",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				Login login= new Login();
				//dispose();
				this.setVisible(false);
				login.setVisible(true);
			}
		} else if (ob == mBtnexit) {
			if (JOptionPane.showConfirmDialog(this, "프로그램을 종료하시겠습니까?", "프로그램종료",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				System.exit(0);
			}
		}
		/*
		else if (ob == mBtnCalc) {//정산
			monitor.show(pMonitor, "Recepit");
			btn.show(pFBtn, "Calcbtn");
		}
		*/

		
		//세부기능들
		
		
		//계정관리의 기능들
		
		else if(ob==accountbtn.AccountSignUp) {//계정추가
			signUp.setVisible(true);
		}else if(ob==accountbtn.AccountChange) {//계정수정
				
			/*
			int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());

			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "수정할 재고를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
			String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
			String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
			String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);
			*/
			int tmp = viewAccount.table.convertRowIndexToModel(viewAccount.table.getSelectedRow());

			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "수정할 계정를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			//선택한 값 추출
			String tmp1 = (String) viewAccount.model.getValueAt(tmp, 0);//이름
			String tmp2 = (String) viewAccount.model.getValueAt(tmp, 1);//아이디
			String tmp3 = (String) viewAccount.model.getValueAt(tmp, 2);//비밀번호
			String tmp4 = (String) viewAccount.model.getValueAt(tmp, 3);//이메일
			String tmp5 = (String) viewAccount.model.getValueAt(tmp, 4);//직급			
			
			//계정수정보이기
			signUpChange.setVisible(true);
			//계정수정값넣기
			signUpChange.name.setText(tmp1);//이름
			signUpChange.txtIDCheck.setText(tmp2);//아이디
			signUpChange.passwordField.setText(tmp3);//비밀번호
			signUpChange.email.setText(tmp4);//이메일
			
		}else if(ob==accountbtn.AccountDelete) {//계정삭제
			
			int tmp = viewAccount.table.convertRowIndexToModel(viewAccount.table.getSelectedRow());
			
			//선택한 값 추출
			String tmp1 = (String) viewAccount.model.getValueAt(tmp, 0);//이름
			String tmp2 = (String) viewAccount.model.getValueAt(tmp, 1);//아이디
			String tmp3 = (String) viewAccount.model.getValueAt(tmp, 2);//비밀번호
			String tmp4 = (String) viewAccount.model.getValueAt(tmp, 3);//이메일
			String tmp5 = (String) viewAccount.model.getValueAt(tmp, 4);//직급
			
			if(tmp5.equals("점장")) {
				JOptionPane.showMessageDialog(this, "점장은 삭제할 수 없습니다.", "삭제오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "수정할 계정를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}


			/*
			System.out.println(productCode);
			int rrr;
			int rrrr =1;
			if (JOptionPane.showConfirmDialog(this, "선택한 상품을 삭제하시겠습니까?", "삭제확인",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				rrr = connect_db.stockdelete(productCode);//DB연동해서 삭제
				rrrr=rrr;
			}
			
			if (rrrr == 0) {
				JOptionPane.showMessageDialog(this, "이미 판매하고 있는 상품은 삭제할 수 없습니다.", "삭제 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			*/
			int rrr;
			int rrrr =1;
			if (JOptionPane.showConfirmDialog(this, "선택한 계정을 삭제하시겠습니까?", "삭제확인",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
				rrr = connect_db.accountdelete(tmp2);//DB연동해서 삭제
				rrrr=rrr;
			}
			
			if (rrrr == 0) {
				JOptionPane.showMessageDialog(this, "판매를 했거나 정산을 한 계정은 삭제할 수 없습니다.", "삭제 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
	        //변경후 화면 띄우기
	        viewAccount.clearRows(viewAccount.model.getRowCount(), viewAccount.model);
	        viewAccount.showMon(connect_db.AccountAll());
			
			
			
		}
		
		else if(ob==signUp.btnIdCheckButton) {//중복확인 눌렀을때
			
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
				JOptionPane.showMessageDialog(signUp, "이미 등록된 아이디가 있습니다.", "중복 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		else if(ob==signUp.No) {//계정-취소버튼 눌렀을때
			signUpclear();
			signUp.setVisible(false);
		}
		else if(ob==signUp.Yes) {//계정-회원가입버튼 눌렀을때
			signupProcess();//회원가입절차진행
		}
		
		
		else if(ob==signUpChange.No) {//계정수정-취소버튼 눌렀을때
			signUpclear();
			signUpChange.setVisible(false);
		}
		else if(ob==signUpChange.Yes) {//계정수정-회원수정버튼 눌렀을때
			signupChangeProcess();

		}
		
		

		/*
		//판매관리의 기능들
		else if(ob == salebtn.sBtnCancel) {//환불
			dealCancel.setVisible(true);
		} else if (ob == dealCancel.btnRefund) {
			int choose = JOptionPane.showConfirmDialog(dealCancel, "환불 절차를 진행하시겠습니까?", "환불",
					JOptionPane.OK_CANCEL_OPTION);
			if (choose == 0) {
				refundProcess();
			}
			dealCancel.Sell_id.setText("");
		}*/	
		
		// 재고 탭의 기능들
				else if (ob == stockbtn.stockevery) {//
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					stockmonitor.showMon(connect_db.StockAll());
				} else if (ob == stockbtn.stockSearch) {
					stockpopupsearch.setVisible(true);
				} else if (ob == stockbtn.stockChg) {
					// 수정할거 골랐는지 유효성검사 getSelectedRow써야됨

					int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());

					if (tmp < 0) {
						JOptionPane.showMessageDialog(this, "수정할 재고를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
					String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
					String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
					String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);

					stockpopupchange.selecItem.setText("상품코드 : " + tmp1 + " 상품명 : " + tmp2 + " 수량 : " + tmp3 + " 가격 : " + tmp4);
					stockpopupchange.setVisible(true);
				} else if (ob == stockbtn.stockIn) {
					stockpopupin.setVisible(true);
				} else if (ob == stockpopupsearch.SearchCode || ob == stockpopupsearch.sCbtn) {
					// 재고조회 코드로 검색하기
					// 유효성 검사 하기 곤란 products테이블에 존재하는 코드로만 검색가능함

					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);//화면비우기

					String scode = stockpopupsearch.SearchCode.getText();

					stockmonitor.showMon(connect_db.StockSearchCode(scode));

					stockpopupsearch.setVisible(false);
					stockpopupsearch.SearchCode.setText("");
					stockpopupsearch.SearchName.setText("");
				} else if (ob == stockpopupsearch.searchCancel) {
					// 재고조회 취소버튼
					stockpopupsearch.setVisible(false);
					stockpopupsearch.SearchCode.setText("");
					stockpopupsearch.SearchName.setText("");
				} else if (ob == stockpopupsearch.SearchName || ob == stockpopupsearch.sNbtn) {
					// 재고조회 이름으로 검색하기
					// 마찬가지로 존재하는 이름인가 아닌가
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);//화면비우기

					String sname = stockpopupsearch.SearchName.getText();

					stockmonitor.showMon(connect_db.StockSearchName(sname));

					stockpopupsearch.setVisible(false);
					stockpopupsearch.SearchCode.setText("");
					stockpopupsearch.SearchName.setText("");
				} else if (ob == stockpopupchange.chgCancel) {
					// 재고수정 취소버튼
					stockpopupchange.setVisible(false);
					stockpopupchange.ChgCountTf.setText("");
					stockpopupchange.selecItem.setText("");
				} else if (ob == stockpopupchange.chgOk || ob == stockpopupchange.ChgCountTf) {
					// 재고수정 확인버튼, 쿼리문으로 수정한 후에 다시 수정된 버젼으로 테이블 새로 뿌림
					if (stockpopupchange.ChgCountTf.getText().trim().isEmpty()
							|| stockpopupchange.ChgCountTf.getText().trim().equals("")) {
						JOptionPane.showMessageDialog(this, "수량을 입력해주세요!", "입력 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (!isNumber(stockpopupchange.ChgCountTf.getText().trim())) {
						JOptionPane.showMessageDialog(this, "숫자만 입력해주세요!", "입력 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
					int count = Integer.parseInt(stockpopupchange.ChgCountTf.getText().trim());//변경할 숫자 저장.

					int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());
					String productCode = (String) stockmonitor.tmodel.getValueAt(tmp, 0);

					connect_db.StockChange(count, productCode);

					//변경후 화면 띄우기
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					stockmonitor.showMon(connect_db.StockAll());
					
					stockpopupchange.ChgCountTf.setText("");
					stockpopupchange.selecItem.setText("");
					stockpopupchange.setVisible(false);
				} else if (ob == stockpopupin.inCancel) {
					// 입고창 취소버튼 내용물 초기화하고 끄기
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
					// 코드 입력하면 입력한코드 옆에 표시
					stockpopupin.pcodeResult.setText(stockpopupin.PcodeTf.getText());

				} else if (ob == stockpopupin.InproductTf || ob == stockpopupin.inproductInput) {
					// 상품 입력하면 입력한상품 표시
					stockpopupin.inproductResult.setText(stockpopupin.InproductTf.getText());

				} else if (ob == stockpopupin.PcntTf || ob == stockpopupin.pcntInput) {
					// 수량 입력하면 입력한 수량 표시
					stockpopupin.pcntResult.setText(stockpopupin.PcntTf.getText());

				} else if (ob == stockpopupin.PcategoryTf || ob == stockpopupin.pcategoryInput) {
					// 종류 입력하면 입력한 종류 표시
					stockpopupin.pcategoryResult.setText(stockpopupin.PcategoryTf.getText());

				} else if (ob == stockpopupin.PprovideTf || ob == stockpopupin.pprovideInput) {
					// 제조사 입력하면 입력한 제조사 표시
					stockpopupin.pprovideResult.setText(stockpopupin.PprovideTf.getText());

				} else if (ob == stockpopupin.inOk) {
					// 위의 5개에서 pcodeResult, inproductResult, pcntResult, pcategoryResult, pprovideResult로 인자값 받아서 in함
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
							JOptionPane.showMessageDialog(this, "빈칸이 있습니다!", "등록 오류", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							String pcode = stockpopupin.pcodeResult.getText().trim();//상품코드
							String indt = stockpopupin.inproductResult.getText().trim();//상품명
							int pcnt = Integer.parseInt(stockpopupin.pcntResult.getText().trim());//가격
							String pcategory = stockpopupin.pcategoryResult.getText().trim();//종류
							String pprovide = stockpopupin.pprovideResult.getText().trim();//제조사
							
							

							boolean namecheck = connect_db.stockname(indt);
							if(namecheck==true) {
								JOptionPane.showMessageDialog(this, "???이미 등록된 상품이 있습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							
							
							int rr=connect_db.StockIn(pcode,indt,pcnt,pcategory,pprovide);//mysql연동(상품테이블에 값넣기)
							if (rr == 0) {
								JOptionPane.showMessageDialog(this, "이름겹침이미 등록된 상품이 있습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							

							
							/*
							if(connect_db.stockname(indt)==true) {
								JOptionPane.showMessageDialog(this, "???이미 등록된 상품이 있습니다.", "등록 오류", JOptionPane.ERROR_MESSAGE);
								return;
							}
							*/
							
							
							
							
							
							
							
							
							
							
							
							
							/*

							int rr = stockdao.StockIn(pcode, indt, pcnt);
							if (rr == 0) {
								JOptionPane.showMessageDialog(this, "같은날 입고된 내역이 있습니다!", "등록 오류", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(this, "가격은 숫자로 써주세요!", "등록 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
		
				} else if(ob == stockbtn.stockdelete) {
					
					int tmp = stockmonitor.table.convertRowIndexToModel(stockmonitor.table.getSelectedRow());

					if (tmp < 0) {
						JOptionPane.showMessageDialog(this, "삭제할 상품를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					String productCode = (String) stockmonitor.tmodel.getValueAt(tmp, 0);//상품코드
					String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);//이름
					String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);//수량
					String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);//가격
					
					System.out.println(productCode);
					int rrr;
					int rrrr =1;
					if (JOptionPane.showConfirmDialog(this, "선택한 상품을 삭제하시겠습니까?", "삭제확인",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
						rrr = connect_db.stockdelete(productCode);//DB연동해서 삭제
						rrrr=rrr;
					}
					
					if (rrrr == 0) {
						JOptionPane.showMessageDialog(this, "이미 판매하고 있는 상품은 삭제할 수 없습니다.", "삭제 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
					

					//변경후 화면 띄우기
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					stockmonitor.showMon(connect_db.StockAll());

				}
		
		        //통계 기능의 버튼들
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
			JOptionPane.showMessageDialog(dealCancel, "거래 번호를 입력해주세요.", "환불 조회 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

//		receipt.setVisible(true);
//		receipt.refundDetail.append("취소영수증/n" + "판매코드 : \t" );

		Vector<PosDto> list = new Vector<PosDto>();
		//list = salesDao.selectUpdateStock(sellId);

		System.out.println(list.toString());

		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(dealCancel, "거래 번호가 올바르지 않습니다.", "환불 조회 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		
		//salesDao.updateMembership(sellId); // 멤버십 : 멤버십 포인트 빼기
		//salesDao.updateMoney(sellId); // 정산 update : 현금 결재액만큼 빼기
		//salesDao.updateStock(list); // 재고 update : 구매 수량만큼 재고 수량에 더하기
		//salesDao.deletehistory_d(sellId);
		//salesDao.deletehisotry(sellId);
		

		JOptionPane.showMessageDialog(dealCancel, "환불 처리가 완료되었습니다.", "환불 완료", JOptionPane.INFORMATION_MESSAGE);
		dealCancel.dispose();
	}*/
	
	// <테이블 내용 가운데 정렬> 메소드
	public static void tableCellCenter(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
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
//	         System.out.println("현재시간 :: " + str);
//	         idLabel.setText();
//	         dateLabel.setText(str);
		}
		
	}
	
	
	public void signupProcess(){//회원가입절차
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
			JOptionPane.showMessageDialog(signUp, "빈칸을 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		    }
		else if(signUp.txtIDCheck.getText().trim().equals("") || signUp.txtIDCheck.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(signUp, "중복확인 버튼을 눌러주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else if(String.valueOf(signUp.passwordField.getPassword()).trim().equals(String.valueOf(signUp.passwordField_Check.getPassword()).trim() )==false) {
			JOptionPane.showMessageDialog(signUp, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		else {
			
			connect_db.AccountIn(signUp.txtIDCheck.getText().trim(), String.valueOf(signUp.passwordField.getPassword()).trim(), signUp.name.getText().trim(), signUp.email.getText().trim() , "점원");
			
			//변경후 화면 띄우기
			viewAccount.clearRows(viewAccount.model.getRowCount(), viewAccount.model);
			viewAccount.showMon(connect_db.AccountAll());
			
			/*
			//변경후 화면 띄우기
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
				JOptionPane.showMessageDialog(signUpChange, "빈칸을 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				return;
			    }
			else if(String.valueOf(signUpChange.passwordField.getPassword()).trim().equals(String.valueOf(signUpChange.passwordField_Check.getPassword()).trim() )==false) {
				JOptionPane.showMessageDialog(signUpChange, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else {
				String ID=signUpChange.txtIDCheck.getText().trim();
				String PW=String.valueOf(signUpChange.passwordField.getPassword()).trim();
				String user_name=signUpChange.name.getText().trim();
				String user_email=signUpChange.email.getText().trim();
				//String.valueOf(signUp.passwordField.getPassword()).trim()
				connect_db.dbsignUpChange(ID, PW, user_name, user_email);
				
				
		
		        //변경후 화면 띄우기
		        viewAccount.clearRows(viewAccount.model.getRowCount(), viewAccount.model);
		        viewAccount.showMon(connect_db.AccountAll());
		
		        signUpchangeclear();
		        signUpChange.setVisible(false);
			}
	}
	

	public void signUpclear() {//회원가입창클리어
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
