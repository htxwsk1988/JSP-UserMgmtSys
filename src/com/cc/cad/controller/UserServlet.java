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
 * Servlet implementation class UserServlet
 */

public class UserServlet extends HttpServlet {
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
		
		// Retrieve Operation
		String Operation = request.getParameter("Operate");
		
		if(Operation.equals("paging")){
			// Paging. 
			String sPageNow = request.getParameter("pageNow"); 
			
			int pageNow = 1; 
			
			try{
				pageNow = Integer.parseInt(sPageNow); 
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			// Return records by page. 
			UserDAO userDAO = new UserDAO(); 
			
			ArrayList<UserBean> lstUser = userDAO.getUsersByPage(pageNow); 
			int pageCount = userDAO.getPageCount(); 
			
			request.setAttribute("UserList", lstUser);
			request.setAttribute("PageNow", pageNow);
			request.setAttribute("PageCount", pageCount);
			
			request.getRequestDispatcher("Welcome.jsp").forward(request, response);
		}else if(Operation.equals("delete")){
			// Delete User.
			String sUserId = request.getParameter("userId");
			
			UserDAO userDAO = new UserDAO(); 
			
			userDAO.delUserById(sUserId);
				
			response.sendRedirect("Main.jsp");
		}else if(Operation.equals("add")){
			
			String userName = request.getParameter("txtName"); 
			String password = request.getParameter("txtPass"); 
			String email = request.getParameter("txtEmail"); 
			String group = request.getParameter("txtGroup"); 
			
			UserDAO userDAO = new UserDAO(); 
			
			userDAO.addUser(userName, password, email, group); 
			
			response.sendRedirect("Main.jsp");
			
		}else if(Operation.equals("update")){
			
			String userId = request.getParameter("txtId"); 
			String userName = request.getParameter("txtName"); 
			String password = request.getParameter("txtPass"); 
			String email = request.getParameter("txtEmail"); 
			String group = request.getParameter("txtGroup"); 
			
			UserDAO userDAO = new UserDAO(); 
			
			userDAO.updateUser(userId, userName, password, email, group); 
			
			response.sendRedirect("Main.jsp");
		}

	}
	
	

}
