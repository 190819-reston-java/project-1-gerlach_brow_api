package com.revature.repositories;

import java.util.List;

import com.revature.model.User;

public interface UserDAO {
	
	User getUser(long id);

	List<User> getUsers();
}
