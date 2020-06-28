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

public class SignUpChange extends JFrame {
	
	
	public JPasswordField passwordField;//회원가입화면
	public JPasswordField passwordField_Check;
	public JPanel p;
	public TextField email;
	public JButton No;
	public Label Email1;
	public Label Password1;
	public Label Name1;
	public TextField name;
	public Label label1;
	public JButton Yes;
	public Label Password1_1;
	public Label lblIdCheck;
	public TextField txtIDCheck;
	
	public SignUpChange(){
		
		
		setBounds(100,100, 500, 472);
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
	    email.setBounds(211, 208, 190, 30);
	    p.add(email);

	 
	    No = new JButton("취소");
	    No.setFont(new Font("굴림", Font.BOLD, 15));
	    p.add(No);
	    No.setBounds(261, 342, 140, 69); 
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
	    Email1.setBounds(10, 208, 195, 30);
	    
	    Password1= new Label("\uBE44\uBC00\uBC88\uD638\uBCC0\uACBD");
	    Password1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Password1.setAlignment(Label.CENTER);
	    p.add(Password1);
	    Password1.setBounds(10,257,195,30);
	    
	    
	    Name1= new Label("이름");
	    Name1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Name1.setAlignment(Label.CENTER);
	    p.add(Name1);
	    Name1.setBounds(10, 113, 195, 30);
	    
	    
	    
	    name = new TextField();
	    name.setFont(new Font("Dialog", Font.PLAIN, 18));
	    p.add(name);
	    name.setBounds(211, 113, 190, 30);
	    
	    
	    
	    label1 = new Label("\uACC4\uC815\uC218\uC815");
	    label1.setFont(new Font("Dialog", Font.BOLD, 48));
	    label1.setAlignment(Label.CENTER);
	    label1.setBounds(0, 10, 494, 70);
	    p.add(label1);
	    
	    
	    
	    Yes = new JButton("\uACC4\uC815\uC218\uC815\uC644\uB8CC");
	    Yes.setFont(new Font("굴림", Font.BOLD, 15));
	    p.add(Yes);
	    Yes.setBounds(82, 342, 140, 69);
	    
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
	    passwordField.setBounds(211, 257, 190, 30);
	    p.add(passwordField);
	    
	    
	    Password1_1 = new Label("\uBE44\uBC00\uBC88\uD638\uD655\uC778");
	    Password1_1.setFont(new Font("Dialog", Font.BOLD, 18));
	    Password1_1.setAlignment(Label.CENTER);
	    Password1_1.setBounds(10, 297, 195, 30);
	    p.add(Password1_1);
	    
	    
	    
	    passwordField_Check = new JPasswordField();
	    passwordField_Check.setBounds(211, 297, 190, 30);
	    p.add(passwordField_Check);
	    
	    lblIdCheck = new Label("\uC544\uC774\uB514");
	    lblIdCheck.setFont(new Font("Dialog", Font.BOLD, 18));
	    lblIdCheck.setAlignment(Label.CENTER);
	    lblIdCheck.setBounds(10, 160, 195, 30);
	    p.add(lblIdCheck);
	    
	    txtIDCheck = new TextField();
	    txtIDCheck.setEditable(false);
	    txtIDCheck.setFont(new Font("Dialog", Font.PLAIN, 18));
	    txtIDCheck.setBounds(211, 160, 190, 30);
	    p.add(txtIDCheck);
	    
	    
	}
	
	
	
	
}
