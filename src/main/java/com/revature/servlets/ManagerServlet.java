package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.User;
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.TransactionsDAOImplPJDBC;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImplPJDBC;

public class ManagerServlet extends HttpServlet{

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
		User manager = userDAO.getUser(Long.valueOf(str));
		
		switch(req.getMethod()) {
		case "GET":
			if (tokens[0].contentEquals("view_all")) {
				
				List<User> user = userDAO.getUsers();
				String jsonUser = om.writeValueAsString(user.toArray());
				pw.write(jsonUser);
			}
//			else if (tokens[0].contentEquals("view_pending")) {
//				
//			}
//			else if (tokens[0].contentEquals("view_res")) {
//				
//			}
			
			break;
//		case "POST":
//			if (tokens[0].contentEquals("view_by_emp")) {
//				
//			}
//			else if (tokens[0].contentEquals("approve_deny")) {
//				
//			}
//			break;
	}
}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("17");
		//doGet(req, resp);
	}
	
}
