package stock;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.Connect_DB;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StockPopupIn extends JFrame {
	//StockDao dao = new StockDao();
	Connect_DB connect_db = new Connect_DB();
	StockMonitor monitor = new StockMonitor();
	JPanel contentPane;
	public JTextField PcodeTf;
	public JTextField InproductTf;
	public JTextField PcntTf;
	JPanel pIn;
	JLabel pcodeL;
	JLabel inproductL;
	JLabel pcntL;
	public JLabel pcodeResult;
	public JLabel inproductResult;
	public JLabel pcntResult;
	public JButton pcodeInput;
	public JButton inproductInput;
	public JButton pcntInput;
	public JButton inOk;
	public JButton inCancel;
	public JButton pcategoryInput;
	public JTextField PcategoryTf;
	private JLabel pprovideL;
	public JTextField PprovideTf;
	public JButton pprovideInput;
	public JLabel pprovideResult;
	public JLabel pcategoryResult;
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockPopupIn frame = new StockPopupIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
    
	
	public StockPopupIn() {
		setTitle("Àç°í °ü¸®");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		contentPane.setLayout(null);
		
		pIn = new JPanel();
		pIn.setBounds(5, 5, 684, 556);
		contentPane.add(pIn);
		pIn.setLayout(null);
		
		pcodeL = new JLabel("»óÇ° ÄÚµå");
		pcodeL.setBackground(new Color(255, 165, 0));
		pcodeL.setHorizontalAlignment(SwingConstants.CENTER);
		pcodeL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeL.setBounds(14, 46, 120, 60);
		pcodeL.setOpaque(true);
		pIn.add(pcodeL);
		
		inproductL = new JLabel("»óÇ°¸í");
		inproductL.setBackground(new Color(255, 165, 0));
		inproductL.setHorizontalAlignment(SwingConstants.CENTER);
		inproductL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inproductL.setBounds(14, 116, 120, 60);
		inproductL.setOpaque(true);
		pIn.add(inproductL);
		
		pcntL = new JLabel("°¡°Ý");
		pcntL.setBackground(new Color(255, 165, 0));
		pcntL.setHorizontalAlignment(SwingConstants.CENTER);
		pcntL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntL.setBounds(14, 186, 120, 60);
		pcntL.setOpaque(true);
		pIn.add(pcntL);
		
		JLabel pcategoryL = new JLabel("Á¾·ù");
		pcategoryL.setOpaque(true);
		pcategoryL.setHorizontalAlignment(SwingConstants.CENTER);
		pcategoryL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcategoryL.setBackground(new Color(255, 165, 0));
		pcategoryL.setBounds(14, 256, 120, 60);
		pIn.add(pcategoryL);
		
		pprovideL = new JLabel("Á¦Á¶»ç");
		pprovideL.setOpaque(true);
		pprovideL.setHorizontalAlignment(SwingConstants.CENTER);
		pprovideL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pprovideL.setBackground(new Color(255, 165, 0));
		pprovideL.setBounds(14, 326, 120, 60);
		pIn.add(pprovideL);
		
		PcodeTf = new JTextField();
		PcodeTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcodeTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcodeTf.setBounds(146, 47, 230, 60);
		pIn.add(PcodeTf);
		PcodeTf.setColumns(10);
		
		InproductTf = new JTextField();
		InproductTf.setHorizontalAlignment(SwingConstants.CENTER);
		InproductTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		InproductTf.setColumns(10);
		InproductTf.setBounds(146, 119, 230, 60);
		pIn.add(InproductTf);
		
		PcntTf = new JTextField();
		PcntTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcntTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcntTf.setColumns(10);
		PcntTf.setBounds(146, 187, 230, 60);
		pIn.add(PcntTf);
		
		PcategoryTf = new JTextField();
		PcategoryTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcategoryTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcategoryTf.setColumns(10);
		PcategoryTf.setBounds(146, 256, 230, 60);
		pIn.add(PcategoryTf);
		
		PprovideTf = new JTextField();
		PprovideTf.setHorizontalAlignment(SwingConstants.CENTER);
		PprovideTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PprovideTf.setColumns(10);
		PprovideTf.setBounds(146, 326, 230, 60);
		pIn.add(PprovideTf);
		
		pcodeResult = new JLabel("");
		pcodeResult.setBackground(Color.LIGHT_GRAY);
		pcodeResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcodeResult.setBounds(497, 47, 177, 60);
		pcodeResult.setOpaque(true);
		pIn.add(pcodeResult);
		
		inproductResult = new JLabel("");
		inproductResult.setBackground(Color.LIGHT_GRAY);
		inproductResult.setHorizontalAlignment(SwingConstants.CENTER);
		inproductResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inproductResult.setBounds(497, 116, 177, 60);
		inproductResult.setOpaque(true);
		pIn.add(inproductResult);
		
		pcntResult = new JLabel("");
		pcntResult.setBackground(Color.LIGHT_GRAY);
		pcntResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcntResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntResult.setBounds(497, 186, 177, 60);
		pcntResult.setOpaque(true);
		pIn.add(pcntResult);
		
		pcategoryResult = new JLabel("");
		pcategoryResult.setOpaque(true);
		pcategoryResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcategoryResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcategoryResult.setBackground(Color.LIGHT_GRAY);
		pcategoryResult.setBounds(497, 256, 177, 60);
		pIn.add(pcategoryResult);
		
		pprovideResult = new JLabel("");
		pprovideResult.setOpaque(true);
		pprovideResult.setHorizontalAlignment(SwingConstants.CENTER);
		pprovideResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pprovideResult.setBackground(Color.LIGHT_GRAY);
		pprovideResult.setBounds(497, 326, 177, 60);
		pIn.add(pprovideResult);
		
		pcodeInput = new JButton("ÀÔ·Â");
		pcodeInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeInput.setBounds(388, 47, 97, 60);
		pIn.add(pcodeInput);
		
		inproductInput = new JButton("ÀÔ·Â");
		inproductInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inproductInput.setBounds(388, 116, 97, 60);
		pIn.add(inproductInput);
		
		pcntInput = new JButton("ÀÔ·Â");
		pcntInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntInput.setBounds(388, 186, 97, 60);
		pIn.add(pcntInput);
		
		pcategoryInput = new JButton("\uC785\uB825");
		pcategoryInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcategoryInput.setBounds(388, 256, 97, 60);
		pIn.add(pcategoryInput);
		
		pprovideInput = new JButton("\uC785\uB825");
		pprovideInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pprovideInput.setBounds(388, 326, 97, 60);
		pIn.add(pprovideInput);
		
		inOk = new JButton("µî ·Ï");
		inOk.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inOk.setBounds(208, 486, 120, 60);
		pIn.add(inOk);
		
		inCancel = new JButton("Ãë ¼Ò");
		inCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inCancel.setBounds(341, 486, 120, 60);
		pIn.add(inCancel);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				InproductTf.setText("");
				PcodeTf.setText("");
				PcntTf.setText("");
				PcategoryTf.setText("");
				PprovideTf.setText("");
				
				pcodeResult.setText("");
				pcntResult.setText("");
				inproductResult.setText("");
				pcategoryResult.setText("");
				pprovideResult.setText("");
			}
		});
		
		
	}
}
