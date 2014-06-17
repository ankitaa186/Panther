package com.ankit.panther.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public class DBDao {
	

	public String test(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
			          .getConnection("jdbc:mysql://localhost/restservices?"
			                  + "user=root&password=root");
			
			Statement statement = connection.createStatement();
		      // resultSet gets the result of the SQL query
		      ResultSet resultSet = statement
		          .executeQuery("select * from RestServices.Person");
		      writeResultSet(resultSet);
		      resultSet = statement
			          .executeQuery("select * from RestServices.Person");
		      resultSet.next();
		      return resultSet.getString("name");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
	    // resultSet is initialised before the first data set
	    while (resultSet.next()) {
	      // it is possible to get the columns via name
	      // also possible to get the columns via the column number
	      // which starts at 1
	      // e.g., resultSet.getSTring(2);
	      String user = resultSet.getString("id");
	      String website = resultSet.getString("name");
	      String summary = resultSet.getString("email");
	      Date date = resultSet.getDate("dob");
	      String comment = resultSet.getString("sex");
	      System.out.println("User: " + user);
	      System.out.println("Website: " + website);
	      System.out.println("Summary: " + summary);
	      System.out.println("Date: " + date);
	      System.out.println("Comment: " + comment);
	    }
	  }
	
	public static void main(String args[]){
		(new DBDao()).test();
	}
	
	

}
