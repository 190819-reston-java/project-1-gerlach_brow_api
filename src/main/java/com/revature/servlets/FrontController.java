package com.revature.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.repositories.UserDAOImplPJDBC;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repositories.TransactionsDAOImplPJDBC;
import com.revature.repositories.UserDAO;
import com.revature.service.uandTServices;


public class FrontController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		uandTServices uts = new uandTServices(new UserDAOImplPJDBC());
		
		
		resp.getWriter().write(uts.getUsers().toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UserDAO userDAO = new UserDAOImplPJDBC();
		String a = req.getParameter("userEmail");
		String b = req.getParameter("password");
		User user = userDAO.getUser(a, b);
		resp.getWriter().write("hello");
		
		if(user != null) {
			
			long l = user.getId();
			uandTServices uts = new uandTServices(new TransactionsDAOImplPJDBC());
			Cookie cookie = new Cookie("userId", Long.toString(l));
			resp.addCookie(cookie);
			resp.sendRedirect("MockSecondServlet");
			////// Cookie usage //////
//			Cookie cookies[] = req.getCookies();
//			String str = null;
//			
//			for(Cookie c : cookies)
//			{
//				if(c.getName().equals("userId")) {
//					str = c.getValue();
//				}
//			}
			
//			resp.getWriter().write(user.toString());
			
			
			//long id = user.getId();
			//String u = req.getParameter("userId");
//			int userInput = Integer.parseInt(u);
//			List<Transaction> test = uts.getTransactions(userInput);
//			
//			for (Transaction transaction : test) {
//				resp.getWriter().write(transaction.toString());
//			}
//			resp.setStatus(HttpServletResponse.SC_CREATED);
		}
		else {
			resp.sendRedirect("index.html");
			resp.getWriter().write("Invalid username or password");
		}
	}
}
