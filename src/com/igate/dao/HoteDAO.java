package com.igate.dao;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.igate.bean.ReservationBean;
import com.igate.exception.HotelException;



public class HoteDAO {
	public Logger log=Logger.getLogger(HoteDAO.class.getName());
	ArrayList list=null;
	public HoteDAO()
	{
		URL url=Loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);

	}
	public boolean isvalidUser(String userName,String passWord) throws HotelException  
	{
		boolean result=false;
		DBConnection connectionGetter = null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		
		String newPassword=null;
		try
		{
			try
			{
				connectionGetter = new DBConnection();
				connection = connectionGetter.getConnection();
				//System.out.println(connection);
				preparedStatement = connection.prepareStatement("select password from login_hotel_802366 where USERNAME=?");
				preparedStatement.setString(1,userName);
				
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next())
				{
					newPassword=resultSet.getString(1);
					if(passWord.equals(newPassword))
					{
						result=true;

					}
					else
					{
						result=false;

					}
				}
				else
				{
					result=false;
				}

			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				if(resultSet != null)
					resultSet = null;
				if(preparedStatement != null)
					preparedStatement.close();
				if(connection != null)
					connection.close();
			}
		}
		catch(SQLException exception)
		{
			throw new HotelException("Sql connection problem");

		} 
		return result;
	}
	public ArrayList fetchHotel() throws HotelException
	{
		list=new ArrayList();
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try
		{
			
		try
		{
		connection = new DBConnection().getConnection();
		if (connection != null) 
		{
			String query="select NAME,TYPE from hotel_802366,room_type_802366 where  hotel_802366.ID=room_type_802366.HOT_ID";
			preparedStatement = connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			
		while(resultSet.next())
		{
			
			String hname=resultSet.getString("NAME");
			String type=resultSet.getString("TYPE");
			String h=hname+"-"+type;
			
			list.add(h);
		}

		}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally
		{
			if(resultSet!=null)
			{
				resultSet.close();
			}
			if(preparedStatement!=null)
			{
				preparedStatement.close();
			}
			if(connection!=null)
			{
				connection.close();
			}
			
		}
		}
		catch(SQLException exception)
		{
			throw new HotelException("Sql connection problem");

		} 
		
		return list;
	}
	public String getId() throws HotelException
	{
		String id=null;
			Connection connection = null;
			ResultSet resultSet = null;
			PreparedStatement preparedStatement = null;
			try
			{
				connection = new DBConnection().getConnection();	
				String sqlStmt="SELECT book_id_802366.nextval from dual";
				preparedStatement = connection.prepareStatement(sqlStmt);
				resultSet=preparedStatement.executeQuery();
				while(resultSet.next())
				{
					id=resultSet.getString(1);

				}
			}
			catch(SQLException exception)
			{
				throw new HotelException("Sql connection problem");

			} 
		return id;
	}
	public boolean addValues(ArrayList<ReservationBean> rbList,String id) throws HotelException
	{
		log.info("I am pressing add reservation ");
		boolean result=false;
		Date sqlDate=null;
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try
		{
			connection = new DBConnection().getConnection();	
			Iterator<ReservationBean> iterator=rbList.iterator();

			while(iterator.hasNext())
			{
				ReservationBean bean=iterator.next();
				String hotelId=null;
				String query="select ID from hotel_802366 where NAME=?";
				PreparedStatement statement=connection.prepareStatement(query);
				statement.setString(1, bean.getHotelname());
				resultSet=statement.executeQuery();
				while(resultSet.next())
				{
					 hotelId=resultSet.getString(1);
				}
				query="insert into hotel_booking_802366(BOOKING_ID,HOT_ID,ARRIVAL_DATE,NO_OF_NIGHTS,NO_OF_ROOMS,ROOM_TYPE) values(?,?,?,?,?,?)";    
				statement=connection.prepareStatement(query);
				statement.setString(1, id);
				statement.setString(2, hotelId);
				
				sqlDate = new java.sql.Date(bean.getArrivalDate().getTime()); 
				statement.setDate(3, sqlDate);
				statement.setInt(4, bean.getNightNo());
				statement.setInt(5, bean.getRoomNo());
				statement.setString(6, bean.getRoomType());
				statement.executeUpdate();
				result=true;
				
			}
			
		}
		catch(SQLException exception)
		{
			throw new HotelException("Sql connection problem");

		} 
		return result;
		
	}

}
