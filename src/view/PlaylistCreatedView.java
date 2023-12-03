package view;

import interface_adapter.playlist_created.PlaylistCreatedViewModel;
import interface_adapter.view_moods.ViewMoodsState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlaylistCreatedView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "playlist created";
    private final PlaylistCreatedViewModel playlistCreatedViewModel;

    public PlaylistCreatedView(PlaylistCreatedViewModel playlistCreatedViewModel) {
        this.playlistCreatedViewModel = playlistCreatedViewModel;

        JLabel title = new JLabel(playlistCreatedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
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
