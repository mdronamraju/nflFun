package org.dronamraju.nfl.model;

import javax.faces.bean.ManagedProperty;
import java.io.Serializable;

/**
 * Created by mdronamr on 9/19/16.
 */
public class Game implements Serializable {
    private String team1Name;
    private String team2Name;
    private String date;
    private String time;
    private String location;
    private String team1Score;
    private String team2Score;
    private String winningTeam;
    private String teamsTotalScore;

    public Game() {
    }

    public Game(String team1Name, String team2Name, String date, String time, String location, String team1Score, String team2Score, String winningTeam, String teamsTotalScore) {
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.winningTeam = winningTeam;
        this.teamsTotalScore = teamsTotalScore;
    }

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
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

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
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
                "team1Name='" + team1Name + '\'' +
                ", team2Name='" + team2Name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}