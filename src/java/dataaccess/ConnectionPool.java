/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TyHalvorsen
 */
public class ConnectionPool {
    //instance of the connection pool
    private static ConnectionPool connectionPool = null;
    //datasource to manage connections
    private static DataSource dataSource = null;
    //private constructor to initialize the pool
    private ConnectionPool() {
        try {
            //create instance to perform JNDP lookup
            Context context = new InitialContext();
            //look up datasource 
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/userdb");
        } catch (NamingException e) {
            //Print error message if initialization fails
            System.err.println("Error initializing data source: " + e.getMessage());
        }
    }
    
    //get instance of the connection pool
    public static synchronized ConnectionPool getInstance() {
        if (connectionPool == null) {
            //if pool is null create instance 
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }
    
    //get a database connection from the connection pool
    public Connection getConnection() {
        try {
            //get connection from datasource
            return dataSource.getConnection();
        } catch (SQLException e) {
            //print error message if connection fails
            System.err.println("Error getting connection: " + e.getMessage());
            return null;
        }
    }
    
    //release database connection back to connection pool
    public void freeConnection(Connection connection) {
        if (connection != null) {
            try {
                //close the connection
                connection.close();
            } catch (SQLException e) {
                //print error message if closing the connection fails
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
    
}
