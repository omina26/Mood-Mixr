package use_case.services;

import com.google.gson.Gson;
import entity.User;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class GroupPlaylistAPIHandler implements GroupPlaylistAPIInterface {
    @Override
    public ArrayList<ArrayList<String>> getPlaylists(User self) {

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
            ArrayList<String> href = new ArrayList<>();
            ArrayList<String> names = new ArrayList<>();
            try (JsonReader jsonReader = Json.createReader(new StringReader(response.body()))) {
                JsonObject root = jsonReader.readObject();
                JsonArray items = root.getJsonArray("items");

                for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                    String uri = item.getString("uri");
                    href.add(uri);
                    String name = item.getString("name");
                    names.add(name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ArrayList<ArrayList<String>> names_and_refs = new ArrayList<>();
            names_and_refs.add(href);
            names_and_refs.add(names);
            return names_and_refs;
        } else {
            // Handle non-200 status codes appropriately
            System.out.println("Error: " + response.statusCode() + " - " + response.body());
            return new ArrayList<>();
        }
    }

    @Override
    public Boolean combinePlaylists(ArrayList<String> playlists, User user) {
        ArrayList<String> songs = getTracks(playlists, user);
        String playlistID = createPlaylist(user);
        Gson gson = new Gson();
        String songUris = gson.toJson(songs);
        String url = "https://api.spotify.com/v1/playlists/" + playlistID + "/tracks";
        String json = "{\"uris\": "+ songUris +", \"position\": 0}";

        String accessToken = user.getToken();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response.body());
        if (response.statusCode() == 201) {
            System.out.println("True");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean isValidPlaylist(String playlistID, User user) {
        String accessToken = user.getToken();

        String url = "https://api.spotify.com/v1/playlists/" + playlistID + "/tracks";
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

        return response.statusCode() == 200;
    }

    public String createPlaylist(User user){

        String url = "https://api.spotify.com/v1/users/"+user.getUserId()+"/playlists";
        System.out.println(url);
        String accessToken = user.getToken();
        HttpClient httpClient = HttpClient.newHttpClient();
        String json = "{\"name\": \"Playlist!!!\", \"description\": \"created group playlist\", \"public\": true}";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json)) // Use the POST method and pass the JSON as a String
                .build();

        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 201) {
            // Parse the response body to extract playlist information
            // You'll need to parse the JSON response to get playlist details
            // This is a placeholder for where you would add your JSON parsing logic
            String playlistID = "";
            try (JsonReader jsonReader = Json.createReader(new StringReader(response.body()))){
                JsonObject values = jsonReader.readObject();
                playlistID = values.getString("id");

                return playlistID;
            } catch (Exception e) {
                e.printStackTrace();
                return "";

            }
        } else {
            System.out.println(response.statusCode());
            System.out.println(response.body());
            return "";
        }
    }


    @Override
    public ArrayList<String> getTracks(ArrayList<String> playlistsToCombine, User user) {
        String url;
        ArrayList<String> songIDs = new ArrayList<String>();
        String accessToken = user.getToken();
        for (String playlistLink : playlistsToCombine) {
            url = "https://api.spotify.com/v1/playlists/" + playlistLink.substring(17) + "/tracks";
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
                try (JsonReader jsonReader = Json.createReader(new StringReader(response.body()))) {
                    JsonObject root = jsonReader.readObject();
                    JsonArray items = root.getJsonArray("items");

                    for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                        JsonObject song = item.getJsonObject("track");
                        String uri = song.getString("uri");
                        songIDs.add(uri);
                    }

                } catch (Exception e) {
                    return new ArrayList<>();
                }
            } else {
                return new ArrayList<>();
            }
        }
        return songIDs;
    }



}
