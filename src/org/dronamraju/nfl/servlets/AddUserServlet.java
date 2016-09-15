package org.dronamraju.nfl.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.model.Person;
import org.dronamraju.nfl.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.dronamraju.nfl.model.User;
import org.dronamraju.nfl.dao.MongoDBDAO;

/**
 * Created by mdronamr on 9/14/16.
 */

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(AddUserServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("lastName");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        log.info("getParameterMap: " + request.getParameterMap());

        if (firstName == null || firstName.trim().equals("")
                || lastName == null || lastName.trim().equals("")
                || email == null || email.trim().equals("")
                || password == null || password.trim().equals("")
                || confirmPassword == null || confirmPassword.trim().equals("")
                || !(password.equals(confirmPassword))) {
            request.setAttribute("error", "Missing required fields...");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/login.jsp");
            rd.forward(request, response);
        } else {
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);

            MongoDBDAO mongoDBDAO = new MongoDBDAO();
            mongoDBDAO.createUser(user);
            System.out.println("Person Added Successfully with id=" + user.getId());
            request.setAttribute("success", "Person Added Successfully");
            List<User> users = mongoDBDAO.readAllUsers();
            request.setAttribute("users", users);
            log.info("users: " + users);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/broncos.jsp");
            rd.forward(request, response);
        }
    }

}
