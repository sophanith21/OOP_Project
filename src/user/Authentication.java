package src.user;

import src.exception.DuplicateUserException;
import src.exception.InvalidCredentialsException;
import src.exception.InvalidUserDetailsException;
import src.exception.UserNotFoundException;

interface Authentication {
    void register(String username, String password, String email, String phone, double walletBalance, String membershipLevel) throws InvalidUserDetailsException, DuplicateUserException;
    User login(String username, String password) throws UserNotFoundException, InvalidCredentialsException;
    boolean logout(User user);
}
