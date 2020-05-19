package sale;

import java.awt.Color;
import java.awt.Font;

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
		sBtnPdChange.setBackground(new Color(0, 0, 128));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 28, 164, 85);
		add(sBtnPdChange);
		
		sBtnPdCancel = new JButton("상품취소");
		sBtnPdCancel.setBackground(new Color(100, 149, 237));
		sBtnPdCancel.setForeground(new Color(255, 255, 255));
		sBtnPdCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdCancel.setBounds(176, 28, 164, 85);
		add(sBtnPdCancel);
		
		sBtnPdHold = new JButton("상품보류");
		sBtnPdHold.setForeground(Color.WHITE);
		sBtnPdHold.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdHold.setBackground(new Color(0, 0, 128));
		sBtnPdHold.setBounds(359, 28, 164, 85);
		add(sBtnPdHold);
		
		sBtnAcancel = new JButton("거래취소");
		sBtnAcancel.setForeground(Color.WHITE);
		sBtnAcancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnAcancel.setBackground(new Color(255, 51, 0));
		sBtnAcancel.setBounds(547, 28, 164, 85);
		add(sBtnAcancel);
		
		sBtnPay = new JButton("결제");
		sBtnPay.setForeground(Color.WHITE);
		sBtnPay.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPay.setBackground(new Color(153, 0, 0));
		sBtnPay.setBounds(723, 28, 164, 85);
		add(sBtnPay);
		
		sBtnCancel = new JButton("환불");
		sBtnCancel.setBounds(899, 28, 164, 85);
		sBtnCancel.setForeground(Color.WHITE);
		sBtnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnCancel.setBackground(new Color(153, 0, 0));
		add(sBtnCancel);
		
		
		
		
	}


	

}
