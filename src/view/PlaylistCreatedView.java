package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import interface_adapter.view_moods.ViewMoodsState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View class for success view after create playlist
 */

public class PlaylistCreatedView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "playlist created";
    private final PlaylistCreatedViewModel playlistCreatedViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * A constructor for this view class
     * @param playlistCreatedViewModel The view model for the PlaylistCreated view
     */
    public PlaylistCreatedView(PlaylistCreatedViewModel playlistCreatedViewModel, ViewManagerModel viewManagerModel) {
        this.playlistCreatedViewModel = playlistCreatedViewModel;
        this.viewManagerModel = viewManagerModel;

        JLabel title = new JLabel(playlistCreatedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel successStatement = new JLabel("Playlist was successfully created. You can find it in your Spotify Account.");

        JButton menu = new JButton("Main menu");


        menu.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("here");
                        if (e.getSource().equals(menu)) {
                            System.out.println("here too");
                            viewManagerModel.setActiveView("logged in");
                            viewManagerModel.firePropertyChanged();
                        }
                    }

                }


        );
        this.add(successStatement);
        this.add(menu);
    }

    /**
     * Deals with action events
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Deals with property change for view
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("playlist created property change");
        ViewMoodsState state = (ViewMoodsState) evt.getNewValue();
    }

}
