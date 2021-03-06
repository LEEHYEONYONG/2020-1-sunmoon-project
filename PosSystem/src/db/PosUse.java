package db;

import java.util.Vector;

public class PosUse {
	
	//상품 테이블(재고)
	private String p_num;//상품번호
	private String p_name;//상품명
	private int p_amount;//상품 개수
	private int p_cost;//단가
	private String p_category;//종류
	private String p_provide;//제조사
	
	private int p_sellCount;//판매 수량
	private int p_costsellCount;//총 가격(상품 개수 X 단가)
	
	//결제 데이블
	private String c_num;//결제 번호
	private String c_name;//결제 상품 이름
	private int c_amount;//결제 수량
	private String c_way;//결제 방법
	private int c_cost;//결제 비용
	private String c_day;//결제 날짜
	private String c_assistant;//담당 점원
	
	//환불관련 필드
		private String [] rf_name = null;
		private int [] rf_amount = null;
		private String [] rf_way = null;
		private int [] rf_cost = null;
		private String[] rf_state = null;
		
	//정산 테이블
	private String b_day;//정산 날짜
	private int b_reserve_fund;//준비금+현금결제금액 또는 준비금액
	private int b_total_amount;//합계금액(당일금고에들어있는 금액) 금고금액
	private int b_difference_pay;//차이금액
	private String b_admin;//관리자(정산한 점원 이름)
	
	
	//계정 데이블
	private String ID;//사용자 아이디
	private String PW;//사용자 페스워드
	private String user_name;//사용자 이름
	private String user_email;//사용자 이메일
	private String rank;//사용자 직급
	private int listNum; // 상품등록창에서 행번호@@@@
	private String checkID;//중복확인용
	
	//통계 테이블
	//상품별
	private int totalamount;//상품별판매수량
	private int totalproductprice;//상품별합계가격
	//연도별
	private String YearSellDate;//매출년도
	private int YearTotalPrice;//매출합계
	private int YearTotalcash;//현금매출
	private int YearTotalcard;//카드매출
	private int YearTotalaccount;//고객수
	//월별
	private String MonthSellDate;//월별매출
	private int MonthTotalPrice;//매출합계
	private int MonthTotalcash;//현금매출
	private int MonthTotalcard;//카드매출
	private int MonthTotalaccount;//고객수
	//일별
	private String DaySellDate;//월별매출
	private int DayTotalPrice;//매출합계
	private int DayTotalcash;//현금매출
	private int DayTotalcard;//카드매출
	private int DayTotalaccount;//고객수
	
	private String ClockTime;//판매시간대
	private int ClockTotalPrice;//시간대별 매출합계
	private int ClockTotalaccount;//고객수
	
	public int getListNum() 
	{
		return listNum;
	}
	
	public void setListNum(int listNum) 
	{
		this.listNum = listNum;
	}
	
	
	public String getp_num()
	{
		return p_num;
	}
	
	public void setp_num(String p_num)
	{
		this.p_num = p_num;
	}
	
	public String getp_name()
	{
		return p_name;
	}
	
	public void setp_name(String p_name)
	{
		this.p_name = p_name;
	}
	
	public int getp_amount()
	{
		return p_amount;
	}
	
	public void setp_amount(int p_amount)
	{
		this.p_amount = p_amount;
	}
	
	public int getp_cost()
	{
		return p_cost;
	}
	
	public void setp_cost(int p_cost)
	{
		this.p_cost = p_cost;
	}
	
	public String getp_category()
	{
		return p_category;
	}
	
	public void setp_category(String p_category)
	{
		this.p_category = p_category;
	}
	
	public String getp_provide()
	{
		return p_provide;
	}
	
	public void setp_provide(String p_provide)
	{
		this.p_provide = p_provide;
	}
	
	public int getp_costsellCount()
	{
		return p_costsellCount;
	}
	
	public void setp_costsellCount(int p_costsellCount)
	{
		this.p_costsellCount = p_costsellCount;
	}
	
	public int getp_sellCount()
	{
		return p_sellCount;
	}
	
	public void setp_sellCount(int p_sellCount)
	{
		this.p_sellCount = p_sellCount;
	}
	
		//결제 관련 메소드
		public String getc_num()
		{
			return c_num;
		}
		
		public void setc_num(String c_num)
		{
			this.c_num = c_num;
		}
		
		public String getc_name()
		{
			return c_name;
		}
		
		public void setc_name(String c_name)
		{
			this.c_name = c_name;
		}
		
		public int getc_amount()
		{
			return c_amount;
		}
		
		public void setc_amount(int c_amount)
		{
			this.c_amount = c_amount;
		}
		
		public String getc_way()
		{
			return c_way;
		}
		
		public void setc_way(String c_way)
		{
			this.c_way = c_way;
		}
		
		public int getc_cost()
		{
			return c_cost;
		}
		
		public void setc_cost(int c_cost)
		{
			this.c_cost = c_cost;
		}
		
		public String getc_day()
		{
			return c_day;
		}
		
		public void setc_day(String c_day)
		{
			this.c_day = c_day;
		}
		
		public String getc_assistant()
		{
			return c_assistant;
		}
		
		public void setc_assistant(String c_assistant)
		{
			this.c_assistant = c_assistant;
		}
	
		//환불관련 메소드
		
		public void setting_index(int index)
		{
			rf_name = new String[index];
			rf_amount = new int[index];
			rf_way = new String[index];
			rf_cost = new int[index];
			rf_state = new String[index];
		}
		
		public String[] getrf_name()
		{
			return rf_name;
		}
		
		public void setrf_name(String[] rf_name)
		{
			this.rf_name = rf_name;
		}
		
		public int[] getrf_amount()
		{
			return rf_amount;
		}
		
		public void setrf_amount(int[] rf_amount)
		{
			this.rf_amount = rf_amount;
		}
		
		public String[] getrf_way()
		{
			return rf_way;
		}
		
		public void setrf_way(String[] rf_way)
		{
			this.rf_way = rf_way;
		}
		
		public int[] getrf_cost()
		{
			return rf_cost;
		}
		
		public void setrf_cost(int[] rf_cost)
		{
			this.rf_cost = rf_cost;
		}
		
		public String[] getrf_state()
		{
			return rf_state;
		}
		
		public void setrf_state(String[] rf_state)
		{
			this.rf_state = rf_state;
		}
	
	public int getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(int totalamount) {
		this.totalamount = totalamount;
	}

	public int getTotalproductprice() {
		return totalproductprice;
	}

	public void setTotalproductprice(int totalproductprice) {
		this.totalproductprice = totalproductprice;
	}
	
	public String getYearSellDate() {
		return YearSellDate;
	}

	public void setYearSellDate(String yearSellDate) {
		YearSellDate = yearSellDate;
	}

	public int getYearTotalPrice() {
		return YearTotalPrice;
	}

	public void setYearTotalPrice(int yearTotalPrice) {
		YearTotalPrice = yearTotalPrice;
	}

	public int getYearTotalcash() {
		return YearTotalcash;
	}

	public void setYearTotalcash(int yearTotalcash) {
		YearTotalcash = yearTotalcash;
	}

	public int getYearTotalcard() {
		return YearTotalcard;
	}

	public void setYearTotalcard(int yearTotalcard) {
		YearTotalcard = yearTotalcard;
	}

	public int getYearTotalaccount() {
		return YearTotalaccount;
	}

	public void setYearTotalaccount(int yearTotalaccount) {
		YearTotalaccount = yearTotalaccount;
	}

	public String getMonthSellDate() {
		return MonthSellDate;
	}

	public void setMonthSellDate(String monthSellDate) {
		MonthSellDate = monthSellDate;
	}

	public int getMonthTotalPrice() {
		return MonthTotalPrice;
	}

	public void setMonthTotalPrice(int monthTotalPrice) {
		MonthTotalPrice = monthTotalPrice;
	}

	public int getMonthTotalcash() {
		return MonthTotalcash;
	}

	public void setMonthTotalcash(int monthTotalcash) {
		MonthTotalcash = monthTotalcash;
	}

	public int getMonthTotalcard() {
		return MonthTotalcard;
	}

	public void setMonthTotalcard(int monthTotalcard) {
		MonthTotalcard = monthTotalcard;
	}

	public int getMonthTotalaccount() {
		return MonthTotalaccount;
	}

	public void setMonthTotalaccount(int monthTotalaccount) {
		MonthTotalaccount = monthTotalaccount;
	}

	public String getDaySellDate() {
		return DaySellDate;
	}

	public void setDaySellDate(String daySellDate) {
		DaySellDate = daySellDate;
	}

	public int getDayTotalPrice() {
		return DayTotalPrice;
	}

	public void setDayTotalPrice(int dayTotalPrice) {
		DayTotalPrice = dayTotalPrice;
	}

	public int getDayTotalcash() {
		return DayTotalcash;
	}

	public void setDayTotalcash(int dayTotalcash) {
		DayTotalcash = dayTotalcash;
	}

	public int getDayTotalcard() {
		return DayTotalcard;
	}

	public void setDayTotalcard(int dayTotalcard) {
		DayTotalcard = dayTotalcard;
	}

	public int getDayTotalaccount() {
		return DayTotalaccount;
	}

	public void setDayTotalaccount(int dayTotalaccount) {
		DayTotalaccount = dayTotalaccount;
	}

	public String getClockTime() {
		return ClockTime;
	}

	public void setClockTime(String clockTime) {
		ClockTime = clockTime;
	}

	public int getClockTotalPrice() {
		return ClockTotalPrice;
	}

	public void setClockTotalPrice(int clockTotalPrice) {
		ClockTotalPrice = clockTotalPrice;
	}

	public int getClockTotalaccount() {
		return ClockTotalaccount;
	}

	public void setClockTotalaccount(int clockTotalaccount) {
		ClockTotalaccount = clockTotalaccount;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getCheckID() {
		return checkID;
	}

	public void setCheckID(String checkID) {
		this.checkID = checkID;
	}

	public String getB_day() {
		return b_day;
	}

	public void setB_day(String b_day) {
		this.b_day = b_day;
	}

	public int getB_reserve_fund() {
		return b_reserve_fund;
	}

	public void setB_reserve_fund(int b_reserve_fund) {
		this.b_reserve_fund = b_reserve_fund;
	}

	public int getB_total_amount() {
		return b_total_amount;
	}

	public void setB_total_amount(int b_total_amount) {
		this.b_total_amount = b_total_amount;
	}

	public int getB_difference_pay() {
		return b_difference_pay;
	}

	public void setB_difference_pay(int b_difference_pay) {
		this.b_difference_pay = b_difference_pay;
	}

	public String getB_admin() {
		return b_admin;
	}

	public void setB_admin(String b_admin) {
		this.b_admin = b_admin;
	}
	
	

}
