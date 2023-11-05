package entity;

public class User {

    public final String name;
    private String token;

    public User(String name, String token){ this.name = name; this.token = token;}

    public String getName(){return this.name;}

    public String getToken(){return this.token;}

    public void setToken(String token){this.token = token;}
}
