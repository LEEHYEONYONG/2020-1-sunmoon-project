package sale;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SaleBtn extends JPanel {
	
	//�ǸŰ����� GUI ��ư��(GUI_003)
	public JButton sBtnPdChange;//��ǰ������ư
	public JButton sBtnPdCancel;//��ǰ��ҹ�ư
	public JButton sBtnPdHold;//��ǰ������ư
	public JButton sBtnPay;//��ǰ������ư
	public JButton sBtnAcancel;//�ŷ��ʱ�ȭ��ư(�ŷ���ҹ�ư)
	public JButton sBtnCancel;//ȯ�ҹ�ư
	
	
	public SaleBtn() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		sBtnPdChange = new JButton("��ǰ����"); //��ǰ������ư �ٹ̱�
		add(sBtnPdChange);
		
		sBtnPdCancel = new JButton("��ǰ���");
		add(sBtnPdCancel);
		
		sBtnPdHold = new JButton("��ǰ����");
		add(sBtnPdHold);
		
		sBtnPay = new JButton("����");
		add(sBtnPay);
		
		sBtnAcancel = new JButton("�ŷ����");
		add(sBtnAcancel);
		
		sBtnCancel = new JButton("��ҹ�ư");
		add(sBtnCancel);
		
		
		
		
		
	}


	

}
