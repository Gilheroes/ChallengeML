package com.challenge.ml.bsn;

import javax.servlet.http.HttpSession;

import com.challenge.ml.beans.UsersVO;
import com.challenge.ml.exception.InvalidDataException;
import com.challenge.ml.exception.NotFoundException;

public interface EnrolamientoBsn {
    /**
     * Method to save new user.
     *
     * @param usersVO User information.
     * @return UsersVo with the information.
     * @throws InvalidDataException If the information is not complete.
     */
    UsersVO saveNewUser(UsersVO usersVO) throws InvalidDataException;

    /**
     * Method to obtains token.
     *
     * @param userName Username.
     * @return token.
     */
    String getJWTToken(String userName);

    /**
     * Method to validate an user.
     *
     * @param userVO User information.
     * @return true if is a valid user, false otherwise.
     */
    boolean validateUser(UsersVO userVO);

    /**
     * Method to find user by name and password.
     *
     * @param usersVO     User information.
     * @param httpSession User session
     * @return User information.
     * @throws InvalidDataException If the information is not complete.
     * @throws NotFoundException    If there is not a user with the provided information.
     */
    UsersVO findByUserAndPwd(UsersVO usersVO, HttpSession httpSession) throws InvalidDataException, NotFoundException;

}
