/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.User;

/**
 *
 * @author TyHalvorsen
 */
public class UserService {

    private final UserDB userDB;
    
    public UserService() {
        // Initialize the UserDB instance for database interactions
        userDB = new UserDB();
    }

    // Retrieve a user by email from the database
    public User getUserByEmail(String email) throws Exception {
        return userDB.get(email);
    }
    
    // Retrieve a list of all users from the database
    public List<User> getAllUsers() throws Exception {
        return userDB.getAll();
    }

    // Insert a new user into the database
    public void insertUser(String email, Boolean active, String firstName, String lastName, String password) throws Exception {
        // Create a new User object with provided data
        User user = new User(email, active, firstName, lastName, password);
        // Call the UserDB insert method to add the user to the database
        userDB.insert(user);
    }

    // Update user information in the database
    public void updateUser(String email, Boolean active, String firstName, String lastName, String password) throws Exception {
        // Create a new User object with updated data
        User user = new User(email, active, firstName, lastName, password);
        // Call the UserDB update method to modify user information in the database
        userDB.update(user);
    }

    // Delete a user from the database
    public void deleteUser(String email) throws Exception {
        // Create a User object with the specified email
        User user = new User();
        user.setEmail(email);
        // Call the UserDB delete method to remove the user from the database
        userDB.delete(user);
    }

    public List<User> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(String emailIn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}