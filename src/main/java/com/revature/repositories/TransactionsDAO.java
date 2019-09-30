package com.revature.repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.revature.model.Transaction;

public interface TransactionsDAO {

	Transaction getTransaction(long id);
	void addImage(byte [] img, int id);
	byte [] getImage(int id);
	
	boolean createTransaction(Transaction trs, long userid);
	boolean updateTransaction(Transaction trs);
	
//	byte [] fileToBytes(File file);
//	File bytesToFile(byte[] imgByte) throws FileNotFoundException;
	
	List<Transaction> getTransactions();
}
