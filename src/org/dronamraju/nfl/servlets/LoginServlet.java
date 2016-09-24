package org.dronamraju.nfl.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.Game;
import org.dronamraju.nfl.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by mdronamr on 9/15/16.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(LoginServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || email.trim().equals("") || password == null || password.trim().equals("")) {
            log.error("Missing required fields...");
            request.setAttribute("loginErrors", "Missing required fields...");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {
            MongoDBDAO mongoDBDAO = new MongoDBDAO();
            mongoDBDAO.dropGameCollection();
            List<Game> games = mongoDBDAO.readAllGames();
            request.getSession().setAttribute("games", games);
            request.setAttribute("games", games);
            User user = mongoDBDAO.findUser(email, password);
            if (user != null) {
                log.info("User logged in successfully...");
                request.setAttribute("user", user);
                request.getSession().setAttribute("user", user);
                List scoreList = mongoDBDAO.readAllScores();
                request.getSession().setAttribute("scoreList", scoreList);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/broncos.jsp");
                rd.forward(request, response);
            } else {
                log.info("User not found...");
                request.setAttribute("loginErrors", "User not found...");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
        }
    }
}