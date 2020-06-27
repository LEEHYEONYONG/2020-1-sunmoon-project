package stock;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StockBtn extends JPanel {
	
	public JButton stockChg;
	public JButton stockIn;
	public JButton stockSearch;
	public JButton stockevery;
	public JButton stockdelete;
	
	
	public StockBtn() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		stockSearch = new JButton("¿Á∞Ì¡∂»∏");
		stockSearch.setBounds(0,28, 164, 85);
		add(stockSearch);
		stockSearch.setForeground(Color.WHITE);
		stockSearch.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		stockSearch.setBackground(new Color(0, 0, 128));
		
		stockIn = new JButton("¿‘∞Ì");
		stockIn.setBounds(176, 28, 164, 85);
		add(stockIn);
		stockIn.setForeground(Color.WHITE);
		stockIn.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		stockIn.setBackground(new Color(100, 149, 237));
		
		stockChg = new JButton("¿Á∞Ìºˆ¡§");
		stockChg.setBounds(359, 28, 164, 85);
		add(stockChg);
		stockChg.setForeground(Color.WHITE);
		stockChg.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		stockChg.setBackground(new Color(0, 0, 128));
		
		stockevery = new JButton("∏µÁ¿Á∞Ì");
		stockevery.setBounds(547, 28, 164, 85);
		add(stockevery);
		stockevery.setForeground(Color.WHITE);
		stockevery.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		stockevery.setBackground(new Color(100, 149, 237));
		
		stockdelete = new JButton("ªÛ«∞ªË¡¶");
		stockdelete.setBounds(720, 28, 164, 85);
		add(stockdelete);
		stockdelete.setForeground(Color.WHITE);
		stockdelete.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		stockdelete.setBackground(new Color(0, 0, 128));
		
		
	}




}
