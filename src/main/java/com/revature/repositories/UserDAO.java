package com.revature.repositories;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	
	User getUser(long id);
	User getUser(String emial, String passsword);
	
	boolean createUser(User u);
	boolean updateUser(User u);
	

	List<User> getUsers();
}
