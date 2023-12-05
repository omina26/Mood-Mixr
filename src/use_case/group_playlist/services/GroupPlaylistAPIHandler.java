package use_case.group_playlist.services;

import entity.Playlist;
import entity.User;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.List;

public class GroupPlaylistAPIHandler implements GroupPlaylistAPIInterface{
    @Override
    public ArrayList<String> getPlaylists(User self) {

        String url = "https://api.spotify.com/v1/me/playlists";
        String accessToken = self.getToken();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 200) {
            // Parse the response body to extract playlist information
            // You'll need to parse the JSON response to get playlist details
            // This is a placeholder for where you would add your JSON parsing logic
            System.out.println(response.body());
            ArrayList<String> href = new ArrayList<>();

            try (JsonReader jsonReader = Json.createReader(new StringReader(response.body()))) {
                JsonObject root = jsonReader.readObject();
                JsonArray items = root.getJsonArray("items");

                for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                    String uri = item.getString("href");
                    href.add(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return href;
        } else {
            // Handle non-200 status codes appropriately
            System.out.println("Error: " + response.statusCode() + " - " + response.body());
            return new ArrayList<>();
        }
    }

    @Override
    public boolean combinePlaylists(ArrayList<String> playlistsToCombine, User user) {
        String url;
        ArrayList<String> songs = new ArrayList<String>();
        String accessToken = user.getToken();
        for (String playlistLink : playlistsToCombine) {
            url = playlistLink + "/tracks";
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + accessToken)
                    .GET()
                    .build();
            HttpResponse<String> response = null;
            try {
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (response.statusCode() == 200) {
                // Parse the response body to extract playlist information
                // You'll need to parse the JSON response to get playlist details
                // This is a placeholder for where you would add your JSON parsing logic
                System.out.println(response.body());
                try (JsonReader jsonReader = Json.createReader(new StringReader(response.body()))) {
                    JsonObject root = jsonReader.readObject();
                    JsonArray items = root.getJsonArray("href");
                    System.out.println(items);
                    for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                        String href = item.getString("href");
                        songs.add(href);
                    }
                    System.out.println(songs);
                } catch (Exception e) {
                    return false;
                }
            } else {
                // Handle non-200 status codes appropriately
                System.out.println("Error: " + response.statusCode() + " - " + response.body());
                return false;
            }
        }
        return true;
    }

    private ArrayList<String> parsePlaylists(String responseBody) {
        // Implement JSON parsing here. The details will depend on the JSON library you choose to use.
        // For example, you might use org.json or com.google.gson to parse the JSON and extract playlist information.
        // This is a placeholder for JSON parsing logic.
        return new ArrayList<>();
    }


    public ArrayList<Playlist> formatAPICallReturn(String response){
        return new ArrayList<>();
    }
}
