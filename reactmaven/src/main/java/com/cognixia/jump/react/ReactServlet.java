package com.cognixia.jump.react;

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

import com.cognixia.jump.react.jdbc.dao.User;
import com.cognixia.jump.react.jdbc.dao.UserDAOClass;
import com.google.gson.Gson;

@WebServlet("/ReactServlet")
public class ReactServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UserDAOClass userDAOClass = new UserDAOClass();
	private Gson gson = new Gson();

	public void init() throws ServletException {
		
	}

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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if(br != null){
			json = br.readLine();
		}

		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject injson = (JSONObject) parser.parse(json);
			
			String username = (String) injson.get("username");
			String password = (String) injson.get("password");
			
			System.out.println(username+" "+password);
			
			User user = userDAOClass.get(username, password);
			
			String jsonobj = gson.toJson(user);

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.print(jsonobj);
			out.flush();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
