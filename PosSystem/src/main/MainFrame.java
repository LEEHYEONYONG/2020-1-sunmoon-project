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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sale.DealCancel;
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

public class MainFrame extends JFrame implements ActionListener, Runnable{// 메인프레임 
	
	StockPopupIn stockpopupin = new StockPopupIn();
	StockPopupChange stockpopupchange = new StockPopupChange();
	StockPopupSearch stockpopupsearch = new StockPopupSearch();
	StockBtn stockbtn;
	StockMonitor stockmonitor = new StockMonitor();
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
	
	// 결제및 결제취소창 프레임
	public DealCancel dealCancel = new DealCancel();
	public Payment_3 payment_3 = new Payment_3();
	public Payment_4 payment_4 = new Payment_4();
	

	public MainFrame(){
		monitor = new CardLayout();
		btn = new CardLayout();
		stockbtn = new StockBtn();
		
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
//		모니터패널에 카드레이아웃주고 각화면 패널 객체생성하여 추가
		pMonitor.add("ViewSalesInput", viewSalesInput);
		pMonitor.add(stockmonitor, "Stock");
		
		pFBtn = new JPanel();
		pFBtn.setBackground(Color.WHITE);
		pFBtn.setBounds(0, 601, 1157, 123);
		contentPane.add(pFBtn);
		pFBtn.setLayout(btn);
		
		
        //////////////////////////////////
//		기능버튼패널 카드 각기능버튼 패널 객체생성후 추가
		pFBtn.add(salebtn, "salebtn");
		pFBtn.add(stockbtn, "Stockbtn");
		//pFBtn.add(statbtn, "Statbtn");
		//pFBtn.add(accbtn, "Accbtn");
		//pFBtn.add(calcbtn, "Calcbtn");
		
		
		// 메인기능버튼
		mBtnInven.addActionListener(this);
		mBtnSale.addActionListener(this);
		//mBtnCalc.addActionListener(calcService);
		mBtnStat.addActionListener(this);
		
		
		// 재고 기능버튼
		stockbtn.stockSearch.addActionListener(this);
		stockbtn.stockIn.addActionListener(this);
		stockbtn.stockChg.addActionListener(this);
		stockbtn.stockevery.addActionListener(this);
		
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
		stockpopupin.IndateTf.addActionListener(this);
		stockpopupin.PcntTf.addActionListener(this);
		stockpopupin.pcodeInput.addActionListener(this);
		stockpopupin.indateInput.addActionListener(this);
		stockpopupin.pcntInput.addActionListener(this);
		
		
		//판매 기능 리스너
		salebtn.sBtnPdHold.addActionListener(this);
		salebtn.sBtnCancel.addActionListener(this);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcancel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);
		
//		결제3 이벤트 등록
		payment_3.btnP3Before.addActionListener(this);
		payment_3.btnP3Cancel.addActionListener(this);
		payment_3.btnP3Input.addActionListener(salesInputService);
		payment_3.btnP3Next.addActionListener(salesInputService);
		
//		결제 4 이벤트 등록

		payment_4.btnP4PrintReceipt.addActionListener(this);
		payment_4.btnP4Payment.addActionListener(salesInputService);

		dealCancel.btnRefund.addActionListener(this);

		viewSalesInput.code_input.addKeyListener(salesInputService);
		viewSalesInput.product_name_input.addKeyListener(salesInputService);
		
		
		thread = new Thread(this);
		thread.start();

	}//메인프레임메소드 끝
	
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
		
		//메인기능들
		if(ob==mBtnSale) {//판매관리
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		} else if (ob == mBtnStat) {//통계
			
		} else if (ob == mBtnInven) {//재고관리
			monitor.show(pMonitor, "Stock");
			btn.show(pFBtn, "Stockbtn");
		} else if (ob == mBtnCalc) {
			
		}
		
		//세부기능들
		
		//판매관리의 기능들
		else if(ob == salebtn.sBtnCancel) {//환불
			dealCancel.setVisible(true);
		}
		else if (ob == salebtn.sBtnPdHold) {//상품보류
			
		} else if (ob == payment_3.btnP3Before) {
			payment_4.setVisible(false);
			payment_3.setVisible(false);

		} else if (ob == payment_3.btnP3Cancel) {
			payment_3.setVisible(false);
		}
		
		// 재고 탭의 기능들
				else if (ob == stockbtn.stockevery) {
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
					//stockmonitor.showMon(stockdao.StockAll());
				} else if (ob == stockbtn.stockSearch) {
					stockpopupsearch.setVisible(true);
				} else if (ob == stockbtn.stockChg) {
					// 수정할거 골랐는지 유효성검사 getSelectedRow써야됨

					int tmp = stockmonitor.StockTable.convertRowIndexToModel(stockmonitor.StockTable.getSelectedRow());

					if (tmp < 0) {
						JOptionPane.showMessageDialog(this, "수정할 재고를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}

					String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
					String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
					String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
					String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);

					stockpopupchange.selecItem.setText("상품코드 : " + tmp1 + " 입고일 : " + tmp2 + " 상품명 : " + tmp3 + " 수량 : " + tmp4);
					stockpopupchange.setVisible(true);
				} else if (ob == stockbtn.stockIn) {
					stockpopupin.setVisible(true);
				} else if (ob == stockpopupsearch.SearchCode || ob == stockpopupsearch.sCbtn) {
					// 재고조회 코드로 검색하기
					// 유효성 검사 하기 곤란 products테이블에 존재하는 코드로만 검색가능함

					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

					String scode = stockpopupsearch.SearchCode.getText();

					//stockmonitor.showMon(stockdao.StockSearchCode(scode));

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
					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

					String sname = stockpopupsearch.SearchName.getText();

					//stockmonitor.showMon(stockdao.StockSearchName(sname));

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
					// 입고창 취소버튼 내용물 초기화하고 끄기
					stockpopupin.setVisible(false);
					stockpopupin.IndateTf.setText("");
					stockpopupin.PcodeTf.setText("");
					stockpopupin.PcntTf.setText("");
					stockpopupin.pcodeResult.setText("");
					stockpopupin.pcntResult.setText("");
					stockpopupin.indateResult.setText("");
				} else if (ob == stockpopupin.PcodeTf || ob == stockpopupin.pcodeInput) {
					// 코드 입력하면 입력한코드 옆에 표시
					stockpopupin.pcodeResult.setText(stockpopupin.PcodeTf.getText());

				} else if (ob == stockpopupin.IndateTf || ob == stockpopupin.indateInput) {
					// 입고일자 입력하면 입력한날짜 표시
					stockpopupin.indateResult.setText(stockpopupin.IndateTf.getText());

				} else if (ob == stockpopupin.PcntTf || ob == stockpopupin.pcntInput) {
					// 수량 입력하면 입력한 수량 표시
					stockpopupin.pcntResult.setText(stockpopupin.PcntTf.getText());

				} else if (ob == stockpopupin.inOk) {
					// 위의 3개에서 pcodeResult, indateResult, pcntResult로 인자값 받아서 in함
					if (isNumber(stockpopupin.pcntResult.getText())) {

						if (stockpopupin.pcodeResult.getText().isEmpty() || stockpopupin.pcodeResult.getText().trim().equals("")
								|| stockpopupin.indateResult.getText().trim().isEmpty()
								|| stockpopupin.indateResult.getText().trim().equals("")
								|| stockpopupin.pcntResult.getText().trim().isEmpty()
								|| stockpopupin.pcntResult.getText().trim().equals("")) {
							JOptionPane.showMessageDialog(this, "빈칸이 있습니다!", "등록 오류", JOptionPane.ERROR_MESSAGE);
							return;
						} else {
							String pcode = stockpopupin.pcodeResult.getText().trim();
							String indt = stockpopupin.indateResult.getText().trim();
							int pcnt = Integer.parseInt(stockpopupin.pcntResult.getText().trim());
							/*

							int rr = stockdao.StockIn(pcode, indt, pcnt);
							if (rr == 0) {
								JOptionPane.showMessageDialog(this, "같은날 입고된 내역이 있습니다!", "등록 오류", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(this, "수량은 숫자로 써주세요!", "등록 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}
		
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
		// TODO Auto-generated method stub
		
	}

}
