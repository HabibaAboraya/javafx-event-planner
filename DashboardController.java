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
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Menna & Habiba
 */
public class DashboardController implements Initializable{


    @FXML
    public TableColumn editEventCol;

    @FXML
    public TableColumn deleteEventCol;

    @FXML
    public TableColumn eventDateCol;

    @FXML
    public TableColumn eventTimeCol;

    @FXML
    public TableColumn eventDescriptionCol;

    @FXML
    public DatePicker editEventDate;

    @FXML
    public TextField editEventTime;

    @FXML
    public TextField editEventDescription;

    @FXML
    public TableColumn attendeeEventNameCol;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button add_btn;

    @FXML
    private Button attendees_btn;

    @FXML
    private Button signout;

    @FXML
    private Button edit_btn;

    @FXML
    private Button events_btn;
    
    @FXML
    private Button feedbackBtn;

    @FXML
    private AnchorPane dashboard;

    @FXML
    private Label dashboard_na;

    @FXML
    private Label dashboard_ne;

    @FXML
    private AnchorPane attendeesForm;

    @FXML
    private TableView<Attendee> attendeesTable;

    @FXML
    private TableColumn<Attendee, Integer> attendeeIdCol;

    @FXML
    private TableColumn<Attendee, String> attendeeNameCol;
    
    @FXML
    private TableColumn<Attendee, String> attendeeEventIdCol;

    @FXML
    private TextField attendeeName;

    @FXML
    private ComboBox<String> attendeeEventName;

    @FXML
    private AnchorPane addEventForm;

    @FXML
    private TextField eventName;

    @FXML
    private TextField eventType;

    @FXML
    private TextField eventLocation;

    @FXML
    private DatePicker eventDate;

    @FXML
    private TextField eventTime;

    @FXML
    private TextField eventDescription;

    @FXML
    private AnchorPane editEventForm;

    @FXML
    private TextField editEventId;

    @FXML
    private TextField editEventName;

    @FXML
    private TextField editEventType;

    @FXML
    private TextField editEventLocation;

    @FXML
    private AnchorPane eventsForm;

    @FXML
    private TableView<Event> eventsTableview;

    @FXML
    private TableColumn<Event, Integer> eventIdCol;

    @FXML
    private TableColumn<Event, String> eventNameCol;

    @FXML
    private TableColumn<Event, String> eventTypeCol;

    @FXML
    private TableColumn<Event, String> eventLocationCol;
    
    @FXML
    private AnchorPane feedbackForm;

    
    private Connection connection;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    
    /**
     * Adds a new event to the database.
     */
    public void eventAdd(){
        String sql = "INSERT INTO Event (event_name, event_date, event_time, event_location, event_description, event_type, user_id)" + "VALUES(?,?,?,?,?,?,?)";

        connection = DatabaseManager.connectDB();
        
        try{
            prepare = connection.prepareStatement(sql);
            prepare.setString(1, eventName.getText());
            prepare.setString(2, eventDate.getValue().toString());
            prepare.setString(3, eventTime.getText());
            prepare.setString(4, eventLocation.getText());
            prepare.setString(5, eventDescription.getText());
            prepare.setString(6, eventType.getText());
            prepare.setInt(7, EventPlanner.data.getUserId());

            
            Alert alert;
            
            if(eventName.getText().isEmpty() || eventLocation.getText().isEmpty() || eventType.getText().isEmpty() || eventDate.getValue() == null || eventTime.getText().isEmpty() || eventDescription.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank field");
                alert.showAndWait();
            }
            else
            {
                // check if the event time is in the format of TIME for MySql database table
                if(!eventTime.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                    alert = new Alert( Alert.AlertType.ERROR );
                    alert.setTitle( "Error Message" );
                    alert.setHeaderText( null );
                    alert.setContentText( "Please enter the time in the format of HH:MM" );
                    alert.showAndWait();

                } else {

                    prepare.executeUpdate();

                    alert = new Alert( Alert.AlertType.INFORMATION );
                    alert.setTitle( "Information Message" );
                    alert.setHeaderText( null );
                    alert.setContentText( "Successfully Added" );
                    alert.showAndWait();
                    eventsShowData();
                }

            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Retrieves the list of events from the database.
     * @return ObservableList of Events.
     */
    public ObservableList<Event> eventsListData(){
        ObservableList<Event> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Event WHERE user_id = "+EventPlanner.data.getUserId()+";";
        connection = DatabaseManager.connectDB();
        try{
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            
            Event event;
            
            while(result.next()){
                event = new Event(result.getInt("event_id"), result.getString("event_name"), result.getString("event_location"), result.getString("event_type"), result.getString("event_date"), result.getString("event_time"), result.getString("event_description"), result.getInt("user_id"));
                
                listData.add(event);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }


    /**
     * Displays the list of events in the TableView.
     */
    public void eventsShowData() {
        ObservableList<Event> eventList = eventsListData();

        eventIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        eventDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        eventDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        eventTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));


        // Create buttons for 'Edit' and 'Delete'
        editEventCol.setCellFactory(col -> {
            TableCell<Event, String> cell = new TableCell<Event, String>() {
                final Button editButton = new Button("Edit");
                final HBox hBox = new HBox(editButton);

                {
                    hBox.setAlignment( Pos.CENTER);
                    editButton.setOnAction(event -> {
                        // switch to the edit form
                        switchForm(new ActionEvent(edit_btn, null));

                        // open the edit form and fill the fields with the selected event data
                        Event toEdit = getTableView().getItems().get(getIndex());
                        editEventId.setText(String.valueOf(toEdit.getId()));
                        editEventName.setText(toEdit.getName());
                        editEventType.setText(toEdit.getType());
                        editEventLocation.setText(toEdit.getLocation());
                        editEventDate.getEditor().setText(toEdit.getDate());
                        editEventTime.setText(toEdit.getTime().substring( 0, 5 ));
                        editEventDescription.setText(toEdit.getDescription());
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hBox);
                    }
                }
            };
            return cell;
        });

        deleteEventCol.setCellFactory(col -> {
            TableCell<Event, String> cell = new TableCell<Event, String>() {
                final Button deleteButton = new Button("Delete");
                final HBox hBox = new HBox(deleteButton);

                {
                    hBox.setAlignment( Pos.CENTER);
                     deleteButton.setOnAction(event -> {
                         /* Your action code here */
                            Event toDelete = getTableView().getItems().get(getIndex());
                            // show a confirmation dialog
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Are you sure you want to DELETE Event ID: " + toDelete.getId() + "?");
                            Optional<ButtonType> option = alert.showAndWait();

                            if(option.get().equals(ButtonType.OK)){

                                // delete the event from the database
                                try{
                                    connection = DatabaseManager.connectDB();
                                    statement = connection.createStatement();

                                    // delete the event from the Attendee table
                                    String sqlAttendee = "DELETE FROM Attendee WHERE event_id = " + toDelete.getId() + ";";
                                    statement.executeUpdate(sqlAttendee);

                                    // delete the event from the Event table
                                    String sqlEvent = "DELETE FROM Event WHERE event_id = " + toDelete.getId() + ";";
                                    statement.executeUpdate(sqlEvent);

                                    // refresh the events list
                                    eventsShowData();
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                     });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(hBox);
                    }
                }
            };
            return cell;
        });

        eventsTableview.setItems(eventList);
    }

    
    
    /**
     * Populates the ComboBox with event names for attendee registration.
     */
    public void eventNameAttendee(){
        String sql = "SELECT event_id, event_name FROM event WHERE user_id = "+EventPlanner.data.getUserId()+";";
        connection = DatabaseManager.connectDB();
        
        try{
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            
            ObservableList listData = FXCollections.observableArrayList();
            
            while(result.next()){

                listData.add( result.getInt( "event_id" ) + " - " + result.getString( "event_name" ) );

            }
            
            attendeeEventName.setItems(listData);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * Updates an existing event in the database.
     */
    public void eventsEdit(){
        String sql = "UPDATE Event SET event_name = '" +
                editEventName.getText()+"', event_type = '"+
                editEventType.getText()+"', event_location = '"+
                editEventLocation.getText()+"', event_date = '"+
                editEventDate.getEditor().getText()+
                "', event_time = '"+ editEventTime.getText()+
                "', event_description = '"+
                editEventDescription.getText()+
                "' WHERE event_id = '"+ editEventId.getText()+"'";
        
        connection = DatabaseManager.connectDB();
        
        try{
            statement = connection.createStatement();
            
            Alert alert;
            
            if( editEventId.getText().isEmpty()
                    || editEventName.getText().isEmpty()
                    || editEventType.getText().isEmpty()
                    || editEventLocation.getText().isEmpty()
                    || editEventDate.getEditor().getText().isEmpty()
                    || editEventTime.getText().isEmpty()
                    || editEventDescription.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank field");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Event ID: " + editEventId.getText() + "?");
                
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){

                    if (!editEventTime.getText().matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Please enter the time in the format of HH:MM");
                        alert.showAndWait();

                    } else {

                        statement = connection.createStatement();
                        statement.executeUpdate( sql );

                        alert = new Alert( Alert.AlertType.INFORMATION );
                        alert.setTitle( "Information Message" );
                        alert.setHeaderText( null );
                        alert.setContentText( "Successfully Updated" );
                        alert.showAndWait();


                        eventsShowData();
                    }
                    
                }else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Adds a new attendee to the database.
    */
    public void attendeeAdd(){
        String sql = "INSERT INTO Attendee ( event_id, attendee_name) VALUES (?,?)";

        connection = DatabaseManager.connectDB();

        try{


            Alert alert;

            if(attendeeName.getText().isEmpty() || attendeeEventName.getSelectionModel().getSelectedItem() == null){



                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank field");
                alert.showAndWait();
            }
            else {

                prepare = connection.prepareStatement(sql);
                prepare.setInt(1, Integer.parseInt(attendeeEventName.getSelectionModel().getSelectedItem().split(" - ")[0]));
                prepare.setString(2, attendeeName.getText());
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added");
                alert.showAndWait();

                attendeesShowData();

            }

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Retrieves the list of attendees from the database.
     * @return ObservableList of Attendees.
    */
    public ObservableList<Attendee> attendeesListData(){
        ObservableList<Attendee> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM attendee JOIN event ON attendee.event_id = event.event_id WHERE event.user_id = "+EventPlanner.data.getUserId()+";";
        connection = DatabaseManager.connectDB();
        try{
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();
            
            Attendee attendee;
            
            while(result.next()){
                attendee = new Attendee(result.getInt("attendee_id"), result.getString("attendee_name"), result.getInt("event_id"), result.getString("event_name"));
                
                listData.add(attendee);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listData;
    }


    /**
     * Displays the list of attendees in the TableView.
     */
    public void attendeesShowData(){

        ObservableList<Attendee> attendeesList = attendeesListData();

        attendeeIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        attendeeNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        attendeeEventNameCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        attendeeEventIdCol.setCellValueFactory(new PropertyValueFactory<>("eventId"));
        attendeesTable.setItems( attendeesList );
    }
    
    
    /**
     * Updates the count of attendees in the dashboard.
    */
    public void dashboardNA(){
        String sql = "SELECT COUNT(attendee_id) FROM attendee WHERE event_id IN (SELECT event_id FROM Event WHERE user_id = "+EventPlanner.data.getUserId()+");";
        
        int na = 0;
        
        connection = DatabaseManager.connectDB();
        
        try{
            
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            
            if(result.next()){
                na = result.getInt("COUNT(attendee_id)");
            }
            
            dashboard_na.setText(String.valueOf(na));
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    /**
     * Updates the count of events in the dashboard.
    */
    public void dashboardNE(){
        String sql = "SELECT COUNT(event_id) FROM Event WHERE user_id = "+EventPlanner.data.getUserId()+";";
        
        int ne = 0;
        
        connection = DatabaseManager.connectDB();
        
        try{
            
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            
            if(result.next()){
                ne = result.getInt("COUNT(event_id)");
            }
            
            dashboard_ne.setText(String.valueOf(ne));
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private double x = 0;
    private double y = 0;
    
    
    /**
     * Logs out the user and navigates back to the login screen.
    */
    public void logout(){
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are You Sure You Want To Logout?");
            Optional<ButtonType> option = alert.showAndWait();
            
            if(option.get().equals(ButtonType.OK)){
                
                // Close the current window
                signout.getScene().getWindow().hide();
                
                // Load the login screen
                Parent root = FXMLLoader.load(getClass().getResource( "SignIn.fxml" ));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                // Enable dragging the window
                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(0.8f);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });
                
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard_btn){
            dashboard.setVisible(true);
            addEventForm.setVisible(false);
            attendeesForm.setVisible(false);
            editEventForm.setVisible(false);
            eventsForm.setVisible(false);
            feedbackForm.setVisible(false);
            dashboardNA();
            dashboardNE();
        }
        else if(event.getSource() == add_btn){
            dashboard.setVisible(false);
            addEventForm.setVisible(true);
            attendeesForm.setVisible(false);
            editEventForm.setVisible(false);
            eventsForm.setVisible(false);
            feedbackForm.setVisible(false);
            eventName.clear();
            eventType.clear();
            eventLocation.clear();
            eventDate.getEditor().clear();
            eventTime.clear();
            eventDescription.clear();
        }
        else if(event.getSource() == attendees_btn){
            dashboard.setVisible(false);
            addEventForm.setVisible(false);
            attendeesForm.setVisible(true);
            editEventForm.setVisible(false);
            eventsForm.setVisible(false);
            feedbackForm.setVisible(false);
            attendeeName.clear();
            attendeeEventName.getSelectionModel().clearSelection();
            eventNameAttendee();
            attendeesShowData();
        }
        else if(event.getSource() == events_btn){
            dashboard.setVisible(false);
            addEventForm.setVisible(false);
            attendeesForm.setVisible(false);
            editEventForm.setVisible(false);
            eventsForm.setVisible(true);
            feedbackForm.setVisible(false);
            eventsShowData();
        }
        else if(event.getSource() == edit_btn){
            dashboard.setVisible(false);
            addEventForm.setVisible(false);
            attendeesForm.setVisible(false);
            editEventForm.setVisible(true);
            eventsForm.setVisible(false);
            feedbackForm.setVisible(false);
            editEventId.clear();
            editEventDate.getEditor().clear();
            editEventTime.clear();
            editEventDescription.clear();
            editEventName.clear();
            editEventType.clear();
            editEventLocation.clear();
        }
        else if(event.getSource() == feedbackBtn){
            dashboard.setVisible(false);
            addEventForm.setVisible(false);
            attendeesForm.setVisible(false);
            editEventForm.setVisible(false);
            eventsForm.setVisible(false);
            feedbackForm.setVisible(true);
        }
    }
    
    public void displayUsername(){
        String user = EventPlanner.data.getUsername();
        user = user.substring(0,1).toUpperCase() + user.substring(1);
        username.setText(user);  
    }
    
    @FXML
    public void close(){
        System.exit(0);
    }
    
    @FXML
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    
    
    /**
     * Initializes the controller class.
     * @param location The location used to resolve relative paths for the root object.
     * @param resources The resources used to localize the root object.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        eventsShowData();
        eventNameAttendee();
        attendeesShowData();
        dashboardNA();
        dashboardNE();
    }
    
}
