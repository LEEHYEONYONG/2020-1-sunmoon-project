package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

import db.Connect_DB;

public class Login extends JFrame implements ActionListener{
	
	Connect_DB connect_db =new Connect_DB();
	
	//public MainFrame mainFrame;// = new MainFrame(); // ���� ������
	public JPanel p;
	public Label Title;
	public JButton BtnLogin;
	public Label Password;
	public Label Id;
	public TextField Id_T;
	public TextField Password_T;
	public JButton btnExit;
	
	
	Image img = null;
	public Login()
	{
		
		setBounds(10, 15, 622, 347);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		p = new JPanel();
		p.setBackground(new Color(32, 178, 170));
        p.setLayout(null);
		getContentPane().add(p);
		
		
		Title= new Label("\uD3B8\uC758\uC810 \uD3EC\uC2A4");
		Title.setForeground(new Color(255, 255, 255));
		Title.setAlignment(Label.CENTER);
		Title.setFont(new Font("���� ���", Font.BOLD, 48));
		p.add(Title);
		Title.setBounds(0, 35, 604, 60);
		
		
		BtnLogin = new JButton("�α���");
		BtnLogin.setForeground(Color.WHITE);
		BtnLogin.setBackground(Color.BLACK);
		p.add(BtnLogin);
		BtnLogin.setBounds(147, 238, 122, 51);
		
		
		Password= new Label("\uBE44\uBC00\uBC88\uD638");
		Password.setForeground(Color.WHITE);
		Password.setAlignment(Label.CENTER);
		Password.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(Password);
		Password.setBounds(147, 180, 122, 33);
		
		
		Id= new Label("\uC544\uC774\uB514");
		Id.setForeground(Color.WHITE);
		Id.setAlignment(Label.CENTER);
		Id.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(Id);
		Id.setBounds(147, 114, 122, 33);
		
		
		
		Id_T = new TextField();
		Id_T.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(Id_T);
		Id_T.setBounds(279, 114, 200, 33);
		
		
		Password_T = new TextField();
		Password_T.setFont(new Font("Dialog", Font.BOLD, 20));
		p.add(Password_T);
		Password_T.setEchoChar('*');//��ȣȭ
		Password_T.setBounds(279, 180, 200, 33);
		
		btnExit = new JButton("����");
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.BLACK);
		btnExit.setBounds(357, 238, 122, 51);
		p.add(btnExit);
		/*
		b6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e2) {//�α��� �Ҷ�
				
				boolean checklogin=connect_db.loginpass(b4.getText(), b5.getText());
				
				
				if(b4.getText()==null || b5.getText()==null) {
					JOptionPane.showMessageDialog(p, "�Է����� ���� ���̵� �Ǵ� ��й�ȣ�� �ֽ��ϴ�.", "��ĭ����", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else {
				
					if(checklogin==false) {
						JOptionPane.showMessageDialog(p, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "�α��� ����", JOptionPane.WARNING_MESSAGE);
						return;
					}
					else {
						
						MainFrame drawing = new MainFrame ();
						dispose();
						
						mainFrame.setVisible(true);
						this.setVisible(false);
					}
				
				}
				
			}
		});
		*/
		
		setSize(622, 347);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("�α��� ȭ�� ");
		setVisible(true);
		setLocationRelativeTo(null);//ȭ�� ����� ����
		
		
		
		Id_T.addActionListener(this);
		Password_T.addActionListener(this);
		BtnLogin.addActionListener(this);
		btnExit.addActionListener(this);
		
		
	}
	
	
	public static void main(String[] args) {//�α���ȭ������ ����
		// TODO Auto-generated method stub
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*
					ForcePos forcePos = new ForcePos();
					forcePos.setVisible(true);
					*/
					Login f = new Login();
					f.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object ob = e.getSource();
		
		if(ob==BtnLogin) {
			
			boolean checklogin=connect_db.loginpass(Id_T.getText(), Password_T.getText());
			
			
			if(Id_T.getText().trim().isEmpty() || Password_T.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(p, "�Է����� ���� ���̵� �Ǵ� ��й�ȣ�� �ֽ��ϴ�.", "�Է¿���", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			
			if(checklogin==false) {
				JOptionPane.showMessageDialog(p, "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.", "�α��� ����", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else {
					
				MainFrame mainframe = new MainFrame ();
				//dispose();
				mainframe.setVisible(true);
				setVisible(false);
					
				//mainFrame.setVisible(true);
			    //this.setVisible(false);
		    }
			
			
			
		}else if(ob==btnExit) {
			System.exit(0);
		}
		
		
	}
}
