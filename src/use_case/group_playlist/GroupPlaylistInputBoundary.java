package use_case.group_playlist;

import java.io.IOException;

public interface GroupPlaylistInputBoundary {
    public void execute(GroupPlaylistInputData groupPlaylistInputData);

    public void getPlaylists(GroupPlaylistInputData inputData) throws IOException;


}
