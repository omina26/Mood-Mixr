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

    public Map<String, Double> getAverageAudioFeatures() {
        return averageAudioFeatures;
    }

    public void setAverageAudioFeatures(Map<String, Double> averageAudioFeatures) {
        this.averageAudioFeatures.clear();
        for (Map.Entry<String, Double> entry : averageAudioFeatures.entrySet()) {
            this.averageAudioFeatures.put(entry.getKey(), entry.getValue() * 100);
        }
    }
}

