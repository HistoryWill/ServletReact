package com.cognixia.jump.react;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cognixia.jump.react.databaseTest.Book;
import com.cognixia.jump.react.databaseTest.BookDAOClass;
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
	private BookDAOClass BookDAO = new BookDAOClass();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(request.getInputStream()));

		String json = "";
		if(br != null){
			json = br.readLine();
		}

		JSONObject obj = new JSONObject();
		JSONParser parser = new JSONParser();
		System.out.println(obj);
		
		try {
			JSONObject injson = (JSONObject) parser.parse(json);
			String username = (String) injson.get("userID");
			int userID = Integer.parseInt(username);
			
		List<UserBook> userBookList = userBooks.getUserBookbyId(userID);
		String jsonobj  = gson.toJson(userBookList);
		System.out.println(jsonobj);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonobj);
		out.flush();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			
			String username = (String) injson.get("bookID");
			int bookID = Integer.parseInt(username);
			String userID1 = (String) injson.get("userID");
			int userID = Integer.parseInt(userID1); 
			String currentpage = (String) injson.get("currentPage");
			int currentPage = Integer.parseInt(currentpage); 
			String currstatus = (String) injson.get("currStatus");
			char currStatus = currstatus.charAt(0);
			String rating = (String) injson.get("rating");
			int Rating = Integer.parseInt(rating); 
			Book book = BookDAO.getBookbyId(bookID);
			
			UserBook newBook = new UserBook(book.getBookId(), book.getTitle(), book.getAuthor(),
					book.getPublisher(),book.getPageCount() , book.getGenre(), book.getSeriesId(),book.getSeriesOrder() , book.isReleased(), book.getFranchiseId(),
					book.getCoverURL(), book.getDescription(),userID, currentPage, currStatus, Rating);
			userBooks.updateUserBook(newBook);
		
		}catch(ParseException e) {
			e.printStackTrace();
		}
	}

}
