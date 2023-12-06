package interface_adapter.group_playlist_created;

import entity.User;

public class GroupPlaylistCreatedState {
    private String message;
    private User user;

    public GroupPlaylistCreatedState() {
    }
    public void setUser(User user){this.user = user;}
    public User getUser(){return this.user;}

    public void setMessage(String message){this.message = message;}
    public String getMessage(){return this.message;}

}
