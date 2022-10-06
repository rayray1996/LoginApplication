/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import ejb.entity.UserEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import util.enumeration.UserRoleEnum;
import util.exception.InvalidLoginCredentialException;
import util.exception.UserAlreadyExistException;
import util.exception.UserNotFoundException;

/**
 *
 * @author rayta
 */
@Stateless
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext(unitName = "LoginApplication-ejbPU")
    private EntityManager em;

    public UserSessionBean() {
    }

    @Override
    public UserEntity createNewUser(String name, String username, String password, String locale, UserRoleEnum role) throws UserAlreadyExistException {

        try {
            UserEntity user = getUser(username);
            if (user != null) {
                throw new UserAlreadyExistException("Username is already in use");
            }

        } catch (UserNotFoundException ex) {
            if (ex.getMessage().equals("User is not found")) {
                UserEntity newUser = new UserEntity(name, username, password, locale, role);
                em.persist(newUser);
                return newUser;
            } else {
                return null;
            }
        }

        return null;
    }

    @Override
    public UserEntity getUser(String username) throws UserNotFoundException {
        if (username != null) {
            Query query = em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username");
            query.setParameter("username", username);
            try {
                UserEntity user = (UserEntity) query.getSingleResult();
                return user;
            } catch (NoResultException ex) {
                throw new UserNotFoundException("User is not found");
            }
        } else {
            throw new UserNotFoundException("Username cannot be empty");
        }
    }

    @Override
    public UserEntity loginUser(String username, String password) throws InvalidLoginCredentialException {
        try {
            UserEntity user = getUser(username);
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new InvalidLoginCredentialException("Username or password is invalid");
            }
        } catch (UserNotFoundException ex) {
            throw new InvalidLoginCredentialException("Username or password is invalid");
        }
    }

    @Override
    public boolean isUserManager(String username) throws UserNotFoundException {
        UserEntity user = getUser(username);
        return (user.getUserRole() == UserRoleEnum.MANAGER);
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
