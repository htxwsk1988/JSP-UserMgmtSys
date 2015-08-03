package com.cc.cad.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.cad.model.UserBean;
import com.cc.cad.model.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Retrieve username and password from Login.jsp.
		String u = request.getParameter("username"); 
		String p = request.getParameter("passwd"); 
		
		// Validate user through UserDAO. 
		UserDAO userDAO = new UserDAO(); 
		
		if(userDAO.checkUser(u, p)){
			
			// Prepare data for Welcome.jsp
			ArrayList<UserBean> lstUser = userDAO.getUsersByPage(1); 
			int pageCount = userDAO.getPageCount(); 
			
			request.setAttribute("UserList", lstUser);
			request.setAttribute("PageNow", 1);
			request.setAttribute("PageCount", pageCount);
			
			// Add user name into session. 
			request.getSession().setAttribute("seName", u);
			
			request.getRequestDispatcher("Main.jsp").forward(request, response);
		}else{

			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
