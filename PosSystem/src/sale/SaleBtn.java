package sale;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SaleBtn extends JPanel {
	
	//판매관리의 GUI 버튼들(GUI_003)
	public JButton sBtnPdChange;//상품수정버튼
	public JButton sBtnPdCancel;//상품취소버튼
	public JButton sBtnPdHold;//상품보류버튼
	public JButton sBtnPay;//상품결제버튼
	public JButton sBtnAcancel;//거래초기화버튼(거래취소버튼)
	public JButton sBtnCancel;//환불버튼
	
	
	public SaleBtn() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		sBtnPdChange = new JButton("상품수정"); //상품수정버튼 꾸미기
		add(sBtnPdChange);
		
		sBtnPdCancel = new JButton("상품취소");
		add(sBtnPdCancel);
		
		sBtnPdHold = new JButton("상품보류");
		add(sBtnPdHold);
		
		sBtnPay = new JButton("결제");
		add(sBtnPay);
		
		sBtnAcancel = new JButton("거래취소");
		add(sBtnAcancel);
		
		sBtnCancel = new JButton("취소버튼");
		add(sBtnCancel);
		
		
		
		
		
	}


	

}
