<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.ArrayList,com.igate.bean.ReservationBean,java.util.List,java.util.Iterator;"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="HotelValidation.js">
	 </script>
</head>
<body>
<form name="ReservationForm" action="HotelReservationContoller" method="post">
<center>
<h1>Hotel Reservation</h1>
</center>
<table align="center" border="1">
	<tr>
		<td>please select a hotel with room type</td>
		<td><select name="selectRoom" id="selectRooms" onchange="displayRooms()">
		<%
		HttpSession newSession=request.getSession(false);
		ArrayList list = (ArrayList)session.getAttribute("list");
		Iterator listIterator = list.iterator();
		if(listIterator.hasNext())
		{
			%>
			<option value="Select">--Select--</option>
			<option value="Blue Diamond-SNG"><%= listIterator.next()%></option>
			<option value="Blue Diamond-DBL"><%= listIterator.next()%></option>
			<option value="12TH Avenue-SNG"><%= listIterator.next()%></option>
			<option value="12TH Avenue-DBL"><%= listIterator.next()%></option>
			
			<%}%>
					</select>	<span class="errmsg" id="errmsg5"></td>
	</tr>
	<tr>
		<td>Reserved By</td>
		<td><input type="text" name="reservtn" id="cName" onblur="isValidCName()"/>
		<span class="errmsg" id="errmsg1">
		</span>
		</td>
		
	</tr>
	<tr>
		<td>Arrival Date</td>
		<td><select name="day">
		    <option value="">--Day--</option>
			<option value="01">1</option>
			<option value="02">2</option>
			<option value="03">3</option>
			<option value="04">4</option>
			<option value="05">5</option>
			<option value="06">6</option>
			<option value="07">7</option>
			<option value="08">8</option>
			<option value="09">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
			<option value="13">13</option>
			<option value="14">14</option>
			<option value="15">15</option>
			<option value="16">16</option>
			<option value="17">17</option>
			<option value="18">18</option>
			<option value="19">19</option>
			<option value="20">20</option>
			<option value="21">21</option>
			<option value="22">22</option>
			<option value="23">23</option>
			<option value="24">24</option>
			<option value="25">25</option>
			<option value="26">26</option>
			<option value="27">27</option>
			<option value="28">28</option>
			<option value="29">29</option>
			<option value="30">30</option>
			<option value="31">31</option>
		</select>
		<select name="month">
		    <option value="">--Month--</option>
			<option value="01">Jan</option>
			<option value="02">Feb</option>
			<option value="03">Mar</option>
			<option value="04">Apr</option>
			<option value="05">May</option>
			<option value="06">Jun</option>
			<option value="07">Jul</option>
			<option value="08">Aug</option>
			<option value="09">Sep</option>
			<option value="10">Oct</option>
			<option value="11">Nov</option>
			<option value="12">Dec</option>
		</select>
		<select name="year">
		    <option value="">--Year--</option>
			<option value="2010">2010</option>
			<option value="2011">2011</option>
			<option value="2012">2012</option>
		</select></td>
	</tr>
	<tr>
		<td>Number of rooms</td>
		<td colspan="2"><input type="text" name="roomNo" id="roomNos" onblur="isValidNo()" />
		<span class="errmsg" id="errmsg2">
		</span>
		</td>
	
	</tr>
	<tr>
		<td>Number of nights</td>
		<td colspan="2"><input type="text" name="nights" id="nightsNos" onblur="isValidnightNo()" />
		<span class="errmsg" id="errmsg3">
		</span>
</td>
	
	</tr>
</table>
<center><input type="submit" value="Add reservation" name="button" ></center>
<%
if(newSession!=null)
{
	ArrayList<ReservationBean> list1=null;

	list1=(ArrayList<ReservationBean>)newSession.getAttribute("ReservationCart");
	
		newSession.setAttribute("ReservationCart",list1);
	%>
	<jsp:include page="ReservationCart.jsp" flush="true"/>
	<%
	
}
%>
</form>
</body>
</html>