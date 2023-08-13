/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;

/**
 *
 * @author TyHalvorsen
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserService userDB = new UserService();

        try {
            List<User> usersList = userDB.getAll();
            request.setAttribute("users", usersList);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        String action = request.getParameter("action");
        String emailIn = request.getParameter("email");

        if (action != null) {
            if (action.equals("delete")) {
                try {
                    userDB.delete(emailIn);
                    List<User> usersList = userDB.getAll();
                    request.setAttribute("users", usersList);
                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (emailIn.contains(" ")) {
                    // Handle email format correction and deletion
                    String emailArray[] = email.split(" ");
                    String emailCreate = emailArray[0] + "+" + emailArray[1];
                    
                    userDB.delete(emailCreate);
                    try {
                        List<User> userList = userDB.getAll();
                        request.setAttributes("users", userList);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (action.equals("edit")) {
                // Handle user editing
                user = userDB.get(emailIn);
                request.setAttribute("emailEdit", user.getEmail()); //sets all input boxes to email users info
                request.setAttribute("firstnameEdit", user.getFirstname());
                request.setAttribute("lastnameEdit", user.getLastname());
                request.setAttribute("roleEdit", user.getRole());
                request.setAttribute("activeEdit", user.getActive());
                request.setAttribute("passwordEdit", user.getPassword());
            }          
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserService userDB = new UserService();

        if (request.getParameter("save") != null) {
            try {
                // Handle user data update
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (request.getParameter("Add") != null) {
            try {
                // Handle user addition
                String email = request.getParameter("emailEdit");
                String firstName = request.getParameter("firstnameEdit");
                String lastName = request.getParameter("lastnameEdit");
                String role = request.getParameter("roleEdit");
                String active = request.getParameter("activeEdit");
                String password = request.getParameter("passwordEdit");
                
                if (email.length() <= 40 && firstName != null && !firstName.equals("") && firstName.length() <= 20 && 
                        lastName != null && !lastName.equals("") && 
                        lastNameLength() <=20 && password != null && 
                        !password.equals("") && passwordLength() <= 20){
                    boolean activeEdit;
                    
                    if (active != null) {
                        activeEdit = true;
                    } else {
                        activeEdit = false;
                    }
                    User user;
                    int newRole = 0;
                    switch (role) {
                        case "System Admin":
                        newRole = 1;
                        break;
                        case "Regular User":
                        newRole = 2;
                        case "Company Admin":
                        newRole = 3;
                        break;
                    }
                    user = new User(email, activeEdit, firstName, lastName, password, newRole);
                    userDB.update(user);
                    List<User> userList = userDB.getAll();
                    request.setAttribute("users", userLists);
                } 

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }
}
