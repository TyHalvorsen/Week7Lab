/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author TyHalvorsen
 */
public class User {

    private String email;        // User's email
    private Boolean active;      // Whether the user is active
    private String firstname;    // User's first name
    private String lastname;     // User's last name
    private String password;     // User's password

    public User() {
        // Default constructor
    }

    // Constructor with parameters to initialize user data
    public User(String email, Boolean active, String firstname, String lastname, String password) {
        this.email = email;
        this.active = active;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    // Getter and setter methods for user properties

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}