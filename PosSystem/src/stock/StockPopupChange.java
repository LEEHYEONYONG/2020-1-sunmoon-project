package stock;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class StockPopupChange extends JFrame {
	//StockDao dao = new StockDao();
	StockMonitor monitor = new StockMonitor();
	JPanel contentPane;
	public JTextField ChgCountTf;
	JPanel pChg;
	public JLabel selecItem;
	JLabel chgCountL;
	public JButton chgOk;
	public JButton chgCancel;
	
	
	/*
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				StockPopupChange frame = new StockPopupChange();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
	
	*/
	
	public StockPopupChange() {
		setTitle("Àç°í °ü¸®");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		contentPane.setLayout(null);
		
		pChg = new JPanel();
		pChg.setBounds(5, 5, 684, 455);
		contentPane.add(pChg);
		pChg.setLayout(null);
		
		selecItem = new JLabel("¼±ÅÃÇÑ ÇàÀÇ »óÇ°Á¤º¸");
		selecItem.setHorizontalAlignment(SwingConstants.CENTER);
		selecItem.setBackground(new Color(221, 160, 221));
		selecItem.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 16));
		selecItem.setBounds(12, 48, 660, 100);
		selecItem.setOpaque(true);
		pChg.add(selecItem);
		
		chgCountL = new JLabel("¼öÁ¤ÇÒ ¼ö·®");
		chgCountL.setBackground(new Color(221, 160, 221));
		chgCountL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		chgCountL.setHorizontalAlignment(SwingConstants.CENTER);
		chgCountL.setBounds(115, 206, 140, 60);
		chgCountL.setOpaque(true);
		pChg.add(chgCountL);
		
		ChgCountTf = new JTextField();
		ChgCountTf.setHorizontalAlignment(SwingConstants.CENTER);
		ChgCountTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		ChgCountTf.setBounds(267, 206, 300, 60);
		pChg.add(ChgCountTf);
		ChgCountTf.setColumns(10);
		
		chgOk = new JButton("¿Ï ·á");
		chgOk.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		chgOk.setBounds(212, 362, 120, 60);
		pChg.add(chgOk);
		
		chgCancel = new JButton("Ãë ¼Ò");
		chgCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		chgCancel.setBounds(344, 362, 120, 60);
		pChg.add(chgCancel);
		
		
		
		
	}

}
