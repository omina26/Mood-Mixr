package interface_adapter.analyzed_playlist;

import java.util.HashSet;
import java.util.Set;

public class AnalyzedPlaylistState {
    private Set<String> playlistAudioFeaturesList = new HashSet<String>();
    public AnalyzedPlaylistState(){
    }

    public AnalyzedPlaylistState(AnalyzedPlaylistState copy) {
        playlistAudioFeaturesList = copy.playlistAudioFeaturesList;
    }

    public Set<String> getPlaylistAudioFeaturesList(){
        return playlistAudioFeaturesList;
    }

    public void setPlaylistAudioFeaturesList(Set <String> playlistAudioFeaturesList) {
        this.playlistAudioFeaturesList = playlistAudioFeaturesList;
    }
}

