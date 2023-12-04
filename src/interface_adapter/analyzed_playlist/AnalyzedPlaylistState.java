package interface_adapter.analyzed_playlist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnalyzedPlaylistState {
    private Set<String> playlistAudioFeaturesList = new HashSet<String>();
    private Map<String, Double> averageAudioFeatures = new HashMap<>();

    public AnalyzedPlaylistState(){
    }

    public AnalyzedPlaylistState(AnalyzedPlaylistState copy) {
        this.playlistAudioFeaturesList = new HashSet<>(copy.playlistAudioFeaturesList);
        this.averageAudioFeatures = new HashMap<>(copy.averageAudioFeatures);
    }

    public Set<String> getPlaylistAudioFeaturesList(){
        return playlistAudioFeaturesList;
    }

    public void setPlaylistAudioFeaturesList(Set <String> playlistAudioFeaturesList) {
        this.playlistAudioFeaturesList = playlistAudioFeaturesList;
    }

    public Map<String, Double> getAverageAudioFeatures() {
        return averageAudioFeatures;
    }

    public void setAverageAudioFeatures(Map<String, Double> averageAudioFeatures) {
        this.averageAudioFeatures = averageAudioFeatures;
    }
}

