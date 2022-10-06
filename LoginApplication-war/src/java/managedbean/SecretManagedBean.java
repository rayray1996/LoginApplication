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
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author rayta
 */
@Named(value = "secretManagedBean")
@SessionScoped
public class SecretManagedBean implements Serializable {

    /**
     * Creates a new instance of SecretManagedBean
     */
    UserEntity user;

    public SecretManagedBean() {
    }

    @PostConstruct
    public void init() {
        user = (UserEntity) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        if (user == null || !isManager(user)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/home.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(HomeManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void goHome(ActionEvent event) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/home.xhtml");
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    private boolean isManager(UserEntity userEntity) {
        return user.getUserRole().equals(util.enumeration.UserRoleEnum.MANAGER);
    }
}
