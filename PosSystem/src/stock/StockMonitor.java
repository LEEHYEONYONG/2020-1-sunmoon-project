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
		// ���̺� �� ��� ����
		//MainFrame.tableCellCenter(StockTable);

		StockTable.setFont(new Font("���� ���", Font.PLAIN, 15));
		StockTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		//showMon(dao.StockAll());
		
		stockScrollPane.setViewportView(StockTable);
		


		
	}
	
	
	
	
	private Vector getColum() {
		col = new Vector();
		col.add("��ǰ�ڵ�");
		col.add("�԰�����");
		col.add("��ǰ��");
		col.add("����");
		col.add("����");
		col.add("�������");

		return col;
	}
	
	
	//���̺� �� �����. ȭ��ܿ�����	
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}

}




