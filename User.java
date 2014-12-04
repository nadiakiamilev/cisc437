
import java.io.*;
import java.sql.*;


public class User {
	Connection cn;
	
	ResultSet currentResults;
	
	Integer currentItem;
	
	public User(String dbname, String userID, String password) {
		cn = null;
		currentResults = null;
		currentItem = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);
		}
		catch (Exception e)
		{
			System.out.println("connection failed: " + e);
		}
		
		try
	    {
		System.out.println("show databases");
		Statement st1 = cn.createStatement();
		ResultSet rs1 = st1.executeQuery("show databases");
		while (rs1.next())
		{
		    System.out.println("Database: "+rs1.getString(1));
		}
		st1.close();
	    }
	    catch (SQLException e) {
		System.out.println("Query failed: " + e);
	    }

	    try
	    {
		System.out.println("use " + dbname);
		Statement st2 = cn.createStatement();
		st2.executeUpdate("use " + dbname);
	    }
	    catch (SQLException e) {
		System.out.println("Update failed: " + e);
	    }

	    try
	    {
		System.out.println("show tables");
		Statement st4 = cn.createStatement();
		ResultSet rs4 = st4.executeQuery("show tables");
		while (rs4.next())
		{
		    System.out.println("Table: "+rs4.getString(1));
		}
		st4.close();
	    }
	    catch (SQLException e) {
		System.out.println("Query failed: " + e);
	    }
	    
	    try {
			cn.close();
		} 
	    catch (SQLException e) 
	    {
	    	
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		String dbname = "name";
		String userID = "name";
		String password = "password";
		
		User app = new User(dbname, userID, password);
	}
}
