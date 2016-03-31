package bookd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bookd.model.Covers;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link JorgensenJorgensenUsers} into your MySQL instance and 
 * retrieve {@link JorgensenJorgensenUsers} from MySQL instance.
 */
public class CoversDao {
	private static CoversDao instance = null;
	protected ConnectionManager connectionManager;
		
	protected CoversDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CoversDao getInstance() {
		if(instance == null) {
			instance = new CoversDao();
		}
		return instance;
	}


	public Covers create(Covers cover) throws SQLException {
		String insertCover = "INSERT INTO Covers(asin,largeThumbnail,mediumThumbnail,"
				+ "smallThumbnail) "
								+  "VALUES(?,?,?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCover, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, cover.getASIN());
			insertStmt.setString(2, cover.getSmallThumbnail());
			insertStmt.setString(3, cover.getMediumThumbnail());
			insertStmt.setString(4, cover.getLargeThumbnail());
			
			insertStmt.executeUpdate();
			
			ResultSet resultKey = insertStmt.getGeneratedKeys();
			int coverId = -1;
			if(resultKey.next()) {
				coverId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			cover.setId(coverId);
			return cover;
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

	public Covers delete(Covers covers) throws SQLException {
		String deleteUser = "DELETE FROM Covers WHERE id=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, covers.getId());
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


	public Covers getCoversById(int requestId) throws SQLException {
		String selectCovers =
			"SELECT * " +
			"FROM Covers " + 
			"WHERE id=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCovers);
			selectStmt.setInt(1, requestId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				
				int id = results.getInt("id");
				String asin = results.getString("asin");
				String smallThumbnail = results.getString("smallThumbnail");
				String mediumThumbnail = results.getString("mediumThumbnail");				
				String largeThumbnail = results.getString("largeThumbnail");
				
				Covers cover = new Covers(id, asin, smallThumbnail, mediumThumbnail, largeThumbnail);
				return cover;
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
	
	public Covers getCoverByAsin(String requestAsin) throws SQLException {
		String selectCovers =
			"SELECT * " +
			"FROM Covers " + 
			"WHERE asin=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCovers);
			selectStmt.setString(1, requestAsin);
			results = selectStmt.executeQuery();
			if(results.next()) {
				
				int id = results.getInt("id");
				String asin = results.getString("asin");
				String smallThumbnail = results.getString("smallThumbnail");
				String mediumThumbnail = results.getString("mediumThumbnail");				
				String largeThumbnail = results.getString("largeThumbnail");
				
				Covers cover = new Covers(id, asin, smallThumbnail, mediumThumbnail, largeThumbnail);
				return cover;
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
