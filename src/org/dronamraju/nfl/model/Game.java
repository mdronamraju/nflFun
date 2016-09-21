package org.dronamraju.nfl.model;

/**
 * Created by mdronamr on 9/19/16.
 */
public class Game {
    private String id;
    String team1Name;
    String team2Name;
    String date;
    String time;
    String location;

    public Game() {
    }

    public Game(String id, String team1Name, String team2Name, String date, String time, String location) {
        this.id = id;
        this.team1Name = team1Name;
        this.team2Name = team2Name;
        this.date = date;
        this.time = time;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                "team1Name='" + team1Name + '\'' +
                ", team2Name='" + team2Name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}