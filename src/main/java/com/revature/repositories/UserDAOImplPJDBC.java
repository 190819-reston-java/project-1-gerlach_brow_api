package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

		User u = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM \"user\" WHERE id = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setLong(1, id);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						if (rs.next()) {
							u = createUserFromRS(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

	public List<User> getUsers() {

		Statement statement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		List<User> users = new ArrayList<User>();

		try {
			conn = ConnectionUtil.getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM \"user\";");
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

		return new User(resultSet.getLong("id"), resultSet.getString("first_name"), resultSet.getString("last_name"),
				resultSet.getBoolean("is_admin"), resultSet.getString("email"), resultSet.getString("password"),
				resultSet.getString("address"), resultSet.getString("address_2"), resultSet.getString("phone_number"),
				resultSet.getString("position"));
	}

	@Override
	public boolean createUser(User u) {

		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "INSERT into \"user\" VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setBoolean(3, u.isAdmin());
			stmt.setString(4, u.getEmail());
			stmt.setString(5, u.getPassword());
			stmt.setString(6, u.getAddress());
			stmt.setString(7, u.getAddress2());
			stmt.setString(8, u.getPhoneNumber());
			stmt.setString(9, u.getPosition());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}

		return true;
	}

	@Override
	public boolean updateUser(User u) {

		Connection conn = null;
		PreparedStatement stmt = null;

		String query = "UPDATE \"user\" SET first_name = ?, last_name = ?, is_admin = ?, password = ?, "
				+ "address = ?, address_2 = ?, phone_number = ?, position = ? WHERE id = ?;";

		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, u.getFirstName());
			stmt.setString(2, u.getLastName());
			stmt.setBoolean(3, u.isAdmin());
			stmt.setString(4, u.getPassword());
			stmt.setString(5, u.getAddress());
			stmt.setString(6, u.getAddress2());
			stmt.setString(7, u.getPhoneNumber());
			stmt.setString(8, u.getPosition());
			stmt.setLong(9, u.getId());
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			StreamCloser.close(conn);
			StreamCloser.close(stmt);
		}

		return true;
	}

	@Override
	public User getUser(String email, String password) {
		User u = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM \"user\" WHERE email = ? AND password = ?;";
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setString(1, email);
				stmt.setString(2, password);
				if (stmt.execute()) {
					try (ResultSet rs = stmt.getResultSet()) {
						if (rs.next()) {
							u = createUserFromRS(rs);
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}

}
