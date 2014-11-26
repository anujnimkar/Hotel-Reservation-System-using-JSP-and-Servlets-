package com.igate.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.igate.bean.ReservationBean;
import com.igate.dao.HoteDAO;
import com.igate.exception.HotelException;

/**
 * Servlet implementation class HotelReservationContoller
 */
public class HotelReservationContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<ReservationBean> rbList=null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HotelReservationContoller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session=request.getSession(false);
		HoteDAO hotelDao=null;	
		String username=request.getParameter("username");
		String passWord=request.getParameter("password");
		boolean result=false;
		hotelDao=new HoteDAO();
		try
		{
		result=hotelDao.isvalidUser(username,passWord);
		if(result)
		{
			ArrayList list=new ArrayList();
			list=hotelDao.fetchHotel();
			session.setAttribute("list", list);
			RequestDispatcher rd=request.getRequestDispatcher("/ReservationForm.jsp");
			rd.forward(request,response);
		}
		}
		catch(HotelException e)
		{
			request.setAttribute("message", e.getMessage());
			RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
			rd.forward(request,response);
		}
			
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		ReservationBean rb=new ReservationBean();
		if(request.getParameter("button").equals("Add reservation"))
		{

			if((ArrayList<ReservationBean>)session.getAttribute("ReservationCart")==null)
			{
				 rbList=new ArrayList<ReservationBean>();
			}
			DateFormat formatter=new SimpleDateFormat("dd-MM-yy");; 
			Date d=null; 
			String customerName=request.getParameter("reservtn");
			String hotelname=request.getParameter("selectRoom");
			String[] hotel=hotelname.split("-");
			String hname=hotel[0];
			String rtype=hotel[1];
			String day =request.getParameter("day");
			String month=request.getParameter("month");
			String year=request.getParameter("year");
			int night=Integer.parseInt(request.getParameter("nights"));
			int room=Integer.parseInt(request.getParameter("roomNo"));
			String date=day+"-"+month+"-"+year;
			try 
			{
				d = (Date)formatter.parse(date);
			} 
			catch (ParseException e) 
			{

				e.printStackTrace();
			} 
			rb.setCustomerName(customerName);
			rb.setHotelname(hname);
			rb.setNightNo(night);
			rb.setRoomNo(room);
			rb.setRoomType(rtype);
			rb.setArrivalDate(d);	
			if(rbList==null)
			{
				rbList=new ArrayList<ReservationBean>();
			}
			rbList.add(rb);
			session.setAttribute("ReservationCart", rbList);
			request.getRequestDispatcher("/ReservationForm.jsp").forward(request, response);
		}
		else if(request.getParameter("button").equals("delete"))
		{
			
			if(session!=null)
			{
				rbList=(ArrayList<ReservationBean>)session.getAttribute("ReservationCart");
				Iterator<ReservationBean> iterator1=rbList.iterator();
				int delcount=Integer.parseInt(request.getParameter("deleteValue")); 
				int count=0;
				while(iterator1.hasNext())
				{
					ReservationBean rb1=(ReservationBean)iterator1.next();
					if(count==delcount)
					{
						iterator1.remove();
					}
					count++;
				}
				session.setAttribute("ReservationCart",rbList);
				RequestDispatcher rd=request.getRequestDispatcher("/ReservationForm.jsp");
				rd.forward(request,response);
			}
		}
		else if(request.getParameter("button").equals("Delete All"))
		{
			
			if(session!=null)
			{
				rbList=(ArrayList<ReservationBean>)session.getAttribute("ReservationCart");
				if(rbList!=null)
				{
					rbList.clear();
				}
				//Iterator<ReservationBean> iterator1=rbList.iterator();
				//while(iterator1.hasNext())
				//{
				//	ReservationBean rb1=(ReservationBean)iterator1.next();
					//iterator1.remove();

				//}
				session.setAttribute("ReservationCart",rbList);
				RequestDispatcher rd=request.getRequestDispatcher("/ReservationForm.jsp");
				rd.forward(request,response);
			}
		}
		else if(request.getParameter("button").equals("Submit All"))
		{
			rbList=(ArrayList<ReservationBean>)session.getAttribute("ReservationCart");
			HoteDAO hotelDao=null;
			boolean result=false;
			String id=null;
			if(rbList!=null)
			{
				hotelDao=new HoteDAO();
				try 
				{
					id=hotelDao.getId();
					result=hotelDao.addValues(rbList,id);
					if(result)
					{
						rbList.clear();
						request.getRequestDispatcher("/Results.jsp").forward(request,response);
						
					}
					else
					{
						String mesg="Values entry failed";
						request.setAttribute("message",mesg);
						RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
						rd.forward(request,response);
					}
				} catch (HotelException e) 
				{
					//session.setAttribute("message", e.getMessage());
					request.setAttribute("message", e.getMessage());
					RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
					rd.forward(request,response);
				}
				
			}
			else
			{
				String message="No value";
				request.setAttribute("message",message);
				RequestDispatcher rd=request.getRequestDispatcher("/Error.jsp");
				rd.forward(request,response);
			}
			
			
		}
		
		
		
		
		
	}

}
