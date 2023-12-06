package entity;

public class UserFactory {
    public User create(String name, String token, String id){return new User(name, token, id);}
}
