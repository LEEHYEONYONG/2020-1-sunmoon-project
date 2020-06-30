package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import main.Login;
import main.MainFrame;
import sale.SalesInputService;

public class Connect_DB {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	//String url = "jdbc:mysql://211.111.101.56/pos_system?serverTimezone=Asia/Seoul";
	//String url = "jdbc:mysql://localhost/pos_system?serverTimezone=Asia/Seoul";
	String url = "jdbc:mysql://112.186.64.92/pos_system?serverTimezone=Asia/Seoul";
	String user = "POS_user";
	String passwd = "pos project";
	
	ResultSet result = null;

	PosDto posDto = null;
	public PosUse posUse = null;
	SalesInputService salesInputService;
	int rf_index = 0;//환불 행
	
	boolean flag;
	
	public int i = 1;
	
	//로그인 저장
	public static String ID=null;
	public static String PW=null;
	public static String user_name=null;
	public static String user_email=null;
	public static String rank=null;
	/*
	posUse.setID(result.getString(1));
	posUse.setPW(result.getString(2));
	posUse.setUser_name(result.getString(3));
	posUse.setUser_email(result.getString(4));
	posUse.setRank(result.getString(5));
	*/
	
	
	
	public Connect_DB()
	{
		
	}
	
	//연동기능 확인용 메소드
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
			System.out.println("연동 성공");
		}
		catch(Exception e)
		{
			System.out.println("연동 실패");
		}
	}
	
	//회원가입 메소드
	public void new_user(String user_id, String user_pw, 
			String user_name, String user_email)
	/* user_id = 유저 아이디
	 * user_pw = 유저 페스워드
	 * user_name = 유저 이름
	 * user_email = 유저 이메일*/
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			//아이디 중복 확인 기능 추가
			ResultSet find_dbid = stmt.executeQuery("SELECT ID FROM user_account");
			while(find_dbid.next())
			{
				String dbid = find_dbid.getString("ID");
				if(user_id.equals(dbid))
				{
					//return 이미 사용중인 아이디 
				}
			}
			
			//회원가입 정보 DB에 넣기
			stmt.executeUpdate("INSERT INTO user_account VALUES("
					+"'"+user_id+"'"+","+"'"+user_pw+"'"+","
					+"'"+user_name+"'"+","+"'"+user_email+"'"+","
					+"'"+"점원"+"'"+")");
			
			stmt.close();
			con.close();
			//return 회원가입 성공
		}
		catch(Exception e)
		{
			
		}
		//return 회원가입 실패
	}
	
	
	//상품 입고 메소드
	public void product_input(int p_num, String p_name, 
			int p_amount, int p_cost, String p_provide)
	/* p_num = 상품번호
	 * p_name = 상품명
	 * p_amount = 상품수량
	 * p_cost = 상품가격
	 * p_provide = 제조사*/
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			//같은 이름의 상품이 들어올 경우 대처 방법
			//1 수량만 증가시킨다
			//2 고유번호를 다르게 해서 새로 추가한다.
			
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
	
	//상품 목록을 보기 위해 필요한 열 개수
	public int countRow()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product");
			
			//마지막 열로 이동
			result.last();
			int rowCount = result.getRow();//마지막 열값 받기
			
			stmt.close();
			con.close();
			return rowCount;
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	
	//상품 목록 보는 메소드
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
	
	
	//검색상품 열 개수
	public int countRow(String p_name)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM product WHERE p_name = "+"'"+p_name+"'");
			
			//마지막 열로 이동
			result.last();
			int rowCount = result.getRow();//마지막 열값 받기
			
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
	
	//상품 코드 검색
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
	
	//상품 검색 기능(나중에 논의 필요)
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
	
	
	
	
	//결제 메소드
	public void charge(int c_num, String c_name, int c_amount, 
			String c_way, int c_cost, String c_day, String c_assistant)
	/* c_num = 거래번호
	 * c_name = 상품명
	 * c_amount = 상품수량
	 * c_way = 결제방법(카드 or 현금)
	 * c_cost = 상품가격(생수 2병 구매시 생수 2병가격표시)
	 * c_day = 결제일(년 월 일 시 분 초 단위 표시)
	 * c_assistant = 담당점원의 이름*/
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
	
	//결제 테이블 목록 개수
	public int c_countRow()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			ResultSet result = stmt.executeQuery("SELECT * FROM charge");
			
			//마지막 열로 이동
			result.last();
			int rowCount = result.getRow();//마지막 열값 받기
			
			stmt.close();
			con.close();
			return rowCount;
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	
	//결제 테이블 목록 표시
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
	
	
	//정산 메소드
	public void balance(int b_num, String b_day, String b_admin, 
			int b_reserve_fund, int total_amount, int difference_pay)
	/* b_num = 정산번호
	 * b_day = 정산일(년 월 일 시 분 초 단위 포시)
	 * b_admin = 관리자(정산한 점원 이름)
	 * b_reserve_fund = 준비금+현금결제금액
	 * b_total_amount = 합계금액(당일금고에들어있는 금액)
	 * b_difference_pay = 차이금액*/
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
	
	
	//로그인 메소드
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
					return "로그인 성공";
				}
			}
			stmt.close();
			con.close();
			
		}
		catch(Exception e)
		{
			
		}
		return "로그인 실페";
	}
	
	
	//계정 삭제
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
			return "삭제 성공";
		}
		catch(Exception e)
		{
			
		}
		return "삭제 실페";
	}
	
	//상품을 삭제하여 ListNum을 1줄인다
	public void removeListNum(int getRows)
	{
		i = i-getRows;
	}
	
	public Vector<PosUse> searchBy(String identifier)//결제 목록찾기 메소드
	{
		Vector<PosUse> salesList = new Vector<PosUse>();

		String query = "";
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			
			System.out.println(SalesInputService.key);
//			"번호", "상품코드", "상품명", "단가", "수량", "금액", "유통기한"	
			if (SalesInputService.key == true) 
			{
				//상품 코드, 상품명, 상품 가격, 1(수량), 유통기한을 가져오고 상품코드를 대문자이고 유통기한을 기준으로 오름차순으로 정렬 후 select하는 코드
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
			
			System.out.println("작동");
			
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
				System.out.println("작동: " +i);
				posUse = new PosUse();
				posUse.setListNum(i);
				posUse.setp_num(result.getString(1)); //int 형
				posUse.setp_name(result.getString(2));// String 형
				posUse.setp_amount(1);// int 형
				posUse.setp_cost(result.getInt(4));// int 형
				posUse.setp_category(result.getString(5));// String 형
				posUse.setp_provide(result.getString(6)); // String 형
				posUse.setp_costsellCount(result.getInt(4)*posUse.getp_amount());//int 형
				
				System.out.println("값 넣기 성공");
				salesList.add(posUse);
				i++;
				
				return salesList;
			}
			
			int x = salesList.size();
			System.out.println(x);
			System.out.println("조회완료");
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
	
	
	//결제 테이블 기본키 중복없이 생성
		public void duplicate_key()
		{
			try
			{
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				/*
				SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
				Calendar time = Calendar.getInstance();
				String time1 = format1.format(time.getTime());
				posUse.setc_num(time1+String.format("%04d",buyCount));
				*/
				
				//기본키 중복 확인
				//String Duplicate = "select c_num, c_name from charge";
				
				//기본키 중복되지 않게 생성
				String Duplicate = "select count(distinct c_num) from charge " + 
						"where date_format(c_day,'%Y%m%d')=date_format(now(),'%Y%m%d')";
				result = stmt.executeQuery(Duplicate);
				
				result.next();
				String num = result.getString("count(distinct c_num)");
				System.out.println("count(distinct c_num) : "+num);
				int buyCount = Integer.parseInt(num)+1;
				
				SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMdd");
				Calendar time = Calendar.getInstance();
				String time1 = format1.format(time.getTime());
				posUse.setc_num(time1+String.format("%04d",buyCount));
				/*
				while(result.next())
				{
					String num = result.getString("c_num");
					
					String name = result.getString("c_name");
					if(num.equals(posUse.getc_num()) && name.equals(posUse.getc_name()))
					{
						System.out.println("c_num : "+num);
						System.out.println("c_name : "+name);
						buyCount++;
					}
					
				}
				*/
				
				/*
				//기본키 중복 확인
				String Duplicate = "select c_num from charge";
				result = stmt.executeQuery(Duplicate);
				while(result.next())
				{
					String num = result.getString("c_num");
				
					if(num.equals(posUse.getc_num()))
					{
						System.out.println("c_num : "+num);
						
						buyCount++;
					}
				}*/
				/*
				System.out.println(time1);
				System.out.println(buyCount);
				System.out.println(String.format("%04d",buyCount));
				posUse.setc_num(time1+String.format("%04d",buyCount));*/
				
				System.out.println(posUse.getc_num());
				
				stmt.close();
				con.close();
				
				
			}
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				//e.printStackTrace(System.out);
				e.printStackTrace();
			} 
		}
	
		
		//결제 기능
		public void registerHistory() {
			//conn = DBManager.getConnection();

			try {
				
				//Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				
				
				String query = "insert into charge(c_num, c_name, c_amount, c_way, c_cost, c_day, c_assistant,c_state) "
						+ "values(?, ?, ?, ?, ?, now(), ?, ?)";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, posUse.getc_num());
				pstmt.setString(2, posUse.getc_name());
				pstmt.setString(3, String.valueOf(posUse.getc_amount()));
				pstmt.setString(4, posUse.getc_way());
				pstmt.setString(5, String.valueOf(posUse.getc_cost()));
				pstmt.setString(6, Connect_DB.ID);
				
				if(SalesInputService.key==true)
				{
					pstmt.setString(7, "구매");
				}
				else if(SalesInputService.key==false)
				{
					pstmt.setString(7, "환불");
				}

				
				int r = pstmt.executeUpdate();
				
				//posUse.getc_assistant()
				
//				System.out.println("변경된 row : " + r);

			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			finally 
			{
				try {
					//DBManager.dbClose(rs, ps, conn);
					pstmt.close();
					con.close();
					result.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}

		}
		
		//수정할 상품 갯수 가져오기
		public int amount_revise1()
		{
			int amount = 0;
			try
			{
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				
				//상품 테이블 상품 갯수 가져오기
				String query ="select p_amount from product where p_num = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, posUse.getc_name());
				
				result = pstmt.executeQuery();
				
				if(result.next())
				{
					amount = result.getInt("p_amount");
				}
				
				System.out.println(posUse.getc_name()+"번 상품 갯수 : "+amount);
				if(SalesInputService.key==true)
				{
					System.out.println("구매");
					return amount - posUse.getc_amount();//수정할 갯수
				}
				else if(SalesInputService.key == false)
				{
					System.out.println("환불");
					return amount + posUse.getc_amount();//수정할 갯수
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				try {
					//DBManager.dbClose(rs, ps, conn);
					pstmt.close();
					con.close();
					result.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}
			return -1;
		}
		
		//상품 테이블 상품 갯수 수정
		public void amount_revise2(int amount)
		{
			try
			{
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				
				//상품 테이블 상품 갯수 수정
				String query ="UPDATE product SET p_amount = ? WHERE p_num = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, String.valueOf(amount));
				pstmt.setString(2, posUse.getc_name());
				
				System.out.println(posUse.getc_name()+"번 상품의 수정 갯수: "+amount);
				//System.out.println("수정할 갯수: "+String.valueOf(amount-posUse.getc_amount()));
				
				int r = pstmt.executeUpdate();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				try {
					//DBManager.dbClose(rs, ps, conn);
					pstmt.close();
					con.close();
					result.close();
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}

			}
		}
	
		//검색한 상품의 갯수 확인하기
		public int amount_value(String identifier)
		{
			int amount=0;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				String query = "";
				
				System.out.println(SalesInputService.key);
				
				if (SalesInputService.key == true) 
				{
					query = "select p_amount from product where p_num = ?";
					System.out.println("code");
				} 
				else if (SalesInputService.key == false) 
				{
					query = "select p_amount from product where p_name = ?";
					System.out.println("name");
				}
				
				System.out.println(query);
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, identifier);
				result = pstmt.executeQuery();
				
				
				if(result.next())
				{
					System.out.println("amount:"+result.getInt("p_amount"));
					amount = result.getInt("p_amount");
					//int amount = result.getInt(1);
				}
				System.out.println("상품개수:" + amount);
				
				return amount;
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					result.close();
					pstmt.close();
					con.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			return -1;
		}
		
		
		//환불 관련 메소드
		public Vector<PosUse> refundc_num(String sellId)
		{
			Vector<PosUse> salesList = new Vector<PosUse>();
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				
				String query = "select c_name, c_amount, c_way, c_cost, c_state from charge where c_num = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, sellId);
				System.out.println(pstmt);
				result = pstmt.executeQuery();
				
				result.last();
				rf_index = result.getRow();
				result.beforeFirst();
				posUse = new PosUse();
				posUse.setting_index(rf_index);
				
				//DB에서 값을 받을 배열
				String[] rf_name = new String[rf_index];
				int[] rf_amount = new int[rf_index];
				String[] rf_way = new String[rf_index];
				int[] rf_cost = new int[rf_index];
				String[] rf_state = new String[rf_index];
				
				int i=0;
				
				while(result.next())
				{
					rf_name[i] = result.getString("c_name");
					System.out.println("환불 상품 명: "+rf_name[i]);
					rf_amount[i] = Integer.parseInt(result.getString("c_amount"));
					rf_way[i] = result.getString("c_way");
					rf_cost[i] = Integer.parseInt(result.getString("c_cost"));
					rf_state[i] = result.getString("c_state");
					
					i++;
					//System.out.println(result.getString("c_name"));
					/*
					posUse.setrf_name(result.getString("c_name"));
					posUse.setrf_amount(Integer.parseInt(result.getString("c_amount")));
					posUse.setrf_way(result.getString("c_way"));
					posUse.setrf_cost(Integer.parseInt(result.getString("c_cost")));*/
				}
				posUse.setrf_name(rf_name);
				posUse.setrf_amount(rf_amount);
				posUse.setrf_way(rf_way);
				posUse.setrf_cost(rf_cost);
				posUse.setrf_state(rf_state);
				
				salesList.add(posUse);
				
				return salesList;
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
		
		public int getrf_index()
		{
			return rf_index;
		}
		
		
		public void change_state(String c_num)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				
				String query ="UPDATE charge SET c_state = ? WHERE c_num = ?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "환불");
				pstmt.setString(2, c_num);
				System.out.println(pstmt);
				
				int r = pstmt.executeUpdate();
				
				System.out.println("환불로 상태변경 환료");
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
			
		}
		
		
		public String refund_getp_name(String c_name)
		{
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,passwd);
				stmt = con.createStatement();
				
				String query = "select p_name from product where p_num = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, c_name);
				System.out.println(pstmt);
				result = pstmt.executeQuery();
				
				String p_name="";
				if(result.next())
				{
					p_name = result.getString("p_name");
				}
				return p_name;
				
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
		
		
	//로그인 기능
	//계정 삭제 기능 추가
	//관리자 변경 기능 추가
	//판매 관리(상품 목록을 DB에서 가져와 출력하는 기능) 추가
	//상품 검색 기능 추가
	//환불 기능 추가 (상품 번호 검색 후 결체 테이블 상품 표시 그 후 환불 내역 추가)
	
	
	//상품목록 가져오는 select문 
	/*번호 상품코드 상품명 단가 수량 금액을 찾을 수 있는 select문
	 *결제완료이후 결제내역에 등록할 수 있는 insert문
	 *누가 어떤 상품을 팔았는지에 대한 insert문(select문)
	 *재고수를 변경하는 update문
	 *오픈소스 참고 가능
	 *판매관련 관계설정 및 명령문*/
	
	
	//통계부분
	//////////////////////////////// 상품별 통계 ////////////////////////////////
	// <상품별 매출내역 select> 메소드
	// : 소분류, 년, 월 입력받아 매출합계 랭킹순으로 조회
	public Vector<PosUse> findProductSell(String minor_level, String year, String month) {
		
		Vector<PosUse> list= new Vector<PosUse>();
		
		//int date = Integer.parseInt(year.concat(month).concat("01"));
		String date = year.concat(month);
		try {
			//DB연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			posDto = new PosDto();
			//쿼리문 세팅
			String query = "SELECT P.P_NUM AS '상품코드', P.P_CATEGORY AS '상품분류', P.P_NAME AS '상품명', P.P_COST AS '가격', V.SC AS '판매수량', P.P_COST * V.SC AS '매출합계', P.P_PROVIDE AS '제조사'\r\n"+
					"FROM PRODUCT P, (SELECT CHARGE.C_NAME, SUM(CHARGE.C_AMOUNT) SC FROM CHARGE where date_format(c_day,'%Y%m')=? and c_state='구매' group by C_NAME) V\r\n"+
					"WHERE P.P_NUM = V.C_NAME and p.p_category =?\r\n"+
					"order by P.P_COST * V.SC desc";
			
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, date);
			pstmt.setString(2, minor_level);
			
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
	
	// <상품별 매출내역 BEST 5 select> 메소드
	// : 소분류, 년, 월 입력받아 매출합계 랭킹 상위 5위까지 조회
	public Vector<PosUse> findProductSellBestFive(String minor_level, String year, String month) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		// String으로 입력한 날짜를 '년월' 합쳐서 int로 변환
		//int date = Integer.parseInt(year.concat(month).concat("01"));
		String date = year.concat(month);
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query = "SELECT P.P_NUM AS '상품코드', P.P_CATEGORY AS '상품분류', P.P_NAME AS '상품명', P.P_COST AS '가격', V.SC AS '판매수량', P.P_COST * V.SC AS '매출합계', P.P_PROVIDE AS '제조사'\r\n"+
					"FROM PRODUCT P, (SELECT CHARGE.C_NAME, SUM(CHARGE.C_AMOUNT) SC FROM CHARGE where date_format(c_day,'%Y%m')=? and c_state='구매' group by C_NAME) V\r\n"+
					"WHERE P.P_NUM = V.C_NAME and p.p_category =?\r\n"+
					"order by P.P_COST * V.SC desc limit 5";
			pstmt = con.prepareStatement(query);
			//pstmt.setInt(1, date);
			pstmt.setString(1, date);
			pstmt.setString(2, minor_level);

			// 쿼리문 실행
			result = pstmt.executeQuery();

			// 결과 저장
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
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
	}
	
	
	//////////////////////////////// 기간별 통계 ////////////////////////////////
	// <연도별 매출내역 select> 메소드
	// : 년 입력받아 조회

	public Vector<PosUse> findYearSell(int startYear, int endYear) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query = "select A.aa as '매출년도', A.ab as '매출합계' , B.bb as '현금매출' ,C.cb as '카드매출', A.ac as '고객수'\r\n"+
					       "from (select date_format(c_day,'%Y') as aa , sum(c_cost) as ab, count(distinct c_num) as ac from charge where c_state='구매' group by date_format(c_day,'%Y')) A\r\n"+ 
					       "left join (select c_way as ba, sum(c_cost) as bb, date_format(c_day,'%Y') as bc from charge where c_way='현금' and c_state='구매' group by date_format(c_day,'%Y'),c_way) B on A.aa=b.bc\r\n"+
					       "left join (select c_way as ca, sum(c_cost) as cb, date_format(c_day,'%Y') as cc from charge where c_way='카드' and c_state='구매' group by date_format(c_day,'%Y'),c_way) C on A.aa=c.cc\r\n"+
					       "where A.aa between ? and ? order by A.aa";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startYear);
			pstmt.setInt(2, endYear);

			// 쿼리문 실행
			result = pstmt.executeQuery();

			// 결과 저장
			while (result.next()) {
				
				posUse = new PosUse();
				
				posUse.setYearSellDate(result.getString(1));
				posUse.setYearTotalPrice(result.getInt(2));
				posUse.setYearTotalcash(result.getInt(3));
				posUse.setYearTotalcard(result.getInt(4));
				posUse.setYearTotalaccount(result.getInt(5));
				
				list.add(posUse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 결과 리턴
		return list;
	}
	
	
	
	// <월별 매출내역 select> 메소드
	// : 년 입력받아 조회
	public Vector<PosUse> findMonthSell(int year) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query ="select A.aa as '월별매출', ifnull(A.ab,0) as '매출합계' , ifnull(B.bb,0) as '현금매출' ,ifnull(C.cb,0) as '카드매출', ifnull(A.ac,0) as '고객수'\r\n"+
					      "from (select date_format(c_day,'%Y')as zz,date_format(c_day,'%Y%m') as aa , sum(c_cost) as ab, count(distinct c_num) as ac from charge where c_state='구매' group by date_format(c_day,'%Y%m') order by date_format(c_day,'%Y%m')) as A\r\n"+ 
					      "left JOIN (select c_way as ba, sum(c_cost) as bb, date_format(c_day,'%Y%m') as bc from charge where c_way='현금' and c_state='구매' group by date_format(c_day,'%Y%m'),c_way order by date_format(c_day,'%Y%m')) as B on A.aa=B.bc \r\n"+
					      "left JOIN (select c_way as ca, sum(c_cost) as cb, date_format(c_day,'%Y%m') as cc from charge where c_way='카드' and c_state='구매' group by date_format(c_day,'%Y%m'),c_way order by date_format(c_day,'%Y%m')) as C on A.aa=C.cc\r\n"+
					      "where A.zz=? order by A.aa";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, year);
			// 쿼리문 실행
			result = pstmt.executeQuery();

			// 결과 저장
			while (result.next()) {
				
				posUse = new PosUse();
				
				posUse.setMonthSellDate(result.getString(1));
				posUse.setMonthTotalPrice(result.getInt(2));
				posUse.setMonthTotalcash(result.getInt(3));
				posUse.setMonthTotalcard(result.getInt(4));
				posUse.setMonthTotalaccount(result.getInt(5));
				
				list.add(posUse);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;
	}
	
	
	
	// <일별 매출내역 select> 메소드
	// : 년, 월, 일 입력받아 조회
	public PosUse findDaySell(String year, String month, String day) {

		// String으로 입력한 날짜를 '년월일' 합쳐서 int로 변환
		int date =Integer.parseInt(year.concat(month).concat(day));
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);
			posUse = new PosUse();
			// 쿼리문 세팅
			String query ="select A.aa as '월별매출', ifnull(A.ab,0) as '매출합계' , ifnull(B.bb,0) as '현금매출' ,ifnull(C.cb,0) as '카드매출', ifnull(A.ac,0) as '고객수'\r\n"+
					      "from (select date_format(c_day,'%Y%m%d') as aa , sum(c_cost) as ab, count(distinct c_num) as ac from charge where c_state='구매' group by date_format(c_day,'%Y%m%d') order by date_format(c_day,'%Y%m%d')) as A\r\n"+
					      "left JOIN (select c_way as ba, sum(c_cost) as bb, date_format(c_day,'%Y%m%d') as bc from charge where c_way='현금' and c_state='구매' group by date_format(c_day,'%Y%m%d'),c_way order by date_format(c_day,'%Y%m%d')) as B on A.aa=B.bc\r\n"+
					      "left JOIN (select c_way as ca, sum(c_cost) as cb, date_format(c_day,'%Y%m%d') as cc from charge where c_way='카드' and c_state='구매' group by date_format(c_day,'%Y%m%d'),c_way order by date_format(c_day,'%Y%m%d')) as C on A.aa=C.cc\r\n"+
					      "where A.aa=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, date);

			// 쿼리문 실행
			result = pstmt.executeQuery();

			// 결과 저장
			while (result.next()) {
				
				posUse.setDaySellDate(result.getString(1));
				posUse.setDayTotalPrice(result.getInt(2));
				posUse.setDayTotalcash(result.getInt(3));
				posUse.setDayTotalcard(result.getInt(4));
				posUse.setDayTotalaccount(result.getInt(5));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		

		// 결과 리턴
		return posUse;
	}
	
	
	// <조회한 일자의 시간대별 매출값 리턴> 메소드
	// : 시간대별 그래프용
	public Vector<PosUse> findDayTimeSell(String year, String month, String day) {

		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		// String으로 입력한 날짜를 '년월일' 합쳐서 int로 변환
		int date = Integer.parseInt(year.concat(month).concat(day));
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query ="select date_format(c_day,'%H') as '판매시간대',  sum(c_cost) as '매출합계', count(distinct c_num) as '고객수' from charge \r\n"+
					      "where date_format(c_day,'%Y%m%d') =? and c_state='구매' group by date_format(c_day,'%H')";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, date);

			// 쿼리문 실행
			result = pstmt.executeQuery();

			// 결과 저장
			while (result.next()) {
				
				posUse = new PosUse();
				
				posUse.setClockTime(result.getString(1).concat("시"));
				posUse.setClockTotalPrice(result.getInt(2));
				posUse.setClockTotalaccount(result.getInt(3));
				System.out.println(result.getString(1).concat("시"));
				System.out.println(result.getInt(2));
				System.out.println(result.getInt(3));
				
				list.add(posUse);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 결과 리턴
		return list;

	}
	
	
	////////////////////////////////재고관련 메소드//////////////////////////////////////////
	
	// 모든재고표시
	public Vector<PosUse> StockAll() {

		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
            String query = "select p_num, p_name, p_cost, p_category, p_provide, p_amount from product";

			// 쿼리문 실행
            stmt = con.prepareStatement(query);
			result = stmt.executeQuery(query);

			// 결과 저장
			while (result.next()) {
				
				posUse = new PosUse();
				
				posUse.setp_num(result.getString(1));
				posUse.setp_name(result.getString(2));
				posUse.setp_cost(result.getInt(3));
				posUse.setp_category(result.getString(4));
				posUse.setp_provide(result.getString(5));
				posUse.setp_amount(result.getInt(6));
				
				list.add(posUse);
				flag=true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		flag=false;
		// 결과 리턴
		return list;

	}
	
	
	//////////////// 재고수정
	// 테이블에서 선택한 행의 정보 표시 후 수량만 수정하기
	// 수량만 자유자재로 바꿀수있음.
	public void StockChange(int volume, String productCode) {

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅, 조건으로사용하는 상품코드는 겟텍스트로 받기.
			String query = "UPDATE product SET p_amount = ? WHERE p_num = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, volume);
			pstmt.setString(2, productCode);

			// 쿼리문 실행
			int r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	
	
         ////////////입고(상품등록)
         // 인자값 5개만 받아서 입력하기 상품코드 상품명 가격 종류 제조사
           // 나머지 5개 데이터는 product테이블 참조해서 채워넣기 가능?
	public int StockIn(String productCode, String product, int volume, String category, String provide) {
		int r = 0;

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query ="INSERT INTO product (p_num, p_name, p_cost, p_category, p_provide) VALUES (?, ?, ?, ?, ?)";
			
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, productCode);
			pstmt.setString(2, product);
			pstmt.setInt(3, volume);
			pstmt.setString(4, category);
			pstmt.setString(5, provide);

			// 
			
			// 쿼리문 실행
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e+"id겹침");
			
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				//result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r;
	}
	
	//이름중복확인
	public boolean stockname(String name) {
		boolean aa=true;
		
		// String으로 입력한 날짜를 '년월일' 합쳐서 int로 변환
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query ="SELECT p_name from product where p_name = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, name);

			// 쿼리문 실행
		    result = pstmt.executeQuery();
			
			String ace=null;
			
			if(result.next()) {
			ace = result.getString(1);
			System.out.println(ace+"상품이름");

			}
			
			if(ace==null) {
				aa=false;
				return aa;
			}
			
			
			


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e+"다른문제있음");
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return aa;
	}
	
	
	
	
	
	////상품삭제 
	
	public int stockdelete(String productCode) {
		int r=0;
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅, 조건으로사용하는 상품코드는 겟텍스트로 받기.
			String query = "delete from product where p_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);

			// 쿼리문 실행
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	
    ///////////////////조회
    // 원하는 재고 조회 ( 상품코드 또는 상품명으로만)
    // 코드로하는 메소드
	public Vector<PosUse> StockSearchCode(String productCode) {
    // 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
    // DB 연결
			con = DriverManager.getConnection(url,user,passwd);

     // 쿼리문 세팅
			String query ="SELECT p_num ,p_name ,p_cost ,p_category ,p_provide ,p_amount FROM pos_system.product where p_num=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productCode);

     // 쿼리문 실행
			result = pstmt.executeQuery();

     // 결과 저장
			while (result.next()) {
				posUse = new PosUse();

				posUse.setp_num(result.getString(1));
				posUse.setp_name(result.getString(2));
				posUse.setp_cost(result.getInt(3));
				posUse.setp_category(result.getString(4));
				posUse.setp_provide(result.getString(5));
				posUse.setp_amount(result.getInt(6));

				list.add(posUse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

    // 결과 리턴
		return list;
	}

    /////////////////// 조회
    // 원하는 재고 조회 ( 상품코드 또는 상품명으로만)
    // 상품명으로하는 메소드
	public Vector<PosUse> StockSearchName(String productName) {
    // 쿼리문 결과 (여러 행) 담을 PosUse 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
    // DB 연결
			con = DriverManager.getConnection(url,user,passwd);

    // 쿼리문 세팅
			String query ="SELECT p_num ,p_name ,p_cost ,p_category ,p_provide ,p_amount FROM pos_system.product where p_name=?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, productName);

    // 쿼리문 실행
			result = pstmt.executeQuery();

    // 결과 저장
			while (result.next()) {
				posUse = new PosUse();

				posUse.setp_num(result.getString(1));
				posUse.setp_name(result.getString(2));
				posUse.setp_cost(result.getInt(3));
				posUse.setp_category(result.getString(4));
				posUse.setp_provide(result.getString(5));
				posUse.setp_amount(result.getInt(6));

				list.add(posUse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

    // 결과 리턴
		return list;
	}
	
	///////////////////////
	//////////////////////////////////계정관리DB////////////////////////
	
	
	// 로그인(대상 검색)
	public boolean loginpass(String id, String pw) {
		
		boolean flag = false;
		

		try {

			con = DriverManager.getConnection(url,user,passwd);
			
			String select = "select * from user_account where ID=? and pw=?";
			pstmt = con.prepareStatement(select);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
	
			result = pstmt.executeQuery();

			while (result.next()) {
			
				
				posUse = new PosUse();
				
				posUse.setID(result.getString(1));
				posUse.setPW(result.getString(2));
				posUse.setUser_name(result.getString(3));
				posUse.setUser_email(result.getString(4));
				posUse.setRank(result.getString(5));
				
				flag = true;
			}
			
			ID=posUse.getID();
			PW=posUse.getPW();
			user_name=posUse.getUser_name();
			user_email=posUse.getUser_email();
			rank=posUse.getRank();
			System.out.println(ID);
			System.out.println(rank);
			
			
		} catch (NullPointerException e) {
			//Login login = null; /*= new Login();*/
			//JOptionPane.showMessageDialog(login.p, "아이디 또는 비밀번호가 일치하지 않습니다.", "로그인 오류", JOptionPane.WARNING_MESSAGE);
			return flag;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	
	//계정관리 화면 띄우기
	
	// 모든계정표시
	public Vector<PosUse> AccountAll() {

		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
            String query = "select ID,PW,user_name,user_email,rankname from user_account";

			// 쿼리문 실행
            stmt = con.prepareStatement(query);
			result = stmt.executeQuery(query);

			// 결과 저장
			while (result.next()) {
				
				posUse = new PosUse();
				
				posUse.setID(result.getString(1));
				posUse.setPW(result.getString(2));
				posUse.setUser_name(result.getString(3));
				posUse.setUser_email(result.getString(4));
				posUse.setRank(result.getString(5));
				
				list.add(posUse);
				flag=true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		flag=false;
		// 결과 리턴
		return list;

	}
	
	
	//계정등록
	public int AccountIn(String ID, String PW, String user_name, String user_email, String rankname) {
		int r = 0;

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query ="INSERT INTO user_account (ID, PW, user_name, user_email, rankname) VALUES (?, ?, ?, ?, ?)";
			
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, ID);
			pstmt.setString(2, PW);
			pstmt.setString(3, user_name);
			pstmt.setString(4, user_email);
			pstmt.setString(5, rankname);

			// 쿼리문 실행
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				//result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r;
	}
	
	//중복확인
	public PosUse OverlapId(String Id) {

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);
			posUse = new PosUse();
			// 쿼리문 세팅
			String query ="select ID from user_account where ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Id);

			// 쿼리문 실행
			result = pstmt.executeQuery();

			// 결과 저장
			while (result.next()) {
				posUse.setCheckID(result.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		

		// 결과 리턴
		return posUse;
	}

	//계정수정
	
	public void dbsignUpChange(String ID, String PW, String user_name, String user_email) {

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅, 조건으로사용하는 상품코드는 겟텍스트로 받기.
			String query = "UPDATE user_account SET PW = ?, user_name = ?, user_email = ? WHERE ID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, PW);
			pstmt.setString(2, user_name);
			pstmt.setString(3, user_email);
			pstmt.setString(4, ID);

			// 쿼리문 실행
			int r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	///계정삭제
	public int accountdelete(String ID) {
		int r=0;
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅, 조건으로사용하는 아이디는 겟텍스트로 받기.
		    //String query = "delete from product where p_num=?";
			String query = "DELETE FROM user_account WHERE ID = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, ID);

			// 쿼리문 실행
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return r;
	}
	
	
	/////////////////////정산///////////////////////
	
	//정산하루현금매출 확인하기
	public int CalDayCash()
	{
		int amount=0;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			stmt = con.createStatement();
			String query = "select sum(c_cost) from charge where date_format(c_day,'%Y%m%d') = date_format(now(),'%Y%m%d') and c_way='현금'";
		
			pstmt = con.prepareStatement(query);
			result = pstmt.executeQuery();
			
			
			while(result.next()) {
			amount = result.getInt(1);
			//int amount = result.getInt(1);
			}
			System.out.println("상품개수:" + amount);
			
			return amount;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				result.close();
				pstmt.close();
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return amount;
	}
	
	
	
	
	//정산등록
	public int CalcIn(String admin, int state, int check, int result) {
		int r = 0;

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query ="INSERT INTO balance (b_day, b_reserve_fund, b_total_amount, b_difference_pay, b_admin) VALUES (now(), ?, ?, ?, ?)";
			
			pstmt= con.prepareStatement(query);
			pstmt.setInt(1, state);
			pstmt.setInt(2, check);
			pstmt.setInt(3, result);
			pstmt.setString(4, admin);

			// 쿼리문 실행
			r = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB 연결 종료
				pstmt.close();
				con.close();
				//result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r;
	}
	
	/*
	//모든정산내역보이기
	public Vector<PosUse> CalcAll() {

		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
            String query = "select b_day,b_reserve_fund,b_total_amount,b_difference_pay,b_admin from balance";

			// 쿼리문 실행
            stmt = con.prepareStatement(query);
			result = stmt.executeQuery(query);

			// 결과 저장
			while (result.next()) {
				
				posUse = new PosUse();
				
				posUse.setB_day(result.getString(1));
				posUse.setB_reserve_fund(result.getInt(2));
				posUse.setB_total_amount(result.getInt(3));
				posUse.setB_difference_pay(result.getInt(4));
				posUse.setB_admin(result.getString(5));
				
				list.add(posUse);
				flag=true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		flag=false;
		// 결과 리턴
		return list;

	}
	*/
	
	
	
	
	
	
	// 테이블 행 모두 지우기 (화면단에서만)
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}
	


}
