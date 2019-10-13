package com.revature.main;

import java.io.File;
import java.io.FileOutputStream;

import com.revature.model.User;
import com.revature.repositories.*;
import com.revature.utils.ImageToByteUtil;

public class Main {

	public static void main(String[] args) {
	
//		TransactionsDAO t = new TransactionsDAOImplPJDBC();
//		UserDAO userDAO = new UserDAOImplPJDBC();
//		User user = userDAO.getUser(1);
//		t.createTransaction("blughle", 1);
//		System.out.println(userDAO.getUser("jelly@jelly.roll", "wasspord"));
//		System.out.println(userDAO.getUsers());
//		
		TransactionsDAO trs = new TransactionsDAOImplPJDBC();
		File file = new File("C:/Users/James/Desktop/bcf.png");
		ImageToByteUtil img = new ImageToByteUtil();
		byte[] array = img.ImageToByte(file);
		trs.addImage(array, 1);
		
		
	}
}
