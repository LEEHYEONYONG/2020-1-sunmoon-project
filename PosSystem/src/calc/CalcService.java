package calc;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import db.Connect_DB;
import db.PosUse;
import main.MainFrame;

public class CalcService implements ActionListener {
	
	MainFrame mainframe;
	Recepit recepit;
	String numStr = "";
	String obStr;
	String v1;
	String v2;
	int value;
	String tmp;
	int sum = 0;
	String sumStr;
	int row = -1;
	int column = -1;
	//CalcDao calcDao;

	public CalcService(MainFrame mainframe) {
		this.mainframe = mainframe;
		//calcDao = new CalcDao();

	}
	
	public void cashCaclEach() {
		/////////////////////////////////// ������ ���� �տ� �ݿ�

		for (int i = 0; i < 8; i++) {
			numStr = "";

			v1 = String.valueOf(mainframe.pCalc.model.getValueAt(i, 0));
			v2 = String.valueOf(mainframe.pCalc.model.getValueAt(i, 1));

			if (!v2.equals("")) {
				value = Integer.parseInt(v1) * Integer.parseInt(v2);
				mainframe.pCalc.model.setValueAt(value, i, 2);
				sum += value;
			}
		}
		///////////////////////////////////// ����ݾ׿� �ݿ�

		mainframe.pCalc.tfCashCheck.setText(String.valueOf(sum));
		sum = 0;
		//////////////////////////////////// ���׹ݿ�
		mainframe.pCalc.tfCalcResult.setText(String.valueOf((Integer.parseInt(mainframe.pCalc.tfCashCheck.getText())
				- Integer.parseInt(mainframe.pCalc.tfCashState.getText())))); //
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		obStr = e.getActionCommand();
		
		System.out.println(obStr);
		
		if(ob ==mainframe.mBtnCalc) {
			mainframe.btn.show(mainframe.pFBtn, "Calcbtn");
			mainframe.monitor.show(mainframe.pMonitor, "PCalc");
			//pMonitor.add("PCalc",pcalc);
			mainframe.pCalc.tfCashState.setText(Integer.toString(mainframe.connect_db.CalDayCash()));
			
		}
		//if()
		else if (isNumber(obStr) == true) {
			if (mainframe.pCalc.cashTable.getSelectedColumn() == 1) {

				row = mainframe.pCalc.cashTable.getSelectedRow();
				column = mainframe.pCalc.cashTable.getSelectedColumn();
				if (mainframe.pCalc.model.isCellEditable(row, column) == false) {
					return;
				}
				numStr = String.valueOf(mainframe.pCalc.cashTable.getValueAt(row, column));
				if (numStr.equals("0")) {
					mainframe.pCalc.cashTable.setValueAt(obStr, row, column);
				} else {
					numStr += obStr;
					mainframe.pCalc.cashTable.setValueAt(numStr, row, column);
				}
				cashCaclEach();
			}
		} else if (ob == mainframe.pCalc.btnCalc_del) {
			if (mainframe.pCalc.cashTable.getSelectedColumn() == 1) {
//			�ǵ� �Ѽ��� ����
				row = mainframe.pCalc.cashTable.getSelectedRow();
				column = mainframe.pCalc.cashTable.getSelectedColumn();
				if (row == -1)
					return;

				String value = String.valueOf(mainframe.pCalc.model.getValueAt(row, column));
				int len = value.length();
				if (len > 1) {
					value = value.substring(0, len - 1);
					mainframe.pCalc.model.setValueAt(value, row, column);
					numStr = value;
				} else {
					mainframe.pCalc.model.setValueAt("0", row, column);
				}
				cashCaclEach();
			}
		} else if (ob == mainframe.pCalc.btnCalc_C) {
			if (mainframe.pCalc.cashTable.getSelectedColumn() == 1) {
				row = mainframe.pCalc.cashTable.getSelectedRow();
				column = mainframe.pCalc.cashTable.getSelectedColumn();
				if (row == -1)
					return;
				mainframe.pCalc.cashTable.setValueAt("0", row, column);
				cashCaclEach();
			}
		} else if (ob == mainframe.calcbtn.cBtnCalc) {//���괩����
			System.out.println("��ư����");
			/*
			if (mainframe.pCalc.tfCashCheck.getText().equals("") || mainframe.pCalc.tfCalcResult.getText().equals("")) {
				JOptionPane.showMessageDialog(mainframe, "����ó���� ���� �����մϴ�.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {*/
//				if())
			if (mainframe.pCalc.tfCashCheck.getText().equals("") || mainframe.pCalc.tfCalcResult.getText().equals("")) {
				JOptionPane.showMessageDialog(mainframe.pCalc, "���������� �Է����ּ���.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			else if (JOptionPane.showConfirmDialog(mainframe.pCalc, "�����Ͻðڽ��ϱ�?", "����Ȯ��",
					JOptionPane.OK_CANCEL_OPTION) == 0) {
					//calcDao.posDto.setComsCalc(Integer.parseInt(mainframe.pCalc.tfCashState.getText()));
					//calcDao.posDto.setCurrentMoney(Integer.parseInt(mainframe.pCalc.tfCashCheck.getText()));
					//calcDao.posDto.setTotalCalc(Integer.parseInt(mainframe.pCalc.tfCalcResult.getText()));
					//calcDao.calc_Apply();
					//DB���
					mainframe.connect_db.CalcIn(Connect_DB.ID, Integer.parseInt(mainframe.pCalc.tfCashState.getText().trim()), Integer.parseInt(mainframe.pCalc.tfCashCheck.getText().trim()), Integer.parseInt(mainframe.pCalc.tfCalcResult.getText().trim()));
					
					
					mainframe.recepit.setVisible(true);
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy�� MM��dd�� HH��mm��ss��");
					Date time = new Date();
					
					String time1 = format1.format(time);
					mainframe.recepit.Date_1.setText(time1);//����ð��޾ƿ���
					mainframe.recepit.Admin_1.setText(Connect_DB.user_name);//������ ����
					mainframe.recepit.Cash_1.setText(mainframe.pCalc.tfCashState.getText());//���ݸ���޾ƿ���
					mainframe.recepit.lbCashChec_1.setText(mainframe.pCalc.tfCashCheck.getText());//�ݰ���ü�ݾ�
					mainframe.recepit.lbCashRes_1.setText(mainframe.pCalc.tfCalcResult.getText());//���̱ݾ�
					
					//���� �޾ƿ���
					
					
					//mainframe.monitor.show(mainframe.pMonitor, "ViewSalesInput");
					//mainframe.btn.show(mainframe.pFBtn, "salebtn");
					
					
				}

			//}

		} else if (ob == mainframe.recepit.Calc) {//���꿵����Ȯ�ι�ư ������
			
			mainframe.recepit.setVisible(false);
			mainframe.pCalc.model.setDataVector(mainframe.pCalc.data, mainframe.pCalc.header);
			mainframe.pCalc.tfCalcResult.setText("");
			mainframe.pCalc.tfCashCheck.setText("");
		} else if(ob==mainframe.recepit.printRec) {
			PrintProcess();
		} 
			
			
		/*
 			int tmp = viewAccount.table.convertRowIndexToModel(viewAccount.table.getSelectedRow());

			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "������ ������ �����ϼ���.", "�̼��� ����", JOptionPane.ERROR_MESSAGE);
				return;
			}

			//������ �� ����
			String tmp1 = (String) viewAccount.model.getValueAt(tmp, 0);//�̸�
			String tmp2 = (String) viewAccount.model.getValueAt(tmp, 1);//���̵�
			String tmp3 = (String) viewAccount.model.getValueAt(tmp, 2);//��й�ȣ
			String tmp4 = (String) viewAccount.model.getValueAt(tmp, 3);//�̸���
			String tmp5 = (String) viewAccount.model.getValueAt(tmp, 4);//����
		 */
		
		
	}
	
	public boolean isNumber(String obStr) {
		boolean flag = true;
		int num = obStr.charAt(0) - 48;
		if (num < 0 || num > 9) {
			flag = false;
		}
		return flag;
	}
	
	
	
	
	
	public void PrintProcess() {//���������
		//BufferedImage img =getScreenShot(mainframe.payment_4.taP4details);
		BufferedImage img =getScreenShot(mainframe.recepit.panelCheck);
		
		FileDialog dialog = new FileDialog(mainframe.recepit, "����", FileDialog.SAVE);
		dialog.setDirectory("C:\\balancesave");
		dialog.setFile(mainframe.recepit.Date_1.getText()+".jpg");
		/*
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		       "JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		chooser.showOpenDialog(chooser);
		*/
		
		dialog.setVisible(true);
		
		if(dialog.getFile() == null) return;
		
		String dfName = dialog.getDirectory() + dialog.getFile();
		
		
		File file = new File(dfName);
		
		try {
			ImageIO.write(img, "jpg", file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		BufferedWriter writer = new BufferedWriter(new FileWriter(dfName));

		writer.write(getScreenShot(mainframe.payment_4));

		writer.close();
		*/

	}
	
	public static BufferedImage getScreenShot(Component component) {//�̹������
		
		BufferedImage image = new BufferedImage(
				component.getWidth(),
				component.getHeight(),
				BufferedImage.TYPE_INT_RGB
				);
		
		component.paint(image.getGraphics());
		return image;
		
	}
	
	

	
	
	
	
	

}
