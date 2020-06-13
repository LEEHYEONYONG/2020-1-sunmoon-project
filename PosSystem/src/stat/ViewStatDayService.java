package stat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import db.PosDto;

public class ViewStatDayService implements ActionListener, ItemListener{


	private Vector<PosDto> results; // ��ȸ���� �Ǵܿ� �������� (����� ���̺���)

	private String statType = "�����հ�"; // ������ư �� (����Ʈ�� "�����հ�")

	private ViewStatDay vd;

	// [������]
	public ViewStatDayService(ViewStatDay vd) {
		this.vd = vd;

		setChart(statType);
	}

	// [ActionListener override/��ȸ��ư]
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == vd.btnSearch) {
			search();
		}
	}

	// [ItemListener override/������ư]
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getSource();
		JRadioButton rb = (JRadioButton) ob;
		statType = rb.getText();

		// ���õ� ���� ��ư�� �̸� �� ��, �´� ��Ʈ ����
		if (statType.equals("�����հ�")) {
			setChart(statType); // ��Ʈ ���� & pShowGraph�г��� ī�� ���̾ƿ����� show()
		} else if (statType.equals("����")) {
			setChart(statType);
		}

	}

	// [��� �޼ҵ�]
	// <��ȸ ��ư Ŭ��> �̺�Ʈ
	public void search() {

		// ���̺� �� ȭ�� ����
		//StatDao.clearRows(vd.tmodel.getRowCount(), vd.tmodel);

		// �޺��ڽ��� ���� ������
		// (�⵵, ��, ��)
		String year = vd.comboYear.getItemAt(vd.comboYear.getSelectedIndex()).toString();
		String month = vd.comboMonth.getItemAt(vd.comboMonth.getSelectedIndex()).toString();
		String day = vd.comboDay.getItemAt(vd.comboDay.getSelectedIndex()).toString();
		if (Integer.parseInt(day) < 10) {
			day = "0".concat(day);
		}

		//StatDao statDao = new StatDao(); // Dao ��ü
		Vector<String> rows = new Vector<String>(); // ��

		results = new Vector<PosDto>(); // �׷����� �� ���� ����

		// select ��� ����
		/*
		PosDto result = statDao.findDaySell(year, month, day); // DB select ��� ���� ����
		results.add(result);

		if (result.getSellDate() == null) { // ��ȸ ��� ������, �˸�â ����
			JOptionPane.showMessageDialog(null, "��ȸ�� �����Ͱ� �����ϴ�.");
		} else { // ��ȸ ��� ������, ��� ���̱�

			// ���̺� �� ����
			rows.addElement(result.getSellDate());
			rows.addElement(Integer.toString(result.getStatTotalPrice()));
			rows.addElement(Integer.toString(result.getTotalTax()));
			rows.addElement(Integer.toString(result.getCashPrice()));
			rows.addElement(Integer.toString(result.getCardPrice()));
			rows.addElement(Integer.toString(result.getCustomerCount()));
   
			vd.tmodel.addRow(rows);

			// ��� ���̺� ����
			vd.spShowTable.setViewportView(vd.tableResult);

			// ����Ʈ �׷����� �����հ赵 ���� �����
			setChart(statType);
		}
		*/

	}

	// <������ư ���� ���� ���� �׷��� ����> �̺�Ʈ
	// option : 1 - �����հ� / 2 - ����
	public void setChart(String type) {

		// #��Ʈ ����#
		// [������ ����]
		DefaultCategoryDataset dataset;

		// �޺��ڽ��� ���� ������
		// (�⵵, ��, ��)
		String year = vd.comboYear.getItemAt(vd.comboYear.getSelectedIndex()).toString();
		String month = vd.comboMonth.getItemAt(vd.comboMonth.getSelectedIndex()).toString();
		String day = vd.comboDay.getItemAt(vd.comboDay.getSelectedIndex()).toString();
		if (Integer.parseInt(day) < 10) {
			day = "0".concat(day);
		}

		// ���� �޺��ڽ��� ���ڿ� �´� �ð��뺰 �����հ�, ���� �޾ƿ���
		//StatDao statDao = new StatDao(); // Dao ��ü
		Vector<PosDto> resultG = new Vector<PosDto>(); // �׷����� �� ���� ����

		// select ��� ����
		if (results != null) { // ��ȸ ��ư �� �� ���ȴٸ�, �׷��� �� ����
			//resultG = statDao.findDayTimeSell(year, month, day); // DB select ��� ���� ����
		}
		// [������ ����]
		// type(��� �з�)�� ���� �ٸ��� ���õ�
		dataset = getGraphDataset(type, resultG);

		// [������ ����]
		final LineAndShapeRenderer renderer = new LineAndShapeRenderer();

		// ������ ����
		// ���� �ɼ� ����
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.INSIDE7, TextAnchor.BASELINE_RIGHT);
		Font f = new Font("�������", Font.BOLD, 17);
		Font axisF = new Font("�������", Font.PLAIN, 13);

		// ���� �� ��� ����
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setBaseFillPaint(Color.WHITE); // �� ä��� ����
		renderer.setBaseItemLabelPaint(Color.WHITE); // �� ǥ�� �ؽ�Ʈ ����
		renderer.setBaseItemLabelFont(f);
		renderer.setBasePositiveItemLabelPosition(p_below);
		renderer.setSeriesPaint(0, new Color(210, 121, 22));
		renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 3.0f));

		// [plot ����]
		final CategoryPlot plot = new CategoryPlot();

		// plot �� ������ ����
		plot.setDataset(dataset);
		plot.setRenderer(renderer);

		// plot �⺻ ����
		plot.setOrientation(PlotOrientation.VERTICAL); // �׷��� ǥ�� ����
		plot.setRangeGridlinesVisible(true); // X�� ���̵� ���� ǥ�ÿ���
		plot.setDomainGridlinesVisible(true); // Y�� ���̵� ���� ǥ�ÿ���

		// X�� ����
		plot.setDomainAxis(new CategoryAxis()); // X�� ���� ����
		plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // ī�װ� �� ��ġ ����
		plot.getDomainAxis().setTickLabelPaint(Color.WHITE); // X�� ���ݶ� ��Ʈ ���� ����

		// Y�� ����
		plot.setRangeAxis(new NumberAxis()); // Y�� ���� ����
		plot.getRangeAxis().setTickLabelFont(axisF); // Y�� ���ݶ� ��Ʈ ����
		plot.getRangeAxis().setTickLabelPaint(Color.WHITE); // Y�� ���ݶ� ��Ʈ ���� ����
		plot.setBackgroundPaint(Color.DARK_GRAY); // ��Ʈ ��� �� ����

		// [��Ʈ ����]
		// ���õ� plot�� �������� chart ����
		final JFreeChart chart = new JFreeChart(plot);
		chart.setBackgroundPaint(Color.DARK_GRAY);

		// #������ ��Ʈ�� ��Ʈ �г� ����#
		ChartPanel chartp = new ChartPanel(chart);
		chartp.setVisible(true);
		chartp.setSize(1120, 368); // ������ ���� �ʼ�!

		switch (type) {
		case "�����հ�":
			vd.pShowGraph.add("graphTotal", chartp);
			vd.graphCard.show(vd.pShowGraph, "graphTotal");
			break;
		default:
			renderer.setSeriesPaint(0, new Color(75, 84, 147));
			vd.pShowGraph.add("graphCustomerCount", chartp);
			vd.graphCard.show(vd.pShowGraph, "graphCustomerCount");
		}

		vd.pShowGraph.setVisible(true);

	}

	// <Ÿ��(�����հ�, ����)�� ���� �׷��� �����ͼ� ����> �޼ҵ�
	public DefaultCategoryDataset getGraphDataset(String type, Vector<PosDto> resultG) {

		DefaultCategoryDataset dataset = null;

		if (results != null) { // ��ȸ ��� ���� ���� �׷��� �� ����
			System.out.println("�׷��� �� ����!!!!!");
			String time[] = { "00��", "01��", "02��", "03��", "04��", "05��", // �ð�(�׷�����)
					"06��", "07��", "08��", "09��", "10��", "11��", "12��", "13��", "14��", "15��", "16��", "17��", "18��", "19��",
					"20��", "21��", "22��", "23��" };

			Vector<String> sell_time = new Vector<String>(); // �Ǹ� �ð� (�� ��)
			Vector<Integer> values = new Vector<Integer>(); // �з��� �� (�� ��)

			dataset = new DefaultCategoryDataset();

			int size = resultG.size();
			boolean flag = false; // �ð��� �� �� �޾ƿ� ����

			switch (type) {
			case "�����հ�":
				for (int i = 0; i < 24; i++) {
					for (int j = 0; j < size; j++) {
						System.out.println(time[i]);
						System.out.println(resultG.get(j).getSellTime());
						if (time[i].equals(resultG.get(j).getSellTime())) {
							dataset.addValue(resultG.get(j).getStatTotalPrice(), type, time[i]);
							break;
						} else {
							dataset.addValue(0, type, time[i]);
						}
					}
				}
				break;
			default: // �� ��
				for (int i = 0; i < 24; i++) {
					for (int j = 0; j < size; j++) {
						System.out.println(time[i]);
						System.out.println(resultG.get(j).getSellTime());
						if (time[i].equals(resultG.get(j).getSellTime())) {
							dataset.addValue(resultG.get(j).getCustomerCount(), type, time[i]);
							break;
						} else {
							dataset.addValue(0, type, time[i]);
						}
					}
				}
				break;
			}
		}

		return dataset;
	}
	
}
