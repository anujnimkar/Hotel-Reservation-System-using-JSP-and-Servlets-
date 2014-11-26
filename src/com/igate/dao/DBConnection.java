package com.igate.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnection {
	private DataSource ds;
	private InitialContext ctx;
	private Connection con;
	public DBConnection()
		{
				
					ds=null;
					ctx=null;
			       con=null;
			
		}
		
		public Connection getConnection()
		{
			
			try {
				ctx=new InitialContext();
				 ds=(DataSource)ctx.lookup("java:OracleDS");
		    	con=ds.getConnection();
		    
			} catch (NamingException e) {
				
				System.out.println("Naming Exception "+e.getMessage());
			} catch (SQLException e) {
				
				
				System.out.println("SQL Exception "+e.getMessage());
			}
			
			return con;
		}

}
