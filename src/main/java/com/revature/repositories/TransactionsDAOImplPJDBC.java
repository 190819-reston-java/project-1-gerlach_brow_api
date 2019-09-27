package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Transaction;
import com.revature.utils.ConnectionUtil;

public class TransactionsDAOImplPJDBC implements TransactionsDAO {

	public Transaction getTransaction(long id) {
		
		return null;
	}

	public List<Transaction> getTransactions() {
		
	Statement stmt = null;
	ResultSet rs = null;
	Connection conn = null;
	
	List<Transaction> trs = new ArrayList<Transaction>();
	
	Try{
		conn = ConnectionUtil.getConnection();
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM Transaction;");
		
		while (rs.next()) {
			trs.add(createTransactionFromRS(rs));
		}
	}catch (SQLException e) {
		e.printStackTrace();
	}
		
		return null;
	}

	private Transaction createTransactionFromRS(ResultSet rs) {
		
		return new Transaction(
				rs.getLong("id"),
				rs.getString("firstName"));
	}

}
