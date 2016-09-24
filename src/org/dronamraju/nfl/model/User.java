package org.dronamraju.nfl.model;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by mdronamr on 9/14/16.
 */

@ManagedBean(name= "user")
@SessionScoped
public class User implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String totalPoints;
    private String availablePoints;
    private String password;
    private Boolean isAdmin;

    public User() {

    }

    public User(String firstName, String lastName, String userName, String totalPoints, String availablePoints, String password, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.totalPoints = totalPoints;
        this.availablePoints = availablePoints;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getAvailablePoints() {
        return availablePoints;
    }

    public void setAvailablePoints(String availablePoints) {
        this.availablePoints = availablePoints;
    }

    @Override
    public String toString() {
        return "User{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                ", availablePoints='" + availablePoints + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
