package calc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CalcBtn extends JPanel{
	
	public JButton cBtnCalc;//����
	
	
	public CalcBtn() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		cBtnCalc = new JButton("����"); //�����ư �ٹ̱�
		cBtnCalc.setBackground(new Color(0, 0, 128));
		cBtnCalc.setForeground(new Color(255, 255, 255));
		cBtnCalc.setFont(new Font("���� ���", Font.BOLD, 20));
		cBtnCalc.setBounds(547, 27, 164, 85);
		add(cBtnCalc);
		
		
		
	}

	
	

}
