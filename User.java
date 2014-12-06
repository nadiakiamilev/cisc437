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
	public void adminExecuteOperation(String dbname, String userID, String password) {

		int adminChoice=0;
		boolean exit = false;

		String adminOptionsText = " \n ADMIN MENU: \n " 
			+ "---------------------------------------------------------------- \n"
			+ "(Enter in the cooresponding number to the query you wish to run) \n"
			+ "---------------------------------------------------------------- \n"
			+ " 1. Update a story title \n " 
			+ "2. Delete a specific person from the database \n "
			+ "3. Add a new entity to the database \n "
			+ "4. Quit program \n "
			+ "---------------------------------------------------------------- \n";

		while(!exit){			
			System.out.println("You're in admin mode. You can now do the following: ");
			System.out.println(adminOptionsText);
			BufferedReader inAdminChoice = new BufferedReader(new InputStreamReader(System.in));

			try {
				adminChoice = Integer.parseInt(inAdminChoice.readLine()); //admin update/delete choice
				System.out.println("Great choice! " + adminChoice);
			} 
			catch (Exception e){
				adminChoice=0;
				System.out.println("!!! Please make sure to enter one of the options listed !!!");
			}

			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);

				switch(adminChoice) {
					case 1: //update
						System.out.println("Please Enter the Story Title to Update: ");
						BufferedReader storyOld = new BufferedReader(new InputStreamReader(System.in));
						String storyOldTitle = storyOld.readLine();

						System.out.println("Please Enter the New Story Title: ");
						BufferedReader storyNew = new BufferedReader(new InputStreamReader(System.in));
						String storyNewTitle = storyOld.readLine();

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

						System.out.println("Please Enter the Last Name of the Person You Want to Delete: ");
						BufferedReader lastName = new BufferedReader(new InputStreamReader(System.in));
						String last_Name = lastName.readLine();

						String sqlDelStatement = "DELETE FROM person "
							+ "WHERE " + "first_name=" + "'" + first_Name + "'"
							+ " last_name=" + "'" + last_Name + "';";

						try {
							Statement st2 = cn.createStatement();
							st2.executeUpdate(sqlDelStatement);
							System.out.println("You have successfully deleted " + first_Name + " " + last_Name + " from the database.");
						}
						catch (SQLException e) {
							System.out.println("That person does not exist in the database. Query failed: " + e );
						}
						break;
					case 3: 
						System.out.println("Let's add a new entity to our database!");

						System.out.println("Youre First Name: ");
						BufferedReader firstNameAdd = new BufferedReader(new InputStreamReader(System.in));
						String first_name_add = firstNameAdd.readLine();

						System.out.println("Your Last Name: ");
						BufferedReader lastNameAdd = new BufferedReader(new InputStreamReader(System.in));
						String last_name_add = lastNameAdd.readLine();

						System.out.println("Please enter where you're coming from (ex. United States): ");
						BufferedReader homeCountry = new BufferedReader(new InputStreamReader(System.in));
						String home_Country = homeCountry.readLine();

						System.out.println("Please enter your age: ");
						BufferedReader age = new BufferedReader(new InputStreamReader(System.in));
						String ageStr = age.readLine();
						
						System.out.println("Please enter what year your story happened in: ");
						BufferedReader storyYear = new BufferedReader(new InputStreamReader(System.in));
						String story_year = storyYear.readLine();

						System.out.println("Please Enter what season your story took place in [fall, winter, spring, summer]: ");
						BufferedReader storySeason = new BufferedReader(new InputStreamReader(System.in));
						String story_season = storySeason.readLine();

						System.out.println("Please Enter the country your story took place in");
						BufferedReader country = new BufferedReader(new InputStreamReader(System.in));
						String story_country = country.readLine();

						System.out.println("Please Enter the city your story took place in: ");
						BufferedReader storyCity = new BufferedReader(new InputStreamReader(System.in));
						String story_city = storyCity.readLine();

						System.out.println("Please Enter the title of the story you'll tell: ");
						BufferedReader storyTitle = new BufferedReader(new InputStreamReader(System.in));
						String story_title = storyTitle.readLine();

						System.out.println("Please Enter the place description of where your story took place (i.e. restaurant): ");
						BufferedReader storyPlace = new BufferedReader(new InputStreamReader(System.in));
						String story_place = storyPlace.readLine();

						System.out.println("Now tell us your story :) : ");
						BufferedReader descr = new BufferedReader(new InputStreamReader(System.in));
						String story_descr = descr.readLine();

						String sqlInsertStatement = "";

						try {
							Statement st2 = cn.createStatement();
							st2.executeUpdate(sqlInsertStatement);
							System.out.println("You have successfully inserted your story! Thank you " + first_name_add + " " + last_name_add);
						}
						catch (SQLException e){
							System.out.println("Query failed: " + e);
						}
						break;
					case 4:
						System.out.println("Quitting Program.");
						exit = true;
						break;
					default:
						System.out.println("Incorrect Option Selected, sorry!");
				}
			}
		catch (Exception e) {
			System.out.println("connection failed: " + e);
		}

		}
	}

	public void userExecuteOperation(String dbname, String userID, String password) {
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
				cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);
		
		int userChoice=0;
		boolean exit = false;

		String userOptionsText = " \n USER MENU: \n"
		+ "---------------------------------------------------------------- \n"
		+ "(Enter in the cooresponding number to the query you wish to run) \n"
		+ "---------------------------------------------------------------- \n"
		+ "1. Find all people in the database \n"
		+ "2. Find stories from a specific person \n"
		+ "3. Find stories from a specific country \n"
		+ "4. Find stories from a range of years/ a single year \n"
		+ "5. Find the story from the story title \n"
		+ "6. Quit the program \n"
		+ "---------------------------------------------------------------- \n";

		while(!exit){
			System.out.println(userOptionsText);
			System.out.print("Select Your Choice and Press Enter: ");
			BufferedReader inUserChoice = new BufferedReader(new InputStreamReader(System.in));
			try{
				userChoice = Integer.parseInt(inUserChoice.readLine()); //user's query choice
				// System.out.println("Great choice! " + userChoice);
			} catch (Exception e){
				userChoice=0;
				System.out.println("!!! Please make sure to enter one of the options listed !!!");
			}

			// try {
				// Class.forName("com.mysql.jdbc.Driver").newInstance();
				// cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbname, userID, password);
				
				switch(userChoice) {
					case 1: 
						//gets everyone from the database
						System.out.println("Option 1 Selected! \n");
						String sqlQuery1 = "SELECT first_name, last_name FROM person";
						try {
							Statement st1 = cn.createStatement();
							ResultSet persons = st1.executeQuery(sqlQuery1);
							System.out.println("Here is the list of people who have stories in the database:");
							System.out.println("------------------------------------------------------------");
							while (persons.next()) {
								System.out.println(persons.getString(1) + " " + persons.getString(2));
							}
						}
						catch (SQLException e) {
							System.out.println("Query user: get everyone failed: " + e);
						}
						break;
					case 2: 
						System.out.println("Option 2 Selected");
						System.out.print("Please Enter the First Name:");
						BufferedReader firstName = new BufferedReader(new InputStreamReader(System.in));
						String first_Name = firstName.readLine();

						System.out.print("Please Enter the Last Name:");
						BufferedReader lastName = new BufferedReader(new InputStreamReader(System.in));
						String last_Name = lastName.readLine();

						String sqlQuery2 = "SELECT title, post_date, description FROM story NATURAL JOIN person_story NATURAL JOIN person WHERE first_name = " 
							+ "'" + first_Name + "'" 
							+ " AND last_name = " + "'" + last_Name + "';";
						try {
							Statement st2 = cn.createStatement();
							ResultSet stories = st2.executeQuery(sqlQuery2);
							System.out.println("__________________________");
							System.out.print(">>>{TITLE} | {POST_DATE}\n"
																								+"~~~~~~~~~~~~~~~~~~~~~~~~~\n"
																								+"{DESCRIPTION}\n");
							System.out.println("__________________________");
							while (stories.next()) {
								System.out.print("\n>>>");
								System.out.println(stories.getString(1) + " | " + stories.getString(2));
								System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
								System.out.println(stories.getString(3));
							}
						}
						catch (SQLException e) {
							System.out.println("Query user: stories by person failed: " + e);
						}

						break;
					case 3: 
						System.out.println("Option 3 Selected");
						System.out.print("Please enter the country you would like stories from:");
						BufferedReader country_reader = new BufferedReader(new InputStreamReader(System.in));
						String country = country_reader.readLine();
						String sqlQuery3 = "SELECT title FROM story NATURAL JOIN story_location NATURAL JOIN location WHERE country = '" + country + "';";
						try {
							Statement st3 = cn.createStatement();
							ResultSet storiesCountry = st3.executeQuery(sqlQuery3);
							System.out.println("");
							System.out.print("{TITLE}\n" + "------------\n");
							while (storiesCountry.next()) {
								System.out.println(storiesCountry.getString(1));
							}
							System.out.print("\n(You can read the story by selecting option 5 from menu)");
						}
						catch (SQLException e) {
							System.out.println("Query user: story by country failed: " + e);
						}

						break;
					case 4: 
						System.out.println("Option 4 Selected");
						System.out.println("(Input the same year twice to find stories from a specific year)");
						
						System.out.print("\nPlease Enter the Lower Bound on the Year Range: ");
						BufferedReader lowerBound = new BufferedReader(new InputStreamReader(System.in));
						String lowerBoundStr = lowerBound.readLine();
						// System.out.println(lowerBoundStr);

						System.out.print("Please Enter the Upper Bound on the Year Range: ");
						BufferedReader upperBound = new BufferedReader(new InputStreamReader(System.in));
						String upperBoundStr = upperBound.readLine();
						// System.out.println(upperBoundStr);

						String sqlQuery4 = "SELECT first_name, last_name, title, year FROM "
						+ "person NATURAL JOIN person_story NATURAL JOIN (story NATURAL JOIN story_date NATURAL JOIN date) "
						+ "WHERE year >= "+ "'" + lowerBoundStr + "'" + " AND year <= " + "'" + upperBoundStr + "';";
						try {
							// System.out.println(sqlQuery4);
							Statement st4 = cn.createStatement();
							ResultSet storiesByYear = st4.executeQuery(sqlQuery4);
							System.out.print("\n{FIRST_NAME LAST_NAME} | {TITLE} | {YEAR}\n"
																								+"------------------------------------------\n");
							while (storiesByYear.next()) {
								System.out.println(storiesByYear.getString(1) +" "+ storiesByYear.getString(2) + " | " + storiesByYear.getString(3) + " | " + storiesByYear.getString(4));
							}
							System.out.print("\n(You can read the story by selecting option 5 from menu)\n");

						}
						catch (SQLException e) {
							System.out.println("Query user: stories by years failed: " + e);
						}

						break;
					case 5:
						System.out.println("Option 5 Selected");
						System.out.print("Please Enter the Title of the Story you Want to Read: ");
						BufferedReader story_reader = new BufferedReader(new InputStreamReader(System.in));
						String story = story_reader.readLine();
						String sqlQuery5 = "SELECT title, post_date, description FROM story WHERE title = '" + story + "'; ";
						try {
							Statement st5 = cn.createStatement();
							ResultSet storiesByTitle = st5.executeQuery(sqlQuery5);
							System.out.println("__________________________");
							System.out.print(">>>{TITLE} | {POST_DATE}\n"
																								+"~~~~~~~~~~~~~~~~~~~~~~~~~\n"
																								+"{DESCRIPTION}\n");
							System.out.println("__________________________");
							while (storiesByTitle.next()) {
								System.out.print("\n>>>");
								System.out.println(storiesByTitle.getString(1) + " | " + storiesByTitle.getString(2));
								System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
								System.out.println(storiesByTitle.getString(3));
							}
						}
						catch (SQLException e) {
							System.out.println("Query user: stories by title failed: " + e);
						}
						break;
					case 6: 
						System.out.println("Quitting Program.");
						exit = true;
						break;

					default:
						System.out.println("\nIncorrect option selected, sorry!");
						System.out.println("We will bring you back to the User menu.\n");
				}
			// } 
			// catch (Exception e){
				// System.out.println("connection failed: " + e);
			// }	
			}
	}catch (Exception e) {
		System.out.println("connection failed: " +e);
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
				
				User userTmp = new User(dbname, userID, password);
				userTmp.adminExecuteOperation(dbname, userID, password);
			}

			else if (typeUser == 2 ) {
				User userTmp = new User(dbname, userID, password);
				userTmp.userExecuteOperation(dbname, userID, password);
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
