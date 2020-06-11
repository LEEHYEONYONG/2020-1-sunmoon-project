package calc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CalcBtn extends JPanel{
	
	public JButton cBtnCalc;//정산
	public JButton cBtnCalcHistory;//정산내역
	
	
	public CalcBtn() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		cBtnCalc = new JButton("정산"); //정산버튼 꾸미기
		cBtnCalc.setBackground(new Color(0, 0, 128));
		cBtnCalc.setForeground(new Color(255, 255, 255));
		cBtnCalc.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		cBtnCalc.setBounds(0, 28, 164, 85);
		add(cBtnCalc);
		
		cBtnCalcHistory = new JButton("정산내역");
		cBtnCalcHistory.setBackground(new Color(100, 149, 237));
		cBtnCalcHistory.setForeground(new Color(255, 255, 255));
		cBtnCalcHistory.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		cBtnCalcHistory.setBounds(176, 28, 164, 85);
		add(cBtnCalcHistory);
		
		
		
	}

	
	

}
