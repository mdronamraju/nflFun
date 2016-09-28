package org.dronamraju.nfl.view;

/**
 * Created by mdronamr on 9/21/16.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.Car;
import org.dronamraju.nfl.model.Game;
import org.dronamraju.nfl.model.User;
import org.dronamraju.nfl.service.CarService;
import org.dronamraju.nfl.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
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

    private Map<String, Document> userGamesMap;

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

    public Map<String, Document> getUserGamesMap() {
        return userGamesMap;
    }

    public void setUserGamesMap(Map<String, Document> userGamesMap) {
        this.userGamesMap = userGamesMap;
    }

    public void saveScores() {
        try {
            List<Document> userScoreDocuments = new ArrayList<>();
            Map<String, String[]> paramValueMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
            log.info("paramValueMap: " + paramValueMap);
            for (int i=0; i<=games.size(); i++) {
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:teamAName_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:teamBName_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:date_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:time_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:location_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:teamAScore_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:teamBScore_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:winningTeam_" + i)[0]);
                log.info("paramValue for " + i + ": " + paramValueMap.get("gamesForm:teamsTotalScore_" + i)[0]);

                Document userScoreDocument = new Document();
                userScoreDocument.append("email", Util.getLoggedInUser().getUserName())
                        .append("teamAName_" + i, paramValueMap.get("teamAName_" + i)[0])
                        .append("teamBName_" + i, paramValueMap.get("teamBName_" + i)[0])
                        .append("date_" + i, paramValueMap.get("date_" + i)[0])
                        .append("time_" + i, paramValueMap.get("time_" + i)[0])
                        .append("location_" + i, paramValueMap.get("location_" + i)[0])
                        .append("teamAScore_" + i, paramValueMap.get("teamAScore_" + i)[0])
                        .append("teamBScore_" + i, paramValueMap.get("teamBScore_" + i)[0])
                        .append("winningTeam_", paramValueMap.get("winningTeam_" + i)[0])
                        .append("teamsTotalScore_", paramValueMap.get("teamsTotalScore_" + i)[0]);
                userScoreDocuments.add(userScoreDocument);
            }
            /*

            String[] paramValues = paramValueMap.get("gamesForm:teamAName_" + i);
            String value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:teamBName_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:date_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:time_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:location_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:teamAScore_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:teamBScore_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:teamBScore_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:winningTeam_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            paramValues = paramValueMap.get("gamesForm:teamsTotalScore_" + i);
            value = paramValues[0];
            log.info(" value: " + value);

            */

            /*
            Document gameDocument = new Document("teamAName_" + i, paramValueMap.get("gamesForm:teamAName_" + i)[0])
                    .append("teamBName_" + i, paramValueMap.get("gamesForm:teamBName_" + i)[0])
                    .append("date_" + i, paramValueMap.get("gamesForm:date_" + i)[0])
                    .append("time_" + i, paramValueMap.get("gamesForm:time_" + i)[0])
                    .append("location_" + i, paramValueMap.get("gamesForm:location_" + i)[0])
                    .append("teamAScore_" + i, paramValueMap.get("gamesForm:teamAScore_" + i)[0])
                    .append("teamBScore_" + i, paramValueMap.get("gamesForm:teamBScore_" + i)[0])
                    .append("winningTeam_" + i, paramValueMap.get("gamesForm:winningTeam_" + i)[0])
                    .append("teamsTotalScore_" + i, paramValueMap.get("gamesForm:teamsTotalScore_" + i)[0]);
            log.info("gameDocument: " + gameDocument);
            documents.add(gameDocument);
            */

            mongoDBDAO.saveScores(userScoreDocuments);

            FacesContext.getCurrentInstance().getExternalContext().redirect("games.xhtml");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}