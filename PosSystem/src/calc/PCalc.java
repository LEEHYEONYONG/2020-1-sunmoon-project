package calc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import main.MainFrame;

public class PCalc extends JPanel {
	
	public DefaultTableModel model;

	String[] header;
	String[][] data;
	public JTable cashTable;
	public JTextField tfCashState;
	public JTextField tfCashCheck;
	public JTextField tfCalcResult;



	public JPanel pcalcmain;
	public JPanel pNum;
	public JButton btnCalc_0;
	public JButton btnCalc_00;
	public JButton btnCalc_1;
	public JButton btnCalc_2;
	public JButton btnCalc_3;
	public JButton btnCalc_4;
	public JButton btnCalc_5;
	public JButton btnCalc_6;
	public JButton btnCalc_7;
	public JButton btnCalc_8;
	public JButton btnCalc_9;
	public JButton btnCalc_del;
	public JButton btnCalc_C;
	public JLabel lbCashState;
	public JLabel lbCashCheck;
	public JLabel lbCalcResult;
	private JScrollPane spTable;
	
	
public PCalc() {
		
		setBackground(new Color(255, 255, 255));
		setBounds(0, 50, 1157, 522);
		
		header = new String[] {"±ÇÁ¾", "¼ö·®", "±Ý¾×"};
		
		data = new String[][] { {"50000","0","0"},{"10000","0","0"},
			{"5000","0","0"},{"1000","0","0"},{"500","0","0"},{"100","0","0"},
			{"50","0","0"},{"10","0","0"},{"ÇÕ°è","0","0"}};
			
			setLayout(null);
			JPanel pCashstate = new JPanel();
			pCashstate.setBounds(30,10,500,455);
			add(pCashstate);
			
			model = new DefaultTableModel(data, header) {
				public boolean isCellEditTable(int row, int column) {
					if(column == 1) {
						return true;
					} else {
						return false;
					}
				}
			};
			
			spTable = new JScrollPane();
			spTable.setWheelScrollingEnabled(false);
			spTable.setEnabled(false);
			spTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			spTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
			pCashstate.add(spTable);
			cashTable = new JTable(model);
			cashTable.setForeground(new Color(0, 0, 0));
			cashTable.setRowHeight(47);
			cashTable.setPreferredScrollableViewportSize(new Dimension(500, 425));
			cashTable.setAutoscrolls(false);
			
			cashTable.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 18));
			cashTable.setPreferredSize(new Dimension(500,425));
			cashTable.setFillsViewportHeight(true);
			cashTable.setBounds(0, 0, 533, 580);
			
			spTable.setBounds(0, 0, 533, 580);
			spTable.setViewportView(cashTable);
			
			pcalcmain = new JPanel();
			pcalcmain.setBackground(Color.WHITE);
			pcalcmain.setBounds(577, 10, 575, 502);
			add(pcalcmain);
			pcalcmain.setLayout(null);
			
			pNum = new JPanel();
			pNum.setBackground(Color.WHITE);
			pNum.setBounds(0, 0, 394, 325);
			pcalcmain.add(pNum);
			pNum.setLayout(new GridLayout(4, 1, 0, 10));
			
			JPanel panel_789 = new JPanel();
			panel_789.setBackground(Color.WHITE);
			pNum.add(panel_789);
			panel_789.setLayout(new GridLayout(0, 3, 10, 10));
			
			btnCalc_7 = new JButton("7");
			btnCalc_7.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_7.setBackground(Color.WHITE);
			panel_789.add(btnCalc_7);
			
			btnCalc_8 = new JButton("8");
			btnCalc_8.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_8.setBackground(Color.WHITE);
			panel_789.add(btnCalc_8);
			
			btnCalc_9 = new JButton("9");
			btnCalc_9.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_9.setBackground(Color.WHITE);
			panel_789.add(btnCalc_9);
			
			JPanel panel_456 = new JPanel();
			panel_456.setBackground(Color.WHITE);
			pNum.add(panel_456);
			panel_456.setLayout(new GridLayout(0, 3, 10, 10));
			
			btnCalc_4 = new JButton("4");
			btnCalc_4.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_4.setBackground(Color.WHITE);
			panel_456.add(btnCalc_4);

			btnCalc_5 = new JButton("5");
			btnCalc_5.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_5.setBackground(Color.WHITE);
			panel_456.add(btnCalc_5);

			btnCalc_6 = new JButton("6");
			btnCalc_6.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_6.setBackground(Color.WHITE);
			panel_456.add(btnCalc_6);
			
			JPanel panel_123 = new JPanel();
			panel_123.setBackground(Color.WHITE);
			pNum.add(panel_123);
			panel_123.setLayout(new GridLayout(0, 3, 10, 10));
			
			btnCalc_1 = new JButton("1");
			btnCalc_1.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_1.setBackground(Color.WHITE);
			panel_123.add(btnCalc_1);
			
			btnCalc_2 = new JButton("2");
			btnCalc_2.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_2.setBackground(Color.WHITE);
			panel_123.add(btnCalc_2);
			
			btnCalc_3 = new JButton("3");
			btnCalc_3.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_3.setBackground(Color.WHITE);
			panel_123.add(btnCalc_3);
			
			JPanel panel_000 = new JPanel();
			panel_000.setBackground(Color.WHITE);
			pNum.add(panel_000);
			panel_000.setLayout(null);
			
			btnCalc_0 = new JButton("0");
			btnCalc_0.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_0.setBackground(Color.WHITE);
			btnCalc_0.setBounds(0, 0, 257, 73);
			panel_000.add(btnCalc_0);
			
			btnCalc_00 = new JButton("00");
			btnCalc_00.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_00.setBackground(Color.WHITE);
			btnCalc_00.setBounds(269, 0, 125, 73);
			panel_000.add(btnCalc_00);
			
			JPanel pcalc_btns = new JPanel();
			pcalc_btns.setBackground(Color.WHITE);
			pcalc_btns.setBounds(406, 0, 169, 325);
			pcalcmain.add(pcalc_btns);
			pcalc_btns.setLayout(null);
			
			btnCalc_del = new JButton("¡ç");
			btnCalc_del.setBackground(Color.WHITE);
			btnCalc_del.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_del.setBounds(0, 0, 169, 158);
			pcalc_btns.add(btnCalc_del);
			
			btnCalc_C = new JButton("C");
			btnCalc_C.setBackground(Color.WHITE);
			btnCalc_C.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			btnCalc_C.setBounds(0, 167, 169, 158);
			pcalc_btns.add(btnCalc_C);
			
			JPanel pcalcState = new JPanel();
			pcalcState.setBounds(23,335,371,157);
			pcalcmain.add(pcalcState);
			pcalcState.setLayout(new BorderLayout(20,0));
			
			JPanel panel = new JPanel();
			pcalcState.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(0, 1, 0, 10));
			
			JLabel lbCashState = new JLabel("Çö±Ý ¸ÅÃâ");
			lbCashState.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			lbCashState.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lbCashState);			
			
			lbCashCheck = new JLabel("º¸À¯Çö±Ý");
			lbCashCheck.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			lbCashCheck.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lbCashCheck);
			
			lbCalcResult = new JLabel("Â÷¾×");
			lbCalcResult.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
			lbCalcResult.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lbCalcResult);
			
			JPanel panel_2 = new JPanel();
			pcalcState.add(panel_2, BorderLayout.CENTER);
			panel_2.setLayout(new GridLayout(0, 1, 0, 10));
			
			tfCashState = new JTextField();
			tfCashState.setEditable(false);
			tfCashState.setForeground(Color.BLACK);
			tfCashState.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			panel_2.add(tfCashState);
			tfCashState.setColumns(10);
			
			tfCashCheck = new JTextField();
			tfCashCheck.setEditable(false);
			tfCashCheck.setForeground(Color.BLACK);
			tfCashCheck.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			panel_2.add(tfCashCheck);
			tfCashCheck.setColumns(10);
			
			tfCalcResult = new JTextField();
			tfCalcResult.setEditable(false);
			tfCalcResult.setForeground(Color.BLACK);
			tfCalcResult.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
			panel_2.add(tfCalcResult);
			tfCalcResult.setColumns(10);
			
			
			
			
		}


}
