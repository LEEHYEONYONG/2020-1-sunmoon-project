package db;

import java.util.Vector;

public class PosUse {
	
	//상품 테이블
	private String p_num;//상품번호
	private String p_name;//상품명
	private int p_amount;//상품 개수
	private int p_cost;//단가
	private String p_category;//종류
	private String p_provide;//제조사
	
	private int p_sellCount;//판매 수량
	private int p_costsellCount;//총 가격(상품 개수 X 단가)
	
	//결제 데이블
	private int c_num;//결제 번호
	private String c_name;//결제 상품 이름
	private int c_amount;//결제 수량
	private String c_way;//결제 방법
	private int c_cost;//결제 비용
	private String c_day;//결제 날짜
	private String c_assistant;//담당 점원
	
	
	//정산 테이블
	private int b_num;//정산번호
	private String b_day;//정산 날짜
	private String b_admin;//관리자(정산한 점원 이름)
	private int b_reserve_fund;//준비금+현금결제금액
	private int b_total_amount;//합계금액(당일금고에들어있는 금액)
	private int b_difference_pay;//차이금액
	
	
	//계정 데이블
	private String ID;//사용자 아이디
	private String PW;//사용자 페스워드
	private String user_name;//사용자 이름
	private String user_email;//사용자 이메일
	private String rank;//사용자 직급
	private int listNum; // 상품등록창에서 행번호@@@@
	
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




}
