package use_case.services;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;


public class CreatePlaylistAPIHandler implements CreatePlaylistAPIHandlerInterface {

    public void createPlaylist(String accessToken, String userId, String playlistName, List<String> recommendations) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String createPlaylistUrl = "https://api.spotify.com/v1/users/" + userId + "/playlists";
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
        if (response.statusCode() == 201) {
            System.out.println(response.body());
            // Parse the response JSON to extract the list of URIs
            playlistId = extractPlaylistIdFromResponse(response.body());

            // Print the created playlist's id
            System.out.println("Created Playlist ID: " + playlistId);

            addTracksToPlaylist(accessToken, playlistId, recommendations);

        } else {
            System.out.println("Error creating a playlist. Error: " + response.statusCode());
            System.out.println(response.body());
        }
    }

    private void addTracksToPlaylist(String accessToken, String playlistId, List<String> recommendations) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String addTracksUrl = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";
//        String recUris = recommendations.stream()
//                .map(uri -> URLEncoder.encode(uri, StandardCharsets.UTF_8))
//                .collect(Collectors.joining(","));
//        String addTracksUrl = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks?uris=" + recUris;
        // Create the json body
        String uriJsonArray = recommendations.stream()
                .map(uri -> "\"" + uri + "\"")
                .collect(Collectors.joining(","));
        String addTracksJsonBody = "{\"uris\":[" + uriJsonArray + "]}";

        // Create an HTTP request to add tracks to the playlist with the Authorization header, Content-Type header, and data
        HttpRequest addTracksRequest = HttpRequest.newBuilder()
                .uri(URI.create(addTracksUrl))
                .header("Authorization", "Bearer " + accessToken)
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(addTracksJsonBody))
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(addTracksRequest, HttpResponse.BodyHandlers.ofString());

        // Check if the request was successful (status code 200)
        if (response.statusCode() == 201) {
            System.out.println(response.body());

            // Print the success statement
            System.out.println("Recommendations added to playlist successfully");

        } else {
            System.out.println("Error adding tracks to playlist. Error: " + response.statusCode());
            System.out.println(response.body());
            }
        }

    private String extractPlaylistIdFromResponse(String jsonResponse) {
        String playlistId = null;

        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();

            playlistId = jsonObject.getString("id");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return playlistId;
    }
}
