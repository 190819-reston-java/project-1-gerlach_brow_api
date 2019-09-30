package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repositories.UserDAOImplPJDBC;
import com.revature.service.uandTServices;


public class UserServlets extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		uandTServices uts = new uandTServices(new UserDAOImplPJDBC());
		
		resp.getWriter().write(uts.getUsers().get(0).toString());
	}
}
