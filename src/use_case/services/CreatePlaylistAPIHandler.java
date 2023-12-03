package use_case.services;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class CreatePlaylistAPIHandler implements CreatePlaylistAPIHandlerInterface {

    public void createPlaylist(String accessToken, String username, String playlistName, String recommendations) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String createPlaylistUrl = "https://api.spotify.com/v1/users/" + username + "/playlists";
        String createPlaylistJsonData = "{\"name\": \"" + playlistName + "\", \"public\": false}";
        // Create an HTTP request to create a playlist with the Authorization header, Content-Type header, and data
        HttpRequest createPlaylistRequest = HttpRequest.newBuilder()
                .uri(URI.create(createPlaylistUrl))
                .header("Authorization", "Bearer " + accessToken)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(createPlaylistJsonData, StandardCharsets.UTF_8))
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(createPlaylistRequest, HttpResponse.BodyHandlers.ofString());
        String playlistId = null;

        // Check if the request was successful (status code 200)
        if (response.statusCode() == 200) {
            System.out.println(response.body());
            // Parse the response JSON to extract the list of URIs
            playlistId = extractPlaylistIdFromResponse(response.body());

            // Print the list of URIs
            System.out.println("Playlist ID:");

            addTracksToPlaylist(accessToken, playlistId, recommendations);

        } else {
            System.out.println("Error: " + response.statusCode());
            System.out.println(response.body());
        }
    }

    private void addTracksToPlaylist(String accessToken, String playlistId, String recommendations) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String addTracksUrl = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks?uris=" + recommendations;
        String addTacksJsonData = "{\"uris\": [ \"string\" ] ";
        // Create an HTTP request to add tracks to the playlist with the Authorization header, Content-Type header, and data
        HttpRequest addTracksRequest = HttpRequest.newBuilder()
                .uri(URI.create(addTracksUrl))
                .header("Authorization", "Bearer " + accessToken)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(addTacksJsonData, StandardCharsets.UTF_8))
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(addTracksRequest, HttpResponse.BodyHandlers.ofString());

        // Check if the request was successful (status code 200)
        if (response.statusCode() == 200) {
            System.out.println(response.body());

            // Print the success statement
            System.out.println("Recommendations added to playlist successfully");

        } else {
            System.out.println("Error: " + response.statusCode());
            System.out.println(response.body());
            }
        }

    private String extractPlaylistIdFromResponse(String jsonResponse) {
        String playlistId = null;

        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
            JsonObject root = jsonReader.readObject();
            JsonObject id = root.getJsonObject("id");
            playlistId = id.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playlistId;
    }
}
