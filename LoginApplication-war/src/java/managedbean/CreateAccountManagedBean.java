/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.entity.UserEntity;
import ejb.stateless.UserSessionBeanLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.PrimeFaces;
import util.enumeration.UserRoleEnum;
import util.exception.UserAlreadyExistException;

/**
 *
 * @author rayta
 */
@Named(value = "createAccountManagedBean")
@RequestScoped
public class CreateAccountManagedBean {

    @EJB
    private UserSessionBeanLocal userSessionBean;

    private String name;
    private String username;
    private String password;
    private String userRole;
    private String locale;

    public CreateAccountManagedBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void createAccount(ActionEvent event) throws InterruptedException, IOException {
        if (name.length() < 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must be more than 3 characters", null));
            return;
        } else if (username.length() < 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username must be more than 3 characters", null));
            return;
        } else if (password.length() < 3) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Password must be more than 3 characters", null));
            return;
        } else if (userRole.equals(" ")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must select at least a role", null));
            return;
        } else if (locale.equals(" ")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "You must select at least a locale", null));
            return;
        }

        UserRoleEnum role;
        if (userRole.equals("user")) {
            role = util.enumeration.UserRoleEnum.USER;
        } else {
            role = util.enumeration.UserRoleEnum.MANAGER;
        }

        UserEntity user = null;
        try {
            user = userSessionBean.createNewUser(name, username, password, name, role);

            if (user != null) {
                PrimeFaces.current().executeScript("PF('success').show();");
            }
        } catch (UserAlreadyExistException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username is already in use", null));
            return;
        }
    }

    public void goLoginPage(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
    }
}
