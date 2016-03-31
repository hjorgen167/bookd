package bookd.servlet;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookd.dao.BooksDao;
import bookd.model.Books;
import bookd.model.Users;

@WebServlet(urlPatterns = {"/books"})
public class BooksServlet extends HttpServlet {
	
	protected BooksDao booksDao;
	private static String BASE_URL = "WEB-INF/webpages/Books/";
	
	@Override
	public void init() throws ServletException {
		booksDao = BooksDao.getInstance();
	}
	
	private String getRedirectURL(String action){
		String page = "";
		
		if(action.equalsIgnoreCase("search") || action.equalsIgnoreCase("delete")){
			page = "SearchBooks.jsp";
		}else if(action.equalsIgnoreCase("add")){
			page = "AddBooks.jsp";
		}else{
			page = "index.jsp";
		}
		
		return BASE_URL + page;
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {       
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		
		String action = req.getParameter("action");
		if(action.equalsIgnoreCase("delete")){
			deleteBooks(req, resp, messages);
		}
		req.getRequestDispatcher(getRedirectURL(action)).forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String action = req.getParameter("action");
        
        if(action.equalsIgnoreCase("search")){
			searchBooks(req, resp, messages);
		}else if(action.equalsIgnoreCase("add")){
			addBooks(req, resp, messages);
		}
        
        req.getRequestDispatcher(getRedirectURL(action)).forward(req, resp);
    }
	
	private void deleteBooks(HttpServletRequest req, HttpServletResponse resp, Map<String, String> messages) throws IOException {
		// TODO Auto-generated method stub
		String ASIN = req.getParameter("asin");
		Books book = new Books(ASIN);
		try{
			booksDao.delete(book);
			searchBooks(req, resp, messages);
			messages.put("success", "Book deleted successfully");
		}catch(SQLException e){
			e.printStackTrace();
			messages.put("error", "An unexpected error occured while deleting.\nPlease try again. ");
		}
	}

	private void addBooks(HttpServletRequest req, HttpServletResponse resp, Map<String, String> messages) throws IOException {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pubDateString = req.getParameter("rel_date");
    	Date rel_date = new Date();
    	String relDateString = req.getParameter("rel_date");
    	Date pub_date = new Date();
    	try {
    		rel_date = dateFormat.parse(relDateString);
    		pub_date = dateFormat.parse(pubDateString);
    	} catch (ParseException e) {
    		e.printStackTrace();
			throw new IOException(e);
    	}
    	String asin = req.getParameter("asin");
		String model = req.getParameter("model");
		String title  = req.getParameter("title");
		String binding = req.getParameter("binding");
		String editorialReview = "";
		String editorialReviews = ""; 
		int pages = req.getParameter("pages").isEmpty() ? 0 : Integer.parseInt(req.getParameter("pages"));
		String publisher = req.getParameter("publisher");
		String region = req.getParameter("region");
    	
		Books book = new Books(
				asin,
				model,
				title,
				binding,
				editorialReview,
				editorialReviews,
				pages,
				publisher,
				pub_date,
				rel_date,
				region
				);
		try {
			List<Books> books = new ArrayList<Books>();
			book = booksDao.create(book);
			books.add(book);
			req.setAttribute("books", books);
			messages.put("success", "Displaying results for:  " + book.getASIN());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			messages.put("error", "An unexpected error occured while retrieving results.\nPlease try again. ");
			e.printStackTrace();
		}
	}

	private void searchBooks(HttpServletRequest req, HttpServletResponse resp, Map<String, String> messages) throws IOException{
		List<Books> books = new ArrayList<Books>();

		String criterion = req.getParameter("criterion");
        if (criterion == null || criterion.trim().isEmpty()) {
            messages.put("error", "Please select a category to search by.");
        } else {
        	String searchParam = req.getParameter("searchParam");
        	try {
        		if(criterion.equalsIgnoreCase("asin")){
        			Books book = booksDao.getBooksByAsin(Integer.parseInt(searchParam));
        			if(book != null)
        				books.add(book);
            	}
            	else if(criterion.equalsIgnoreCase("title")){
            		books = booksDao.getBooksByTitle(searchParam);
            	}else if(criterion.equalsIgnoreCase("publisher")){
            		books = booksDao.getBooksByPublisher(searchParam);
            	}
        		if(books.size() > 0)
        			messages.put("success", "Displaying results for:  " + searchParam.toUpperCase() + " by: " + criterion.toUpperCase());
        		else
        			messages.put("error", "No results found for : " + searchParam.toUpperCase());
        		// Save the previous search term, so it can be used as the default
            	// in the input box when rendering FindBooks.jsp.
            	messages.put("previousSearchParam", searchParam);
            } catch (SQLException e) {
    			e.printStackTrace();
    			messages.put("error", "An unexpected error occured while retrieving results.\nPlease try again. ");
    			throw new IOException(e);
            }
        	catch(NumberFormatException e){
        		messages.put("error", "Invalid ASIN : " + searchParam.toUpperCase());
			}
        }
        req.setAttribute("books", books);
	}
}
