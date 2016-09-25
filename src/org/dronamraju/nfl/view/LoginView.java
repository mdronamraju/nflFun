package org.dronamraju.nfl.view;

/**
 * Created by mdronamr on 9/21/16.
 */

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.Game;
import org.dronamraju.nfl.model.User;
import org.dronamraju.nfl.util.Util;
import org.primefaces.context.RequestContext;

import java.util.List;
import java.util.Map;

@ManagedBean(name= "userLoginView")
@SessionScoped
public class LoginView {
    private static Log log = LogFactory.getLog(LoginView.class);

    private String userName;
    private String password;
    private List<Game> games;
    private User user;
    private List<Document> gamesList;

    private MongoDBDAO mongoDBDAO = new MongoDBDAO();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Document>  getGamesList() {
        return gamesList;
    }

    public void setGamesMap(List<Document>  gamesList) {
        this.gamesList = gamesList;
    }

    public void login(ActionEvent event) {
        log.info("login: " + userName + ", " + password);
        try {
            FacesMessage messages = null;
            if (userName == null || userName.trim().equals("")) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid User Name!", messages);
            }
            if (password == null || password.trim().equals("")) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Password!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid Password!", messages);
            }

            if (FacesContext.getCurrentInstance().getMessages() != null && FacesContext.getCurrentInstance().getMessages().hasNext()) {
                return;
            }
            log.info("Verifying login...");

            user = mongoDBDAO.findUser(userName, password);
            if (user != null) {
                log.info("User logged in successfully...");
                Util.getHttpSession().setAttribute("loggedInUser", user);
                //gamesMap = mongoDBDAO.getUserGamesMap(Util.getLoggedInUser().getUserName());
                if (gamesList == null || gamesList.size() < 1) {
                    gamesList = mongoDBDAO.getGamesList();
                }
                log.info("gamesList: " + gamesList);
                FacesContext.getCurrentInstance().getExternalContext().redirect("games.xhtml");
            } else {
                log.info("User not found...");
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null);
                FacesContext.getCurrentInstance().addMessage("Invalid credentials", messages);
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}