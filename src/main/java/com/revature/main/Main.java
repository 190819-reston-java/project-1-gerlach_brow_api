package com.revature.main;

import com.revature.repositories.*;

public class Main {

	public static void main(String[] args) {
	
		UserDAO userDAO = new UserDAOImplPJDBC();
		System.out.println(userDAO.getUsers());
	}
}
