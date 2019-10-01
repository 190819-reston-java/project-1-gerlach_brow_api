package com.revature.servlets;
import java.io.IOException;
import java.io.PrintWriter;
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
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.TransactionsDAOImplPJDBC;
import com.revature.repositories.UserDAO;
import com.revature.service.uandTServices;

public class MockSecondServlet extends HttpServlet {
	
	TransactionsDAO trs = new TransactionsDAOImplPJDBC();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Cookie cookies[] = req.getCookies();
		String str = null;
		
		for(Cookie c : cookies)
		{
			if(c.getName().equals("userId")) {
				str = c.getValue();
			}
		}
		long id = Long.valueOf(str);
		List<Transaction> print = new ArrayList<Transaction>();
		print = trs.getTransactions(id);
		for (Transaction transaction : print) {
			resp.getWriter().write(transaction.toString());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
}
