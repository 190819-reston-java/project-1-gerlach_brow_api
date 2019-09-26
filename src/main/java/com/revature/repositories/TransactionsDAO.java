package com.revature.repositories;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionsDAO {

	Transaction getTransaction(long id);
	
	List<Transaction> getTransactions();
}
