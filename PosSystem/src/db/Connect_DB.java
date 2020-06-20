package db;

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import sale.SalesInputService;

public class Connect_DB {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	//String url = "jdbc:mysql://211.111.101.56/pos_system?serverTimezone=Asia/Seoul";
	String url = "jdbc:mysql://localhost/pos_system?serverTimezone=Asia/Seoul";
	String user = "POS_user";
	String passwd = "pos project";
	
	ResultSet result = null;

	PosDto posDto = null;
	PosUse posUse = null;
	SalesInputService salesInputService;
	
	
	public int i = 1;
	
	public Connect_DB()
	{
		
	}
	
	//������� Ȯ�ο� �޼ҵ�
	public void test()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet find_dbid = stmt.executeQuery("SELECT * FROM user_account");
			
			stmt.close();
			con.close();
			System.out.println("���� ����");
		}
		catch(Exception e)
		{
			System.out.println("���� ����");
		}
	}
	
	//ȸ������ �޼ҵ�
	public void new_user(String user_id, String user_pw, 
			String user_name, String user_email)
	/* user_id = ���� ���̵�
	 * user_pw = ���� �佺����
	 * user_name = ���� �̸�
	 * user_email = ���� �̸���*/
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			//���̵� �ߺ� Ȯ�� ��� �߰�
			ResultSet find_dbid = stmt.executeQuery("SELECT ID FROM user_account");
			while(find_dbid.next())
			{
				String dbid = find_dbid.getString("ID");
				if(user_id.equals(dbid))
				{
					//return �̹� ������� ���̵� 
				}
			}
			
			//ȸ������ ���� DB�� �ֱ�
			stmt.executeUpdate("INSERT INTO user_account VALUES("
					+"'"+user_id+"'"+","+"'"+user_pw+"'"+","
					+"'"+user_name+"'"+","+"'"+user_email+"'"+","
					+"'"+"����"+"'"+")");
			
			stmt.close();
			con.close();
			//return ȸ������ ����
		}
		catch(Exception e)
		{
			
		}
		//return ȸ������ ����
	}
	
	
	//��ǰ �԰� �޼ҵ�
	public void product_input(int p_num, String p_name, 
			int p_amount, int p_cost, String p_provide)
	/* p_num = ��ǰ��ȣ
	 * p_name = ��ǰ��
	 * p_amount = ��ǰ����
	 * p_cost = ��ǰ����
	 * p_provide = ������*/
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			//���� �̸��� ��ǰ�� ���� ��� ��ó ���
			//1 ������ ������Ų��
			//2 ������ȣ�� �ٸ��� �ؼ� ���� �߰��Ѵ�.
			
			stmt.executeUpdate("INSERT INTO product VALUES("
					+p_num+","+"'"+p_name+"'"+","+p_amount+","
					+p_cost+","+"'"+p_provide+"'"+")");
			
			stmt.close();
			con.close();
			
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	//��ǰ ����� ���� ���� �ʿ��� �� ����
	public int countRow()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product");
			
			//������ ���� �̵�
			result.last();
			int rowCount = result.getRow();//������ ���� �ޱ�
			
			stmt.close();
			con.close();
			return rowCount;
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	
	//��ǰ ��� ���� �޼ҵ�
	public String[][] product_select(int rowCount)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product");
				
			String product[][] = new String[rowCount][5];
			
			int i = 0;
			while(result.next())
			{
				product[i][0] = result.getString("p_num");
				product[i][1] = result.getString("p_name");
				product[i][2] = result.getString("p_amount");
				product[i][3] = result.getString("p_cost");
				product[i][4] = result.getString("p_provide");
				i++;
				/*System.out.println(product[0]+" "+product[1]+" "+product[2]
						+" "+product[3]+" "+product[4]);*/
			}
			
			stmt.close();
			con.close();
			return product;
			
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	
	
	//�˻���ǰ �� ����
	public int countRow(String p_name)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product WHERE p_name = "+"'"+p_name+"'");
			
			//������ ���� �̵�
			result.last();
			int rowCount = result.getRow();//������ ���� �ޱ�
			
			stmt.close();
			con.close();
			return rowCount;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	//��ǰ �ڵ� �˻�
	public String[][] find_product(int p_num)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product WHERE p_num = "+p_num);
			
			String f_product[][] = new String[1][5];
			
			while(result.next())
			{
				f_product[0][0] = result.getString("p_num");
				f_product[0][1] = result.getString("p_name");
				f_product[0][2] = result.getString("p_amount");
				f_product[0][3] = result.getString("p_cost");
				f_product[0][4] = result.getString("p_provide");
			}
			
			stmt.close();
			con.close();
			
			return f_product;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	//��ǰ �˻� ���(���߿� ���� �ʿ�)
	public String[][] find_product(String p_name, int rowCount)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product WHERE p_name = "+"'"+p_name+"'");
			
			String f_product[][] = new String[rowCount][5];
			int i = 0;
			while(result.next())
			{
				f_product[i][0] = result.getString("p_num");
				f_product[i][1] = result.getString("p_name");
				f_product[i][2] = result.getString("p_amount");
				f_product[i][3] = result.getString("p_cost");
				f_product[i][4] = result.getString("p_provide");
				i++;
			}
			
			stmt.close();
			con.close();
			
			return f_product;
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	
	
	//���� �޼ҵ�
	public void charge(int c_num, String c_name, int c_amount, 
			String c_way, int c_cost, String c_day, String c_assistant)
	/* c_num = �ŷ���ȣ
	 * c_name = ��ǰ��
	 * c_amount = ��ǰ����
	 * c_way = �������(ī�� or ����)
	 * c_cost = ��ǰ����(���� 2�� ���Ž� ���� 2������ǥ��)
	 * c_day = ������(�� �� �� �� �� �� ���� ǥ��)
	 * c_assistant = ��������� �̸�*/
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO charge VALUES("
					+c_num+","+"'"+c_name+"'"+","+c_amount+","
					+"'"+c_way+"'"+","+c_cost+","+"'"+c_day+"'"
					+","+"'"+c_assistant+"'"+")");
			
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	//���� ���̺� ��� ����
	public int c_countRow()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM charge");
			
			//������ ���� �̵�
			result.last();
			int rowCount = result.getRow();//������ ���� �ޱ�
			
			stmt.close();
			con.close();
			return rowCount;
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	
	//���� ���̺� ��� ǥ��
	public String[][] charge_info(int rowCount)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM charge");
			
			String[][] chargev = new String[rowCount][7];
			int i = 0;
			
			while(result.next())
			{
				chargev[i][0] = result.getString("c_num");
				chargev[i][1] = result.getString("c_name");
				chargev[i][2] = result.getString("c_amount");
				chargev[i][3] = result.getString("c_way");
				chargev[i][4] = result.getString("c_cost");
				chargev[i][5] = result.getString("c_day");
				chargev[i][6] = result.getString("c_assistant");
				i++;
			}
			
			stmt.close();
			con.close();
			return chargev;
			
		}
		catch(Exception e)
		{
			
		}
		return null;
	}
	
	
	//���� �޼ҵ�
	public void balance(int b_num, String b_day, String b_admin, 
			int b_reserve_fund, int total_amount, int difference_pay)
	/* b_num = �����ȣ
	 * b_day = ������(�� �� �� �� �� �� ���� ����)
	 * b_admin = ������(������ ���� �̸�)
	 * b_reserve_fund = �غ��+���ݰ����ݾ�
	 * b_total_amount = �հ�ݾ�(���ϱݰ�����ִ� �ݾ�)
	 * b_difference_pay = ���̱ݾ�*/
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			stmt.executeUpdate("INSERT INTO balance VALUES("
					+b_num+","+"'"+b_day+"'"+","+"'"+b_admin+"'"+","
					+b_reserve_fund+","+total_amount+","
					+difference_pay+")");
			
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			
		}
		
	}
	
	
	//�α��� �޼ҵ�
	public String login(String id, String pw)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet find_dbinfo = stmt.executeQuery("SELECT * FROM user_account");
			
			while(find_dbinfo.next())
			{
				String dbid = find_dbinfo.getString("ID");
				String dbpw = find_dbinfo.getString("PW");
				
				
				if(id.equals(dbid)&&pw.equals(dbpw))
				{
					stmt.close();
					con.close();
					return "�α��� ����";
				}
			}
			stmt.close();
			con.close();
			
		}
		catch(Exception e)
		{
			
		}
		return "�α��� ����";
	}
	
	
	//���� ����
	public String delet_user(String user_id)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			/**/
			pstmt = con.prepareStatement("DELETE FROM user_account WHERE ID = ?");
			pstmt.setString(1,user_id);
			pstmt.execute();
			/*
			StringBuilder sb = new StringBuilder();
			
			String sql = sb.append("delete from user_account where ID = ")
		                .append(user_id)
		                .append(";")
		                .toString();
			System.out.println(sql);
			stmt.executeUpdate(sql);*/
			//stmt.executeUpdate("DELETE FROM user_account WHERE ID = "+user_id+";");
			
			stmt.close();
			con.close();
			return "���� ����";
		}
		catch(Exception e)
		{
			
		}
		return "���� ����";
	}
	
	//��ǰ�� �����Ͽ� ListNum�� 1���δ�
	public void removeListNum(int getRows)
	{
		i = i-getRows;
	}
	
	public Vector<PosUse> searchBy(String identifier)//���� ���ã�� �޼ҵ�
	{
		Vector<PosUse> salesList = new Vector<PosUse>();

		String query = "";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			System.out.println(SalesInputService.key);
//			"��ȣ", "��ǰ�ڵ�", "��ǰ��", "�ܰ�", "����", "�ݾ�", "�������"	
			if (SalesInputService.key == true) 
			{
				//��ǰ �ڵ�, ��ǰ��, ��ǰ ����, 1(����), ��������� �������� ��ǰ�ڵ带 �빮���̰� ��������� �������� ������������ ���� �� select�ϴ� �ڵ�
				query = "select p_num, p_name, 1, p_cost, p_category, p_provide from product where p_num = ?";
				System.out.println("code");
			} 
			else if (SalesInputService.key == false) 
			{
				query = "select p_num, p_name, 1, p_cost, p_category, p_provide from product where p_name = ?";
				System.out.println("name");

			}
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, identifier);

			result = pstmt.executeQuery();
			
			System.out.println("�۵�");
			
			while (result.next()) 
			{/*
				posDto = new PosDto();
				posDto.setListNum(i);
				posDto.setProductCode(result.getString(1)); //1 String
				posDto.setProductName(result.getString(2)); //2 String
				posDto.setPrice(result.getInt(3)); //3 int
				posDto.setSellCount(1); //4 int
				posDto.setPricensellCount(result.getInt(3) * posDto.getSellCount()); //5 int
				posDto.setInDate(result.getString(5)); //6 String
				

				salesList.add(posDto);*/
				System.out.println("�۵�: " +i);
				posUse = new PosUse();
				posUse.setListNum(i);
				posUse.setp_num(result.getString(1)); //int ��
				posUse.setp_name(result.getString(2));// String ��
				posUse.setp_amount(1);// int ��
				posUse.setp_cost(result.getInt(4));// int ��
				posUse.setp_category(result.getString(5));// String ��
				posUse.setp_provide(result.getString(6)); // String ��
				posUse.setp_costsellCount(result.getInt(4)*posUse.getp_amount());//int ��
				
				System.out.println("�� �ֱ� ����");
				salesList.add(posUse);
				i++;
				
				return salesList;
			}
			
			int x = salesList.size();
			System.out.println(x);
			System.out.println("��ȸ�Ϸ�");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pstmt.close();
				con.close();
				result.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	//�α��� ���
	//���� ���� ��� �߰�
	//������ ���� ��� �߰�
	//�Ǹ� ����(��ǰ ����� DB���� ������ ����ϴ� ���) �߰�
	//��ǰ �˻� ��� �߰�
	//ȯ�� ��� �߰� (��ǰ ��ȣ �˻� �� ��ü ���̺� ��ǰ ǥ�� �� �� ȯ�� ���� �߰�)
	
	
	//��ǰ��� �������� select�� 
	/*��ȣ ��ǰ�ڵ� ��ǰ�� �ܰ� ���� �ݾ��� ã�� �� �ִ� select��
	 *�����Ϸ����� ���������� ����� �� �ִ� insert��
	 *���� � ��ǰ�� �ȾҴ����� ���� insert��(select��)
	 *������ �����ϴ� update��
	 *���¼ҽ� ���� ����
	 *�ǸŰ��� ���輳�� �� ��ɹ�*/
	
	
	//���κ�
	//////////////////////////////// ��ǰ�� ��� ////////////////////////////////
	// <��ǰ�� ���⳻�� select> �޼ҵ�
	// : �Һз�, ��, �� �Է¹޾� �����հ� ��ŷ������ ��ȸ
	public Vector<PosUse> findProductSell(String minor_level, String year, String month) {
		
		Vector<PosUse> list= new Vector<PosUse>();
		
		int date = Integer.parseInt(year.concat(month).concat("01"));
		try {
			//DB����
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			posDto = new PosDto();
			//������ ����
			String query = "SELECT P.P_NUM AS '��ǰ�ڵ�', P.P_CATEGORY AS '��ǰ�з�', P.P_NAME AS '��ǰ��', P.P_COST AS '����', V.SC AS '�Ǹż���', P.P_COST * V.SC AS '�����հ�', P.P_PROVIDE AS '������'\r\n"+
					"FROM PRODUCT P, (SELECT CHARGE.C_NAME, SUM(CHARGE.C_AMOUNT) SC FROM CHARGE group by C_NAME) V\r\n"+
					"WHERE P.P_NUM = V.C_NAME\r\n"+
					"order by P.P_COST * V.SC desc";
			
			pstmt= con.prepareStatement(query);
			result = pstmt.executeQuery();
			
			while (result.next()) {
				posUse = new PosUse();
				
				posUse.setp_num(result.getString(1));
				posUse.setp_category(result.getString(2));
				posUse.setp_name(result.getString(3));
				posUse.setp_cost(result.getInt(4));
				posUse.setTotalamount(result.getInt(5));
				posUse.setTotalproductprice(result.getInt(6));
				posUse.setp_provide(result.getString(7));
				
				list.add(posUse);
				
			}

		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				pstmt.close();
				con.close();
				result.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		}
		
		return list;
	}
	
	// <��ǰ�� ���⳻�� BEST 5 select> �޼ҵ�
	// : �Һз�, ��, �� �Է¹޾� �����հ� ��ŷ ���� 5������ ��ȸ
	public Vector<PosUse> findProductSellBestFive(String minor_level, String year, String month) {
		// ������ ��� (���� ��) ���� PosDto ��ü
		Vector<PosUse> list = new Vector<PosUse>();

		// String���� �Է��� ��¥�� '���' ���ļ� int�� ��ȯ
		int date = Integer.parseInt(year.concat(month).concat("01"));
		try {
			// DB ����
			con = DriverManager.getConnection(url,user,passwd);

			// ������ ����
			String query = "SELECT P.P_NUM AS '��ǰ�ڵ�', P.P_CATEGORY AS '��ǰ�з�', P.P_NAME AS '��ǰ��', P.P_COST AS '����', V.SC AS '�Ǹż���', P.P_COST * V.SC AS '�����հ�', P.P_PROVIDE AS '������'\r\n"+
					"FROM PRODUCT P, (SELECT CHARGE.C_NAME, SUM(CHARGE.C_AMOUNT) SC FROM CHARGE group by C_NAME) V\r\n"+
					"WHERE P.P_NUM = V.C_NAME\r\n"+
					"order by P.P_COST * V.SC desc limit 5";
			pstmt = con.prepareStatement(query);
			//pstmt.setInt(1, date);
			//pstmt.setString(2, minor_level);

			// ������ ����
			result = pstmt.executeQuery();

			// ��� ����
			while (result.next()) {
				posUse = new PosUse();
				
				
				posUse.setp_num(result.getString(1));
				posUse.setp_category(result.getString(2));
				posUse.setp_name(result.getString(3));
				posUse.setp_cost(result.getInt(4));
				posUse.setTotalamount(result.getInt(5));
				posUse.setTotalproductprice(result.getInt(6));
				posUse.setp_provide(result.getString(7));

				list.add(posUse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB ���� ����
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ��� ����
		return list;
	}
	
	// ���̺� �� ��� ����� (ȭ��ܿ�����)
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}
	
	
	


	
	
	
	

}
