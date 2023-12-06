package use_case.analyze_playlist;

import entity.AnalyzedPlaylist;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface AnalyzePlaylistDataAccessInterface {

    public Map<String, AnalyzedPlaylist> getPlaylistID();

    public void save() throws IOException;

    Set<String> savePlaylistID(String name, String playlistID) throws IOException;
}
