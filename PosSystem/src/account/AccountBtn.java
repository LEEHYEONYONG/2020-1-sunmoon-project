package account;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountBtn extends JPanel {
	
	public JButton AccountChg;
	public JButton AccountIn;
	public JButton AccountSearch;
	public JButton Accountevery;
	
	public AccountBtn() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		AccountSearch = new JButton("∞¸∏Æ¿⁄∫Ø∞Ê");
		AccountSearch.setBounds(0,28, 164, 85);
		add(AccountSearch);
		AccountSearch.setForeground(Color.WHITE);
		AccountSearch.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		AccountSearch.setBackground(new Color(0, 0, 128));
		
		AccountIn = new JButton("∞Ë¡§ªË¡¶");
		AccountIn.setBounds(176, 28, 164, 85);
		add(AccountIn);
		AccountIn.setForeground(Color.WHITE);
		AccountIn.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		AccountIn.setBackground(new Color(100, 149, 237));
		
		AccountChg = new JButton("∞Ë¡§√ﬂ∞°");
		AccountChg.setBounds(359, 28, 164, 85);
		add(AccountChg);
		AccountChg.setForeground(Color.WHITE);
		AccountChg.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		AccountChg.setBackground(new Color(0, 0, 128));
		
		Accountevery = new JButton("∞Ë¡§∫Ø∞Ê");
		Accountevery.setBounds(547, 28, 164, 85);
		add(Accountevery);
		Accountevery.setForeground(Color.WHITE);
		Accountevery.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		Accountevery.setBackground(new Color(100, 149, 237));
		
		
	}

}
