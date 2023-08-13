/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;
/**
 *
 * @author TyHalvorsen
 */
public class RoleDB {

    // Retrieve a list of all users' roles from the database along with user information
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preaparedStatement = null;
        ResultSet resultSet = null;

        
        String sql = "SELECT u.*, r.role_id, r.role_name " +
                     "FROM user u " +
                     "INNER JOIN user_role ur ON u.email = ur.email " +
                     "INNER JOIN role r ON ur.role_id = r.role_id";

        try {
            preaparedStatement = connection.prepareStatement(sql);
            resultSet = preaparedStatement.executeQuery();
            while (resultSet.next()) {
                // Extract user and role data from the result set
                String email = resultSet.getString("email");
                Boolean active = resultSet.getBoolean("active");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String password = resultSet.getString("password");
                int roleId = resultSet.getInt("role_id");
                String roleName = resultSet.getString("role_name");

                // Create a User and Role object and add it to the list
                Role role = new Role(roleId, roleName);
                User user = new User(email, active, firstName, lastName, password, role);
                users.add(user);
            }
        } finally {
            // Close resources
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preaparedStatement);
            connectionPool.freeConnection(connection);
        }
        return users;
    }

    // Update user role in the database
    public void update(String userEmail, int newRoleId) throws Exception {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement preaparedStatement = null;
        String sql = "UPDATE user_role SET role_id = ? WHERE email = ?";

        try {
            preaparedStatement = connection.prepareStatement(sql);
            // Set parameter values for the query
            preaparedStatement.setInt(1, newRoleId);
            preaparedStatement.setString(2, userEmail);
            preaparedStatement.executeUpdate();
        } finally {
            // Close resources
            DBUtil.closePreparedStatement(preaparedStatement);
            connectionPool.freeConnection(connection);
        }
    }

}

