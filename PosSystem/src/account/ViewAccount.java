package account;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;
import db.PosUse;
import javax.swing.ListSelectionModel;

public class ViewAccount extends JPanel{
	
	public DefaultTableModel model;
	public JTable table;
	public JPanel backgroundpanel;
	public JScrollPane enroll;
	Connect_DB connect_db = new Connect_DB();
	public Vector<PosUse> mon = new Vector<PosUse>();
	
	public ViewAccount() {
		setBounds(new Rectangle(0, 0, 1144, 535));
		setBackground(Color.WHITE);
		setLayout(null);
		
		backgroundpanel = new JPanel();
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
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBackground(Color.WHITE);
		table.setRowMargin(10);
		table.setRowHeight(30);
		
		
		enroll = new JScrollPane(table);
		enroll.setEnabled(false);
		enroll.setBounds(0, 0, 1144, 535);
		backgroundpanel.add(enroll);
		
		
		
		showMon(connect_db.AccountAll());//계정화면띄우기
		
	}
	
	
	
	public void showMon(Vector<PosUse> mon) {

		int size = mon.size();
		this.mon = new Vector<PosUse>();
		this.mon = mon;

		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>();
			rows.addElement(mon.get(i).getUser_name());
			rows.addElement(mon.get(i).getID());
			rows.addElement(mon.get(i).getPW());
			rows.addElement(mon.get(i).getUser_email());
			rows.addElement(mon.get(i).getRank());

			model.addRow(rows);
		}

		enroll.setViewportView(table);
	}
	
	//테이블 행 지우기. 화면단에서만	
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}

}
