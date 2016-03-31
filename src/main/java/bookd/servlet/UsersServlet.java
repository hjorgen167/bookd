package bookd.servlet;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bookd.dao.UsersDao;
import bookd.model.Books;
import bookd.model.Users;
import bookd.model.Users;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	
	protected UsersDao usersDao;
	private static String BASE_URL = "WEB-INF/webpages/Users/";
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	private String getRedirectURL(String action){
		String page = "";
		
		if(action.equalsIgnoreCase("search") || action.equalsIgnoreCase("delete")){
			page = "SearchUsers.jsp";
		}else if(action.equalsIgnoreCase("add")){
			page = "AddUsers.jsp";
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
			deleteUsers(req, resp, messages);
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
			searchUsers(req, resp, messages);
		}else if(action.equalsIgnoreCase("add")){
			addUsers(req, resp, messages);
		}
        
        req.getRequestDispatcher(getRedirectURL(action)).forward(req, resp);
    }

	private void addUsers(HttpServletRequest req, HttpServletResponse resp, Map<String, String> messages) {
		// TODO Auto-generated method stub
		Users user = new Users(
				req.getParameter("userId"),
				req.getParameter("username")
				);
		try {
			List<Users> users = new ArrayList<Users>();
			user = usersDao.create(user);
			users.add(user);
			req.setAttribute("users", users);
			messages.put("success", "Displaying results for:  " + user.getName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			messages.put("error", "An unexpected error occured while retrieving results.\nPlease try again. ");
			e.printStackTrace();
		}
	}

	private void searchUsers(HttpServletRequest req, HttpServletResponse resp, Map<String, String> messages) throws IOException {
		// TODO Auto-generated method stub
		List<Users> users = new ArrayList<Users>();

		String searchParam = req.getParameter("searchParam");
        if (searchParam == null || searchParam.trim().isEmpty()) {
            messages.put("error", "Please enter a username.");
        } else {
        	try {
        		Users user = usersDao.getUsersByUserName(searchParam);
        		if(user == null){
        			messages.put("error", "No results found for : " + searchParam.toUpperCase());
        		}else{
        			users.add(user);
        			messages.put("success", "Displaying results for: " + searchParam.toUpperCase());
        		}
            	messages.put("previousSearchParam", searchParam);
            } catch (SQLException e) {
    			e.printStackTrace();
    			messages.put("error", "An unexpected error occured while retrieving results.\nPlease try again. ");
    			throw new IOException(e);
            }
        }
        req.setAttribute("users", users);
	}
	
	private void deleteUsers(HttpServletRequest req, HttpServletResponse resp, Map<String, String> messages) {
		// TODO Auto-generated method stub
		String userId = req.getParameter("userId");
		Users user = new Users(userId);
		try{
			usersDao.delete(user);
			messages.put("success", "Book deleted successfully");
		}catch(SQLException e){
			e.printStackTrace();
			messages.put("error", "An unexpected error occured while deleting.\nPlease try again. ");
		}
	}
}
