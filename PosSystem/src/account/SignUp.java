package account;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class SignUp extends JFrame {
	
	
	public JPasswordField passwordField;//회원가입화면
	public JPasswordField passwordField_Check;
	public JPanel p;
	public TextField email;
	public JButton No;
	public Label Email1;
	public Label Password1;
	public TextField id;
	public Label Id1;
	public Label Name1;
	public TextField name;
	public Label label1;
	public JButton Yes;
	public Label Password1_1;
	public JButton btnIdCheckButton;
	public Label lblIdCheck;
	public TextField txtIDCheck;
	
	public SignUp(){
		
		
		setBounds(100,100, 500, 555);
		setTitle("회원가입");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setVisible(true);
        setLocationRelativeTo(null);//화면 가운데에 띄우기
        setResizable(false);
		
		
		
		
		p = new JPanel();
	    getContentPane().add(p);
	    p.setLayout(null);
	    
	    
	    email = new TextField();
	    email.setFont(new Font("Dialog", Font.PLAIN, 18));
	    email.setBounds(160, 253, 190, 30);
	    p.add(email);

	 
	    No = new JButton("취소");
	    No.setFont(new Font("굴림", Font.BOLD, 15));
	    p.add(No);
	    No.setBounds(261, 428, 140, 69); 
	    /*
	    No.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		dispose();
	    	}
	    	
	    });
	    */
	    
	    Email1 = new Label("\uC774\uBA54\uC77C\uC8FC\uC18C");
	    Email1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Email1.setAlignment(Label.CENTER);
	    p.add(Email1);
	    Email1.setBounds(10, 253, 144, 30);
	    
	    Password1= new Label("\uBE44\uBC00\uBC88\uD638");
	    Password1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Password1.setAlignment(Label.CENTER);
	    p.add(Password1);
	    Password1.setBounds(10,305,144,30);
	    
	    id = new TextField();
	    id.setFont(new Font("Dialog", Font.PLAIN, 18));
	    p.add(id);
	    id.setBounds(160, 162, 190, 30);
	    
	    
	    Id1 = new Label("아이디");
	    Id1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Id1.setAlignment(Label.CENTER);
	    p.add(Id1);
	    Id1.setBounds(10, 162, 144, 30);
	    
	    
	    Name1= new Label("이름");
	    Name1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Name1.setAlignment(Label.CENTER);
	    p.add(Name1);
	    Name1.setBounds(10, 113, 144, 30);
	    
	    
	    
	    name = new TextField();
	    name.setFont(new Font("Dialog", Font.PLAIN, 18));
	    p.add(name);
	    name.setBounds(160, 113, 190, 30);
	    
	    
	    
	    label1 = new Label("\uD68C\uC6D0\uAC00\uC785");
	    label1.setFont(new Font("Dialog", Font.BOLD, 48));
	    label1.setAlignment(Label.CENTER);
	    label1.setBounds(0, 10, 482, 70);
	    p.add(label1);
	    
	    
	    
	    Yes = new JButton("\uD68C\uC6D0\uAC00\uC785\uC644\uB8CC");
	    Yes.setFont(new Font("굴림", Font.BOLD, 15));
	    p.add(Yes);
	    Yes.setBounds(80, 428, 140, 69);
	    
	    /*
	    Yes.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent arg0) {
	    		String nameTxt = name.getText();
	    		String idTxt = id.getText();
	    		String passwordTxt = String.valueOf(passwordField.getPassword());
	    		String emailTxt = email.getText();
	    	}
	    	
	    });
	    */
	    
	    
	    passwordField = new JPasswordField();
	    passwordField.setBounds(160, 305, 190, 30);
	    p.add(passwordField);
	    
	    
	    Password1_1 = new Label("\uBE44\uBC00\uBC88\uD638\uD655\uC778");
	    Password1_1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Password1_1.setAlignment(Label.CENTER);
	    Password1_1.setBounds(10, 360, 144, 30);
	    p.add(Password1_1);
	    
	    
	    
	    passwordField_Check = new JPasswordField();
	    passwordField_Check.setBounds(160, 360, 190, 30);
	    p.add(passwordField_Check);
	    
	    
	    
	    btnIdCheckButton = new JButton("\uC911\uBCF5 \uD655\uC778");
	    btnIdCheckButton.setBounds(356, 162, 116, 30);
	    p.add(btnIdCheckButton);
	    
	    lblIdCheck = new Label("\uC544\uC774\uB514\uD655\uC778");
	    lblIdCheck.setFont(new Font("Dialog", Font.BOLD, 18));
	    lblIdCheck.setAlignment(Label.CENTER);
	    lblIdCheck.setBounds(10, 207, 144, 30);
	    p.add(lblIdCheck);
	    
	    txtIDCheck = new TextField();
	    txtIDCheck.setEditable(false);
	    txtIDCheck.setFont(new Font("Dialog", Font.PLAIN, 18));
	    txtIDCheck.setBounds(160, 207, 190, 30);
	    p.add(txtIDCheck);
	    
	    
	}
	
	
	
	
}
