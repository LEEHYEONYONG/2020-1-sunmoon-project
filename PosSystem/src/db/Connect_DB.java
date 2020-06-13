package db;

import java.sql.*;

public class Connect_DB {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	String url = "jdbc:mysql://localhost/pos_system?serverTimezone=Asia/Seoul";
	String user = "POS_user";
	String passwd = "pos project";
	

	
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

}
