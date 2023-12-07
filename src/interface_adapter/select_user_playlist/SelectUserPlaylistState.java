package interface_adapter.select_user_playlist;

import entity.User;

import java.util.ArrayList;

public class SelectUserPlaylistState {

    ArrayList<String> allPlaylists;
    private User user;
    private Boolean userPlaylistsOnly;
    private String nonUserPlaylistID = "";

    private ArrayList<String> names;
    SelectUserPlaylistState(){
    }

    public ArrayList<String> getAllPlaylists() {
        return allPlaylists;
    }

    public void setAllPlaylists(ArrayList<String> allPlaylists){
        this.allPlaylists = allPlaylists;
    }

    public void setUserPlaylistsOnly(Boolean userPlaylistsOnly) {
        this.userPlaylistsOnly = userPlaylistsOnly;
    }
    public Boolean getUserPlaylistsOnly(){return this.userPlaylistsOnly;}

    public String getNonUserPlaylistID(){return this.nonUserPlaylistID;}

    public void setNonUserPlaylistID(String playlistID){this.nonUserPlaylistID = playlistID;}

    public void setUser(User user){this.user = user;}

    public User getUser() {return user;}

    public  void setNames(ArrayList<String> names){this.names = names;}

    public ArrayList<String> getNames(){return this.names;}
}
