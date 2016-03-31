package bookd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bookd.model.Editions;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link JorgensenJorgensenUsers} into your MySQL instance and 
 * retrieve {@link JorgensenJorgensenUsers} from MySQL instance.
 */
public class EditionsDao {
	private static EditionsDao instance = null;
	protected ConnectionManager connectionManager;
		
	protected EditionsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static EditionsDao getInstance() {
		if(instance == null) {
			instance = new EditionsDao();
		}
		return instance;
	}


	public Editions create(Editions edition) throws SQLException {
		String insertEdition = "INSERT INTO Editions(asin,edition,locale) "
								+  "VALUES(?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertEdition, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, edition.getASIN());
			insertStmt.setString(2, edition.getEdition());
			insertStmt.setString(3, edition.getLocale());
			
			insertStmt.executeUpdate();
			
			ResultSet resultKey = insertStmt.getGeneratedKeys();
			int editionId = -1;
			if(resultKey.next()) {
				editionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			edition.setId(editionId);
			return edition;
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

	public Editions delete(Editions editions) throws SQLException {
		String deleteUser = "DELETE FROM Editions WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, editions.getId());
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


	public Editions getEditionsById(int requestId) throws SQLException {
		String selectEditions =
			"SELECT * " +
			"FROM Editions " + 
			"WHERE id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEditions);
			selectStmt.setInt(1, requestId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				
				int id = results.getInt("id");
				String asin = results.getString("asin");
				String locale = results.getString("locale");
				String editionName = results.getString("edition");
				
				Editions edition = new Editions(id, asin, editionName, locale);
				return edition;
			}
			return null;
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
	}
	
	public Editions getEditionByAsin(String requestAsin) throws SQLException {
		String selectEditions =
			"SELECT * " +
			"FROM Editions " + 
			"WHERE asin=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectEditions);
			selectStmt.setString(1, requestAsin);
			results = selectStmt.executeQuery();
			if(results.next()) {
				
				int id = results.getInt("id");
				String asin = results.getString("asin");
				String locale = results.getString("locale");
				String editionName = results.getString("edition");
				
				Editions edition = new Editions(id, asin, editionName, locale);
				return edition;
			}
			return null;
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
	}
}
