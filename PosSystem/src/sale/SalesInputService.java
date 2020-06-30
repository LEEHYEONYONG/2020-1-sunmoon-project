package sale;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import db.PosDto;
import db.PosUse;
import main.MainFrame;
import db.Connect_DB;

public class SalesInputService implements KeyListener, ActionListener, ItemListener {// �Ǹ� �̺�Ʈ ó��

	SaleBtn salebtn = new SaleBtn();
	MainFrame mainframe;
	// SalesInputDao salesInputDao;//DB
	public static boolean key;
	//Vector<PosDto> salesList;
	Vector<PosUse> salesList;//DB
	Vector<Object> getgoods;
	int overlapRow;
	boolean memshipcheck = false;

	//ȯ�� ��ǰ ����
	int index = 0;
	String [] rf_state = null;
	String[] rf_name = null;
	int[] rf_amount = null;
	String[] rf_way = null;
	int[] rf_cost = null;
	
	//�����ͺ��̽� �ʵ� �߰�
	Connect_DB connect_db;
	
	public SalesInputService(MainFrame mainframe) {
		super();
		this.mainframe = mainframe;
		// salesInputDao = new SalesInputDao();
		
		//�蹫�� �߰�
		connect_db = new Connect_DB();
		
	}

	
	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if(e.getStateChange()== ItemEvent.SELECTED) {
		
		/*mainframe.payment_3.JcomboBoxPay*/	
	    Object item =e.getItem();
	    
        //String item = String.valueOf(mainframe.payment_3.JcomboBoxPay.getToolkit());
		//String item = String.valueOf(mainframe.payment_3.JcomboBoxPay.getSelectedItem());
		if(item.equals("ī��")) {
			mainframe.payment_3.tfP3CashP.setEnabled(false);
			mainframe.payment_3.tfP3CardP.setEnabled(true);
			mainframe.payment_3.tfP3CashP.setText("");
		} else {
			mainframe.payment_3.tfP3CashP.setEnabled(true);
			mainframe.payment_3.tfP3CardP.setEnabled(false);
			mainframe.payment_3.tfP3CardP.setText("");
		}
		
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == 10) {
			System.out.println(e);
			goodsListProcess();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

		if (ob == mainframe.salebtn.sBtnPdChange) {// ��ǰ����Ŭ����
			
			int row = mainframe.viewSalesInput.table.getSelectedRow();
			System.out.println("row : "+row);
			if (row < 0) {
				JOptionPane.showMessageDialog(mainframe, "������ ��ǰ�� Ŭ���� �ּ���.", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);

				return;
			}
			if (!Integer.toString(row).equals(null)) {
				String cnt = JOptionPane.showInputDialog(mainframe, "������ ������ �Է��ϼ���.", "��������",
						JOptionPane.QUESTION_MESSAGE);

				if (cnt == null || !isNumber(cnt) || cnt.isEmpty() || cnt.trim().equals("")) 
				{
					return;
				} 
				else  //��� ���� �ʰ� ���� Ȯ��
				{
					key = true;
					Object find_num = mainframe.viewSalesInput.table.getValueAt(row,1);
					int amount = connect_db.amount_value(String.valueOf(find_num));
					
					if(Integer.parseInt(cnt) <= 0)
					{
						JOptionPane.showMessageDialog(mainframe, "������ 0���Ϸ� ������ �� �����ϴ�", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);
					}
					else if(Integer.parseInt(cnt) > amount)
					{
						JOptionPane.showMessageDialog(mainframe, "��� ������ �ʰ��Ͽ����ϴ�", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
						int price = Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(row, 4)));//���� 4��
						mainframe.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 3);//���� 3��
						mainframe.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 7);//�� �ݾ� 7��
					}
				}
			} else {
				JOptionPane.showMessageDialog(mainframe, "������ ��ǰ�� �������ּ���", "���ÿ���", JOptionPane.ERROR_MESSAGE);
			}
			totalApply();

		} else if (ob == mainframe.salebtn.sBtnAcancel) {// �ŷ����Ŭ����
			int row = mainframe.viewSalesInput.table.getRowCount();
			if (row < 0) {
				JOptionPane.showMessageDialog(mainframe, "���� ����� ��ǰ�� �����ϴ�.", "���� ��� ����", JOptionPane.WARNING_MESSAGE);

				return;
			}
			connect_db.removeListNum(mainframe.viewSalesInput.table.getRowCount());
			setblank();
			//payEnd();

		} else if (ob == mainframe.salebtn.sBtnPdCancel) {// ��ǰ���Ŭ����
			int row = mainframe.viewSalesInput.table.getSelectedRow();
			
			int[] rows = mainframe.viewSalesInput.table.getSelectedRows();
			
            System.out.println("rows.length : "+rows.length);
			
			System.out.println("row : "+row);
			
			System.out.println("getRowCount : "+mainframe.viewSalesInput.model.getRowCount());
			
			for(int i=0;i<rows.length;i++)
			{
				System.out.println("\ngetSelectedRowsIndex : "+ i);
				System.out.println("getSelectedRows : "+ rows[i]);//table row index
			}
			
			if (row != -1) {
				int del = JOptionPane.showConfirmDialog(mainframe, "������ ��ǰ�� ����Ͻðڽ��ϱ�?", "��ǰ���",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (del == 0) {
					for(int i=0;i<rows.length;i++)
					{
						//��ǰ���ؽ��� 0~5������ �� 6�� ���� �� ���ؽ� 1~4������ ��ǰ�� �����ϰ� ������ư�� ������
						//�ݺ����� ���鼭 ���õ� ��ǰ�� �����ȴ�
						//1�� �ε����� ��ǰ�� �����ϸ� 2~4������ �ִ� 3���� ��ǰ���� 1�� �ε��� ��ǰ�� ���������Ƿ�
						//�ε����� 1�� �پ���. 2�� �ε��� ��ǰ�� 1������ �ٲ� 3�� �ε��� ��ǰ�� 2������ �ٲ�
						mainframe.viewSalesInput.model.removeRow(rows[i]-i);//��� ���� �޼ҵ�
						System.out.println("���� �Ϸ� row = "+(rows[i]-i));
					}
					
					//listNum �缳��
					int t_size = mainframe.viewSalesInput.table.getRowCount();
					for(int i =0; i < t_size; i++)
					{
						mainframe.viewSalesInput.model.setValueAt(i+1,i,0);//������ ��ȣ, ���ȣ, ����ȣ
					}
					//listNum 1����
					connect_db.removeListNum(rows.length);
					
					int size = salesList.size();
					System.out.println("������ : "+size);
				
					System.out.println("getListNum : "+salesList.get(0).getListNum());
					System.out.println("getp_num : "+salesList.get(0).getp_num());
					System.out.println("getp_name : "+salesList.get(0).getp_name());
					System.out.println("getp_amount : "+salesList.get(0).getp_amount());
					System.out.println("getp_cost : "+salesList.get(0).getp_cost());
					System.out.println("getp_provide : "+salesList.get(0).getp_provide());
					totalApply();
				}
//				int price = Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(row, 3)));
//				mainframe.viewSalesInput.model.setValueAt(Integer.parseInt(cnt), row, 4);
//				mainframe.viewSalesInput.model.setValueAt(price * Integer.parseInt(cnt), row, 5);
			} else {
				JOptionPane.showMessageDialog(mainframe, "����� ��ǰ�� �������ּ���", "���ÿ���", JOptionPane.ERROR_MESSAGE);
			}
		} else if (ob == mainframe.salebtn.sBtnPay) {// ����Ŭ����
			if (mainframe.viewSalesInput.model.getRowCount() > 0) {
				
				mainframe.payment_3.setVisible(true);//���� gui����
				mainframe.payment_3.lbP3PaymentView.setText(mainframe.viewSalesInput.total_price_input.getText());
				mainframe.payment_3.lbP3FinalPayView.setText("0");
				
				//mainframe.payment_1.setVisible(true);// ����â���Ϳ���
				//mainframe.payment_1.tfP1BeforePrice.setText(mainframe.viewSalesInput.total_price_input.getText());
				// String item =
				// String.valueOf(mainframe.payment_1.cbP1Cooperation.getSelectedItem());
				// cooperDCProcess(item);
				// cpCalc();
			} else {
				JOptionPane.showMessageDialog(mainframe, "������ ��ǰ�� �����ϴ�.", "��ǰ�̼���", JOptionPane.ERROR_MESSAGE);
			}
			
//-------------------���� ���� ���� �κ�-------------------------------------------------------------------------
			
		/*} else if (ob == mainframe.payment_1.btnP1Apply) {//���� ���� ��ư
			//cpCalc();

		} else if (ob == mainframe.payment_1.btnP1Before) {//���� �������� ��ư
			//mainframe.payment_1.setVisible(false);
			//mainframe.payment_4.setVisible(false);
			//mainframe.payment_3.setVisible(false);
		} else if (ob == mainframe.payment_1.btnP1Next) {//���� �������� ��ư
			if (mainframe.payment_1.tfP1Afterprice.getText().equals(""))
				JOptionPane.showMessageDialog(mainframe.payment_1, "������ �����Ͻÿ�.", "���ι�����", JOptionPane.ERROR_MESSAGE);
			else {
				mainframe.payment_1.setVisible(false);
				mainframe.payment_3.setVisible(true);
				mainframe.payment_3.lbP3PaymentView.setText(mainframe.payment_1.tfP1Afterprice.getText());
				mainframe.payment_3.lbP3FinalPayView.setText("0");
			}
		} else if (ob == mainframe.payment_3.JcomboBoxPay) {
			
			//itemStateChanged �޼ҵ�� ��ȯ
			 
			//String item = String.valueOf(mainframe.payment_3.JcomboBoxPay.getToolkit());
			
			//if(item.equals("ī��")) {
				//mainframe.payment_3.tfP3CashP.setEnabled(false);
				//mainframe.payment_3.tfP3CardP.setEnabled(true);
			//} else {
				//mainframe.payment_3.tfP3CashP.setEnabled(true);
				//mainframe.payment_3.tfP3CardP.setEnabled(false);
			//}
			
			*/
//-------------------���� ���� ���� �κ�-------------------------------------------------------------------------
			
		} else if (ob == mainframe.payment_3.btnP3Input) {//����gui �Է¿Ϸ� ��ư
			
			int card = 0;
			int cash = 0;

			if (mainframe.payment_3.tfP3CardP.getText().trim().equals("")
					&& mainframe.payment_3.tfP3CashP.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(mainframe.payment_3, "���� �Ǵ� ī�带 �Է����ֽʽÿ�.", "������������",
						JOptionPane.ERROR_MESSAGE);

			} else if (isNumber(mainframe.payment_3.tfP3CardP.getText().trim()) != true
					|| isNumber(mainframe.payment_3.tfP3CashP.getText().trim()) != true) {
				JOptionPane.showMessageDialog(mainframe.payment_3, "���ڸ� �Է����ֽʽÿ�.", "�Է¿���",
						JOptionPane.WARNING_MESSAGE);

			} else {

				if (!mainframe.payment_3.tfP3CardP.getText().trim().equals("")) {
					card = Integer.parseInt(mainframe.payment_3.tfP3CardP.getText().trim());
					// salesInputDao.posDto.setCardPrice(card);
					// System.out.println(salesInputDao.posDto.getCardPrice());
				}

				if (!mainframe.payment_3.tfP3CashP.getText().trim().equals("")) {
					cash = Integer.parseInt(mainframe.payment_3.tfP3CashP.getText().trim());
					// salesInputDao.posDto.setCashPrice(cash);
					// System.out.println(salesInputDao.posDto.getCashPrice());
				}
				
				if (card + cash != Integer.parseInt(mainframe.payment_3.lbP3PaymentView.getText())) {
					System.out.println(card + cash);
					mainframe.payment_3.tfP3SM.setText("");
					mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(card + cash));
					JOptionPane.showMessageDialog(mainframe.payment_3, "�����ݾװ� �����ʽ��ϴ�.", "�����ݾ״�ҿ���",
							JOptionPane.WARNING_MESSAGE);
				} else {
					// salesInputDao.posDto.setTotalPrice(card + cash);
					// mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(salesInputDao.posDto.getTotalPrice()));
					mainframe.payment_3.lbP3FinalPayView.setText(String.valueOf(card + cash));
					mainframe.payment_3.tfP3SM.setText("�����ݾ��� �����Ǿ����ϴ�");
				}
			}
			
			
//--------------------------------------------------------------------------------------------

		} else if (ob == mainframe.payment_3.btnP3Cancel) {//����gui �ŷ���� ��ư
			mainframe.payment_3.setVisible(false);
		} else if (ob == mainframe.payment_3.btnP3Next) {//����gui ���� ��ư
			

			if (!mainframe.payment_3.tfP3SM.getText().equals("")) {
				if (JOptionPane.showConfirmDialog(mainframe.payment_3, "�����Ͻðڽ��ϱ�?", "����Ȯ��",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					
					//���� ���̺� ���� ���� �߰� �޼ҵ�
					make_list();
					
					// paymentProcess();
					JOptionPane.showMessageDialog(mainframe.payment_3, "�������Ϸ�Ǿ����ϴ�.", "�����Ϸ�",
							JOptionPane.INFORMATION_MESSAGE);

					mainframe.payment_3.setVisible(false);
					mainframe.payment_4.setVisible(true);

					//������ �ؽ�Ʈ������ ���� �߰�
					payNPrint();
					
					
					//���� ���� �� ���̺� ����
					int row = mainframe.viewSalesInput.table.getRowCount();
					connect_db.removeListNum(mainframe.viewSalesInput.table.getRowCount());
					setblank();
					
					// salesInputDao.SellIdDate();
					// payNPrint();

					// setblank();
					// payEnd();

//				} else if (JOptionPane.showMessageDialog(mainframe.payment_3, "���������� �������� �ʾҽ��ϴ�.", "���Ǻ���",
//						JOptionPane.ERROR_MESSAGE)) {
//					return;
				} else {
					JOptionPane.showMessageDialog(mainframe.payment_3, "���������� �������� �ʾҽ��ϴ�.", "���Ǻ���",
							JOptionPane.ERROR_MESSAGE);
				}

			}
			
			
		} else if (ob == mainframe.payment_4.btnP4PrintReceipt) {
			
			PrintProcess();
			
		} else if (ob == mainframe.payment_4.btnP4Payment) {

			mainframe.payment_4.setVisible(false);
			mainframe.payment_4.taP4details.setText("");

		}
		else if(ob == mainframe.salebtn.sBtnCancel) {//ȯ��
			mainframe.dealCancel.setVisible(true);
		} 
		else if (ob == mainframe.dealCancel.btnRefund) 
		{
			int choose = JOptionPane.showConfirmDialog(mainframe.dealCancel, "ȯ�� ������ �����Ͻðڽ��ϱ�?", "ȯ��",
					JOptionPane.OK_CANCEL_OPTION);
			if (choose == 0) {
				refundProcess();
				mainframe.dealCancel.setVisible(false);
				
				if(index <= 0 || salesList.isEmpty())
				{
					
				}
				else
				{
					mainframe.payment_4.setVisible(true);//������ gui
					mainframe.payment_4.setTitle("ȯ�� ������");
				}
				
				
				//ȯ�� ������
				key = false;
				
				//������ �ؽ�Ʈ������ ���� �߰�
				payNPrint();
				
			}
			mainframe.dealCancel.Sell_id.setText("");
		}

	}


	
	
	public void PrintProcess() {//���������
		BufferedImage img =getScreenShot(mainframe.payment_4.taP4details);
		
		FileDialog dialog = new FileDialog(mainframe.payment_4, "����", FileDialog.SAVE);
		dialog.setDirectory(".\\chargesave");
		dialog.setFile(connect_db.posUse.getc_num()+".jpg");
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
	
	
	//���� ��� DB�� ������
		public void make_list()
		{
			key = true;
			int size = mainframe.viewSalesInput.table.getRowCount();
			connect_db.posUse = new PosUse();
			/*
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
			Calendar time = Calendar.getInstance();
			String time1 = format1.format(time.getTime());
			connect_db.posUse.setc_num(time1+String.format("%04d",buyCount));*/
			
			//���� ���̺� �⺻Ű �ߺ� ���� ����
			connect_db.duplicate_key();
			//������ ��ǰ ����
			int amount_revise;
			for(int i=0;i<size;i++)
			{
				
				connect_db.posUse.setc_name(mainframe.viewSalesInput.table.getValueAt(i, 1).toString());//���� ��ǰ ��ȣ
				connect_db.posUse.setc_amount(Integer.parseInt(mainframe.viewSalesInput.table.getValueAt(i, 3).toString()));//���� ��ǰ ����
				connect_db.posUse.setc_way(mainframe.payment_3.JcomboBoxPay.getSelectedItem().toString());//���� ���
				connect_db.posUse.setc_cost(Integer.parseInt(mainframe.viewSalesInput.table.getValueAt(i, 7).toString()));//��ǰ ����
				//connect_db.posUse.setc_assistant("admin");//���� ���� posUse.getid()
				
				//��ǰ ���̺� ��ǰ ���� ����
				amount_revise = connect_db.amount_revise1();
				if(amount_revise<=-1)
				{
					System.out.println("��ǰ ���� �������� ����");
					continue;
				}
				else if(amount_revise >= 0)
				{
					connect_db.amount_revise2(amount_revise);
					System.out.println("��ǰ ���̺� ��ǰ ���� ���� �Ϸ�");
					
					connect_db.registerHistory();
				}
				
				
				
			}
			//buyCount++;
		}
		
		//������ �ؽ�Ʈ ������ ���� �߰�
		private void payNPrint()
		{
			if(key == true)//���� ������
			{
				int size = mainframe.viewSalesInput.table.getRowCount();
				String num = connect_db.posUse.getc_num();
				mainframe.payment_4.taP4details.setText("���� �ڵ�: "+num+"\n");
				mainframe.payment_4.taP4details.append("��ǰ�� \t �ܰ� \t ���� \t �ݾ�\n");
				for(int i=0;i<size;i++)
				{
					String name = mainframe.viewSalesInput.table.getValueAt(i, 2).toString();//��ǰ��
					String one_cost = mainframe.viewSalesInput.table.getValueAt(i, 4).toString();//�ܰ�
					String amount = mainframe.viewSalesInput.table.getValueAt(i, 3).toString();//����
					String plus_cost = mainframe.viewSalesInput.table.getValueAt(i, 7).toString();//�ݾ�
					
					mainframe.payment_4.taP4details.append(name+" \t "+one_cost+" \t "+amount+" \t "+plus_cost+"\n");
				}
				
				String all_cost = mainframe.viewSalesInput.total_price_input.getText();//�� ���� �ݾ�
				mainframe.payment_4.taP4details.append("\n�հ� �ݾ�: "+ all_cost);
				
				String card = mainframe.payment_3.tfP3CardP.getText();
				String cash = mainframe.payment_3.tfP3CashP.getText();
				if(card.equals(""))
				{
					card = "0";
				}
				else if(cash.equals(""))
				{
					cash = "0";
				}
				mainframe.payment_4.taP4details.append("\n\n���� ����\n");
				mainframe.payment_4.taP4details.append("ī��: "+card+"��\n");
				mainframe.payment_4.taP4details.append("����: "+cash+"��\n");
			}
			else if(index <= 0 || salesList.isEmpty())
			{
				return;
			}
			else if(key == false)//ȯ�� ������
			{
				String num = connect_db.posUse.getc_num();//ȯ�� �ڵ� ��������
				mainframe.payment_4.taP4details.setText("ȯ�� �ڵ�: "+num+"\n");
				mainframe.payment_4.taP4details.append("��ǰ�� \t �ܰ� \t ���� \t �ݾ�\n");
				
				
				int total_price = 0;
				for(int i = 0; i<index;i++)
				{
					int cost = rf_cost[i]/rf_amount[i];
					
					//��ǰ�� ��������
					String p_name = connect_db.refund_getp_name(rf_name[i]);
					
					mainframe.payment_4.taP4details.append(
							p_name+" \t "+(cost-(cost*2))+" \t "+rf_amount[i]+" \t "+(rf_cost[i]-(rf_cost[i]*2))+"\n");
					total_price += (rf_cost[i]-(rf_cost[i]*2));
				}
				mainframe.payment_4.taP4details.append("\n�հ� �ݾ�: "+ total_price);
				
				mainframe.payment_4.taP4details.append("\n\nȯ�� ����\n");
				if(rf_way[0].equals("ī��"))
				{
					mainframe.payment_4.taP4details.append("ī��: "+total_price+"��\n");
					mainframe.payment_4.taP4details.append("����: 0��\n");
				}
				else if(rf_way[0].equals("����"))
				{
					mainframe.payment_4.taP4details.append("ī��: 0��\n");
					mainframe.payment_4.taP4details.append("����: "+total_price+"��\n");
				}
				
			}
		}

		
		//ȯ�� �޼ҵ�
		private void refundProcess() {
			key = false;
			String sellId = null;
			sellId = mainframe.dealCancel.Sell_id.getText().trim();
			System.out.println("sellId: "+sellId);
			
			//Vector<String> refund_list = new Vector<String>();
			
			salesList = connect_db.refundc_num(sellId);
			index = connect_db.getrf_index();
			System.out.println("index �� ���� : "+index);
			rf_state = new String[index];
			
			System.out.println("salesList.size ���: "+salesList.size());
			System.out.println("salesList.isEmpty() ���� ��� :"+salesList.isEmpty());
			
			rf_state = salesList.get(0).getrf_state();
			
			if (sellId.contentEquals("")) {
				JOptionPane.showMessageDialog(mainframe.dealCancel, "�ŷ� ��ȣ�� �Է����ּ���.", "ȯ�� ��ȸ ����", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(salesList.isEmpty() || index <= 0)
			{
				JOptionPane.showMessageDialog(mainframe.dealCancel, "�ŷ� ��ȣ�� �ùٸ��� �ʽ��ϴ�.", "ȯ�� ��ȸ ����", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(rf_state[0].equals("ȯ��"))
			{
				JOptionPane.showMessageDialog(mainframe.dealCancel, "ȯ���� �� ���� �ŷ� ��ȣ �Դϴ�.", "ȯ�� ��ȸ ����", JOptionPane.WARNING_MESSAGE);
				return;
			}
			else
			{
				
				connect_db.change_state(sellId);
				
				rf_name = new String[index];
				rf_amount = new int[index];
				rf_way = new String[index];
				rf_cost = new int[index];
				
				
				rf_name = salesList.get(0).getrf_name();
				rf_amount = salesList.get(0).getrf_amount();
				rf_way = salesList.get(0).getrf_way();
				rf_cost = salesList.get(0).getrf_cost();
				
				//c_num �ߺ����� �ʰ� ����
				connect_db.duplicate_key();
				//������ ��ǰ ����
				int amount_revise;
				
				for(int i=0; i<index;i++)
				{
					connect_db.posUse.setc_name(rf_name[i]);
					connect_db.posUse.setc_amount(rf_amount[i]);
					connect_db.posUse.setc_way(rf_way[i]);
					connect_db.posUse.setc_cost(rf_cost[i]-(rf_cost[i]*2));
					connect_db.posUse.setc_assistant("admin");//���� ���� posUse.getid()
					
					//��ǰ ���̺� ��ǰ ���� ����
					amount_revise = connect_db.amount_revise1();
					if(amount_revise<=-1)
					{
						System.out.println("��ǰ ���� �������� ����");
						continue;
					}
					else if(amount_revise >= 0)
					{
						connect_db.amount_revise2(amount_revise);
						System.out.println("��ǰ ���̺� ��ǰ ���� ���� �Ϸ�");
						
						connect_db.registerHistory();
					}
				}
				
				//refund_list.addElement(salesList.get(0).getc_name());
				//refund_list.addElement(String.valueOf(salesList.get(0).getc_amount()));
				//refund_list.addElement(salesList.get(0).getc_way());
				//refund_list.addElement(String.valueOf(salesList.get(0).getc_cost()));
				
				//for(int i=0;i<refund_list.size();i++)
				//{
				//	System.out.println(refund_list.get(i));
				//}
				
			}

//			receipt.setVisible(true);
//			receipt.refundDetail.append("��ҿ�����/n" + "�Ǹ��ڵ� : \t" );

			//Vector<PosDto> list = new Vector<PosDto>();
			//list = salesDao.selectUpdateStock(sellId);

			//System.out.println(list.toString());

			//if (list.isEmpty()) {
				//JOptionPane.showMessageDialog(mainframe.dealCancel, "�ŷ� ��ȣ�� �ùٸ��� �ʽ��ϴ�.", "ȯ�� ��ȸ ����", JOptionPane.WARNING_MESSAGE);
				//return;
			//}
			
			/*
			salesDao.updateMembership(sellId); // ����� : ����� ����Ʈ ����
			salesDao.updateMoney(sellId); // ���� update : ���� ����׸�ŭ ����
			salesDao.updateStock(list); // ��� update : ���� ������ŭ ��� ������ ���ϱ�
			salesDao.deletehistory_d(sellId);
			salesDao.deletehisotry(sellId);
			*/

			JOptionPane.showMessageDialog(mainframe.dealCancel, "ȯ�� ó���� �Ϸ�Ǿ����ϴ�.", "ȯ�� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
			mainframe.dealCancel.dispose();
		}
	/*
	 
	
		public void StocksProcess() {

		getgoods = new Vector<Object>();
		int size = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < size; i++) {
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 1));// p_code
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 6));// in_date
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 4));// p_cnt
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 2));// p_name
			getgoods.addElement(mainframe.viewSalesInput.table.getValueAt(i, 3));// p_price
		}

		salesInputDao.registerHisDetail(size, getgoods);
		salesInputDao.reflectStocks(size, getgoods);

	}

	private void paymentProcess() {
		salesInputDao.registerHistory();
		StocksProcess();

//		if()�����̸��� ���ٸ� ���������� �ȉ����Ƿ� ����Ʈó�� ����
		if (memshipcheck == true)
			pointreflect();

	}

	private void pointreflect() {

		int point = Integer.parseInt(mainframe.payment_2.tfP2point.getText());
		int savePoint = Integer.parseInt(mainframe.payment_3.lbP3PointView.getText());
		int usePoint = 0;
		if (!mainframe.payment_2.tfP2UsePoint.getText().equals(""))
			usePoint = Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText());

		salesInputDao.posDto.setPoint(point - usePoint + savePoint);
		salesInputDao.pointUpdate();
	}

	private void payNPrint() {
		mainframe.payment_4.taP4details.setText("�Ǹ��ڵ� : " + ForcePos.selldto.getSellId() + "\t�Ǹ����� : "
				+ ForcePos.selldto.getSellDate() + "\n\t�Ǹſ��ڵ� : " + ForcePos.usercodeDto.getUserCode()
				+ "\n==========================================\n");

		int x = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < x; i++) {
			for (int j = 1; j < 6; j++) {
				mainframe.payment_4.taP4details.append(mainframe.viewSalesInput.model.getValueAt(i, j) + "\t");
			}
			mainframe.payment_4.taP4details.append("\n");
		}

		mainframe.payment_4.taP4details.append("\n==========================================\n"
				+ (salesInputDao.posDto.getDiscountCode().equals("d4") ? "\n"
						: "�����ڵ� : " + salesInputDao.posDto.getDiscountCode() + " ���޻� : "
								+ salesInputDao.posDto.getCooperateName() + " ������ : "
								+ mainframe.payment_1.tfP1DiscountPercent.getText() + "\n"));

		if (salesInputDao.posDto.getMembershipId().equals("nomember")) {

		} else {
			if (mainframe.payment_2.tfP2UsePoint.getText().isEmpty()) {
				mainframe.payment_4.taP4details.append("����� : " + salesInputDao.posDto.getMembershipId()
						+ "\t������ ����Ʈ : " + mainframe.payment_3.lbP3PointView.getText() + "\n\t\t���� ����Ʈ : "
						+ (salesInputDao.posDto.getPoint()
								+ Integer.parseInt(mainframe.payment_3.lbP3PointView.getText()))
						+ "\n");
			} else {
				mainframe.payment_4.taP4details.append("����� : " + salesInputDao.posDto.getMembershipId() + "\t�������Ʈ : "
						+ mainframe.payment_2.tfP2UsePoint.getText() + "\n������ ����Ʈ : "
						+ mainframe.payment_3.lbP3PointView.getText() + "\t���� ����Ʈ : "
						+ (salesInputDao.posDto.getPoint()
								- Integer.parseInt(mainframe.payment_2.tfP2UsePoint.getText())
								+ Integer.parseInt(mainframe.payment_3.lbP3PointView.getText()))
						+ "\n");
			}

		}

		mainframe.payment_4.taP4details.append(("\n----------------------------------------------------------------\n"
				+ "\t �Ѱ����ݾ� : " + salesInputDao.posDto.getTotalPrice() + "\n"
				+ (salesInputDao.posDto.getCardPrice() == 0 ? ""
						: "\t ī������ݾ� : " + salesInputDao.posDto.getCardPrice() + "\n")
				+ (salesInputDao.posDto.getCashPrice() == 0 ? ""
						: "\t ���ݰ����ݾ� : " + salesInputDao.posDto.getCashPrice() + "\n")));

	}

	public void membershipRef() {
		if (mainframe.payment_2.tfP2phoneNum.getText().equals("")) {
			JOptionPane.showMessageDialog(mainframe.payment_2, "��ȣ�� �Է��Ͻÿ�.", "�Է¿���", JOptionPane.WARNING_MESSAGE);
		} else {

			if (salesInputDao.costomerRef(mainframe.payment_2.tfP2phoneNum.getText()) == true) {
				mainframe.payment_2.tfP2SM.setText(salesInputDao.posDto.getMemberName() + "("
						+ salesInputDao.posDto.getMembershipId() + ")���� ������� Ȯ�εǾ����ϴ�.");
				mainframe.payment_2.tfP2point.setText(String.valueOf(salesInputDao.posDto.getPoint()));
				memshipcheck = true;
			} else {
				mainframe.payment_2.tfP2SM.setText(mainframe.payment_2.tfP2phoneNum.getText() + "���� ������� ã�� �� �����ϴ�.");
			}
		}

	}
	
	*/
	

	public void cpCalc() {
		/*
		 * mainframe.payment_1.tfP1Afterprice .setText(Integer.toString((int)
		 * (((Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText()) ((100 -
		 * Integer.parseInt(mainframe.payment_1.tfP1DiscountPercent.getText())))) /
		 * 100))));
		 */
		if (mainframe.payment_1.txtP1Check.getText() == null) {
			JOptionPane.showMessageDialog(mainframe.payment_1, "������ �Է����ּ���.", "�Է¿���", JOptionPane.WARNING_MESSAGE);
		} else if (isNumber(mainframe.payment_1.txtP1Check.getText().trim()) != true) {
			JOptionPane.showMessageDialog(mainframe.payment_1, "���ڸ� �Է����ּ���.", "�Է¿���", JOptionPane.WARNING_MESSAGE);
		} else if (Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText()) < Integer
				.parseInt(mainframe.payment_1.txtP1Check.getText())) {
			JOptionPane.showMessageDialog(mainframe.payment_1, "���αݾ��� �����ݾ׺��� Ů�ϴ�.", "����", JOptionPane.WARNING_MESSAGE);
		} else {
			mainframe.payment_1.tfP1Afterprice
					.setText(Integer.toString((int) ((Integer.parseInt(mainframe.payment_1.tfP1BeforePrice.getText())
							- (Integer.parseInt(mainframe.payment_1.txtP1Check.getText()))))));
			mainframe.payment_1.tfP1SM.setText("������ ����Ǿ����ϴ�.");
		}
	}

	/*
	 * public void cooperDCProcess(String item) {
	 * 
	 * salesInputDao.searchByCP(item);
	 * 
	 * mainframe.payment_1.tfP1DiscountPercent .setText(String.valueOf((int)
	 * (salesInputDao.posDto.getDiscountPct() * 100)));
	 * 
	 * mainframe.payment_1.tfP1Afterprice.setText("");
	 * 
	 * }
	 * 
	 * 
	 */

	public boolean checkOverlap(String identifier, int col) {
		System.out.println("check" + identifier);
		int h = mainframe.viewSalesInput.model.getRowCount();
		for (int i = 0; i < h; i++) {
			System.out.println(mainframe.viewSalesInput.model.getValueAt(i, col));
			if (mainframe.viewSalesInput.model.getValueAt(i, col).equals(identifier)) {
				System.out.println("�ߺ�");
				overlapRow = i;

				return false;
			}

		}

		return true;
	}

	public void goodsListProcess() {

		if (mainframe.viewSalesInput.code_input.getText().trim().length() > 0) {

			//��ǰ ���� Ȯ��
			key = true;
			int amount = connect_db.amount_value(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase());
			if (checkOverlap(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase(), 1)) {
				
				System.out.println(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase());
				System.out.println("�ڵ�ΰ˻�" + mainframe.viewSalesInput.code_input.getText().length());
				if(amount <= 0)
				{
					//���̾� �α� ����
					JOptionPane.showMessageDialog(mainframe, "��ǰ�� ��� �����մϴ�", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);
					
				}
				else
				{
					//�蹫���� ���� connect_db.searchBy �޼ҵ�
					listAdd(connect_db.searchBy(mainframe.viewSalesInput.code_input.getText().trim().toUpperCase()));
				}
			} else 
			{
				// ���� ����
				System.out.println("�ڵ��ߺ� �߻�");
				
				//���� ���� �� �������� ����
				if(amount == Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3))))
				{
					//���̾� �α� ����
					JOptionPane.showMessageDialog(mainframe, "��ǰ�� ��� �����մϴ�", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					// ���� ����
					mainframe.viewSalesInput.model.setValueAt(
							Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3))) + 1,
							overlapRow, 3);// 4�� �Ǿ��ִ� �κ� 3���� ����(3���� ����)
					
					// �ܰ� * ����
					mainframe.viewSalesInput.model.setValueAt(
							Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3)))//����
									* Integer.valueOf(
											String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))),//����
							overlapRow, 7);//5�� �Ǿ��ִ� �κ� 7���� ����(7�� �� �ݾ�)
				}
			
			}
			mainframe.viewSalesInput.product_name_input.setText("");
		} else if (mainframe.viewSalesInput.product_name_input.getText().trim().length() > 0) {

			//��ǰ ���� Ȯ��
			key = false;
			int amount = connect_db.amount_value(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase());
			
			if (checkOverlap(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase(), 2)) {
				
				System.out.println(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase());
				System.out.println("�̸����ΰ˻�" + mainframe.viewSalesInput.product_name_input.getText().length());
				
				if(amount <= 0)
				{
					//���̾� �α� ����
					JOptionPane.showMessageDialog(mainframe, "��ǰ�� ��� �����մϴ�", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);
					
				}
				else
				{
					//�蹫���� ���� connect_db.searchBy �޼ҵ�
					listAdd(connect_db
							.searchBy(mainframe.viewSalesInput.product_name_input.getText().trim().toUpperCase()));
				}

			} 
			else 
			{
				System.out.println("�̸��ߺ� �߻�");
				
				//���� ���� �� �������� ����
				if(amount == Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3))))
				{
					//���̾� �α� ����
					JOptionPane.showMessageDialog(mainframe, "��ǰ�� ��� �����մϴ�", "��ǰ ���� ����", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					// ����������
					mainframe.viewSalesInput.model.setValueAt(
							Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3))) + 1,
							overlapRow, 3);//4�� �Ǿ��ִ� �κ� 3���� ����
					// �ܰ� * ���� ����
					mainframe.viewSalesInput.model.setValueAt(
							Integer.valueOf(String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 3)))//����
									* Integer.valueOf(
											String.valueOf(mainframe.viewSalesInput.model.getValueAt(overlapRow, 4))),//����
							overlapRow, 7);//5�� �Ǿ��ִ� �κ� 7���� ����
				}
			
			
			}

		} else {
			JOptionPane.showMessageDialog(mainframe, "�ش� ��ǰ�� �����ϴ�.", "�˻�����", JOptionPane.WARNING_MESSAGE);

		}
		totalApply();
		mainframe.viewSalesInput.code_input.setText("");
		mainframe.viewSalesInput.product_name_input.setText("");
	}

	public void listAdd(Vector<PosUse> salesList) {
		if (salesList == null)
			JOptionPane.showMessageDialog(mainframe, "��ǰ�� ���������ʽ��ϴ�.", "��ǰ����", JOptionPane.ERROR_MESSAGE);
		else {
			int size = salesList.size();
			
			this.salesList = new Vector<PosUse>();

			//this.salesList.addElement(salesList.get(0).getListNum());
			
			this.salesList = salesList;
			
			System.out.println("listAdd size : "+size);
			Vector<String> rows = new Vector<String>();
			/*
			rows.addElement(Integer.toString(salesList.get(0).getListNum()));
			rows.addElement(salesList.get(0).getProductCode()); 1 String
			rows.addElement(salesList.get(0).getProductName()); 2 String
			rows.addElement(Integer.toString(salesList.get(0).getPrice())); 3 int
			rows.addElement(String.valueOf(salesList.get(0).getSellCount())); 4 int
			rows.addElement(String.valueOf(salesList.get(0).getPricensellCount())); 5 int
			rows.addElement(salesList.get(0).getInDate()); 6 String*/
			
			rows.addElement(Integer.toString(salesList.get(0).getListNum()));
			rows.addElement(salesList.get(0).getp_num());
			rows.addElement(salesList.get(0).getp_name());
			rows.addElement(String.valueOf(salesList.get(0).getp_amount()));
			rows.addElement(Integer.toString(salesList.get(0).getp_cost()));
			rows.addElement(salesList.get(0).getp_category());
			rows.addElement(salesList.get(0).getp_provide());
			rows.addElement(String.valueOf(salesList.get(0).getp_costsellCount()));
			/*
			System.out.println("rows.size : "+rows.size());
			System.out.println("rows.get(0) : "+rows.get(0));
			System.out.println("rows.get(1) : "+rows.get(1));
			System.out.println("rows.get(2) : "+rows.get(2));
			System.out.println("rows.get(3) : "+rows.get(3));
			System.out.println("rows.get(4) : "+rows.get(4));
			System.out.println("rows.get(5) : "+rows.get(5));
			System.out.println("rows.get(6) : "+rows.get(6));
			
			System.out.println("salesList.get(0) : "+salesList.get(0));*/
			
			System.out.println(this.salesList.get(0).getp_name());
			
			
			mainframe.viewSalesInput.model.addRow(rows);
			
			System.out.println("�ݿ��Ϸ�");

		}
	}

	public void totalApply() {//����� �ѱݾװ�������
		int tp = 0;
		int row = mainframe.viewSalesInput.table.getRowCount();
		for (int i = 0; i < row; i++) {
			tp += Integer.parseInt(String.valueOf(mainframe.viewSalesInput.model.getValueAt(i, 7)));
		}
		mainframe.viewSalesInput.total_price_input.setText(String.valueOf(tp));
	}

	public boolean isNumber(String obStr) {
		boolean flag = true;
		int len = obStr.length();
		int num;
		for (int i = 0; i < len; i++) {
			num = obStr.charAt(i) - 48;
			if (num < 0 || num > 9)
				flag = false;
		}
		return flag;
	}

	public void isNumError(boolean b, JPanel p) {
		if (b == false)
			JOptionPane.showMessageDialog(p, "���ڸ� �Է°����մϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
	}

	public static void clearRows(int rowCount, DefaultTableModel model) {
		if (rowCount > 0) {
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}
	}

	private void setblank() {
		clearRows(mainframe.viewSalesInput.table.getRowCount(), mainframe.viewSalesInput.model);
		mainframe.viewSalesInput.total_price_input.setText("0");
		mainframe.payment_1.tfP1SM.setText("");
		mainframe.payment_1.tfP1BeforePrice.setText("");
		mainframe.payment_1.tfP1Afterprice.setText("");
		mainframe.payment_1.lbP1Afterprice.setText("");
		mainframe.payment_1.txtP1Check.setText("");
		mainframe.payment_3.tfP3SM.setText("");
		mainframe.payment_3.tfP3CardP.setText("");
		mainframe.payment_3.lbP3FinalPay.setText("");
		mainframe.payment_3.lbP3Payment.setText("");
		mainframe.payment_3.tfP3CashP.setText("");
		mainframe.payment_3.lbP3FinalPayView.setText("");
		mainframe.payment_3.lbP3PaymentView.setText("");

	}


	/*
	public void payEnd() {
		salesInputDao.posDto.setMembershipId("");
		salesInputDao.posDto.setMemberName("");
		salesInputDao.posDto.setPoint(0);
		salesInputDao.posDto.setDiscountCode("");
		salesInputDao.posDto.setCooperateName("");
		salesInputDao.posDto.setDiscountPct(0);

		salesInputDao.posDto.setListNum(0);
		salesInputDao.posDto.setCardPrice(0);
		salesInputDao.posDto.setCashPrice(0);
		salesInputDao.posDto.setTotalPrice(0);

	}
	*/
	 

}
