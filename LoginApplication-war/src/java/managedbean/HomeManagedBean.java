/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import ejb.entity.UserEntity;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import util.enumeration.UserRoleEnum;

/**
 *
 * @author rayta
 */
@Named(value = "homeManagedBean")
@SessionScoped
public class HomeManagedBean implements Serializable {

    /**
     * Creates a new instance of HomeManagedBean
     */
    private UserEntity user;

    public HomeManagedBean() {
    }

    @PostConstruct
    public void init() {
        user = (UserEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
            } catch (IOException ex) {
                Logger.getLogger(HomeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void logout(ActionEvent event) throws IOException {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();

        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isManager() {
        if (user != null) {
            return user.getUserRole().equals(util.enumeration.UserRoleEnum.MANAGER);
        } else {
            return false;
        }
    }

    public void goSecretPage(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/secret.xhtml");
    }
}
