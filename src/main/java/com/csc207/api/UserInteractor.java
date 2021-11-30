package com.csc207.api;

import com.csc207.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;

@Configuration
public class UserInteractor {
    @Autowired
    private final UserRepository userRepo;

    public UserInteractor(UserRepository userRepo){
        this.userRepo = userRepo;
    }


    /**
     * Check if the username and password match up in the database.
     *
     * @param username: the username input by the user
     * @param password: the password input by the user
     * @return whether they match up
     */
    public boolean checkSignIn(String username, String password){
        boolean existsUser = this.userRepo.existsUserByUsername(username);
        String expectedPassword = this.userRepo.getUserByUsername(username).get(0).getPassword();
        return existsUser && password.equals(expectedPassword);
    }

    /**
     * Get the id of a user.
     *
     * @param username: the Username of the user.
     */
    public long getUserIdByUsername(String username){
        return this.userRepo.getUserByUsername(username).get(0).getId();
    }

    public boolean isUsernameInDb(String username) {
        return this.userRepo.existsUserByUsername(username);
    }

    public User getUserFromUsername(String username) {
        return this.userRepo.getUserByUsername(username).get(0);
    }

    /**
     * Save this user to the database.
     *
     * @param user: the User.
     */
    @Transactional
    public void saveUser(User user) {
        this.userRepo.save(user);
    }

}