package use_case.group_playlist;

import entity.User;

public class GroupPlaylistInputData {
    User user;
    boolean self_only;

    public GroupPlaylistInputData(User user, Boolean selfOnly) {
        this.user = user;
        self_only = selfOnly;
    }


}
