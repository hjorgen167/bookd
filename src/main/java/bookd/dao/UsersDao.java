package bookd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookd.model.Users;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Users} into your MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class UsersDao {
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	protected ConnectionManager connectionManager;
		
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	public Users createOrUpdate(Users user) throws SQLException {
		String selectUser = "SELECT * FROM BookUser WHERE BookUser.UserID = ?;";
		String updateUSer = "UPDATE BookUser SET BookUser.Name = ? WHERE BookUser.UserID = ?";
		String insertUser = "INSERT INTO BookUser(UserID,Name)"
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement stmt = null;
		connection = connectionManager.getConnection();
		try {
			stmt = connection.prepareStatement(selectUser);
			stmt.setString(1, user.getUserId());
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				stmt = connection.prepareStatement(updateUSer);
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getUserId());
				stmt.executeUpdate();
			}else{
				stmt = connection.prepareStatement(insertUser);
				stmt.setString(1, user.getUserId());
				stmt.setString(2, user.getName());
				stmt.executeUpdate();
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
	}

	/**
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM BookUser WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public Users getUsersByUserName(String requestUserName)
			throws SQLException {
		String selectUsers =
			"SELECT * " +
			"FROM BookUser " + 
			"WHERE Name=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUsers);
			selectStmt.setString(1, requestUserName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String userId = results.getString("UserID");
				String name = results.getString("Name");
				
				Users user = new Users(userId, name);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
}
