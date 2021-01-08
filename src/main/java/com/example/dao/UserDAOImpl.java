package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.User;

public class UserDAOImpl implements UserDAO {
	public static String DB_url = System.getenv("REVATURE_DB_URL");
	public static String DB_username = System.getenv("REVATURE_DB_USERNAME");
	public static String DB_password = System.getenv("REVATURE_DB_PASSWORD");

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("THE STATIC BLOCK HAS FAILED");
			e.printStackTrace();
		}
	}

	@Override
	public void createUser(String username, String password, String first_name, String last_name, String email,
			int role_id) {
		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {
			String sql = "INSERT INTO ERS_USERS VALUES (DEFAUT, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, first_name);
			ps.setString(3, first_name);
			ps.setString(4, last_name);
			ps.setString(5, email);
			ps.setInt(6, role_id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> readAllUsers() {
		List<User> userList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {

			String sql = "SELECT * FROM ERS_USERS ORDER BY ERS_USERS_ID";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // executeQuery not executeUpdate

			while (rs.next()) {
				userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userList;
	}
}
