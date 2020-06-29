package stock;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;
import db.PosUse;
import main.MainFrame;
import java.awt.Color;

public class StockMonitor extends JPanel{
	public JScrollPane stockScrollPane = new JScrollPane();
	public Vector col=null;
	//public DefaultTableModel tmodel = new DefaultTableModel(col, 0);
	public DefaultTableModel tmodel=null;
	public JTable table=null;
	Connect_DB connect_db = new Connect_DB();
	public Vector<PosUse> mon = new Vector<PosUse>();
	
	String header[]= {"상품코드","상품명","수량","가격","종류","제조사"};
	String contents[][]= {
			
	};
	

	public StockMonitor() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JPanel backgroundpanel = new JPanel();
		backgroundpanel.setBounds(0, 0, 1144, 535);
		add(backgroundpanel);
		backgroundpanel.setLayout(null);
		
		
		/*
		tmodel=new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"상품코드", "상품명", "수량", "가격", "종류", "제조사"
				}
			)
        */
		
	    tmodel = new DefaultTableModel(contents,header) {
	    	@Override
			public boolean isCellEditable(int row,int column) {
				if (column == 1) {
					return true;
				} else {
					return false;
				}
			}
	    };
		
		table = new JTable(tmodel);
		
		table.setFillsViewportHeight(true);
		table.setRowMargin(10);
		table.setRowHeight(30);
		// 테이블 값 가운데 정렬
		MainFrame.tableCellCenter(table);
		
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stockScrollPane.setBounds(0, 0, 1133, 532);
	    backgroundpanel.add(stockScrollPane);
				
						
		
						
		stockScrollPane.setViewportView(table);
		table.setBackground(Color.WHITE);

		/*
		tmodel = new DefaultTableModel(contents, header) {
			@Override
			public boolean isCellEditable(int row,int column) {
				if (column == 1) {
					return true;
				} else {
					return false;
				}
			}
		};
		
		
		table = new JTable(tmodel);
		*/
		
		showMon(connect_db.StockAll());//재고테이블 화면에 보이기

	}
	
	public void showMon(Vector<PosUse> mon) {//재고테이블 화면에 보이기
		
		int size = mon.size();		
		this.mon = new Vector<PosUse>();
		this.mon=mon;
		
		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>();
			rows.addElement(mon.get(i).getp_num());
			rows.addElement(mon.get(i).getp_name());
			rows.addElement(Integer.toString(mon.get(i).getp_amount()));
			rows.addElement(Integer.toString(mon.get(i).getp_cost()));
			rows.addElement(mon.get(i).getp_category());
			rows.addElement(mon.get(i).getp_provide());

			tmodel.addRow(rows);
		}

		stockScrollPane.setViewportView(table);
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




