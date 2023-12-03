package use_case.create_playlist.services;

import java.util.Set;

public class GetRecommendationAPIHandler {
    public Set<String> getRecommendation(Set<String> seedTracks, double acousticness, double danceability, double energy, double instrumentalness, double liveness, double speechiness, double valence) {
        return seedTracks;
    }
}
