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

public class StockPopupSearch extends JFrame{
	
	JPanel contentPane;
	public JTextField SearchCode;
	public JTextField SearchName;
	JPanel pSearch = new JPanel();
	JLabel searchCodeL;
	JLabel searchNameL;
	public JButton searchCancel;
	public JButton sCbtn;
	public JButton sNbtn;
	//StockDao dao = new StockDao();
	StockMonitor monitor = new StockMonitor();
	
	
	
	public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				StockPopupSearch frame = new StockPopupSearch();
				frame.setVisible(true);
			} catch (Exception e) {				
			    e.printStackTrace();
			}
		}
	});
    }
	
	public StockPopupSearch() {
		
		setTitle("재고 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		contentPane.setLayout(null);
		pSearch.setBounds(5, 5, 684, 455);
		
		pSearch.setLayout(null);
		
		searchCodeL = new JLabel("조회할 상품 코드");
		searchCodeL.setBackground(new Color(0, 250, 154));
		searchCodeL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		searchCodeL.setHorizontalAlignment(SwingConstants.CENTER);
		searchCodeL.setBounds(12, 75, 190, 80);
		searchCodeL.setOpaque(true);
		pSearch.add(searchCodeL);
		
		searchNameL = new JLabel("조회할 상품명");
		searchNameL.setBackground(new Color(0, 250, 154));
		searchNameL.setHorizontalAlignment(SwingConstants.CENTER);
		searchNameL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		searchNameL.setBounds(12, 215, 190, 80);
		searchNameL.setOpaque(true);
		pSearch.add(searchNameL);
		
		SearchCode = new JTextField();
		SearchCode.setBounds(214, 75, 293, 80);
		SearchCode.setColumns(10);
		pSearch.add(SearchCode);
		
		SearchName = new JTextField();
		SearchName.setColumns(10);
		SearchName.setBounds(214, 215, 293, 80);
		pSearch.add(SearchName);
		
		searchCancel = new JButton("취 소");
		searchCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		searchCancel.setBounds(279, 368, 131, 63);
		pSearch.add(searchCancel);
		
		sCbtn = new JButton("상품코드로 조회");
		sCbtn.setBounds(519, 75, 153, 80);
		pSearch.add(sCbtn);
		
		sNbtn = new JButton("상품명으로 조회");
		sNbtn.setBounds(519, 215, 153, 80);
		pSearch.add(sNbtn);
		
		contentPane.add(pSearch);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				SearchCode.setText("");
				SearchName.setText("");
			}
		});
		
	}
  
 

}
