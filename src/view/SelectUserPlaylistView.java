package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.group_playlist.GroupPlaylistController;
import interface_adapter.group_playlist.GroupPlaylistState;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.select_user_playlist.SelectUserPlaylistState;
import interface_adapter.select_user_playlist.SelectUserPlaylistsViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SelectUserPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Select User Playlists";
    private final SelectUserPlaylistsViewModel selectUserPlaylistsViewModel;

    final JLabel instruction;
    final JButton GET_GROUP_PLAYLIST;
    private JPanel allPlaylists;

    private ArrayList<String> selectedPlaylists;
    private ArrayList<JCheckBox> checkBoxes;
    private GroupPlaylistController groupPlaylistController;
    public SelectUserPlaylistView(SelectUserPlaylistsViewModel selectUserPlaylistsViewModel,
                                  GroupPlaylistController groupPlaylistController,
                                  ViewManagerModel viewManagerModel) {

        this.selectUserPlaylistsViewModel = selectUserPlaylistsViewModel;
        this.selectUserPlaylistsViewModel.addPropertyChangeListener(this);
        this.groupPlaylistController = groupPlaylistController;
        this.selectedPlaylists = new ArrayList<String>();

        allPlaylists = new JPanel();
        allPlaylists.setLayout(new BoxLayout(allPlaylists, BoxLayout.Y_AXIS));
        this.checkBoxes = new ArrayList<JCheckBox>();

        JLabel title = new JLabel("Select playlists to group");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        instruction = new JLabel("Please select which playlists you would like to add to the grouping:");

        JPanel buttons = new JPanel();

        GET_GROUP_PLAYLIST = new JButton("Get Group Playlist");
        buttons.add(GET_GROUP_PLAYLIST);

        GET_GROUP_PLAYLIST.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectUserPlaylistState selectUserPlaylistState = selectUserPlaylistsViewModel.getState();
                groupPlaylistController.executeUseCase(selectedPlaylists,
                        selectUserPlaylistState.getUserPlaylistsOnly(),
                        selectUserPlaylistState.getUser(),
                        selectUserPlaylistState.getNonUserPlaylistID());
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(instruction);
        this.add(allPlaylists);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SelectUserPlaylistState state = (SelectUserPlaylistState) evt.getNewValue();
        ArrayList<String> playlists = state.getAllPlaylists();
        System.out.println(playlists);
        for (String playlist: playlists){
            checkBoxes.add(new JCheckBox(playlist));
        }
        for (JCheckBox checkBox: checkBoxes){
            allPlaylists.add(checkBox);
            checkBox.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    if (!checkBox.isSelected()){
                        selectedPlaylists.remove(checkBox.getText());
                    }
                    else{
                        if(!selectedPlaylists.contains(checkBox.getText())){
                            selectedPlaylists.add(checkBox.getText());
                        }
                    }
                }
            });
        }

    }

}

