cisc437
=======

cisc437 db project. 

for reference: 

1. GIT PULL
2. CODE
3. GIT ADD --all
4. GIT COMMIT -am 'message goes here'
5. GIT PUSH ORIGIN MASTER
6. SCP to server 

=======

Welcome to our Travel Tales database!
We've put together a collection of user stories from around the world. Enjoy :)

To compile code: /usr/local/java1.6/bin/javac User.java
To run code: java -classpath .:/usr/local/mysql-connector-java/mysql-connector-java-5.1.18-bin.jar User

=======

Welcome to our Travel Tales database! 
We've put together a collection of user stories from around the world. Enjoy :)

There are two permissions for our database - user or administration. Based on the type of user you select yourself as, you will have access to a different set of commands for each mode. In the administration mode, your permissions are focused on being able to change records and delete records. In the user mode, your permissions are focused on performing queries on the dataset. There are no changes allowed to the database as a user. You can find details on the type of queries and actions available on the dataset below:

To compile code: /usr/local/java1.6/bin/javac User.java
To run code: java -classpath .:/usr/local/mysql-connector-java/mysql-connector-java-5.1.18-bin.jar User

You will be presented with the option to go into (1)Administration or (2)User mode.
In Administraiton mode, you have the choice to:
1. update a story title with a new title.
2. delete a person from the database
3. Quit the program

In User mode, you have the choice to:
1. Query for all persons in the database
2. Query stories by name
3. Find states from a country
4. Find stories based on a range of years
5. Quit the program
