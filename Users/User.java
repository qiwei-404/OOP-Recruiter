package Users;

import StartUp.ManagingSystem;

/**
 * Represents a User in this System and is an Abstract class where the user must be either an Applicant, Referee, or HR.
 * They are identified by their username, password combination and userType, which is created when they sign up and
 * is used for log in. Users are kept track of in the StartUp.ManagingSystem and in the StartUp.DirectorySystem.
 */
public abstract class User {
    public String userType="";
    public String username;
    public String password;
    public ManagingSystem currentMS;

    /**
     * Constructor for User
     * @param username A String variable that sets the username for the User
     * @param password A String variable that sets the password for the User
     * @param currentMS The ManagingSystem that the User is part of
     */
    public User(String username, String password, ManagingSystem currentMS) {
        this.currentMS = currentMS;
        this.password = password;
        this.username = username;
    }

    /**
     * Sets the username of a User
     * @param username A String to set the username of the User to
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password of a User
     * @param password A String to set the password of the User to
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the username of a User
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the password of a User
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the ManagingSystem of a User
     */
    public ManagingSystem getCurrentMS() {
        return this.currentMS;
    }

    /**
     * Sets the current ManagingSystem of a User
     */
    public void setCurrentMS(ManagingSystem MS) {
        this.currentMS = MS;
    }



    /**
     * Returns the String Representation of the User
     * @return A String of the User's username
     */
    public String toString() {
        return this.getUsername();
    }

}
