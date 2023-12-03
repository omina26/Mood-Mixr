package use_case.login.services;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserPlaylistItemsAPIHandler implements use_case.login.services.UserPlaylistItemsAPIHandlerInterface {
    public List<String> getPlaylistItems(String accessToken, String playlistID) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "https://api.spotify.com/v1/playlists/" + playlistID + "/tracks";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<String> trackUris = null;

        if (response.statusCode() == 200) {
            trackUris = extractTrackUrisFromResponse(response.body());
        } else {
            System.out.println("Error: " + response.statusCode());
            System.out.println(response.body());
        }
        return trackUris;
    }

    private static List<String> extractTrackUrisFromResponse(String jsonResponse) {
        List<String> trackUris = new ArrayList<>();

        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
            JsonObject root = jsonReader.readObject();
            JsonArray items = root.getJsonArray("items");

            for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                JsonObject track = item.getJsonObject("track");
                String uri = track.getString("uri");
                trackUris.add(uri);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return trackUris;
    }
}
