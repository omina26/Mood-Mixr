package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist_created.GroupPlaylistCreatedViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroupPlaylistCreatedView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Group Playlist Created";
    public final GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel;
    public final ViewManagerModel viewManagerModel;

    public GroupPlaylistCreatedView(GroupPlaylistCreatedViewModel groupPlaylistCreatedViewModel,
                                    ViewManagerModel viewManagerModel){
        this.groupPlaylistCreatedViewModel = groupPlaylistCreatedViewModel;
        this.viewManagerModel = viewManagerModel;
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel Message = new JLabel(groupPlaylistCreatedViewModel.getState().getMessage());
        panel.add(Message);

        JButton returnToLoggedIn = new JButton("Return to Logged In Screen");
        panel.add(returnToLoggedIn);

        returnToLoggedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("logged in");
                viewManagerModel.firePropertyChanged();
            }
        });
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
