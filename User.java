//hey there this is a change to commit

import java.io.*;
import java.sql.*;
import java.lang.*;


public class User {
	
	//This is just a test//
	
	Connection cn;

	ResultSet currentResults;

	Integer currentItem;

	String str;
	public User(String dbname, String userID, String password) {
		cn = null;
		currentResults = null;
		currentItem = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);
		}catch (Exception e)
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
		}catch (SQLException e) {
			System.out.println("Query failed: " + e);
		}

		try
		{
			System.out.println("use " + dbname);
			Statement st2 = cn.createStatement();
			st2.executeUpdate("use " + dbname);
		}catch (SQLException e) {
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
		}catch (SQLException e) {
			System.out.println("Query failed: " + e);
		}

		try
		{
			System.out.println("Show People in DB");
			Statement st5 = cn.createStatement();
			ResultSet rs5 = st5.executeQuery("SELECT * FROM person");
			while (rs5.next())
			{
				System.out.print(rs5.getString(1)+", ");
				System.out.print(rs5.getString(2)+", ");
				System.out.print(rs5.getString(3)+", ");
				System.out.println(rs5.getString(4));
			}
		}catch (SQLException e) {
			System.out.println("Query failed: " + e);
		}
		try
		{
			System.out.println("Bhavin's Story");
			Statement st6 = cn.createStatement();
			ResultSet rs6 = st6.executeQuery("SELECT description FROM (story NATURAL JOIN person_story NATURAL JOIN person) WHERE first_name='Bhavin'");
			while (rs6.next())
			{
				System.out.println(rs6.getString(1));
			}
		}catch (SQLException e){
			System.out.println("Query failed: " + e);
		}
		try
		{
			System.out.print("Input a name:");
			BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));
			try {
				str = "'"+in1.readLine()+"'";
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String input = "SELECT description FROM (story NATURAL JOIN person_story NATURAL JOIN person) WHERE first_name=" +str;
			System.out.println(input);
			Statement st7 = cn.createStatement();
			ResultSet rs7 = st7.executeQuery(input);
			while (rs7.next())
			{
				System.out.println(rs7.getString(1));
			}
		}catch (SQLException e){
			System.out.println("Query7 failes: " +e);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			String s = in.readLine();
			System.out.println(s);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			cn.close();
		}catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	// public void mainMenu() {
	// 	System.out.println("MAIN MENU");
	// }

	public static void main(String[] args){
		String dbname = "bgala";
		String userID = "bgala";
		String password = "4659";

		// mainMenu();
		
		System.out.println("Input a user type integer [1. ADMIN or 2. USER]: ");
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));

		try {
			String str = in1.readLine();
			int typeUser = Integer.parseInt(str);
			System.out.println(str);

			if (typeUser == 1) {
				System.out.println("hey we are in admin status");
			}

			else if (typeUser == 2 ) {
				System.out.println("hey we are in user status");
			}

			else {
				System.out.print("hey, not the right type of user here. sorry.");
			}

				
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
}
