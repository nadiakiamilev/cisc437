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

	// Method to execute administration operation
	public void adminExecuteOperation(int option, String dbname, String userID, String password) {
		System.out.println("We are going to execute this operation: " );

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);

			switch(option) {
				case 1: //update
					System.out.println("Please Enter the Story Title to Update: ");
					BufferedReader storyOld = new BufferedReader(new InputStreamReader(System.in));
					String storyOldTitle = storyOld.readLine();
					System.out.println(storyOldTitle);

					System.out.println("Please Enter the New Story Title: ");
					BufferedReader storyNew = new BufferedReader(new InputStreamReader(System.in));
					String storyNewTitle = storyOld.readLine();
					System.out.println(storyNewTitle);
					
					String sqlStatement = "UPDATE story " 
						+ "SET title=" + "'" + storyNewTitle + "'"
						+ " WHERE " + "title=" + "'"+ storyOldTitle + "'";
					
					System.out.println(sqlStatement);
					try {
						Statement st1 = cn.createStatement();
						st1.executeUpdate(sqlStatement); 
						System.out.println("We updated " + storyOldTitle + " with the new story title " + storyNewTitle);
					}
					catch (SQLException e) {
						System.out.println("Query failed: " + e);
					}

					break;
				case 2: //delete
					System.out.println("Please Enter the First Name of the Person You Want to Delete: ");
					BufferedReader firstName = new BufferedReader(new InputStreamReader(System.in));
					String first_Name = firstName.readLine();
					System.out.println(first_Name);

					System.out.println("Please Enter the Last Name of the Person You Want to Delete: ");
					BufferedReader lastName = new BufferedReader(new InputStreamReader(System.in));
					String last_Name = lastName.readLine();
					System.out.println(last_Name);

					String sqlDelStatement = "DELETE FROM person "
						+ "WHERE " + "first_name=" + "'" + first_Name + "'"
						+ " last_name=" + "'" + last_Name + "';";

					try {
						Statement st2 = cn.createStatement();
						st2.executeUpdate(sqlDelStatement);
						System.out.println("You have successfully deleted " + first_Name + " " + last_Name + " from the database.");
					}
					catch (SQLException e) {
						System.out.println("That person does not exist. Query failed: " + e );
					}
					break;
				case 3: 
					System.out.println("You opted to return to the main menu.");
					break;
			}

		}
		catch (Exception e) {
			System.out.println("connection failed: " + e);
		}
	}

	/*******BHAVIN YOUR CODE GOES HERE!!***********/
	public void userExecuteOperation(int option, String dbname, String userID, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);
			
			switch(option) {
				case 1: 
					//gets everyone from the database
					System.out.println("option 1 selected");
					String sqlQuery1 = "SELECT first_name, last_name FROM person";
					try {
						System.out.println(sqlQuery1);
						Statement st1 = cn.createStatement();
						ResultSet persons = st1.executeQuery(sqlQuery1);
						while (persons.next()) {
							System.out.println(persons.getString(1) + " " + persons.getString(2));
						}
					}
					catch (SQLException e) {
						System.out.println("Query failed: " + e);
					}

					break;
				case 2: 
					System.out.println("option 2 selected");
					String sqlQuery2 = "";
					try {
						System.out.println(sqlQuery2);
						Statement st2 = cn.createStatement();
						ResultSet stories = st2.executeQuery(sqlQuery2);
						while (stories.next()) {
							System.out.println(stories.getString(1));
						}
					}
					catch (SQLException e) {
						System.out.println("Query failed: " + e);
					}

					break;
				case 3: 
					System.out.println("option 3 selected");
					String sqlQuery3 = "";
					try {
						System.out.println(sqlQuery3);
						Statement st3 = cn.createStatement();
						ResultSet storiesCountry = st3.executeQuery(sqlQuery3);
						while (storiesCountry.next()) {
							System.out.println(storiesCountry.getString(1));
						}
					}
					catch (SQLException e) {
						System.out.println("Query failed: " + e);
					}

					break;
				case 4: 
					System.out.println("option 4 selected");
					
					System.out.println("Please Enter the Lower Bound on the Year Range: ");
					BufferedReader lowerBound = new BufferedReader(new InputStreamReader(System.in));
					String lowerBoundStr = lowerBound.readLine();
					System.out.println(lowerBoundStr);

					System.out.println("Please Enter the Upper Bound on the Year Range: ");
					BufferedReader upperBound = new BufferedReader(new InputStreamReader(System.in));
					String upperBoundStr = upperBound.readLine();
					System.out.println(upperBoundStr);

					String sqlQuery4 = "";
					try {
						System.out.println(sqlQuery4);
						Statement st4 = cn.createStatement();
						ResultSet storiesByYear = st4.executeQuery(sqlQuery4);
						while (storiesByYear.next()) {
							System.out.println(storiesByYear.getString(1));
						}
					}
					catch (SQLException e) {
						System.out.println("Query failed: " + e);
					}

					break;
				// case 5: 
				// 	System.out.println("option 5 selected");
				// 	String sqlQuery5 = "";
				// 	try {
				// 		System.out.println(sqlQuery5);
				// 		Statement st5 = cn.createStatement();
				// 		ResultSet storiesByYear = st5.executeQuery(sqlQuery4);
				// 		while (storiesByYear.next()) {
				// 			System.out.println(storiesByYear.getString(1));
				// 		}
				// 	}
				// 	catch (SQLException e) {
				// 		System.out.println("Query failed: " + e);
				// 	}

				// 	break;
			}
		} 
		catch (Exception e){
			System.out.println("connection failed: " + e);
		}	
	}


	public static void main(String[] args){
		String dbname = "bgala";
		String userID = "bgala";
		String password = "4659";

		String sqlDelStatement = "DELETE FROM person "
			+ "WHERE " + "first_name=" + "'" + "first_Name" + "'"
			+ " last_name=" + "'" + "last_Name" + "'";

		System.out.println(sqlDelStatement);

		// mainMenu();
		String adminOptionsText = " 1. Update a story title \n " 
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
				userTmp.userExecuteOperation(userChoice, dbname, userID, password);
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
