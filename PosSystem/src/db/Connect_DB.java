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
		
		int date = Integer.parseInt(year.concat(month).concat("01"));
		try {
			//DB연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url,user,passwd);
			posDto = new PosDto();
			//쿼리문 세팅
			String query = "SELECT P.P_NUM AS '상품코드', P.P_CATEGORY AS '상품분류', P.P_NAME AS '상품명', P.P_COST AS '가격', V.SC AS '판매수량', P.P_COST * V.SC AS '매출합계', P.P_PROVIDE AS '제조사'\r\n"+
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
	
	// <상품별 매출내역 BEST 5 select> 메소드
	// : 소분류, 년, 월 입력받아 매출합계 랭킹 상위 5위까지 조회
	public Vector<PosUse> findProductSellBestFive(String minor_level, String year, String month) {
		// 쿼리문 결과 (여러 행) 담을 PosDto 객체
		Vector<PosUse> list = new Vector<PosUse>();

		// String으로 입력한 날짜를 '년월' 합쳐서 int로 변환
		int date = Integer.parseInt(year.concat(month).concat("01"));
		try {
			// DB 연결
			con = DriverManager.getConnection(url,user,passwd);

			// 쿼리문 세팅
			String query = "SELECT P.P_NUM AS '상품코드', P.P_CATEGORY AS '상품분류', P.P_NAME AS '상품명', P.P_COST AS '가격', V.SC AS '판매수량', P.P_COST * V.SC AS '매출합계', P.P_PROVIDE AS '제조사'\r\n"+
					"FROM PRODUCT P, (SELECT CHARGE.C_NAME, SUM(CHARGE.C_AMOUNT) SC FROM CHARGE group by C_NAME) V\r\n"+
					"WHERE P.P_NUM = V.C_NAME\r\n"+
					"order by P.P_COST * V.SC desc limit 5";
			pstmt = con.prepareStatement(query);
			//pstmt.setInt(1, date);
			//pstmt.setString(2, minor_level);

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
	
	// 테이블 행 모두 지우기 (화면단에서만)
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}
	
	
	


	
	
	
	

}
