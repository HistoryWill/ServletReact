package com.cognixia.jump.react;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.react.databaseTest.BadUserIdException;
import com.cognixia.jump.react.databaseTest.IncorrectPasswordException;
import com.cognixia.jump.react.databaseTest.User;
import com.cognixia.jump.react.databaseTest.UserDAOClass;
import com.cognixia.jump.react.databaseTest.UsernameAlreadyTakenException;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserCreationServlet
 */
@WebServlet("/UserCreationServlet")
public class UserCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	private UserDAOClass userDAOClass = new UserDAOClass();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("actor-id");
		String password = request.getParameter("password");
		User user = new User(0, username, password);
		
		boolean login =false;
			userDAOClass.addUser(user);
			try{ //this try block determines if the username is already taken 
				login = userDAOClass.authenticateUser(username, password);
				
				if(login == false) {
					user = userDAOClass.getUserbyUsername(username);
					if(user == null) {
						userDAOClass.addUser(user);
					}else {
						throw new UsernameAlreadyTakenException();
					}
					
				}
				
				
			}catch(UsernameAlreadyTakenException e){
				e.getMessage();
				
			}
		
		
		doGet(request, response);
	}

}
