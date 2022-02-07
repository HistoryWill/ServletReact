package com.cognixia.jump.react;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.react.databaseTest.UserBook;
import com.cognixia.jump.react.databaseTest.UserBooksDAOClass;
import com.google.gson.Gson;

/**
 * Servlet implementation class UserBookServlet
 */
@WebServlet("/UserBookServlet")
public class UserBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBooksDAOClass userBooks = new UserBooksDAOClass();
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public UserBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		List<UserBook> userBookList = userBooks.getUserBookbyId(Integer.parseInt(request.getParameter("actor-id")));
		String jsonobj  = gson.toJson(userBookList);
		
		System.out.println(jsonobj);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonobj);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
	}

}
