package org.dronamraju.nfl.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.util.Validator;

/**
 * Created by mdronamr on 9/14/16.
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(RegisterServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        log.info("getParameterMap: " + request.getParameterMap());
        StringBuilder errorsString = new StringBuilder();
        Boolean errors = false;
        if (firstName == null || firstName.trim().equals("")) {
            errorsString.append("Invalid First Name, ");
            errors = true;
        }
        if (lastName == null || lastName.trim().equals("")) {
            errorsString.append("Invalid Last Name, ");
            errors = true;
        }
        if (email == null || email.trim().equals("") || !Validator.validateEmail(email)) {
            errorsString.append("Invalid EMail, ");
            errors = true;
        }
        if (password == null || password.trim().equals("")
                || confirmPassword == null || confirmPassword.trim().equals("")
                || !(password.equals(confirmPassword))) {
            errorsString.append("Invalid Password or Confirmation Password");
            errors = true;
        }
        if (errors) {
            request.setAttribute("registrationErrors", errorsString.toString());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
        } else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            user.setTotalPoints("100");
            user.setAvailablePoints("100");
            if ("manudr@hotmail.com".equals(user.getEmail())) {
                user.setIsAdmin(true);
            } else {
                user.setIsAdmin(false);
            }
            MongoDBDAO mongoDBDAO = new MongoDBDAO();
            if (mongoDBDAO.findUser(user.getEmail()) != null) {
                request.setAttribute("registrationErrors", "User already exists with email: " + user.getEmail());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
                rd.forward(request, response);
            } else {
                mongoDBDAO.createUser(user);
                request.setAttribute("success", "User Added Successfully");
                List<User> users = mongoDBDAO.readAllUsers();
                request.setAttribute("users", users);
                log.info("users: " + users);
                request.setAttribute("registrationSuccess", "You have been registered. Try login.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
    }

}
