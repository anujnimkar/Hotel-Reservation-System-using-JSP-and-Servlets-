package com.igate.filter;

import java.io.IOException;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.igate.dao.HoteDAO;

/**
 * Servlet Filter implementation class HotelFilter
 */
public class HotelFilter implements Filter {
	public Logger log=Logger.getLogger(HoteDAO.class.getName());

    /**
     * Default constructor. 
     */
    public HotelFilter() {
    	URL url=Loader.getResource("log4j.properties");
		PropertyConfigurator.configure(url);
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		String var=request.getParameter("button");
		if(var!=null)
		{
			log.info("I am pressing add reservation ");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
