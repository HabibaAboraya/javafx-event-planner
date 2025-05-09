/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventPlanner;

/**
 * The Events class represents an event with details such as ID, name, location, and type.
 * It includes a constructor to initialize these properties and getter methods to access them.
 * 
 * @author Menna & Habiba
 */
public class Event{
    // Private fields to store event details
    private int id;           // Unique identifier for the event
    private String name;      // Name of the event
    private String location;  // Location where the event is held
    private String type;      // Type or category of the event
    private String date;      // Date of the event
    private String time;      // Time of the event
    private String description; // Description of the event
    private int userId;            // User ID of the event creator

    /**
     * Constructor to initialize an Event object with the given details.
     * 
     * @param id The unique identifier for the event.
     * @param name The name of the event.
     * @param location The location of the event.
     * @param type The type or category of the event.
     */
    public Event ( int id, String name, String location, String type ) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
    }

    /**
     * Constructor to initialize an Event object with the all details.
     *
     * @param id The unique identifier for the event.
     * @param name The name of the event.
     * @param location The location of the event.
     * @param type The type or category of the event.
     * @param date The date of the event.
     * @param time The time of the event.
     * @param description The description of the event.
     * @param userId The user ID of the event creator.
     */
    public Event ( int id, String name, String location, String type, String date, String time, String description, int userId ) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.date = date;
        this.time = time;
        this.description = description;
        this.userId = userId;
    }


    /**
     * Gets the unique identifier of the event.
     * 
     * @return The event ID.
     */
    public int getId () {
        return id;
    }

    /**
     * Gets the name of the event.
     * 
     * @return The event name.
     */
    public String getName () {
        return name;
    }

    /**
     * Gets the location of the event.
     * 
     * @return The event location.
     */
    public String getLocation () {
        return location;
    }

    /**
     * Gets the type or category of the event.
     * 
     * @return The event type.
     */
    public String getType () {
        return type;
    }

    /**
     * Gets the date of the event.
     *
     * @return The event date.
     */
    public String getDate (){
        return date;
    }

    /**
     * Gets the time of the event.
     *
     * @return The event time.
     */
    public String getTime (){
        return time;
    }

    /**
     * Gets the description of the event.
     *
     * @return The event description.
     */
    public String getDescription (){
        return description;
    }

    /**
     * Gets the user ID of the event creator.
     *
     * @return The user ID.
     */
    public int getUserId(){
        return userId;
    }

    /**
     * Sets the name of the event.
     *
     * @param event_name The new name of the event.
     */
    public void setEventName(String event_name){
        this.name = event_name;
    }

    /**
     * Sets the location of the event.
     *
     * @param event_location The new location of the event.
     */
    public void setEventLocation(String event_location){
        this.location = event_location;
    }

    /**
     * Sets the type or category of the event.
     *
     * @param event_type The new type of the event.
     */
    public void setEventType(String event_type){
        this.type = event_type;
    }

    /**
     * Sets the date of the event.
     *
     * @param event_date The new date of the event.
     */
    public void setEventDate(String event_date){
        this.date = event_date;
    }

    /**
     * Sets the time of the event.
     *
     * @param event_time The new time of the event.
     */
    public void setEventTime(String event_time){
        this.time = event_time;
    }

    /**
     * Sets the description of the event.
     *
     * @param event_description The new description of the event.
     */
    public void setEventDescription(String event_description){
        this.description = event_description;
    }

    /**
     * Sets the user ID of the event creator.
     *
     * @param user_id The new user ID.
     */
    public void setUserId(int user_id){
        this.userId = user_id;
    }

    /**
     * Returns a string representation of the event object.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString(){
        return "Event ID: " + id + "\nEvent Name: " + name + "\nEvent Location: " + location + "\nEvent Type: " + type + "\nEvent Date: " + date + "\nEvent Time: " + time + "\nEvent Description: " + description + "\nUser ID: " + userId;
    }
}
