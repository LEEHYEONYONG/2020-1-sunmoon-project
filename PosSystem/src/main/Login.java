package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class Login extends JFrame{
	
	Image img = null;
	public Login()
	{
		JPanel p = new JPanel();
		p.setBackground(new Color(32, 178, 170));
        p.setLayout(null);
		getContentPane().add(p);
		Label t1= new Label("\uD3B8\uC758\uC810 \uD3EC\uC2A4");
		t1.setForeground(new Color(255, 255, 255));
		t1.setAlignment(Label.CENTER);
		t1.setFont(new Font("맑은 고딕", Font.BOLD, 48));
		p.add(t1);
		t1.setBounds(0, 35, 604, 60);
		JButton b6 = new JButton("로그인");
		b6.setForeground(Color.WHITE);
		b6.setBackground(Color.BLACK);
		p.add(b6);
		b6.setBounds(244, 238, 122, 51);
		Label b3= new Label("\uBE44\uBC00\uBC88\uD638 :");
		b3.setForeground(Color.WHITE);
		b3.setAlignment(Label.RIGHT);
		b3.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(b3);
		b3.setBounds(147, 169, 100, 49);
		Label b2= new Label("\uC544\uC774\uB514 :");
		b2.setForeground(Color.WHITE);
		b2.setAlignment(Label.RIGHT);
		b2.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(b2);
		b2.setBounds(165, 101, 82, 49);
		
		TextField b4 = new TextField();
		b4.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(b4);
		b4.setBounds(279, 114, 200, 33);
		
		TextField b5 = new TextField();
		b5.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(b5);
		b5.setEchoChar('*');//암호화
		b5.setBounds(279, 180, 200, 33);
		b6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e2) {//로그인 할때 
				MainFrame drawing = new MainFrame ();
				dispose();
			}
		});
		setSize(622, 348);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("로그인 화면 ");
		setVisible(true);
		setLocationRelativeTo(null);//화면 가운데에 띄우기
		
	}
	
	
	public static void main(String[] args) {//로그인화면으로 실행
		// TODO Auto-generated method stub
		Login f = new Login();
	
	}
	

}
