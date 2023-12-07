package view;

import entity.User;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistViewModel;
import interface_adapter.logged_in.LoggedInState;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroupPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Group Playlist";
    public final GroupPlaylistController groupPlaylistController;
    public final GroupPlaylistViewModel groupPlaylistViewModel;
    public LoggedInState loggedInState;
    private final JButton getMyPlaylists;
    private User user;
    public final String other_playlist_id;
    public Boolean user_playlists;


    public GroupPlaylistView(GroupPlaylistController groupPlaylistController, GroupPlaylistViewModel groupPlaylistViewModel, LoggedInState loggedInState) {
        this.groupPlaylistController = groupPlaylistController;
        this.groupPlaylistViewModel = groupPlaylistViewModel;
        this.loggedInState = loggedInState;
        this.user_playlists = false;
        this.other_playlist_id = "";
        JPanel buttons = new JPanel();
        JPanel check_box = new JPanel();
        JLabel playlistIDFieldLabel = new JLabel("Enter the playlist ID of the playlist you'd like to group with");

        check_box.setLayout(new BoxLayout(check_box, BoxLayout.Y_AXIS));
        check_box.setAlignmentX(Component.CENTER_ALIGNMENT);
        check_box.setMaximumSize(new Dimension(1000, 90));


        JLabel pageName = new JLabel("Group Playlist");
        pageName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JCheckBox own_playlists = new JCheckBox("I only want to group my own playlists");
        check_box.add(own_playlists);

        check_box.add(playlistIDFieldLabel);
        JTextField playlist_id = new JTextField();
        playlist_id.setAlignmentX(Component.CENTER_ALIGNMENT);
        playlistIDFieldLabel.setLabelFor(playlist_id);
        check_box.add(playlist_id);



        own_playlists.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                user_playlists = !user_playlists;
                if(user_playlists){
                    playlist_id.setVisible(false);
                    playlistIDFieldLabel.setVisible(false);
                }
                else{
                    playlist_id.setVisible(true);
                    playlistIDFieldLabel.setVisible(true);
                }
            }
        });


        playlist_id.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {

            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.getMyPlaylists = new JButton("Next");
        buttons.add(getMyPlaylists);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        getMyPlaylists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                groupPlaylistController.getPlaylists(loggedInState.getUser(), user_playlists, playlist_id.getText());

            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(pageName);
        this.add(check_box);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
