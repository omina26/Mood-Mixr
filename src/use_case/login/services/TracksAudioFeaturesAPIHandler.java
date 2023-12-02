package use_case.login.services;

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

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class TracksAudioFeaturesAPIHandler {
    public JsonObject getTracksAudioFeatures(List<String> trackIDs, String accessToken) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String trackIdsParam = String.join(",", trackIDs);
        String apiUrl = "https://api.spotify.com/v1/audio-features?ids=" + URLEncoder.encode(trackIdsParam, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", "Bearer " + accessToken)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            try (JsonReader jsonReader = Json.createReader(new StringReader(response.body()))) {
                return jsonReader.readObject();
            }
        } else {
            System.out.println("Error: " + response.statusCode());
            System.out.println(response.body());
            return Json.createObjectBuilder().build();
        }
    }
}
}
