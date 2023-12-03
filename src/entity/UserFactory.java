package entity;

/**
 * Factory class for creating User objects.
 */

public class UserFactory {

    /**
     * Creates a new User object.
     *
     * @param name The name of the user.
     * @param token The token of the user.
     * @return A new User object.
     */
    public User create(String name, String token){return new User(name, token);}
}
