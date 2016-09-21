package org.dronamraju.nfl.servlets;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.Person;
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
import java.util.List;
import java.util.Map;

/**
 * Created by mdronamr on 9/13/16.
 */
@WebServlet("/addScore")
public class AddScoreServlet extends HttpServlet {
    private static Log log = LogFactory.getLog(AddScoreServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        MongoDBDAO mongoDBDAO = new MongoDBDAO();
        mongoDBDAO.dropScoreCollection();
        mongoDBDAO.saveScores(user, request.getParameterMap());

        Map<String, String[]> paramMap = request.getParameterMap();
        for (String key : paramMap.keySet()) {
            String[] paramValues = paramMap.get(key);
            log.info("Key: " + key + ", Value: " + paramValues[0]);
            String value = (paramValues[0] == null || paramValues[0].trim() == null || "null".equals(paramValues[0].trim())) ? "" : paramValues[0];
            request.setAttribute(key, value);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/broncos.jsp");
        rd.forward(request, response);
    }
}