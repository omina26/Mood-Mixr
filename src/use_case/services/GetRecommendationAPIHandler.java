package use_case.services;

import use_case.services.GetRecommendationAPIHandlerInterface;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.nio.charset.StandardCharsets;



public class GetRecommendationAPIHandler implements GetRecommendationAPIHandlerInterface {
    public List<String> getRecommendation(String accessToken, List<String> seedTracks, double acousticness, double danceability, double energy, double instrumentalness, double liveness, double speechiness, double valence) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        String trackIdParam = seedTracks.stream()
                                        .map(trackId -> URLEncoder.encode(trackId, StandardCharsets.UTF_8))
                                        .collect(Collectors.joining(","));
        String apiUrl = "https://api.spotify.com/v1/recommendations?limit=10&seed_tracks=" + trackIdParam
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
            System.out.println("Error getting recommendations. Error: " + response.statusCode());
            System.out.println(response.body());
        }

        return recommendations;
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
