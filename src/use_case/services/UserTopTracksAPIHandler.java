package use_case.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class UserTopTracksAPIHandler implements UserTopTracksAPIHandlerInterface{

    public List<String> getUserTopTracks(String accessToken) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "https://api.spotify.com/v1/me/top/tracks?limit=5";
        // Create an HTTP request with the Authorization header
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + accessToken)
                .build();


        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<String> uris = null;

        // Check if the request was successful (status code 200)
        if (response.statusCode() == 200) {
            System.out.println(response.body());
            // Parse the response JSON to extract the list of URIs
            uris = extractUrisFromResponse(response.body());

            // Print the list of URIs
            System.out.println("List of User's Top Track URIs:");
            for (String uri : uris) {
                System.out.println(uri);
            }
        } else {
            System.out.println("Error: " + response.statusCode());
            System.out.println(response.body());
        }

        return uris;
    }

    private static List<String> extractUrisFromResponse(String jsonResponse) {
        List<String> uris = new ArrayList<>();

        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
            JsonObject root = jsonReader.readObject();
            JsonArray items = root.getJsonArray("items");

            for (JsonObject item : items.getValuesAs(JsonObject.class)) {
                String uri = item.getString("uri");
                uris.add(uri);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return uris;
    }
}
