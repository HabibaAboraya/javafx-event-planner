/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventPlanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Menna & Habiba
 */
public class EventPlanner extends Application {
    
    // Variables to store the initial positions when mouse is pressed
    private double x = 0;
    private double y = 0;
    public static Data data = new Data();


    /**
     * The start method is the main entry point for all JavaFX applications.
     * It is called after the init method has returned, and after the system is ready for the application to begin running.
     * 
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception If an error occurs during loading the FXML resource.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML document
        Parent root = FXMLLoader.load(getClass().getResource( "SignIn.fxml" ));
        
        // Create a new scene with the loaded FXML document
        Scene scene = new Scene(root);
        
        // Handle mouse pressed event to record the initial position
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        // Handle mouse dragged event to move the stage accordingly
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            
            // Set the opacity to 0.8 while dragging for visual feedback
            stage.setOpacity(0.8f);
        });
        
        // Handle mouse released event to restore the opacity
        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });
        
        // Set the stage style to TRANSPARENT for a borderless window
        stage.initStyle(StageStyle.TRANSPARENT);
        
        // Set the scene for the stage
        stage.setScene(scene);
        // Show the stage
        stage.show();
    }

    /**
     * The main method is the entry point for the application.
     * It calls the launch method which in turn calls the start method.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
