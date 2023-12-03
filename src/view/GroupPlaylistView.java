package view;

import entity.User;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.logged_in.LoggedInState;
import org.apache.commons.logging.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroupPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Group Playlist";
    public final GroupPlaylistController groupPlaylistController;
    public final GroupPlaylistViewModel groupPlaylistViewModel;
    public LoggedInState loggedInState;

    private User user;



    public GroupPlaylistView(GroupPlaylistController groupPlaylistController, GroupPlaylistViewModel groupPlaylistViewModel, LoggedInState loggedInState) {
        this.groupPlaylistController = groupPlaylistController;
        this.groupPlaylistViewModel = groupPlaylistViewModel;
        this.loggedInState = loggedInState;

        JLabel pageName = new JLabel("Group Playlist");
        pageName.setAlignmentX(Component.CENTER_ALIGNMENT);



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
