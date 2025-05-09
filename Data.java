/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EventPlanner;

/**
 * The data class is used to store global variables that can be accessed across different classes in the application with the created instance in the Main class Event planner.
 * 
 * Currently, it holds multiple variables such as the username and user Id, which is used to keep track of the logged-in user.
 * 
 * @author Menna & Habiba
 */
public class Data{
    // variable to store the username of the logged-in user
    public String username;

    // variable to store the user ID of the logged-in user
    public int userId;

    public Data() {
        username = "";
        userId = 0;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void clearData() {
        username = "";
        userId = 0;
    }
}

