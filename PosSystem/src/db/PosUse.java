package db;

import java.util.Vector;

public class PosUse {
	
	//��ǰ ���̺�(���)
	private String p_num;//��ǰ��ȣ
	private String p_name;//��ǰ��
	private int p_amount;//��ǰ ����
	private int p_cost;//�ܰ�
	private String p_category;//����
	private String p_provide;//������
	
	private int p_sellCount;//�Ǹ� ����
	private int p_costsellCount;//�� ����(��ǰ ���� X �ܰ�)
	
	//���� ���̺�
	private String c_num;//���� ��ȣ
	private String c_name;//���� ��ǰ �̸�
	private int c_amount;//���� ����
	private String c_way;//���� ���
	private int c_cost;//���� ���
	private String c_day;//���� ��¥
	private String c_assistant;//��� ����
	
	
	//���� ���̺�
	private int b_num;//�����ȣ
	private String b_day;//���� ��¥
	private String b_admin;//������(������ ���� �̸�)
	private int b_reserve_fund;//�غ��+���ݰ����ݾ�
	private int b_total_amount;//�հ�ݾ�(���ϱݰ�����ִ� �ݾ�)
	private int b_difference_pay;//���̱ݾ�
	
	
	//���� ���̺�
	private String ID;//����� ���̵�
	private String PW;//����� �佺����
	private String user_name;//����� �̸�
	private String user_email;//����� �̸���
	private String rank;//����� ����
	private int listNum; // ��ǰ���â���� ���ȣ@@@@
	private String checkID;//�ߺ�Ȯ�ο�
	
	//��� ���̺�
	//��ǰ��
	private int totalamount;//��ǰ���Ǹż���
	private int totalproductprice;//��ǰ���հ谡��
	//������
	private String YearSellDate;//����⵵
	private int YearTotalPrice;//�����հ�
	private int YearTotalcash;//���ݸ���
	private int YearTotalcard;//ī�����
	private int YearTotalaccount;//����
	//����
	private String MonthSellDate;//��������
	private int MonthTotalPrice;//�����հ�
	private int MonthTotalcash;//���ݸ���
	private int MonthTotalcard;//ī�����
	private int MonthTotalaccount;//����
	//�Ϻ�
	private String DaySellDate;//��������
	private int DayTotalPrice;//�����հ�
	private int DayTotalcash;//���ݸ���
	private int DayTotalcard;//ī�����
	private int DayTotalaccount;//����
	
	private String ClockTime;//�ǸŽð���
	private int ClockTotalPrice;//�ð��뺰 �����հ�
	private int ClockTotalaccount;//����
	
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
	
		//���� ���� �޼ҵ�
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
	
	

}
