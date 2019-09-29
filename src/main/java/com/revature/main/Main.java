package com.revature.main;

import java.io.File;
import java.io.FileOutputStream;

import com.revature.repositories.*;
import com.revature.utils.ImageToByteUtil;

public class Main {

	public static void main(String[] args) {
	
		//UserDAO userDAO = new UserDAOImplPJDBC();
		//System.out.println(userDAO.getUsers());
		
		TransactionsDAO trDAO = new TransactionsDAOImplPJDBC();
		
		String fileSource = "C:/Users/Mamba/Desktop/Jason-770x470.jpg";
		String fileDestination = "C:/Users/Mamba/Downloads/DS4Windows";
		
		try {
			byte [] byteImage = ImageToByteUtil.ImageToByte(new File(fileSource));
			
			trDAO.addImage(byteImage, 1);
			
			System.out.println(org.postgresql.util.Base64.encodeBytes(trDAO.getImage(1)));
//			ImageToByteUtil.
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
//		byte[] img = trDAO.getImage(1);
//		FileOutputStream imgf = trDAO.bytesToFile(img);
//		
//		System.out.println(imgf);
	}
}
