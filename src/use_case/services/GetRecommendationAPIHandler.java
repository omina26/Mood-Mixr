package use_case.services;

import use_case.services.GetRecommendationAPIHandlerInterface;

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
import java.util.Set;


public class GetRecommendationAPIHandler implements GetRecommendationAPIHandlerInterface {
    public String getRecommendation(String accessToken, String seedTracks, double acousticness, double danceability, double energy, double instrumentalness, double liveness, double speechiness, double valence) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        String apiUrl = "https://api.spotify.com/v1/recommendations?seed_tracks=" + seedTracks
                + "&target_acousticness=" + acousticness
                + "&target_danceability=" + danceability
                + "&target_energy=" + energy
                + "&target_instrumentalness=" + instrumentalness
                + "&target_liveness=" + liveness
                + "&target_speechiness=" + speechiness
                + "&target_valence=" + valence;
        // Create an HTTP request with the Authorization header
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        // Send the HTTP request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<String> recommendations = null;

        // Check if the request was successful (status code 200)
        if (response.statusCode() == 200) {
            System.out.println(response.body());
            // Parse the response JSON to extract the list of URIs
            recommendations = extractRecommendationsFromResponse(response.body());

            // Print the list of URIs
            System.out.println("List of Recommended Track URIs:");
            for (String uri : recommendations) {
                System.out.println(uri);
            }
        } else {
            System.out.println("Error: " + response.statusCode());
            System.out.println(response.body());
        }

        StringBuilder recommendationsUris = new StringBuilder();
        assert recommendations != null;
        for (String recommendation: recommendations) {
            recommendationsUris.append("spotify%3Atrack%3A").append(recommendation);
        }

        return recommendationsUris.toString();
    }

    private static List<String> extractRecommendationsFromResponse(String jsonResponse) {
        List<String> recommendations = new ArrayList<>();

        try (JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse))) {
            JsonObject root = jsonReader.readObject();
            JsonArray tracks = root.getJsonArray("tracks");


            for (JsonObject item : tracks.getValuesAs(JsonObject.class)) {
                String uri = item.getString("uri");
                recommendations.add(uri);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommendations;
    }
}
