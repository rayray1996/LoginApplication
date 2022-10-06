package managedbean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ejb.entity.UserEntity;
import ejb.stateless.UserSessionBeanLocal;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author rayta
 */
@Named(value = "indexManagedBean")
@ViewScoped
public class IndexManagedBean implements Serializable {
    
    @EJB
    private UserSessionBeanLocal userSessionBean;

    private String username;
    
    private String password;
    
    private UserEntity user;

    public IndexManagedBean() {
    }
    
    public void login(ActionEvent event) throws IOException {
        try {
            UserEntity user = userSessionBean.loginUser(username, password);
            if (user != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/home.xhtml");
            }
        } catch (InvalidLoginCredentialException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), null));
        }
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
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser(UserEntity user) {
        this.user = user;
    }
    
    public void goRegister(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/create-account.xhtml");
        
    }
}
