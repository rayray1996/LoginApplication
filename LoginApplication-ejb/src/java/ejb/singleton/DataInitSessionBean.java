/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.singleton;

import ejb.entity.UserEntity;
import ejb.stateless.UserSessionBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.enumeration.UserRoleEnum;
import util.exception.UserAlreadyExistException;
import util.exception.UserNotFoundException;

/**
 *
 * @author rayta
 */
@Singleton
@LocalBean
@Startup
public class DataInitSessionBean {

    @EJB
    private UserSessionBeanLocal userSessionBean;

    @PersistenceContext(unitName = "LoginApplication-ejbPU")
    private EntityManager em;

    @PostConstruct
    public void dataInit() {
        if (em.find(UserEntity.class, 1L) == null) {
            try {
                //continue to add new users
                userSessionBean.createNewUser("Ray", "rayray1996", "admin", "en", UserRoleEnum.USER);
                userSessionBean.createNewUser("RayM", "ray1996", "admin", "en", UserRoleEnum.MANAGER);
            } catch (UserAlreadyExistException ex) {
                return;
            }

        }
    }
}
