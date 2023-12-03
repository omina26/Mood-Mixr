package view;

import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistViewModel;

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



    public GroupPlaylistView(GroupPlaylistController groupPlaylistController, GroupPlaylistViewModel groupPlaylistViewModel) {
        this.groupPlaylistController = groupPlaylistController;
        this.groupPlaylistViewModel = groupPlaylistViewModel;

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
