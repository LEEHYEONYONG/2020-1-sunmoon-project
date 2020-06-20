package sale;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import db.PosDto;
import db.PosUse;
import main.MainFrame;
import db.Connect_DB;

public class SalesInputService implements KeyListener, ActionListener, ItemListener {// 판매 이벤트 처리

	SaleBtn salebtn = new SaleBtn();
	MainFrame mainframe;
	// SalesInputDao salesInputDao;//DB
	public static boolean key;
	//Vector<PosDto> salesList;
	Vector<PosUse> salesList;//DB
	Vector<Object> getgoods;
	int overlapRow;
	boolean memshipcheck = false;

	//데이터베이스 필드 추가
	Connect_DB connect_db;
	
	public SalesInputService(MainFrame mainframe) {
		super();
		this.mainframe = mainframe;
		// salesInputDao = new SalesInputDao();
		
		//김무현 추가
		connect_db = new Connect_DB();
		
	}

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getStateChange()== ItemEvent.SELECTED) {
		
		/*mainframe.payment_3.JcomboBoxPay*/	
	    Object item =e.getItem();
	    
        //String item = String.valueOf(mainframe.payment_3.JcomboBoxPay.getToolkit());
		//String item = String.valueOf(mainframe.payment_3.JcomboBoxPay.getSelectedItem());
		if(item.equals("카드")) {
			mainframe.payment_3.tfP3CashP.setEnabled(false);
			mainframe.payment_3.tfP3CardP.setEnabled(true);
		} else {
			mainframe.payment_3.tfP3CashP.setEnabled(true);
			mainframe.payment_3.tfP3CardP.setEnabled(false);
		}
		
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 10) {
			System.out.println(e);
			goodsListProcess();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == mainframe.salebtn.sBtnPdChange) {// 상품수정클릭시
			
			int row = mainframe.viewSalesInput.table.getSelectedRow();
			System.out.println("row : "+row);
			if (row < 0) {
				JOptionPane.showMessageDialog(mainframe, "수정할 상품을 클릭해 주세요.", "상품 수정 오류", JOptionPane.WARNING_MESSAGE);

				return;
			}
			if (!Integer.toString(row).equals(null)) {
				String cnt = JOptionPane.showInputDialog(mainframe, "변경할 수량을 입력하세요.", "수량변경",
						JOptionPane.QUESTION_MESSAGE);

				if (cnt == null || !isNumber(cnt) || cnt.isEmpty() || cnt.trim().equals("")) {
					return;
				} else {
					int price = Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(row, 4)));//가격 4번
					mainframe.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 3);//수량 3번
					mainframe.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 7);//총 금액 7번
				}
			} else {
				JOptionPane.showMessageDialog(mainframe, "수정할 상품을 선택해주세요", "선택오류", JOptionPane.ERROR_MESSAGE);
			}
			totalApply();

		} else if (ob == mainframe.salebtn.sBtnAcancel) {// 거래취소클릭시
			int row = mainframe.viewSalesInput.table.getRowCount();
			if (row < 0) {
				JOptionPane.showMessageDialog(mainframe, "결제 취소할 상품이 없습니다.", "결제 취소 오류", JOptionPane.WARNING_MESSAGE);

				return;
			}
			connect_db.removeListNum(mainframe.viewSalesInput.table.getRowCount());
			setblank();
			//payEnd();

		} else if (ob == mainframe.salebtn.sBtnPdCancel) {// 상품취소클릭시
			int row = mainframe.viewSalesInput.table.getSelectedRow();
			
			int[] rows = mainframe.viewSalesInput.table.getSelectedRows();
			
            System.out.println("rows.length : "+rows.length);
			
			System.out.println("row : "+row);
			
			System.out.println("getRowCount : "+mainframe.viewSalesInput.model.getRowCount());
			
			for(int i=0;i<rows.length;i++)
			{
				System.out.println("\ngetSelectedRowsIndex : "+ i);
				System.out.println("getSelectedRows : "+ rows[i]);//table row index
			}
			
			if (row != -1) {
				int del = JOptionPane.showConfirmDialog(mainframe, "선택한 상품을 취소하시겠습니까?", "상품취소",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (del == 0) {
					for(int i=0;i<rows.length;i++)
					{
						//상품인텍스가 0~5번까지 총 6개 있을 때 인텍스 1~4번까지 상품을 선택하고 삭제버튼을 누른다
						//반복문을 돌면서 선택된 상품이 삭제된다
						//1번 인덱스의 상품을 삭제하면 2~4번까지 있던 3개의 상품들은 1번 인덱스 상품이 삭제됬으므로
						//인덱스가 1씩 줄어든다. 2번 인덱스 상품은 1번으로 바뀜 3번 인덱스 상품은 2번으로 바뀜
						mainframe.viewSalesInput.model.removeRow(rows[i]-i);//목록 삭제 메소드
						System.out.println("삭제 완료 row = "+(rows[i]-i));
					}
					
					//listNum 재설정
					int t_size = mainframe.viewSalesInput.table.getRowCount();
					for(int i =0; i < t_size; i++)
					{
						mainframe.viewSalesInput.model.setValueAt(i+1,i,0);//삽입할 번호, 행번호, 열번호
					}
					//listNum 1감소
					connect_db.removeListNum(rows.length);
					
					int size = salesList.size();
					System.out.println("사이즈 : "+size);
				
					System.out.println("getListNum : "+salesList.get(0).getListNum());
					System.out.println("getp_num : "+salesList.get(0).getp_num());
					System.out.println("getp_name : "+salesList.get(0).getp_name());
					System.out.println("getp_amount : "+salesList.get(0).getp_amount());
					System.out.println("getp_cost : "+salesList.get(0).getp_cost());
					System.out.println("getp_provide : "+salesList.get(0).getp_provide());
					totalApply();
				}
//				int price = Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(row, 3)));
//				mainframe.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 4);
//				mainframe.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 5);
			} else {
				JOptionPane.showMessageDialog(mainframe, "취소할 상품을 선택해주세요", "선택오류", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == mainframe.salebtn.sBtnPay) {// 결제클릭시
			if (mainframe.viewSalesInput.model.getRowCount() > 0) {
				mainframe.payment_1.setVisible(true);// 할인창부터열기
				mainframe.payment_1.tfP1BeforePrice.setText(mainframe.viewSalesInput.total_price_input.getText());
				// String item =
				// String.valueOf(mainframe.payment_1.cbP1Cooperation.getSelectedItem());
				// cooperDCProcess(item);
				// cpCalc();
			} else {
				JOptionPane.showMessageDialog(mainframe, "선택한 상품이 없습니다.", "상품미선정", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == mainframe.payment_1.btnP1Apply) {
			cpCalc();

		} else if (ob == mainframe.payment_1.btnP1Before) {
			mainframe.payment_1.setVisible(false);
			mainframe.payment_4.setVisible(false);
			mainframe.payment_3.setVisible(false);
		} else if (ob == mainframe.payment_1.btnP1Next) {
			if (mainframe.payment_1.tfP1Afterprice.getText().equals(""))
				JOptionPane.showMessageDialog(mainframe.payment_1, "할인을 적용하시오.", "할인미적용", JOptionPane.ERROR_MESSAGE);
			else {
				mainframe.payment_1.setVisible(false);
				mainframe.payment_3.setVisible(true);
				mainframe.payment_3.lbP3PaymentView.setText(mainframe.payment_1.tfP1Afterprice.getText());
				mainframe.payment_3.lbP3FinalPayView.setText("0");
			}
		} else if (ob == mainframe.payment_3.JcomboBoxPay) {
			/*
			String item = String.valueOf(mainframe.payment_3.JcomboBoxPay.getToolkit());
			
			if(item.equals("카드")) {
				mainframe.payment_3.tfP3CashP.setEnabled(false);
				mainframe.payment_3.tfP3CardP.setEnabled(true);
			} else {
				mainframe.payment_3.tfP3CashP.setEnabled(true);
				mainframe.payment_3.tfP3CardP.setEnabled(false);
			}
			*/
			
		} else if (ob == mainframe.payment_3.btnP3Input) {
			
			int card = 0;
			int cash = 0;

			if (mainframe.payment_3.tfP3CardP.getText().trim().equals("")
					&& mainframe.payment_3.tfP3CashP.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(mainframe.payment_3, "현금 또는 카드를 입력해주십시오.", "결제유형오류",
						JOptionPane.ERROR_MESSAGE);

			} else if (isNumber(mainframe.payment_3.tfP3CardP.getText().trim()) != true
					|| isNumber(mainframe.payment_3.tfP3CashP.getText().trim()) != true) {
				JOptionPane.showMessageDialog(mainframe.payment_3, "숫자만 입력해주십시오.", "입력오류",
						JOptionPane.WARNING_MESSAGE);

			} else {

				if (!mainframe.payment_3.tfP3CardP.getText().trim().equals("")) {
					card = Integer.parseInt(mainframe.payment_3.tfP3CardP.getText().trim());
					// salesInputDao.posDto.setCardPrice(card);
					// System.out.println(salesInputDao.posDto.getCardPrice());
				}

				if (!mainframe.payment_3.tfP3CashP.getText().trim().equals("")) {
					cash = Integer.parseInt(mainframe.payment_3.tfP3CashP.getText().trim());
					// salesInputDao.posDto.setCashPrice(cash);
					// System.out.println(salesInputDao.posDto.getCashPrice());
				}
				
				if (card + cash != Integer.parseInt(mainframe.payment_3.lbP3PaymentView.getText())) {
					System.out.println(card + cash);
					mainframe.payment_3.tfP3SM.setText("");
					mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(card + cash));
					JOptionPane.showMessageDialog(mainframe.payment_3, "결제금액과 맞지않습니다.", "결제금액대소오류",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// salesInputDao.posDto.setTotalPrice(card + cash);
					// mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(salesInputDao.posDto.getTotalPrice()));
					mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(card + cash));
					mainframe.payment_3.tfP3SM.setText("결제금액이 충족되었습니다");
				}
			}
			
			
//--------------------------------------------------------------------------------------------

		} else if (ob == mainframe.payment_3.btnP3Before) {
			mainframe.payment_1.setVisible(true);
			mainframe.payment_3.setVisible(false);

		} else if (ob == mainframe.payment_3.btnP3Cancel) {
			mainframe.payment_3.setVisible(false);
		} else if (ob == mainframe.payment_3.btnP3Next) {
			

			if (!mainframe.payment_3.tfP3SM.getText().equals("")) {
				if (JOptionPane.showConfirmDialog(mainframe.payment_3, "결제하시겠습니까?", "결제확인",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					// paymentProcess();
					JOptionPane.showMessageDialog(mainframe.payment_3, "결제가완료되었습니다.", "결제완료",
							JOptionPane.INFORMATION_MESSAGE);

					mainframe.payment_3.setVisible(false);
					mainframe.payment_4.setVisible(true);

					// salesInputDao.SellIdDate();
					// payNPrint();

					// setblank();
					// payEnd();

//				} else if (JOptionPane.showMessageDialog(mainframe.payment_3, "결제조건이 충족되지 않았습니다.", "조건부족",
//						JOptionPane.ERROR_MESSAGE)) {
//					return;
				} else {
					JOptionPane.showMessageDialog(mainframe.payment_3, "결제조건이 충족되지 않았습니다.", "조건부족",
							JOptionPane.ERROR_MESSAGE);
				}

			}
			
			
		} else if (ob == mainframe.payment_4.btnP4PrintReceipt) {
			
			PrintProcess();
			
		} else if (ob == mainframe.payment_4.btnP4Payment) {

			mainframe.payment_4.setVisible(false);
			mainframe.payment_4.taP4details.setText("");

		}

	}
	
	
	public void PrintProcess() {//영수증출력
		BufferedImage img =getScreenShot(mainframe.payment_4.taP4details);
		
		FileDialog dialog = new FileDialog(mainframe.payment_4, "저장", FileDialog.SAVE);
		dialog.setDirectory(".");
		dialog.setFile("*.jpg");
		/*
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		       "JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(chooser);
		*/
		
		dialog.setVisible(true);
		
		if(dialog.getFile() == null) return;
		
		String dfName = dialog.getDirectory() + dialog.getFile();
		
		
		File file = new File(dfName);
		
		try {
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));

		writer.write(getScreenShot(mainframe.payment_4));

		writer.close();
		*/

	}
	
	public static BufferedImage getScreenShot(Component component) {//이미지얻기
		
		BufferedImage image = new BufferedImage(
				component.getWidth(),
				component.getHeight(),
				BufferedImage.TYPE_INT_RGB
				);
		
		component.paint(image.getGraphics());
		return image;
		
	}

	/*
	 
	
		public void StocksProcess() {

		getgoods = new Vector<Object>();
		int size = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < size; i++) {
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 1));// p_code
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 6));// in_date
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 4));// p_cnt
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 2));// p_name
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 3));// p_price
		}

		salesInputDao.registerHisDetail(size, getgoods);
		salesInputDao.reflectStocks(size, getgoods);

	}

	private void paymentProcess() {
		salesInputDao.registerHistory();
		StocksProcess();

//		if()유저이름이 없다면 멤버쉽등록이 안됬으므로 포인트처리 생략
		if (memshipcheck == true)
			pointreflect();

	}

	private void pointreflect() {

		int point = Integer.parseInt(mainframe.payment_2.tfP2point.getText());
		int savePoint = Integer.parseInt(mainframe.payment_3.lbP3PointView.getText());
		int usePoint = 0;
		if (!mainframe.payment_2.tfP2UsePoint.getText().equals(""))
			usePoint = Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText());

		salesInputDao.posDto.setPoint(point - usePoint + savePoint);
		salesInputDao.pointUpdate();
	}

	private void payNPrint() {
		mainframe.payment_4.taP4details.setText("판매코드 : " + ForcePos.selldto.getSellId() + "\t판매일자 : "
				+ ForcePos.selldto.getSellDate() + "\n\t판매원코드 : " + ForcePos.usercodeDto.getUserCode()
				+ "\n==========================================\n");

		int x = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < x; i++) {
			for (int j = 1; j < 6; j++) {
				mainframe.payment_4.taP4details.append(mainframe.viewSalesInput.model.getValueAt(i, j) + "\t");
			}
			mainframe.payment_4.taP4details.append("\n");
		}

		mainframe.payment_4.taP4details.append("\n==========================================\n"
				+ (salesInputDao.posDto.getDiscountCode().equals("d4") ? "\n"
						: "할인코드 : " + salesInputDao.posDto.getDiscountCode() + " 제휴사 : "
								+ salesInputDao.posDto.getCooperateName() + " 할인율 : "
								+ mainframe.payment_1.tfP1DiscountPercent.getText() + "\n"));

		if (salesInputDao.posDto.getMembershipId().equals("nomember")) {

		} else {
			if (mainframe.payment_2.tfP2UsePoint.getText().isEmpty()) {
				mainframe.payment_4.taP4details.append("멤버쉽 : " + salesInputDao.posDto.getMembershipId()
						+ "\t적립된 포인트 : " + mainframe.payment_3.lbP3PointView.getText() + "\n\t\t현재 포인트 : "
						+ (salesInputDao.posDto.getPoint()
								+ Integer.parseInt(mainframe.payment_3.lbP3PointView.getText()))
						+ "\n");
			} else {
				mainframe.payment_4.taP4details.append("멤버쉽 : " + salesInputDao.posDto.getMembershipId() + "\t사용포인트 : "
						+ mainframe.payment_2.tfP2UsePoint.getText() + "\n적립된 포인트 : "
						+ mainframe.payment_3.lbP3PointView.getText() + "\t현재 포인트 : "
						+ (salesInputDao.posDto.getPoint()
								- Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText())
								+ Integer.parseInt(mainframe.payment_3.lbP3PointView.getText()))
						+ "\n");
			}

		}

		mainframe.payment_4.taP4details.append(("\n----------------------------------------------------------------\n"
				+ "\t 총결제금액 : " + salesInputDao.posDto.getTotalPrice() + "\n"
				+ (salesInputDao.posDto.getCardPrice() == 0 ? ""
						: "\t 카드결제금액 : " + salesInputDao.posDto.getCardPrice() + "\n")
				+ (salesInputDao.posDto.getCashPrice() == 0 ? ""
						: "\t 현금결제금액 : " + salesInputDao.posDto.getCashPrice() + "\n")));

	}

	public void membershipRef() {
		if (mainframe.payment_2.tfP2phoneNum.getText().equals("")) {
			JOptionPane.showMessageDialog(mainframe.payment_2, "번호를 입력하시오.", "입력오류", JOptionPane.WARNING_MESSAGE);
		} else {

			if (salesInputDao.costomerRef(mainframe.payment_2.tfP2phoneNum.getText()) == true) {
				mainframe.payment_2.tfP2SM.setText(salesInputDao.posDto.getMemberName() + "("
						+ salesInputDao.posDto.getMembershipId() + ")님의 멤버쉽이 확인되었습니다.");
				mainframe.payment_2.tfP2point.setText(String.valueOf(salesInputDao.posDto.getPoint()));
				memshipcheck = true;
			} else {
				mainframe.payment_2.tfP2SM.setText(mainframe.payment_2.tfP2phoneNum.getText() + "님의 멤버쉽을 찾을 수 없습니다.");
			}
		}

	}
	
	*/
	

	public void cpCalc() {
		/*
		 * mainframe.payment_1.tfP1Afterprice .setText(Integer.toString((int)
		 * (((Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText()) ((100 -
		 * Integer.parseInt(mainframe.payment_1.tfP1DiscountPercent.getText())))) /
		 * 100))));
		 */
		if (mainframe.payment_1.txtP1Check.getText() == null) {
			JOptionPane.showMessageDialog(mainframe.payment_1, "가격을 입력해주세요.", "입력오류", JOptionPane.WARNING_MESSAGE);
		} else if (isNumber(mainframe.payment_1.txtP1Check.getText().trim()) != true) {
			JOptionPane.showMessageDialog(mainframe.payment_1, "숫자만 입력해주세요.", "입력오류", JOptionPane.WARNING_MESSAGE);
		} else if (Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText()) < Integer
				.parseInt(mainframe.payment_1.txtP1Check.getText())) {
			JOptionPane.showMessageDialog(mainframe.payment_1, "할인금액이 결제금액보다 큽니다.", "오류", JOptionPane.WARNING_MESSAGE);
		} else {
			mainframe.payment_1.tfP1Afterprice
					.setText(Integer.toString((int) ((Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText())
							- (Integer.parseInt(mainframe.payment_1.txtP1Check.getText()))))));
			mainframe.payment_1.tfP1SM.setText("할인이 적용되었습니다.");
		}
	}

	/*
	 * public void cooperDCProcess(String item) {
	 * 
	 * salesInputDao.searchByCP(item);
	 * 
	 * mainframe.payment_1.tfP1DiscountPercent .setText(String.valueOf((int)
	 * (salesInputDao.posDto.getDiscountPct() * 100)));
	 * 
	 * mainframe.payment_1.tfP1Afterprice.setText("");
	 * 
	 * }
	 * 
	 * 
	 */

	public boolean checkOverlap(String identifier, int col) {
		System.out.println("check" + identifier);
		int h = mainframe.viewSalesInput.model.getRowCount();
		for (int i = 0; i < h; i++) {
			System.out.println(mainframe.viewSalesInput.model.getValueAt(i, col));
			if (mainframe.viewSalesInput.model.getValueAt(i, col).equals(identifier)) {
				System.out.println("중복");
				overlapRow = i;

				return false;
			}

		}

		return true;
	}

	public void goodsListProcess() {

		if (mainframe.viewSalesInput.code_input.getText().trim().length() > 0) {

			if (checkOverlap(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase(), 1)) {
				key = true;
				System.out.println(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase());
				System.out.println("코드로검색" + mainframe.viewSalesInput.code_input.getText().length());
				
				//김무현이 만든 connect_db.searchBy 메소드
				listAdd(connect_db.searchBy(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase()));

			} else {
				// 수량 변경
				System.out.println("코드중복 발생");
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3))) + 1,
						overlapRow, 3);// 4로 되어있던 부분 3으로 변경(3번이 수량)
				// 단가 * 수량
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3)))//수량
								* Integer.valueOf(
										String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))),//가격
						overlapRow, 7);//5로 되어있던 부분 7으로 변경(7번 총 금액)
			}
			mainframe.viewSalesInput.product_name_input.setText("");
		} else if (mainframe.viewSalesInput.product_name_input.getText().trim().length() > 0) {

			if (checkOverlap(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase(), 2)) {
				key = false;
				System.out.println("이름으로검색" + mainframe.viewSalesInput.product_name_input.getText().length());
				//김무현이 만든 connect_db.searchBy 메소드
				listAdd(connect_db
						.searchBy(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase()));

			} else {
				System.out.println("이름중복 발생");
				// 수량만증가
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3))) + 1,
						overlapRow, 3);//4로 되어있던 부분 3으로 변경
				// 단가 * 수량 적용
				mainframe.viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3)))//수량
								* Integer.valueOf(
										String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))),//가격
						overlapRow, 7);//5로 되어있던 부분 7으로 변경
			}

		} else {
			JOptionPane.showMessageDialog(mainframe, "해당 상품은 없습니다.", "검색실패", JOptionPane.WARNING_MESSAGE);

		}
		totalApply();
		mainframe.viewSalesInput.code_input.setText("");
		mainframe.viewSalesInput.product_name_input.setText("");
	}

	public void listAdd(Vector<PosUse> salesList) {
		if (salesList == null)
			JOptionPane.showMessageDialog(mainframe, "상품이 존재하지않습니다.", "상품없음", JOptionPane.ERROR_MESSAGE);
		else {
			int size = salesList.size();
			
			this.salesList = new Vector<PosUse>();

			//this.salesList.addElement(salesList.get(0).getListNum());
			
			this.salesList = salesList;
			
			System.out.println("listAdd size : "+size);
			Vector<String> rows = new Vector<String>();
			/*
			rows.addElement(Integer.toString(salesList.get(0).getListNum()));
			rows.addElement(salesList.get(0).getProductCode()); 1 String
			rows.addElement(salesList.get(0).getProductName()); 2 String
			rows.addElement(Integer.toString(salesList.get(0).getPrice())); 3 int
			rows.addElement(String.valueOf(salesList.get(0).getSellCount())); 4 int
			rows.addElement(String.valueOf(salesList.get(0).getPricensellCount())); 5 int
			rows.addElement(salesList.get(0).getInDate()); 6 String*/
			
			rows.addElement(Integer.toString(salesList.get(0).getListNum()));
			rows.addElement(salesList.get(0).getp_num());
			rows.addElement(salesList.get(0).getp_name());
			rows.addElement(String.valueOf(salesList.get(0).getp_amount()));
			rows.addElement(Integer.toString(salesList.get(0).getp_cost()));
			rows.addElement(salesList.get(0).getp_category());
			rows.addElement(salesList.get(0).getp_provide());
			rows.addElement(String.valueOf(salesList.get(0).getp_costsellCount()));
			/*
			System.out.println("rows.size : "+rows.size());
			System.out.println("rows.get(0) : "+rows.get(0));
			System.out.println("rows.get(1) : "+rows.get(1));
			System.out.println("rows.get(2) : "+rows.get(2));
			System.out.println("rows.get(3) : "+rows.get(3));
			System.out.println("rows.get(4) : "+rows.get(4));
			System.out.println("rows.get(5) : "+rows.get(5));
			System.out.println("rows.get(6) : "+rows.get(6));
			
			System.out.println("salesList.get(0) : "+salesList.get(0));*/
			
			System.out.println(this.salesList.get(0).getp_name());
			
			
			mainframe.viewSalesInput.model.addRow(rows);
			
			System.out.println("반영완료");

		}
	}

	public void totalApply() {//계산한 총금액가져오기
		int tp = 0;
		int row = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < row; i++) {
			tp += Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(i, 7)));
		}
		mainframe.viewSalesInput.total_price_input.setText(String.valueOf(tp));
	}

	public boolean isNumber(String obStr) {
		boolean flag = true;
		int len = obStr.length();
		int num;
		for (int i = 0; i < len; i++) {
			num = obStr.charAt(i) - 48;
			if (num < 0 || num > 9)
				flag = false;
		}
		return flag;
	}

	public void isNumError(boolean b, JPanel p) {
		if (b == false)
			JOptionPane.showMessageDialog(p, "숫자만 입력가능합니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
	}

	public static void clearRows(int rowCount, DefaultTableModel model) {
		if (rowCount > 0) {
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
	}

	private void setblank() {
		clearRows(mainframe.viewSalesInput.table.getRowCount(), mainframe.viewSalesInput.model);
		mainframe.viewSalesInput.total_price_input.setText("0");
		mainframe.payment_1.tfP1SM.setText("");
		mainframe.payment_1.tfP1BeforePrice.setText("");
		mainframe.payment_1.tfP1Afterprice.setText("");
		mainframe.payment_1.lbP1Afterprice.setText("");
		mainframe.payment_1.txtP1Check.setText("");
		mainframe.payment_3.tfP3SM.setText("");
		mainframe.payment_3.tfP3CardP.setText("");
		mainframe.payment_3.lbP3FinalPay.setText("");
		mainframe.payment_3.lbP3Payment.setText("");
		mainframe.payment_3.tfP3CashP.setText("");
		mainframe.payment_3.lbP3FinalPayView.setText("");
		mainframe.payment_3.lbP3PaymentView.setText("");

	}


	/*
	public void payEnd() {
		salesInputDao.posDto.setMembershipId("");
		salesInputDao.posDto.setMemberName("");
		salesInputDao.posDto.setPoint(0);
		salesInputDao.posDto.setDiscountCode("");
		salesInputDao.posDto.setCooperateName("");
		salesInputDao.posDto.setDiscountPct(0);

		salesInputDao.posDto.setListNum(0);
		salesInputDao.posDto.setCardPrice(0);
		salesInputDao.posDto.setCashPrice(0);
		salesInputDao.posDto.setTotalPrice(0);

	}
	*/
	 

}
