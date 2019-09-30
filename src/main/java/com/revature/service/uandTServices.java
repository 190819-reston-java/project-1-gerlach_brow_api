package com.revature.service;

import java.util.List;

import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repositories.TransactionsDAO;
import com.revature.repositories.UserDAO;

public class uandTServices {
	
	private TransactionsDAO trDAO;
	private UserDAO uDAO;
	private User u;
	private Transaction tr;
	
	public uandTServices(UserDAO udao) {
		this.uDAO = udao;
	}
	
	public List<User> getUsers() {
		return uDAO.getUsers();
	}
}
