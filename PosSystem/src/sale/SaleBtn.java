package sale;

import java.awt.Color;
import java.awt.Font;

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
		sBtnPdChange.setBackground(new Color(0, 0, 128));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 28, 164, 85);
		add(sBtnPdChange);
		
		sBtnPdCancel = new JButton("��ǰ���");
		sBtnPdCancel.setBackground(new Color(100, 149, 237));
		sBtnPdCancel.setForeground(new Color(255, 255, 255));
		sBtnPdCancel.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnPdCancel.setBounds(176, 28, 164, 85);
		add(sBtnPdCancel);
		
		sBtnPdHold = new JButton("��ǰ����");
		sBtnPdHold.setForeground(Color.WHITE);
		sBtnPdHold.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnPdHold.setBackground(new Color(0, 0, 128));
		sBtnPdHold.setBounds(359, 28, 164, 85);
		add(sBtnPdHold);
		
		sBtnAcancel = new JButton("�ŷ����");
		sBtnAcancel.setForeground(Color.WHITE);
		sBtnAcancel.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnAcancel.setBackground(new Color(255, 51, 0));
		sBtnAcancel.setBounds(547, 28, 164, 85);
		add(sBtnAcancel);
		
		sBtnPay = new JButton("����");
		sBtnPay.setForeground(Color.WHITE);
		sBtnPay.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnPay.setBackground(new Color(153, 0, 0));
		sBtnPay.setBounds(723, 28, 164, 85);
		add(sBtnPay);
		
		sBtnCancel = new JButton("ȯ��");
		sBtnCancel.setBounds(899, 28, 164, 85);
		sBtnCancel.setForeground(Color.WHITE);
		sBtnCancel.setFont(new Font("���� ���", Font.BOLD, 20));
		sBtnCancel.setBackground(new Color(153, 0, 0));
		add(sBtnCancel);
		
		
		
		
	}


	

}
