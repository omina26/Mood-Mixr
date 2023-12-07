package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroupPlaylistCreatedView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Group Playlist Created";
    public final GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel;
    public final ViewManagerModel viewManagerModel;

    public JButton returnToLoggedIn;
    public GroupPlaylistCreatedView(GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel,
                                    ViewManagerModel viewManagerModel){
        this.groupPlaylistCreatedViewModel = groupPlaylistCreatedViewModel;
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel("Playlist Created and Added to Spotify!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel = new JPanel();


        returnToLoggedIn = new JButton("Return to Logged In Screen");
        returnToLoggedIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(returnToLoggedIn);

        returnToLoggedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("logged in");
                viewManagerModel.firePropertyChanged();
            }
        });
        panel.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
