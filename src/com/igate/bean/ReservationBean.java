package com.igate.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReservationBean 
{
	private String customerName;
	private String hotelname;
	private Date arrivalDate;
	private int nightNo;
	private int roomNo;
	private String roomType=null;

	public ReservationBean()
	{
		customerName=null;
		hotelname=null;
		arrivalDate=null;
		nightNo=0;
		roomNo=0;

	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public int getNightNo() {
		return nightNo;
	}
	public void setNightNo(int nightNo) {
		this.nightNo = nightNo;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getDateToString(Date date)
	{
		DateFormat formatter ; 
		formatter = new SimpleDateFormat("dd-MMM-yy");
		String s = formatter.format(date);
		return s;
	}



}
