function isValidCName()
{    var result=true;
     var regex=/^[A-Z|a-z]+$/
     var name=document.getElementById("cName").value;
	 if(!regex.test(name)||name=="")
	 { 
	    errmsg1.innerHTML="Invalid Name";
		result=false;
	 }
	 else
	 {
	    errmsg1.innerHTML="";
		result=true;
	 }
	 return result;
}

function isValidNo()
{
    var result =true;
	 var regex=/^\d{1,2}$/;
	 var id=document.getElementById("roomNos").value;
	 if(!regex.test(id)||id=="")
	  {
	    errmsg2.innerHTML="Invalid number";
		result=false;
	 }
	 else
		{ errmsg2.innerHTML="";
		result=true;
		}
	 return result;
	 }
	 

function isValidnightNo()
{
    var result =true;
	 var regex=/^\d[0-9]{1}$/;
	 var id=document.getElementById("nightsNos").value;
	 if(!regex.test(id)||id=="")
	  {
	    errmsg3.innerHTML="Invalid number";
		result=false;
	 }
	 else
		{ errmsg3.innerHTML="";
		result=true;
		}
	 return result;
	 }

function displayRooms()
{
     var mname=document.getElementById("selectRooms").value;
	 var mno=document.getElementById("selectRooms").selectedIndex;
	 var result=true;
	 if(mname.equals("Select"))
	 {            
		 document.getElementById("selectRooms").innerHTML="Select valid option";
		 errmsg5.innerHTML="Select valid option";
		result=false;
	 }
	 else
	 {
	     alert(mname);
	     
	 }
	 return result;
}





function validate()
{	
	var hotelName=document.ReservationForm.selectRoom.value;
	var reservedBy=document.ReservationForm.reservtn.value;
	var numberOfRooms=document.ReservationForm.roomNo.value;
	var numberOfNights=document.ReservationForm.nights.value;
	
	var regx=/^[A-Za-z]{3,15}$/;
	var regy=/^[1-9]$/;
	if(hotelName=="select")
	{  
		alert("Select HotelName"); 
		document.ReservationForm.hotelName.focus(); 
	    return false;
	}
	else
	{  
		if(reservedBy=="")
		{   
			alert("Enter Name"); 
			document.ReservationForm.txtReservedBy.focus(); 
			return false;
		}
		else
		{	
			if(reservedBy.length<3||reservedBy.length>15)
			{
				alert("Enter Characters(3-15)"); 
				document.ReservationForm.txtReservedBy.value="";
				document.ReservationForm.txtReservedBy.focus(); 
				return false;
			}
			else if(!regx.test(reservedBy))
			{
				alert("Enter Characters only"); 
				document.ReservationForm.txtReservedBy.value="";
				document.ReservationForm.txtReservedBy.focus(); 
				return false;
			}
			
		}
	}
	return true;
}
