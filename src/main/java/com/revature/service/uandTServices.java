package com.revature.service;

import java.util.List;

import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.TransactionsDAOImplPJDBC;
import com.revature.repositories.UserDAO;

public class uandTServices {
	
	private TransactionsDAO trDAO;
	private UserDAO uDAO;
	private User u;
	
	public uandTServices(UserDAO udao) {
		this.uDAO = udao;
	}
	
	public uandTServices(TransactionsDAOImplPJDBC tdao) {
		this.trDAO = tdao;
	}

	public List<User> getUsers() {
		return uDAO.getUsers();
	}

	public List<Transaction> getTransactions(long i) {
		return trDAO.getTransactions(i);
	}
}
