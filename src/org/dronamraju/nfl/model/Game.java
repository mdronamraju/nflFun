package org.dronamraju.nfl.model;

import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * Created by mdronamr on 9/19/16.
 */
public class Game implements Serializable {
    private String teamAName;
    private String teamBName;
    private String date;
    private String time;
    private String location;
    private String teamAScore;
    private String teamBScore;
    private String winningTeam;
    private String teamsTotalScore;

    public Game() {
    }

    public Game(String teamAName, String teamBName, String date, String time, String location, String teamAScore, String teamBScore, String winningTeam, String teamsTotalScore) {
        this.teamAName = teamAName;
        this.teamBName = teamBName;
        this.date = date;
        this.time = time;
        this.location = location;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        this.winningTeam = winningTeam;
        this.teamsTotalScore = teamsTotalScore;
    }

    public String getTeamAName() {
        return teamAName;
    }

    public void setTeamAName(String teamAName) {
        this.teamAName = teamAName;
    }

    public String getTeamBName() {
        return teamBName;
    }

    public void setTeamBName(String teamBName) {
        this.teamBName = teamBName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeamAScore() {
        return teamAScore;
    }

    public void setTeamAScore(String teamAScore) {
        this.teamAScore = teamAScore;
    }

    public String getTeamBScore() {
        return teamBScore;
    }

    public void setTeamBScore(String teamBScore) {
        this.teamBScore = teamBScore;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public void setWinningTeam(String winningTeam) {
        this.winningTeam = winningTeam;
    }

    public String getTeamsTotalScore() {
        return teamsTotalScore;
    }

    public void setTeamsTotalScore(String teamsTotalScore) {
        this.teamsTotalScore = teamsTotalScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "teamAName='" + teamAName + '\'' +
                ", teamBName='" + teamBName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}