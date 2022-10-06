/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import ejb.entity.UserEntity;
import javax.ejb.Local;
import util.enumeration.UserRoleEnum;
import util.exception.InvalidLoginCredentialException;
import util.exception.UserAlreadyExistException;
import util.exception.UserNotFoundException;

/**
 *
 * @author rayta
 */
@Local
public interface UserSessionBeanLocal {

    public UserEntity createNewUser(String name, String username, String password, String locale, UserRoleEnum role) throws UserAlreadyExistException;

    public UserEntity getUser(String username) throws UserNotFoundException;

    public boolean isUserManager(String username) throws UserNotFoundException;

    public UserEntity loginUser(String username, String password) throws InvalidLoginCredentialException;

}
