package src.user;

import src.exception.DuplicateUserException;
import src.exception.InvalidCredentialsException;
import src.exception.InvalidUserDetailsException;
import src.exception.UserNotFoundException;

interface Authentication {
    void register (String username, String email, String password, String phone) throws InvalidUserDetailsException, DuplicateUserException;
    User login(String username, String password) throws UserNotFoundException, InvalidCredentialsException;
    boolean logout(String username);
}
