package org.dronamraju.nfl.view;

/**
 * Created by mdronamr on 9/21/16.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.Car;
import org.dronamraju.nfl.model.Game;
import org.dronamraju.nfl.model.User;
import org.dronamraju.nfl.service.CarService;
import org.dronamraju.nfl.util.Util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name= "gamesView")
@SessionScoped
public class GamesView implements Serializable {
    private static Log log = LogFactory.getLog(GamesView.class);

    private List<Game> games;

    private MongoDBDAO mongoDBDAO = new MongoDBDAO();

    @PostConstruct
    public void init() {
        games = mongoDBDAO.readAllGames();
    }


    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public void saveScores() {
        try {
            Map<String, String[]> paramValueMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            mongoDBDAO.saveScores(Util.getLoggedInUser(), paramValueMap);
            Util.getHttpSession().setAttribute("userScores", mongoDBDAO.readUserGames(Util.getLoggedInUser().getUserName()));
            games = mongoDBDAO.readUserGames(Util.getLoggedInUser().getUserName());
            if (games == null || games.size() < 1) {
                games = mongoDBDAO.readAllGames();
            }
            log.info("games for games view: " + games);
            FacesContext.getCurrentInstance().getExternalContext().redirect("games.xhtml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}