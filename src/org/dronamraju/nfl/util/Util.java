package org.dronamraju.nfl.util;

import com.mongodb.Mongo;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.User;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mdronamr on 9/19/16.
 */
public class Util {
    private static final String EMAIL_REGEX="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static Matcher matcher;
    private static Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    public Util() {
    }

    public static boolean validateEmail(String email){
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static HttpSession getHttpSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static User getLoggedInUser() {
        return (User)getHttpSession().getAttribute("loggedInUser");
    }

    public static void cleanDatabase() {
        MongoDBDAO mongoDBDAO = new MongoDBDAO();
        mongoDBDAO.dropScoreCollection();
        mongoDBDAO.dropGameCollection();
        mongoDBDAO.dropUserCollection();

        mongoDBDAO.addGames();
    }

    public static void main(String [] args) {
        cleanDatabase();
    }
}
