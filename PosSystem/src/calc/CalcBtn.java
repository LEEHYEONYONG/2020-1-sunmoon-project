package calc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CalcBtn extends JPanel{
	
	public JButton cBtnCalc;//����
	public JButton cBtnCalcHistory;//���곻��
	
	
	public CalcBtn() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		cBtnCalc = new JButton("����"); //�����ư �ٹ̱�
		cBtnCalc.setBackground(new Color(0, 0, 128));
		cBtnCalc.setForeground(new Color(255, 255, 255));
		cBtnCalc.setFont(new Font("���� ���", Font.BOLD, 20));
		cBtnCalc.setBounds(0, 28, 164, 85);
		add(cBtnCalc);
		
		cBtnCalcHistory = new JButton("���곻��");
		cBtnCalcHistory.setBackground(new Color(100, 149, 237));
		cBtnCalcHistory.setForeground(new Color(255, 255, 255));
		cBtnCalcHistory.setFont(new Font("���� ���", Font.BOLD, 20));
		cBtnCalcHistory.setBounds(176, 28, 164, 85);
		add(cBtnCalcHistory);
		
		
		
	}

	
	

}
