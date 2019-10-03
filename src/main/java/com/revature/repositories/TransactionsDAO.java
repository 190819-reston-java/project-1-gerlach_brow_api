package com.revature.repositories;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.revature.model.Transaction;
import com.revature.model.User;

public interface TransactionsDAO {

	void addImage(byte [] img, int id);
	byte [] getImage(int id);
	
	boolean createTransaction(String comment, long id);
	boolean updateTransaction(Transaction trs);
	
//	byte [] fileToBytes(File file);
//	File bytesToFile(byte[] imgByte) throws FileNotFoundException;
	
	List<Transaction> getTransactions();
	List<Transaction> getTransactions(long userId);
	List<Transaction> getTrsPending(long userId);
    List<Transaction> getTrsApproved(long userId);
    List<Transaction> getTrsDenied(long userId);
}
