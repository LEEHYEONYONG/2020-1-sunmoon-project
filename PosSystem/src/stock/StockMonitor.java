package stock;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import main.MainFrame;

public class StockMonitor extends JPanel{
	public JScrollPane stockScrollPane = new JScrollPane();
	public Vector col = getColum();
	public DefaultTableModel tmodel = new DefaultTableModel(col, 0);
	public JTable StockTable = new JTable(tmodel);
	//StockDao dao = new StockDao();
	//public Vector<PosDto> mon = new Vector<PosDto>();
	
	
	public StockMonitor() {
//		col = getColum();

		setLayout(null);
		stockScrollPane.setBounds(12, 10, 1133, 532);
		add(stockScrollPane);

//		tmodel = new DefaultTableModel(col, 0);
		
		

//		StockTable = new JTable(tmodel);
		StockTable.setRowMargin(10);
		StockTable.setRowHeight(30);
		// 테이블 값 가운데 정렬
		//MainFrame.tableCellCenter(StockTable);

		StockTable.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		StockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		//showMon(dao.StockAll());
		
		stockScrollPane.setViewportView(StockTable);
		


		
	}
	
	
	
	
	private Vector getColum() {
		col = new Vector();
		col.add("상품코드");
		col.add("입고일자");
		col.add("상품명");
		col.add("수량");
		col.add("가격");
		col.add("유통기한");

		return col;
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




