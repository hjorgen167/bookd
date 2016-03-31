package bookd.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bookd.model.Books;
import bookd.model.Users;


/**
 * Data access object (DAO) class to interact with the underlying Administrators table in your
 * MySQL instance. This is used to store {@link Users} into your MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class BooksDao {
	private static BooksDao instance = null;
	protected ConnectionManager connectionManager;
		
	protected BooksDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static BooksDao getInstance() {
		if(instance == null) {
			instance = new BooksDao();
		}
		return instance;
	}


	public Books create(Books book) throws SQLException {
		String insertBook = "INSERT INTO Books(asin,model,title,binding,"
				+ "editorial_review,editorial_reviews,pages,publisher,publication_date,"
				+ "release_date,region) "
								+  "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBook);
			insertStmt.setString(1, book.getASIN());
			insertStmt.setString(2, book.getModel());
			insertStmt.setString(3, book.getTitle());
			insertStmt.setString(4, book.getBinding());
			insertStmt.setString(5, book.getEditorialReview());
			insertStmt.setString(6, book.getEditorialReviews());
			insertStmt.setInt(7, book.getPages());
			insertStmt.setString(8, book.getPublisher());
			insertStmt.setDate(9, new Date(book.getPublicationDate().getTime()));
			insertStmt.setDate(10, new Date(book.getReleaseDate().getTime()));
			insertStmt.setString(11, book.getRegion());
			System.out.println(insertStmt.toString());;
			insertStmt.executeUpdate();
			return book;
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

	public Books delete(Books books) throws SQLException {
		String deleteUser = "DELETE FROM Books WHERE asin=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, books.getASIN());
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


	public Books getBooksByAsin(int requestId) throws SQLException {

		
		String selectBooks =
			"SELECT * " +
			"FROM Books " + 
			"WHERE asin=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBooks);
			selectStmt.setInt(1, requestId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String asin = results.getString("asin");
				String model = results.getString("model");
				String title = results.getString("title");
				String binding = results.getString("binding");
				String editorial_review = results.getString("editorial_review");
				String editorial_reviews = results.getString("editorial_reviews");
				int pages = results.getInt("pages");
				String publisher = results.getString("publisher");
				Date publicationDate = results.getDate("publication_date");
				Date releaseDate = results.getDate("release_date");
				String region = results.getString("region");
				
				Books book = new Books(asin, model, title, binding, editorial_review, editorial_reviews,
						pages, publisher, publicationDate, releaseDate, region);
				return book;
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
	
	
	public List<Books> getBooksByTitle(String requestTitle) throws SQLException {
		String selectRestaurants =
			"SELECT * " +
			"FROM Books " + 
			"WHERE title like (?);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			
			// Append wildcards
			requestTitle = "%" + requestTitle + "%"; 
			selectStmt.setString(1, requestTitle);
			results = selectStmt.executeQuery();
			ArrayList<Books> books = new ArrayList<Books>(); 
			while(results.next()) {

				String asin = results.getString("asin");
				String model = results.getString("model");
				String title = results.getString("title");
				String binding = results.getString("binding");
				String editorial_review = results.getString("editorial_review");
				String editorial_reviews = results.getString("editorial_reviews");
				int pages = results.getInt("pages");
				String publisher = results.getString("publisher");
				Date publicationDate = results.getDate("publication_date");
				Date releaseDate = results.getDate("release_date");
				String region = results.getString("region");
				
				Books book = new Books(asin, model, title, binding, editorial_review, editorial_reviews,
						pages, publisher, publicationDate, releaseDate, region);
				
				books.add(book);
			}
			return books;
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
	

	public List<Books> getBooksByPublisher(String requestPublisher) throws SQLException {
		String selectRestaurants =
			"SELECT * " +
			"FROM Books " + 
			"WHERE publisher like (?);";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurants);
			requestPublisher = "%" + requestPublisher + "%";
			selectStmt.setString(1, requestPublisher);
			results = selectStmt.executeQuery();
			ArrayList<Books> books = new ArrayList<Books>(); 
			while(results.next()) {

				String asin = results.getString("asin");
				String model = results.getString("model");
				String title = results.getString("title");
				String binding = results.getString("binding");
				String editorial_review = results.getString("editorial_review");
				String editorial_reviews = results.getString("editorial_reviews");
				int pages = results.getInt("pages");
				String publisher = results.getString("publisher");
				Date publicationDate = results.getDate("publication_date");
				Date releaseDate = results.getDate("release_date");
				String region = results.getString("region");
				
				Books book = new Books(asin, model, title, binding, editorial_review, editorial_reviews,
						pages, publisher, publicationDate, releaseDate, region);
				
				books.add(book);
			}
			return books;
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
