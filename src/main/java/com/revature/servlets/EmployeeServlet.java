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
		
		switch (req.getMethod()) {
		case "GET":
			if (tokens[0].contentEquals("view_info")) {
				String jsonUser = om.writeValueAsString(user);
				pw.write(jsonUser);
			}
			else if (tokens[0].contentEquals("view_res")) {
				List<Transaction> trans1 = trs.getTrsApproved(user.getId());
				List<Transaction> trans2 = trs.getTrsDenied(user.getId());
				String json2 = om.writeValueAsString(trans2.toArray());
				String newJson2 = json2.substring(1);
				String json = om.writeValueAsString(trans1.toArray());
				String newJson = json.substring(0, json.length() - 1);

				if (trans2.isEmpty())
					pw.write(json);
				else if (trans1.isEmpty())
					pw.write(json2);
				else {
					String jsonTransactions = newJson + "," + newJson2;
					pw.write(jsonTransactions);
				}
				
			}
			else if (tokens[0].contentEquals("view_pending")) {
				List<Transaction> trans = trs.getTrsPending(user.getId());
				String jsonTransactions = om.writeValueAsString(trans.toArray());
				pw.write(jsonTransactions);
			}
			break;
		case "POST":
			if (tokens[0].contentEquals("create_new")) {
				Transaction create = null;
				create = om.readValue(req.getReader(), Transaction.class);
				trs.createTransaction(create.getComment(), user.getId());
				
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
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
		
	}
}
