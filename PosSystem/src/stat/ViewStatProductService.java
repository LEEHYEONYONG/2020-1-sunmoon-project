package stat;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

import db.Connect_DB;
import db.PosDto;
import db.PosUse;

public class ViewStatProductService implements ActionListener {
	
	Vector<PosUse> bests = null; // Best5 ��ǰ �׷����� ���� ����
	Vector<PosUse> results = null; // �˻� ��� ��ǰ ���̺�� ���� ����
	Connect_DB connect_DB = new Connect_DB(); // Dao ��ü

	private String minorLevel = null; // �޺� �ڽ� ���� ����
	private String year = null;
	private String month = null;

	private ViewStatProduct vp;
	Connect_DB connect_db = new Connect_DB();

	// [������]
	public ViewStatProductService(ViewStatProduct vp) {
		this.vp = vp;
		setGraphbestProducts(); //�� �׷��� ����
	}

	// [ActionListener override/��ȸ��ư]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vp.btnSearch) {
			search();
		}
	}

	// [��� �޼ҵ�]
	// <��ȸ ��ư Ŭ��> �޼ҵ�
	public void search() {

		// ���̺� �� ȭ�� ����
		Connect_DB.clearRows(vp.tmodel.getRowCount(), vp.tmodel);
		
		// �޺��ڽ��� ���� ������
		// (�Һз�, ���۳⵵, ���⵵)
		minorLevel = vp.comboMinorLevel.getItemAt(vp.comboMinorLevel.getSelectedIndex()).toString();
		year = vp.comboYear.getItemAt(vp.comboYear.getSelectedIndex()).toString();
		month = vp.comboMonth.getItemAt(vp.comboMonth.getSelectedIndex()).toString();

		results = new Vector<PosUse>(); // select ����� ���õ� Dto Vector

		// select ��� ����
		results = connect_db.findProductSell(minorLevel, year, month); // DB select ��� ���� ����

		if (results.isEmpty()) { // ��ȸ ��� ������, �˸�â ����
			JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� �����ϴ�.");
		} else { // ��ȸ ��� ������, ��� ���̱�

			// ���̺� �� ����
			int size = results.size();
			for (int i = 0; i < size; i++) {
				Vector<String> rows = new Vector<String>(); // ��
				rows.addElement(Integer.toString(i+1));
				rows.addElement(results.get(i).getp_num());
				rows.addElement(results.get(i).getp_category());
				rows.addElement(results.get(i).getp_name());
				rows.addElement(Integer.toString(results.get(i).getp_cost()));
				rows.addElement(Integer.toString(results.get(i).getTotalamount()));
				rows.addElement(Integer.toString(results.get(i).getTotalproductprice()));
				rows.addElement(results.get(i).getp_provide());
				
				vp.tmodel.addRow(rows);
			}

			// ��� ���̺� ����
			vp.spShowTable.setViewportView(vp.tableResult);

			// �׷����� ���� ����
			setGraphbestProducts();

		}
	}

	// <���ǿ� �´� Best5 ��ǰ �׷��� ����> �޼ҵ�
	public void setGraphbestProducts() {
				
		DefaultPieDataset pieDataset = null;
		JFreeChart chart;
		
		bests = new Vector<PosUse>();
		if(results != null) {
			bests = connect_DB.findProductSellBestFive(minorLevel, year, month); // ��ŷ 5 �̱� sql�� ��� ����

			pieDataset = new DefaultPieDataset(); // ���� ��Ʈ �����ͼ� ����

			Vector<String> productName = new Vector<String>(); // ���������ġ
			Vector<Integer> totalSellPrice = new Vector<Integer>(); // 20000

			// �� ����
			int size = bests.size();
			for (int i = 0; i < size; i++) {

				productName.addElement(bests.get(i).getp_name());
				totalSellPrice.addElement(bests.get(i).getTotalproductprice());

				// ��, ����, ī�װ� ����
				pieDataset.setValue(productName.get(i), totalSellPrice.get(i));
			}
			
			// 3d ������Ʈ ���� (���Ǻ� �̸� ����)
			chart = ChartFactory.createPieChart3D
					(year + "." + month + " BEST 5", pieDataset, false, true,true);
		} else {
			//3d ������Ʈ ���� (�� �׷��� ��)
			chart = ChartFactory.createPieChart3D
					("BEST 5", pieDataset, false, true,true);			
		}
		chart.setBackgroundPaint(Color.lightGray); // ��Ʈ ���� ����

		PiePlot3D p = (PiePlot3D) chart.getPlot();
		p.setBaseSectionOutlinePaint(Color.WHITE); // ��Ʈ �׵θ� ��
		p.setDepthFactor(0.08f); // ��Ʈ �β�
		p.setLabelLinkPaint(Color.white); // ��Ʈ �󺧼� ��
		p.setLabelLinkStyle(PieLabelLinkStyle.QUAD_CURVE); // ��Ʈ �󺧼� ��Ÿ��
		p.setForegroundAlpha(0.7f); // ��Ʈ ����
		p.setBackgroundPaint(Color.DARK_GRAY); // ��Ʈ ����
		p.setLabelBackgroundPaint(Color.WHITE);// ��Ʈ �� ����
		p.setLabelFont(new Font("�������", Font.BOLD, 14)); // ��Ʈ �� ��Ʈ

		// ���� ��Ʈ�� ��Ʈ �ǳ� ����
		ChartPanel pChart = new ChartPanel(chart);
		pChart.setVisible(true);
		pChart.setSize(554, 451); // ũ�� ����

		vp.pShowGraph.add("bestGraph", pChart);
		vp.graphCard.show(vp.pShowGraph, "bestGraph");

		vp.pShowGraph.setVisible(true);

	}
	
	

}
