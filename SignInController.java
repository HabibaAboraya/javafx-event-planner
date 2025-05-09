/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventPlanner;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Menna
 */

public class SignInController implements Initializable {
    
    @FXML
    private AnchorPane main_form; // Root container of the login form

    @FXML
    private TextField username; // TextField for the username input

    @FXML
    private PasswordField password;  // PasswordField for the password input

    @FXML
    private Button signInBtn;  // Button to trigger the login process

    @FXML
    private Button signUpBtn;  // Button to trigger the sign up process
    
    // Database connection and query preparation objects
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    
    // Variables to store initial positions for window dragging
    private double x = 0;
    private double y = 0;
    
    
    /**
     * Handles the login process when the login button is clicked.
     * It validates the user inputs, checks the credentials against the database, and shows appropriate alerts.
     */
    @FXML
    public void signIn(){
        // SQL query to validate the username and password
        String sql = "SELECT * FROM User WHERE username = ? and password = ?";
        
        // Establish database connection
        connect = DatabaseManager.connectDB();
        
        try{
            // Prepare the SQL query with the provided username and password
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            // Execute the query and store the result
            result = prepare.executeQuery();
            
            Alert alert;
            
            // Check if the input fields are empty
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank field");
                alert.showAndWait();
            }else {
                // Check if the credentials are valid
                if(result.next()){
                    // Store the username in a static variable for further use
                    EventPlanner.data.setUsername( username.getText() );
                    EventPlanner.data.setUserId( result.getInt( "user_id" ) );
                    
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();
                    
                    // Hide the current window
                    signInBtn.getScene().getWindow().hide();
                    
                    // Load the dashboard scene
                    Parent root = FXMLLoader.load(getClass().getResource( "Dashboard.fxml" ));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    // Enable dragging for the dashboard window
                    root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                    });
                    
                    // Set the dashboard window style to TRANSPARENT
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    // Set the scene and show the dashboard stage
                    stage.setScene(scene);
                    stage.show();
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username or password");
                    alert.showAndWait();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void signUp (){
        // Establish database connection
        connect = DatabaseManager.connectDB();

        try{
            // Prepare the SQL query with the provided username and password
            String sql = "INSERT INTO User (username, password) VALUES (?, ?)";
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            // Execute the query and store the result
            prepare.executeUpdate();

            Alert alert;

            // Check if the input fields are empty
            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please Fill all blank field");
                alert.showAndWait();
            } else {
                EventPlanner.data.setUsername( username.getText() );

                // get the user id
                String sql2 = "SELECT user_id FROM User WHERE username = ? and password = ?";
                prepare = connect.prepareStatement(sql2);
                prepare.setString(1, username.getText());
                prepare.setString(2, password.getText());
                result = prepare.executeQuery();
                result.next();
                EventPlanner.data.setUserId(   result.getInt( "user_id" ) );


                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Sign Up!");
                alert.showAndWait();

                // Hide the current window
                signUpBtn.getScene().getWindow().hide();

                // Load the dashboard scene
                Parent root = FXMLLoader.load(getClass().getResource( "Dashboard.fxml" ));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                // Enable dragging for the dashboard window
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);
                });

                // Set the dashboard window style to TRANSPARENT
                stage.initStyle(StageStyle.TRANSPARENT);

                // Set the scene and show the dashboard stage
                stage.setScene(scene);
                stage.show();


            }
        }
        catch ( SQLException e){
            Alert alert;
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Username already exists");
            alert.showAndWait();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    
    /**
     * Closes the application when the close button is clicked.
     */
    @FXML
    public void close(){
        System.exit(0);
    }
    
    
    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     * 
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
