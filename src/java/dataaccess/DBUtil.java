/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

//import java.io.IOException;
//import models.User;
//import services.UserService;
//import dataaccess.UserDB;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 *
 * @author TyHalvorsen
 */
public class DBUtil {
    //close prepared statment safely
    public static void closePreparedStatement(Statement preparedStatement) {
        try {
            //check if the prepared statment is not null
            if (preparedStatement != null) {
                //close the PreparedStatement
                preparedStatement.close();
            }
        } catch (SQLException e) {
            //print SQLException error to console
            System.out.println(e);
            System.err.println("SQLException error: " + e.getMessage());
        }
    }
    //close resultset
    public static void closeResultSet(ResultSet resultSet) {
        try {
            //check if the result is not null
            if (resultSet != null) {
                //close result
                resultSet.close();
            }
        } catch (SQLException e) {
            //if error print
            System.out.println(e);
            System.err.println("error closing result: " + e.getMessage());
        }
    }
}
