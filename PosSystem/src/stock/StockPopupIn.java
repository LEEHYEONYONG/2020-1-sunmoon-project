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

public class StockPopupIn extends JFrame {
	//StockDao dao = new StockDao();
	StockMonitor monitor = new StockMonitor();
	JPanel contentPane;
	public JTextField PcodeTf;
	public JTextField IndateTf;
	public JTextField PcntTf;
	JPanel pIn;
	JLabel pcodeL;
	JLabel indateL;
	JLabel pcntL;
	public JLabel pcodeResult;
	public JLabel indateResult;
	public JLabel pcntResult;
	public JButton pcodeInput;
	public JButton indateInput;
	public JButton pcntInput;
	public JButton inOk;
	public JButton inCancel;
	
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
		setTitle("\uC7AC\uACE0 \uAD00\uB9AC");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		contentPane.setLayout(null);
		
		pIn = new JPanel();
		pIn.setBounds(5, 5, 684, 455);
		contentPane.add(pIn);
		pIn.setLayout(null);
		
		pcodeL = new JLabel("»óÇ° ÄÚµå");
		pcodeL.setBackground(new Color(255, 165, 0));
		pcodeL.setHorizontalAlignment(SwingConstants.CENTER);
		pcodeL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeL.setBounds(14, 47, 120, 60);
		pcodeL.setOpaque(true);
		pIn.add(pcodeL);
		
		indateL = new JLabel("ÀÔ°í ÀÏÀÚ");
		indateL.setBackground(new Color(255, 165, 0));
		indateL.setHorizontalAlignment(SwingConstants.CENTER);
		indateL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		indateL.setBounds(14, 155, 120, 60);
		indateL.setOpaque(true);
		pIn.add(indateL);
		
		pcntL = new JLabel("¼ö·®");
		pcntL.setBackground(new Color(255, 165, 0));
		pcntL.setHorizontalAlignment(SwingConstants.CENTER);
		pcntL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntL.setBounds(14, 268, 120, 60);
		pcntL.setOpaque(true);
		pIn.add(pcntL);
		
		PcodeTf = new JTextField();
		PcodeTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcodeTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcodeTf.setBounds(146, 47, 230, 60);
		pIn.add(PcodeTf);
		PcodeTf.setColumns(10);
		
		IndateTf = new JTextField();
		IndateTf.setHorizontalAlignment(SwingConstants.CENTER);
		IndateTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		IndateTf.setColumns(10);
		IndateTf.setBounds(146, 155, 230, 60);
		pIn.add(IndateTf);
		
		PcntTf = new JTextField();
		PcntTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcntTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcntTf.setColumns(10);
		PcntTf.setBounds(146, 268, 230, 60);
		pIn.add(PcntTf);
		
		pcodeResult = new JLabel("");
		pcodeResult.setBackground(Color.LIGHT_GRAY);
		pcodeResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcodeResult.setBounds(497, 47, 177, 60);
		pcodeResult.setOpaque(true);
		pIn.add(pcodeResult);
		
		indateResult = new JLabel("");
		indateResult.setBackground(Color.LIGHT_GRAY);
		indateResult.setHorizontalAlignment(SwingConstants.CENTER);
		indateResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		indateResult.setBounds(497, 155, 177, 60);
		indateResult.setOpaque(true);
		pIn.add(indateResult);
		
		pcntResult = new JLabel("");
		pcntResult.setBackground(Color.LIGHT_GRAY);
		pcntResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcntResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntResult.setBounds(497, 268, 177, 60);
		pcntResult.setOpaque(true);
		pIn.add(pcntResult);
		
		pcodeInput = new JButton("ÀÔ·Â");
		pcodeInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeInput.setBounds(388, 47, 97, 60);
		pIn.add(pcodeInput);
		
		indateInput = new JButton("ÀÔ·Â");
		indateInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		indateInput.setBounds(388, 155, 97, 60);
		pIn.add(indateInput);
		
		pcntInput = new JButton("ÀÔ·Â");
		pcntInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntInput.setBounds(388, 268, 97, 60);
		pIn.add(pcntInput);
		
		inOk = new JButton("µî ·Ï");
		inOk.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inOk.setBounds(210, 373, 120, 60);
		pIn.add(inOk);
		
		inCancel = new JButton("Ãë ¼Ò");
		inCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inCancel.setBounds(342, 373, 120, 60);
		pIn.add(inCancel);
		
		JLabel label = new JLabel("6ÀÚ¸® ¼ýÀÚ");
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		label.setBackground(new Color(255, 165, 0));
		label.setBounds(14, 184, 120, 31);
		pIn.add(label);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				IndateTf.setText("");
				PcodeTf.setText("");
				PcntTf.setText("");
				pcodeResult.setText("");
				pcntResult.setText("");
				indateResult.setText("");
			}
		});
		
		
	}
 

}
