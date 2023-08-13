/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author TyHalvorsen
 */
public class RoleService {


    private final RoleDB roleDB;

    public RoleService() {
        // Initialize the RoleDB instance for database interactions
        roleDB = new RoleDB();
    }

    // Retrieve a role by ID from the database
    public Role getRoleByID(int roleID) throws Exception {
        return roleDB.get(roleID);
    }

    // Retrieve a list of all roles from the database
    public List<Role> getAllRoles() throws Exception {
        return roleDB.getAll();
    }

    // Insert a new role into the database
    public void insertRole(int roleID, String roleName) throws Exception {
        // Create a new Role object with provided data
        Role role = new Role(roleID, roleName);
        // Call the RoleDB insert method to add the role to the database
        roleDB.insert(role);
    }

    // Update role information in the database
    public void updateRole(int roleID, String roleName) throws Exception {
        // Create a new Role object with updated data
        Role role = new Role(roleID, roleName);
        // Call the RoleDB update method to modify role information in the database
        roleDB.update(role);
    }

    // Delete a role from the database
    public void deleteRole(int roleID) throws Exception {
        // Create a Role object with the specified role ID
        Role role = new Role();
        role.setRoleID(roleID);
        // Call the RoleDB delete method to remove the role from the database
        roleDB.delete(role);
    }
}
