package db;

import java.util.Vector;

public class PosUse {
	
	//��ǰ ���̺�
	private String p_num;//��ǰ��ȣ
	private String p_name;//��ǰ��
	private int p_amount;//��ǰ ����
	private int p_cost;//�ܰ�
	private String p_category;//����
	private String p_provide;//������
	
	private int p_sellCount;//�Ǹ� ����
	private int p_costsellCount;//�� ����(��ǰ ���� X �ܰ�)
	
	//���� ���̺�
	private int c_num;//���� ��ȣ
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
