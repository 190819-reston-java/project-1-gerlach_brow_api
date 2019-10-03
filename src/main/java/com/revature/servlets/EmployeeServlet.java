package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.TransactionsDAOImplPJDBC;
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

		for (String string : tokens) {
			System.out.println(string);
		}
		handleRequests(req, resp, tokens);
		
		}
	
	private void handleRequests(HttpServletRequest req, HttpServletResponse resp,
			String[] tokens) throws ServletException, IOException {
		
		ObjectMapper om = new ObjectMapper();
		PrintWriter pw = resp.getWriter();
		Cookie cookies[] = req.getCookies();
		String str = null;
		UserDAO userDAO = new UserDAOImplPJDBC();
		TransactionsDAO trs = new TransactionsDAOImplPJDBC();
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("userId")) {
				str = c.getValue();
			}
		}
		
		User user = userDAO.getUser(Long.valueOf(str));
		User newUser = null;
		for (String string : tokens) {
			System.out.println(string);
		}
		switch (req.getMethod()) {
		case "GET":
			if (tokens[0].contentEquals("view_info")) {
				String jsonUser = om.writeValueAsString(user);
				//System.out.println(jsonUser);
				pw.write(jsonUser);
			}
			else if (tokens[0].contentEquals("view_res")) {
				List<Transaction> trans = trs.getTrsResolved(user.getId());
				String jsonTransactions = om.writeValueAsString(trans.toArray());
				pw.write(jsonTransactions);
			}
			else if (tokens[0].contentEquals("view_pending")) {
				List<Transaction> trans = trs.getTrsPending(user.getId());
				String jsonTransactions = om.writeValueAsString(trans.toArray());
				pw.write(jsonTransactions);
			}
			break;
		case "POST":
			if (tokens[0].contentEquals("create_new")) {
				break;
			}
			else if (tokens[0].contentEquals("update_info")) {
				newUser = om.readValue(req.getReader(), User.class);
				System.out.println(newUser.toString());
				user.setFirstName(newUser.getFirstName());
				user.setLastName(newUser.getLastName());
				user.setPassword(newUser.getPassword());
				user.setAddress(newUser.getAddress());
				user.setAddress2(newUser.getAddress2());
				user.setPhoneNumber(newUser.getPhoneNumber());
				userDAO.updateUser(user);
			}
//				RequestDispatcher loginDispatcher = req.getRequestDispatcher("index.html");
//				
//				loginDispatcher.forward(req, resp);
			break;
		case "PUT":
			user = om.readValue(req.getReader(), User.class);
			if (tokens[0].contentEquals("update_info")) {
				
			}
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
//		Cookie cookies[] = req.getCookies();
//		String str = null;
//		UserDAO userDAO = new UserDAOImplPJDBC();
//		PrintWriter pw = resp.getWriter();
//		
//		for(Cookie c : cookies)
//		{
//			if(c.getName().equals("userId")) {
//				str = c.getValue();
//			}
//		}
//		User old = userDAO.getUser(Long.valueOf(str));
//		old.setFirstName(req.getParameter("firstName"));
//		old.setLastName(req.getParameter("lastName"));
//		old.setPassword(req.getParameter("password"));
//		old.setAddress(req.getParameter("address"));
//		old.setAddress2(req.getParameter("address2"));
//		old.setPhoneNumber(req.getParameter("phoneNumber"));
//		userDAO.updateUser(old);
//		RequestDispatcher loginDispatcher = req.getRequestDispatcher("index.html");
//		
//		loginDispatcher.forward(req, resp);
	}
}
