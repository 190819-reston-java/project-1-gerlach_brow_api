package com.revature.main;

import java.io.File;
import java.io.FileOutputStream;

import com.revature.repositories.*;
import com.revature.utils.ImageToByteUtil;

public class Main {

	public static void main(String[] args) {
	
		UserDAO userDAO = new UserDAOImplPJDBC();
		System.out.println(userDAO.getUsers());
		
	}
}
