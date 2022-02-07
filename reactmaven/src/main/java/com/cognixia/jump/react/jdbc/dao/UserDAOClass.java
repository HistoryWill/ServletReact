package com.cognixia.jump.react.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.cognixia.jump.react.connection.ConnectionManager;

public class UserDAOClass implements Dao{
	
	private Connection conn = ConnectionManager.getConnection();;
	PreparedStatement pstmnt = null;
	ResultSet rs = null;
	
	@Override
	public User get(String username, String password) {
		
		System.out.println(username+" "+password);
		
		int id = -1;
		try {
			pstmnt = conn.prepareStatement("select * from users where username = ? and password = ?");
			
			pstmnt.setString(1, username);
			pstmnt.setString(2, password);
			
			rs = pstmnt.executeQuery();
			rs.next();
			id = rs.getInt("userID");
			System.out.println(id);
			
			if(id != -1) {
				User user = new User(
					rs.getInt("userID"),
					rs.getString("username"),
					rs.getString("password"));
				
				System.out.println(user);
				
				return user;
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmnt.close();
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return null;
	}

}
