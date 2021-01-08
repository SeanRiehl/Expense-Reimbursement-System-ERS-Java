package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO {
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
	public void createReimbursement(float amount, String description, int author, int type_id) {
		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {
			String sql = "INSERT INTO ERS_REIMBURSEMENT VALUES (DEFAULT, ?, CURRENT_TIMESTAMP, NULL, ?, NULL, ?, NULL, 0, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, amount);
			ps.setString(2, description);
			ps.setInt(3, author);
			ps.setInt(4, type_id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Reimbursement> readAllReimbursements() {
		List<Reimbursement> reimbursementList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT ORDER BY REIMB_ID";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery(); // executeQuery not executeUpdate

			while (rs.next()) {
				reimbursementList.add(new Reimbursement(rs.getInt(1), rs.getFloat(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbursementList;
	}

	@Override
	public List<Reimbursement> readAllReimbursementsByStatus(int status_id) {
		List<Reimbursement> reimbursementList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_STATUS_ID = ? ORDER BY REIMB_ID";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status_id);

			ResultSet rs = ps.executeQuery(); // executeQuery not executeUpdate

			while (rs.next()) {
				reimbursementList.add(new Reimbursement(rs.getInt(1), rs.getFloat(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbursementList;
	}

	@Override
	public List<Reimbursement> readAllReimbursementsByAuthor(int author) {
		List<Reimbursement> reimbursementList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {

			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ? ORDER BY REIMB_ID";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, author);

			ResultSet rs = ps.executeQuery(); // executeQuery not executeUpdate

			while (rs.next()) {
				reimbursementList.add(new Reimbursement(rs.getInt(1), rs.getFloat(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reimbursementList;
	}

	@Override
	public void updateReimbursement(int reimb_id, int author, int resolver, int status_id) {
		try (Connection conn = DriverManager.getConnection(DB_url, DB_username, DB_password)) {

			String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED = CURRENT_TIMESTAMP, REIMB_RESOLVER = ?, REIMB_STATUS_ID = ? WHERE REIMB_ID = ? AND REIMB_AUTHOR = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, resolver);
			ps.setInt(2, status_id);
			ps.setInt(3, reimb_id);
			ps.setInt(4, author);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
