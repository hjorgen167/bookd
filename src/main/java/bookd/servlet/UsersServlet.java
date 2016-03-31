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
import bookd.model.Users;
import bookd.model.Users;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {       
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

		req.getRequestDispatcher("/users.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

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
        
        req.getRequestDispatcher("/users.jsp").forward(req, resp);

    }
}
