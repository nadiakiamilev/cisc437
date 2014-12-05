//hey there this is a change to commit

import java.io.*;
import java.sql.*;
import java.lang.*;


public class User {
	
	Connection cn;
	ResultSet currentResults;
	Integer currentItem;
	String str;

	public User(String dbname, String userID, String password) {
		cn = null;
		currentResults = null;
		currentItem = null;
	}

	public void helloWorld() {
		System.out.println("hello world!");
	}


	// Method to execute administration operation
	public void adminExecuteOperation(int option, String dbname, String userID, String password) {
		System.out.println("We are going to execute this operation: " );

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);

			switch(option) {
				case 1: //update
					System.out.println("ok here's case 1. You're need to type in info for instance we want to add!");
					// BufferedReader instanceInformation = new BufferedReader(new InputStreamReader(System.in));
					// String newInstance = instanceInformation.readLine();
					// System.out.println(newInstance);
					
					String sqlStatement = "INSERT INTO person" 
						+ "(first_name, last_name, age, homecountry)"
						+ "VALUES" + "('Gabi', 'Kraiter', 22, 'United States');";
					System.out.println(sqlStatement);
					try {
						System.out.println("Update instance");
						Statement st1 = cn.createStatement();
						st1.executeUpdate(sqlStatement); 
						System.out.println("We inserted gabi into the database.");
					}
					catch (SQLException e) {
						System.out.println("Query failed: " + e);
					}

					break;
				case 2: //delete
					System.out.println("here case 2");
					break;
			}

		}
		catch (Exception e) {
			System.out.println("connection failed: " + e);
		}
	}

	/*******BHAVIN YOUR CODE GOES HERE!!***********/
	public void userExecuteOperation(int option) {
		System.out.println("We are going to execute this user query: " + option);

		switch(option) {
			case 1:
				//query 1
				break;
			case 2:
				//query 2
				break;
			case 3:
				//query 3
				break;
			case 4:
				//query 4
				break;
			case 5:
				//query 5
				break;
			case 6:
				//query 6
				break;
		}

	}

	public static void main(String[] args){
		String dbname = "bgala";
		String userID = "bgala";
		String password = "4659";

		// mainMenu();
		String adminOptionsText = " 1. Insert a new instance \n " 
			+ "2. Delete a specific instance \n "
			+ "3. Return to Main Menu \n ";

		String userOptionsText = "1. Find all people in the database \n"
			+ "2. Find stories from requested person \n"
			+ "3. Find stories from a specific country \n"
			+ "4. Find stories from a range of years \n"
			+ "5. Return to Main Menu \n ";

		System.out.println("Input a user type integer [1. ADMIN or 2. USER]: ");
		BufferedReader in1 = new BufferedReader(new InputStreamReader(System.in));

		try {
			String str = in1.readLine();
			int typeUser = Integer.parseInt(str);

			if (typeUser == 1) {
				System.out.println("You're in admin mode. You can now do the following: ");
				System.out.println(adminOptionsText);
				BufferedReader inAdminChoice = new BufferedReader(new InputStreamReader(System.in));
				int adminChoice = Integer.parseInt(inAdminChoice.readLine()); //admin update/delete choice
				System.out.println("Great choice! " + adminChoice);
				User userTmp = new User(dbname, userID, password);
				userTmp.adminExecuteOperation(adminChoice, dbname, userID, password);
			}

			else if (typeUser == 2 ) {
				System.out.println("hey we are in user status");
				System.out.println(userOptionsText);
				BufferedReader inUserChoice = new BufferedReader(new InputStreamReader(System.in));
				int userChoice = Integer.parseInt(inUserChoice.readLine()); //user's query choice
				System.out.println("Great choice! " + userChoice);
				User userTmp = new User(dbname, userID, password);
				userTmp.userExecuteOperation(userChoice);
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
