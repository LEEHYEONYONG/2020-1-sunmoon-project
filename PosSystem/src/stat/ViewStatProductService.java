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

import db.PosDto;

public class ViewStatProductService implements ActionListener {
	
	Vector<PosDto> bests = null; // Best5 ��ǰ �׷����� ���� ����

	Vector<PosDto> results = null; // �˻� ��� ��ǰ ���̺�� ���� ����
	//StatDao statDao = new StatDao(); // Dao ��ü

	private String minorLevel = null; // �޺� �ڽ� ���� ����
	private String year = null;
	private String month = null;

	private ViewStatProduct vp;

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
		//StatDao.clearRows(vp.tmodel.getRowCount(), vp.tmodel);
		
		// �޺��ڽ��� ���� ������
		// (�Һз�, ���۳⵵, ���⵵)
		minorLevel = vp.comboMinorLevel.getItemAt(vp.comboMinorLevel.getSelectedIndex()).toString();
		year = vp.comboYear.getItemAt(vp.comboYear.getSelectedIndex()).toString();
		month = vp.comboMonth.getItemAt(vp.comboMonth.getSelectedIndex()).toString();

		results = new Vector<PosDto>(); // select ����� ���õ� Dto Vector

		// select ��� ����
		//results = statDao.findProductSell(minorLevel, year, month); // DB select ��� ���� ����

		if (results.isEmpty()) { // ��ȸ ��� ������, �˸�â ����
			JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� �����ϴ�.");
		} else { // ��ȸ ��� ������, ��� ���̱�

			// ���̺� �� ����
			int size = results.size();
			for (int i = 0; i < size; i++) {
				Vector<String> rows = new Vector<String>(); // ��
				rows.addElement(Integer.toString(results.get(i).getRanking()));
				rows.addElement(results.get(i).getProductCode());
				rows.addElement(results.get(i).getMinorLevel());
				rows.addElement(results.get(i).getProductName());
				rows.addElement(Integer.toString(results.get(i).getPrice()));
				rows.addElement(Integer.toString(results.get(i).getPurchase()));
				rows.addElement(Integer.toString(results.get(i).getSellCount()));
				rows.addElement(Integer.toString(results.get(i).getStatTotalPrice()));
				rows.addElement(results.get(i).getCompany());

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
		
		bests = new Vector<PosDto>();
		if(results != null) {
			//bests = statDao.findProductSellBestFive(minorLevel, year, month); // ��ŷ 5 �̱� sql�� ��� ����

			pieDataset = new DefaultPieDataset(); // ���� ��Ʈ �����ͼ� ����

			Vector<String> productName = new Vector<String>(); // ���������ġ
			Vector<Integer> totalSellPrice = new Vector<Integer>(); // 20000

			// �� ����
			int size = bests.size();
			for (int i = 0; i < size; i++) {

				productName.addElement(bests.get(i).getProductName());
				totalSellPrice.addElement(bests.get(i).getStatTotalPrice());

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
