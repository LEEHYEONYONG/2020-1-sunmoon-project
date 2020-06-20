package stat;

import java.awt.*;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import main.MainFrame;

import javax.swing.border.MatteBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

public class ViewStatProduct extends JPanel {

	JComboBox comboYear;
	JComboBox comboMonth;
	JComboBox comboMinorLevel;
	JButton btnSearch;

	JTable tableResult;
	DefaultTableModel tmodel;
	JScrollPane spShowTable;
	
	JPanel pShowGraph;
	CardLayout graphCard = new CardLayout(); //�׷����� ī�� ���̾ƿ�
	
	ViewStatProductService vps;

	public ViewStatProduct() {
		setLayout(null);
		setSize(new Dimension(1144, 535));

		// #�˻����� �г�#
		JPanel pSetSearch = new JPanel();
		pSetSearch.setBounds(12, 27, 1120, 37);
		add(pSetSearch);
		pSetSearch.setLayout(null);

		JLabel lbProductLevel = new JLabel("��ǰ�з� :");
		lbProductLevel.setBounds(0, 0, 101, 37);
		pSetSearch.add(lbProductLevel);
		lbProductLevel.setFont(new Font("���� ���", Font.BOLD, 20));

		// [�޺��ڽ�]
		// �޺��ڽ� ���� �� ����
		// ��ǰ�з� ������ �迭
		String MinorLevels[] = { "����", "����", "���ɸ�", "������ġ", "�ﰢ���", "�ҽ���", "���ö�", "����", "���Ʈ", "ġ��", "ź��", "����", "Ŀ��",
				"�ߴٸ�", "ȣ��", "���", "������", "����", "�õ���ǰ", "�Ϲ�", "���ڴ��", "�ֹ��ǰ", "�����ǰ", "������ǰ", "��Ȱ��ǰ", "��Ÿ" };

		Vector<Integer> YearValues = new Vector<Integer>(); // �⵵ ������ ����
		// �� ������ ����
		String MonthValues[] = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		// sYearValues�� ���� �⵵ - 1990����� ��
		Calendar oCalendar = Calendar.getInstance(); // ���� ��¥/�ð� ���� ���� ���� ���
		// ���� ��¥
		int toyear = oCalendar.get(Calendar.YEAR);
		for (int i = toyear; i >= 2000; i--) {
			YearValues.add(i);
		}

		// �޺��ڽ� ����
		comboMinorLevel = new JComboBox(MinorLevels);
		comboMinorLevel.setFont(new Font("���� ���", Font.PLAIN, 20));
		comboMinorLevel.setBounds(103, 0, 170, 37);
		pSetSearch.add(comboMinorLevel);

		JLabel lbShowDate = new JLabel("��ȸ�Ⱓ :");
		lbShowDate.setBounds(334, 0, 101, 37);
		pSetSearch.add(lbShowDate);
		lbShowDate.setFont(new Font("���� ���", Font.BOLD, 20));

		comboYear = new JComboBox(YearValues);
		comboYear.setFont(new Font("���� ���", Font.PLAIN, 20));
		comboYear.setBounds(432, 0, 114, 37);
		pSetSearch.add(comboYear);

		JLabel label = new JLabel("-");
		label.setFont(new Font("���� ���", Font.PLAIN, 20));
		label.setBounds(555, 2, 15, 32);
		pSetSearch.add(label);

		comboMonth = new JComboBox(MonthValues);
		comboMonth.setFont(new Font("���� ���", Font.PLAIN, 20));
		comboMonth.setBounds(570, 0, 114, 37);
		pSetSearch.add(comboMonth);

		btnSearch = new JButton("��ȸ");
		btnSearch.setFont(ViewSetting.sbtnFont);
		btnSearch.setBounds(698, 0, 101, 37);
		pSetSearch.add(btnSearch);

		// #���̺� ��ũ�� �г�#
		spShowTable = new JScrollPane();
		spShowTable.setBounds(12, 74, 554, 451);
		add(spShowTable);

		// [���̺�]
		// ���̺� �� ����
		Vector<String> col = new Vector<String>(); // ��
		col.add("�������");
		col.add("��ǰ�ڵ�");
		col.add("��ǰ�з�");
		col.add("��ǰ��");
		col.add("����");
		col.add("�Ǹż���");
		col.add("�����հ�");
		col.add("������");
		tmodel = new DefaultTableModel(col, 0);

		// ���̺� ���̱�
		tableResult = new JTable(tmodel);
		tableResult.setRowMargin(10);
		tableResult.setRowHeight(30);
		// ���̺� �� ��� ����
		MainFrame.tableCellCenter(tableResult);

		spShowTable.setViewportView(tableResult);

		// #�׷��� �г�#
		pShowGraph = new JPanel();
		pShowGraph.setVisible(false);
		pShowGraph.setLayout(graphCard);
		pShowGraph.setBounds(578, 74, 554, 451);
		add(pShowGraph);

		// #�̺�Ʈ ���#
		vps = new ViewStatProductService(this);
		btnSearch.addActionListener(vps);
	
        }
	
}
