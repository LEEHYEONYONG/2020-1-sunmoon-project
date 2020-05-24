package sale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DealCancel extends JFrame {//ȯ�� GUI
	JPanel contentPane;
	public JTextField Sell_id;
	public JButton btnRefund;
	
   /*
   public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
	public void run() {
			try {
				DealCancel frame = new DealCancel();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
   }
   */
	
	public DealCancel() {
		setTitle("ȯ��");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel CancelLabel = new JLabel("�ŷ� ���");
		CancelLabel.setFont(new Font("����", Font.PLAIN, 24));
		CancelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(CancelLabel, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel LCancel = new JLabel("�ŷ���ȣ : ");
		LCancel.setFont(new Font("����", Font.PLAIN, 16));
		LCancel.setHorizontalAlignment(SwingConstants.RIGHT);
		LCancel.setBounds(12, 23, 112, 39);
		panel_2.add(LCancel);
		
		Sell_id = new JTextField();
		Sell_id.setBounds(136, 23, 253, 39);
		panel_2.add(Sell_id);
		Sell_id.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(null);
		
		btnRefund = new JButton("ȯ   ��");
		btnRefund.setBounds(162, 10, 97, 64);
		panel_3.add(btnRefund);
		
		
	}

}
