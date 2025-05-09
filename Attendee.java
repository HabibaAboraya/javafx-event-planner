/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventPlanner;

/**
 * The Attendee class represents an attendee of an event, with details such as ID, name, and the event they are attending.
 * It includes a constructor to initialize these properties and getter methods to access them.
 * 
 * @author Menna & Habiba
 */
public class Attendee {
    // Private fields to store attendee details
    private int id;          // Unique identifier for the attendee
    private String name;     // Name of the attendee
    private int eventId;  // id of the event the attendee is attending
    private String eventName; // name of the event the attendee is attending

    /**
     * Constructor to initialize an Attendee object with the given details.
     * 
     * @param id The unique identifier for the attendee.
     * @param name The name of the attendee.
     * @param eventId The name of the event the attendee is attending.
     */
    public Attendee( int id, String name, int eventId, String eventName ) {
        this.id = id;
        this.name = name;
        this.eventId = eventId;
        this.eventName = eventName;
    }

    /**
     * Gets the unique identifier of the attendee.
     * 
     * @return The attendee ID.
     */
    public int getId () {
        return id;
    }

    /**
     * Gets the name of the attendee.
     * 
     * @return The attendee name.
     */
    public String getName () {
        return name;
    }

    /**
     * Gets the id of the event the attendee is attending.
     * 
     * @return The event name.
     */
    public int getEventId () {
        return eventId;
    }

    /**
     * Gets the name of the event the attendee is attending.
     *
     * @return The event name.
     */
    public String getEventName (){
        return eventName;
    }

    /**
     * Sets the name of the attendee.
     *
     * @param name The new name of the attendee.
     */
    public void setName ( String name ){
        this.name = name;
    }

    /**
     * Sets the id of the event the attendee is attending.
     *
     * @param eventId The new id of the event the attendee is attending.
     */
    public void setEventId ( int eventId ){
        this.eventId = eventId;
    }

    /**
     * Sets the name of the event the attendee is attending.
     *
     * @param eventName The new name of the event the attendee is attending.
     */
    public void setEventName ( String eventName ){
        this.eventName = eventName;
    }

    /**
     * Returns a string representation of the Attendee object.
     *
     * @return The string representation of the Attendee object.
     */
    @Override
    public String toString () {
        return "Attendee{" + "id=" + id + ", name=" + name + ", eventId=" + eventId + ", eventName=" + eventName + '}';
    }
}
