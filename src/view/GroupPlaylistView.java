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
import java.io.IOException;

public class GroupPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Group Playlist";
    public final GroupPlaylistController groupPlaylistController;
    public final GroupPlaylistViewModel groupPlaylistViewModel;
    public LoggedInState loggedInState;

    final JButton getMyPlaylists;
    private User user;



    public GroupPlaylistView(GroupPlaylistController groupPlaylistController, GroupPlaylistViewModel groupPlaylistViewModel, LoggedInState loggedInState) {
        this.groupPlaylistController = groupPlaylistController;
        this.groupPlaylistViewModel = groupPlaylistViewModel;
        this.loggedInState = loggedInState;

        JPanel buttons = new JPanel();
        JLabel pageName = new JLabel("Group Playlist");
        pageName.setAlignmentX(Component.CENTER_ALIGNMENT);

        getMyPlaylists = new JButton("Get My Playlists");
        buttons.add(getMyPlaylists);
        getMyPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(loggedInState.getUser());
                try {
                    groupPlaylistController.getPlaylists(loggedInState.getUser());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(pageName);
        this.add(buttons);



    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
