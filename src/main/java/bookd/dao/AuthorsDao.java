package bookd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bookd.model.Authors;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Authors} into your MySQL instance and 
 * retrieve {@link Authors} from MySQL instance.
 */
public class AuthorsDao {
	// Single pattern: instantiation is limited to one object.
	private static AuthorsDao instance = null;
	protected ConnectionManager connectionManager;
		
	protected AuthorsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static AuthorsDao getInstance() {
		if(instance == null) {
			instance = new AuthorsDao();
		}
		return instance;
	}
	
	public Authors create(Authors author) throws SQLException {

		String insertAuthor = "INSERT INTO Authors(id,asin,author)"
				+ "VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertAuthor);
			insertStmt.setInt(1, author.getId());
			insertStmt.setString(2, author.getASIN());
			insertStmt.setString(3, author.getAuthor());
			insertStmt.executeUpdate();
			return author;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	/**
	 * Delete the Authors instance.
	 * This runs a DELETE statement.
	 */
	public Authors delete(Authors author) throws SQLException {
		String deleteAuthor = "DELETE FROM Authors WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAuthor);
			deleteStmt.setInt(1, author.getId());
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
	
	public Authors getAuthorsByAuthorName(String requestAuthorName)
			throws SQLException {
		String selectAuthors =
			"SELECT * " +
			"FROM Authors " + 
			"WHERE author=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAuthors);
			selectStmt.setString(1, requestAuthorName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("author");
				String asin = results.getString("asin");
				
				Authors author = new Authors(id, asin, name);
				return author;
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
