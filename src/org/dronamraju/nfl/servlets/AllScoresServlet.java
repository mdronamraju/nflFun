package org.dronamraju.nfl.servlets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.dao.MongoDBDAO;
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
@WebServlet("/allScores")
public class AllScoresServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(RegisterServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}