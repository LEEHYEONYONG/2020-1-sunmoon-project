package account;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountBtn extends JPanel {
	public JButton AccountDelete;
	public JButton AccountSignUp;
	public JButton AccountChange;
	
	public AccountBtn() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		AccountSignUp = new JButton("∞Ë¡§√ﬂ∞°");
		AccountSignUp.setBounds(0,28, 164, 85);
		add(AccountSignUp);
		AccountSignUp.setForeground(Color.WHITE);
		AccountSignUp.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		AccountSignUp.setBackground(new Color(0, 0, 128));
		/*
		AccountSignUp.addActionListener( new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
            	SignUp f3 = new SignUp();
            }
		});
		*/
		
		AccountDelete = new JButton("∞Ë¡§ªË¡¶");
		AccountDelete.setBounds(176, 28, 164, 85);
		add(AccountDelete);
		AccountDelete.setForeground(Color.WHITE);
		AccountDelete.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		AccountDelete.setBackground(new Color(100, 149, 237));
		
		AccountChange = new JButton("∞Ë¡§∫Ø∞Ê");
		AccountChange.setBounds(350, 28, 164, 85);
		add(AccountChange);
		AccountChange.setForeground(Color.WHITE);
		AccountChange.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 20));
		AccountChange.setBackground(new Color(100, 149, 237));
		
	}

}
