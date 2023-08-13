/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.io.IOException;
import models.User;
import services.UserService;
import dataaccess.UserDB;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TyHalvorsen
 */
public class UserDB {
    
    
    //retrieve a list of all users from the database
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatment preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM user";
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Extract user data from result set
                String email = resultSet.getString(1);
                Boolean active = resultSet.getBoolean(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String password = resultSet.getString(5);
                
                //create a user object and add it to the list
                User user = new User(email, active, firstName, lastName, password);
                
            }
        } finally {
            //close resources 
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            connectionPool.freeConnection(connection);
            
        }
        return user;
    }
    //insert a new user into the database
    public void insert (User user) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO user (email, active, first_name, last_name, password) VALUES (?, ? ,?, ?, ?)";
        try {
            preparedStatement.connection.prepareStatement(sql);
            //set parameter values for the query
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getActive());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.executeUpdate();
        } finally {
            //close resources
            DBUtil.closePreparedStatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
    }
    
    //retrieve a user by email from the database
    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM user where email=?";
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                //extract user data from the result set
                Boolean active = resultSet.getBoolean(2);
                String firstName = resultSet.getString(3);
                String lastName = resultSet.getString(4);
                String password = resultSet.getString(5);
                
                //create a new user
                user = new User(email, active, firstName, lastName, password);
            }
        } finally {
            //close resources
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            connectionPool.freeConnection(connection);
            
        }
        return user;
    }
    //update user information in the database
    public void update (User user) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE user SET active=?, first_name=?, password=? WHERE email=?";
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            // set parameter values for the query 
            preparedStatement.setBoolean(1, user.getActive());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, getLastName());
            preparedStatement.setString(5, getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();
        } finally {
            //close resourse
            DBUtil.closePreparedStatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
    }
    
    //delete user from database
    public void delete(User user) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM user WHERE email=?";
        
        try {
            preparedStatement = connection.preparedStatement(sql);
            //set parameter value for the query
            preparedStatement.setString(1, user.getEmail());
            ps.executeUpdate();
        } finally {
            //close resources
            DBUtil.closePreparedStatement(preparedStatement);
            connectionPool.freeConnection(connection);
        }
    }
}
