package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImplPJDBC;
import com.revature.service.uandTServices;

public class EmployeeServlet extends HttpServlet {

	private uandTServices services;
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] splitURI = req.getRequestURI().split("/");
		String[] tokens = Arrays.copyOfRange(splitURI, 3, splitURI.length);

		switch (tokens[0]) {
		case "view_info":
			handleRequests(req, resp, tokens);
			break;
		
		}
	}
	
	private void handleRequests(HttpServletRequest req, HttpServletResponse resp,
			String[] tokens) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		Cookie cookies[] = req.getCookies();
		String str = null;
		UserDAO userDAO = new UserDAOImplPJDBC();
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("userId")) {
				str = c.getValue();
			}
		}
		User user = userDAO.getUser(Long.valueOf(str));
		switch (req.getMethod()) {
		case "GET":
			String jsonUser = om.writeValueAsString(user);
			pw.write(jsonUser);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cookie cookies[] = req.getCookies();
		String str = null;
		UserDAO userDAO = new UserDAOImplPJDBC();
		PrintWriter pw = resp.getWriter();
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("userId")) {
				str = c.getValue();
			}
		}
		User old = userDAO.getUser(Long.valueOf(str));
		old.setFirstName(req.getParameter("firstName"));
		old.setLastName(req.getParameter("lastName"));
		old.setPassword(req.getParameter("password"));
		old.setAddress(req.getParameter("address"));
		old.setAddress2(req.getParameter("address2"));
		old.setPhoneNumber(req.getParameter("phoneNumber"));
		userDAO.updateUser(old);
		RequestDispatcher loginDispatcher = req.getRequestDispatcher("index.html");
		
		loginDispatcher.forward(req, resp);
	}
}
