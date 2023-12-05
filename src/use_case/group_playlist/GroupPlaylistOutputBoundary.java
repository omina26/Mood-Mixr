package use_case.group_playlist;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface GroupPlaylistOutputBoundary {
    ArrayList<String> getCurrentUserPlaylists(GroupPlaylistOutputData groupPlaylistOutputData);
}
