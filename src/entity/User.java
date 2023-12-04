package entity;

import java.util.List;

/**
 * Represents a user.
 */

public class User {

    public final String name;
    private String token;
    private final String userId;

    /**
     * Constructor for User.
     *
     * @param name User's name.
     * @param token User's token.
     */

    public User(String name, String token, String userId){
        this.name = name;
        this.token = token;
        this.userId = userId;
    }

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
     * Gets the user's user id.
     *
     * @return User id of the user.
     */
    public String getUserId(){return this.userId;}

    /**
     * Sets the user's token.
     *
     * @param token New token for the user.
     */
    public void setToken(String token){this.token = token;}
}
