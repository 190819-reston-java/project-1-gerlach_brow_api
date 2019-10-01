package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImplPJDBC;

public class EmployeeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookies[] = req.getCookies();
		String str = null;
		UserDAO userDAO = new UserDAOImplPJDBC();
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("userId")) {
				str = c.getValue();
			}
		}
		resp.getWriter().print((userDAO.getUser(Long.valueOf(str))));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
