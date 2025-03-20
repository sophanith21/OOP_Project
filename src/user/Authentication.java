package user;

import exception.InvalidCredentialsException;
import exception.UserAlreadyExistsException;
import exception.UserNotFoundException;

interface Authentication {
    User login(String username, String password) throws InvalidCredentialsException;
    void logout(String username) throws UserNotFoundException;
    void register(User user) throws UserAlreadyExistsException;
}
