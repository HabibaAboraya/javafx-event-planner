/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventPlanner;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * The database class handles the connection to the MySQL database.
 * It provides a static method to establish and return a database connection.
 * 
 * @author Menna & Habiba
 */
public class DatabaseManager extends DashboardController{
    
    /**
     * Establishes a connection to the MySQL database.
     * 
     * @return Connection object if the connection is successful, null otherwise.
     */
    public static Connection connectDB () {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establish the connection to the database
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/EventPlanner", "root", "");
            
            // Return the established connection
            return connect;
        } catch (Exception e) {
            // Print the stack trace for any exceptions encountered
            e.printStackTrace();
        }
        
        // Return null if the connection fails
        return null;
    }
}

