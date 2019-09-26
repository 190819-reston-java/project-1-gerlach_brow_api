package com.revature.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.StreamCloser;

public class UserDAOImplPJDBC implements UserDAO {

	public User getUser(long id) {

		return null;
	}

	public List<User> getUsers() {
		
		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		List<User> users = new ArrayList<User>();
		
		try {
			conn = ConnectionUtil.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM user");
			while (resultSet.next()) {
				users.add(createUserFromRS(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			StreamCloser.close(resultSet);
			StreamCloser.close(statement);
			StreamCloser.close(conn);
		}
		
		return users;
	}

	private User createUserFromRS(ResultSet resultSet) throws SQLException {
		
		return new User(
				resultSet.getLong("id"),
				resultSet.getString("first_name"),
				resultSet.getString("last_name"),
				resultSet.getBoolean("is_admin"),
				resultSet.getString("email"),
				resultSet.getString("password"),
				resultSet.getString("address"),
				resultSet.getString("address_2"),
				resultSet.getString("phone_number"),
				resultSet.getString("position"));
	}

}
