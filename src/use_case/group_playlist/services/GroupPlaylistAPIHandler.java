package use_case.group_playlist.services;

import entity.Playlist;
import entity.User;

import java.util.ArrayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class GroupPlaylistAPIHandler implements GroupPlaylistAPIInterface{
    @Override
    public ArrayList<Playlist> getPlaylists(User self) {
        String url = "https://api.spotify.com/v1/me/playlists";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).header("Authorization",
                "Bearer " + self.getToken()).GET().build();
        try{
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());
            return formatAPICallReturn(httpResponse.body());
        }catch(InterruptedException| IOException exception){
            return new ArrayList<>();
        }
    }


    public ArrayList<Playlist> formatAPICallReturn(String response){
        return new ArrayList<>();
    }
}
