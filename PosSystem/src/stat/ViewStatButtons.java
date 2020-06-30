package stat;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewStatButtons extends JPanel{
	
	public JButton sBtnProduct;
	public JButton sBtnYear;
	public JButton sBtnMonth;
	public JButton sBtnDay;
	
	
	public ViewStatButtons() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		sBtnProduct = new JButton("»óÇ°º°");
		sBtnProduct.setBounds(0,28, 164, 85);
		add(sBtnProduct);
		sBtnProduct.setForeground(Color.WHITE);
		sBtnProduct.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnProduct.setBackground(new Color(0, 0, 128));
		
		sBtnYear = new JButton("¿¬µµº°");
		sBtnYear.setBounds(170, 28, 164, 85);
		add(sBtnYear);
		sBtnYear.setForeground(Color.WHITE);
		sBtnYear.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnYear.setBackground(new Color(100, 149, 237));
		
		sBtnMonth = new JButton("¿ùº°");
		sBtnMonth.setBounds(340, 28, 164, 85);
		add(sBtnMonth);
		sBtnMonth.setForeground(Color.WHITE);
		sBtnMonth.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnMonth.setBackground(new Color(0, 0, 128));
		
		sBtnDay = new JButton("ÀÏº°");
		sBtnDay.setBounds(510, 28, 164, 85);
		add(sBtnDay);
		sBtnDay.setForeground(Color.WHITE);
		sBtnDay.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnDay.setBackground(new Color(100, 149, 237));
		
	}
	

}
