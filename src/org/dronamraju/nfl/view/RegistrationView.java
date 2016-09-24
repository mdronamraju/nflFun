package org.dronamraju.nfl.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dronamraju.nfl.dao.MongoDBDAO;
import org.dronamraju.nfl.model.Game;
import org.dronamraju.nfl.model.User;
import org.dronamraju.nfl.util.Util;
import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.RequestDispatcher;
import java.util.List;

/**
 * Created by mdronamr on 9/24/16.
 */

@ManagedBean(name= "registrationView")
@RequestScoped
public class RegistrationView {
    private static Log log = LogFactory.getLog(RegistrationView.class);

    private String id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String confirmPassword;
    private String totalPoints;
    private String availablePoints;
    private Boolean isAdmin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    private MongoDBDAO mongoDBDAO = new MongoDBDAO();

    public void register(ActionEvent event) {
        log.info("register(): " + firstName + ", " + lastName + ", " + userName + ", " + password + ", " + confirmPassword + ", " + totalPoints);
        try {
            FacesMessage messages = null;
            if (firstName == null || firstName.trim().equals("")) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid First Name!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid First Name!", messages);
            }

            if (lastName == null || lastName.trim().equals("")) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Last Name!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid Last Name!", messages);
            }

            if (userName == null || userName.trim().equals("") || !Util.validateEmail(userName)) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid User Name!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid User Name!", messages);
            }
            if (password == null || password.trim().equals("")) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Password!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid Password!", messages);
            }

            if (confirmPassword == null || confirmPassword.trim().equals("")) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Confirm Password!", null);
                FacesContext.getCurrentInstance().addMessage("Invalid Confirm Password!", messages);
            }

            if (FacesContext.getCurrentInstance().getMessages() != null && FacesContext.getCurrentInstance().getMessages().hasNext()) {
                return;
            }

            if (!password.equals(confirmPassword)) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords do not match!", null);
                FacesContext.getCurrentInstance().addMessage("Passwords do not match!", messages);
            }

            if (FacesContext.getCurrentInstance().getMessages() != null && FacesContext.getCurrentInstance().getMessages().hasNext()) {
                return;
            }

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setUserName(userName);
            user.setPassword(password);
            user.setTotalPoints("100");
            user.setAvailablePoints("100");
            if ("manudr@hotmail.com".equals(user.getUserName())) {
                user.setIsAdmin(true);
            } else {
                user.setIsAdmin(false);
            }
            MongoDBDAO mongoDBDAO = new MongoDBDAO();
            if (mongoDBDAO.findUser(user.getUserName()) != null) {
                messages = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User already exists with email: " + user.getUserName(), null);
                FacesContext.getCurrentInstance().addMessage("User already exists with email: " + user.getUserName(), messages);
                return;
            } else {
                mongoDBDAO.createUser(user);
                messages = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Successful!", null);
                FacesContext.getCurrentInstance().addMessage("Registration Successful!", messages);
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}