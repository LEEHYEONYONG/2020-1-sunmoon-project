package account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class ViewAccount extends JPanel{
	
	public DefaultTableModel model;
	public JTable table;
	
	public ViewAccount() {
		setBounds(new Rectangle(0, 0, 1144, 535));
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
		backgroundpanel.setBounds(0, 0, 1144, 535);
		add(backgroundpanel);
		backgroundpanel.setLayout(null);
		
		String header[] = {"이름", "아이디", "비밀번호", "이메일", "직급"};
		String contents[][]= {
				
		};
		
		
		model = new DefaultTableModel(contents, header) {
			@Override
			public boolean isCellEditable(int row,int column) {
//				if (column == 1) {
//					return true;
//				} else {
					return false;
//				}
			}
		};
		
		table = new JTable(model);
		
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setRowMargin(10);
		table.setRowHeight(30);
		JScrollPane enroll = new JScrollPane(table);
		enroll.setEnabled(false);
		enroll.setBounds(0, 0, 1144, 380);
		backgroundpanel.add(enroll);
		
	}

}
