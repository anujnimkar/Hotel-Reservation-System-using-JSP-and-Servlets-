<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page
	import="java.util.ArrayList,java.util.List,com.igate.bean.ReservationBean,java.util.Iterator;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>Reservation Cart</h3>
</center>
<form action="HotelReservationServlet" >
<table border="1" align="center">
	<thead bgcolor="#FDE6B5">
		<font color="white">
		<tr>
			<td>Hotel Name</td>
			<td>Reserved By</td>
			<td>Arrival Date</td>
			<td>Number of rooms</td>
			<td>Room Type</td>
			<td>Number of Nights</td>
			<td></td>
		</tr>
		</font>
	</thead>
	<%
	  ReservationBean rb;
		HttpSession newSession=request.getSession(false);
		ArrayList<ReservationBean> rbList = (ArrayList<ReservationBean>)session.getAttribute("ReservationCart");
		Iterator<ReservationBean> ReservationBeanIterator = rbList.iterator();
		
		int i=0;
		while (ReservationBeanIterator.hasNext())

		{
		
			//ReservationBean rbs=ReservationBeanIterator.next();
			 rb = (ReservationBean) ReservationBeanIterator.next();
			
			  %>
		<tr>	
			<td><%= rb.getHotelname()%></td>
			<td><%= rb.getCustomerName()%></td>
			<td><%= rb.getDateToString(rb.getArrivalDate())%></td>
			<td><%= rb.getRoomNo()%></td>
			<td><%= rb.getNightNo()%></td>
			<td><%= rb.getRoomType()%></td>
			<td><input type="hidden" value="<%= i%>" name="deleteValue"/><input type="submit" value="delete" name="button"></td> 
			</tr>
	<%
		i++;
		}
	%>
<tr><td><input type="submit" value="Submit All" name="button"/>
	<input type="submit" value="Delete All" name="button"/></td></tr>
</table>
</form>
</body>
</html>