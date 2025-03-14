package src.user;

import src.exception.InvalidCredentialsException;
import src.exception.UserAlreadyExistsException;
import src.exception.UserNotFoundException;

interface Authentication {
    User login(String username, String password) throws InvalidCredentialsException;
    void logout(String username) throws UserNotFoundException;
    void register(User user) throws UserAlreadyExistsException;
}
