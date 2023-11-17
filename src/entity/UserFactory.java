package entity;

public class UserFactory {
    public User create(String name, String token){return new User(name, token);}
}
