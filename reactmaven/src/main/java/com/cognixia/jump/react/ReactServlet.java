package com.cognixia.jump.react;
import com.cognixia.jump.react.databaseTest.BadUserIdException;
import com.cognixia.jump.react.databaseTest.ConnectionManagerProperties;
import com.cognixia.jump.react.databaseTest.IncorrectPasswordException;
import com.cognixia.jump.react.databaseTest.User;
import com.cognixia.jump.react.databaseTest.UserDAOClass;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.google.gson.Gson;

@WebServlet("/ReactServlet")
public class ReactServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UserDAOClass userDAOClass = new UserDAOClass();
	private Gson gson = new Gson();

	public void init() throws ServletException {
		
	}
/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter pw = response.getWriter();

		JSONObject obj = new JSONObject();

		obj.put("username", "Pasang");
		obj.put("password", "123");
		obj.put("userID", 2 );

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(obj);
		out.flush();
	}
*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		String username = request.getParameter("actor-id");
		String password = request.getParameter("password");
		
		UserDAOClass test = new UserDAOClass();
		System.out.println(username);
		System.out.println(password);
		boolean login = false;
		User user = null;
		System.out.println(test.authenticateUser(username, password));
		try{
			login = test.authenticateUser(username, password);
			
			if(login == false) {
				user = test.getUserbyUsername(username);
				if(user == null) {
					throw new BadUserIdException();
				}else {
					throw new IncorrectPasswordException();
				}
				
			}
			
			
		}catch(BadUserIdException e){
			e.getMessage();
			
		}catch(IncorrectPasswordException e) {
			e.getMessage();
		}
		 user = test.getUserbyUsername(username);

		
		String jsonobj = gson.toJson(user);
		System.out.println(jsonobj);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonobj);
		out.flush();

	}
}
