package com.revature.repositories;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	
	User getUser(long id);
	
	boolean createUser(User u);
	boolean updateUser(User u);
	

	List<User> getUsers();
}
