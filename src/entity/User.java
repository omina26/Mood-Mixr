package entity;

import java.util.List;

/**
 * Represents a user.
 */

public class User {

    public final String name;
    private String token;

    /**
     * Constructor for User.
     *
     * @param name User's name.
     * @param token User's token.
     */

    public User(String name, String token){ this.name = name; this.token = token;}

    /**
     * Gets the user's name.
     *
     * @return Name of the user.
     */
    public String getName(){return this.name;}

    /**
     * Gets the user's token.
     *
     * @return Token of the user.
     */
    public String getToken(){return this.token;}

    /**
     * Sets the user's token.
     *
     * @param token New token for the user.
     */
    public void setToken(String token){this.token = token;}
}
