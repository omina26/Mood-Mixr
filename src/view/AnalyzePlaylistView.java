package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.AnalyzePlaylistState;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.create_playlist.CreatePlaylistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * AnalyzePlaylistView provides a user interface for analyzing Spotify playlists.
 * Includes input fields for entering a playlist ID and buttons to initiate analysis or go back to the login screen.
 * Listens to user actions and property changes in the AnalyzePlaylistViewModel.
 */

public class AnalyzePlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Analyze Playlist";

    private final AnalyzePlaylistViewModel analyzePlaylistViewModel;
    private final JTextField analyzePlaylistInputField = new JTextField(15);

    final JButton analyze;
    final JButton mainMenu;

    private final AnalyzePlaylistController analyzePlaylistController;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs the AnalyzePlaylistView with necessary model and controller.
     * @param viewManagerModel The ViewManagerModel for the application.
     * @param analyzePlaylistViewModel The ViewModel associated with this view.
     * @param analyzePlaylistController The Controller that handles the business logic.
     */

    public AnalyzePlaylistView(ViewManagerModel viewManagerModel,
                               AnalyzePlaylistViewModel analyzePlaylistViewModel,
                               AnalyzePlaylistController analyzePlaylistController) {
        this.viewManagerModel = viewManagerModel;
        this.analyzePlaylistController = analyzePlaylistController;
        this.analyzePlaylistViewModel = analyzePlaylistViewModel;

        JLabel title = new JLabel("Analyze Playlist Screen");
        JLabel directions = new JLabel("Input the playlist ID for a public playlist you would like to analyze. "  +
                "You can find the ID by selecting 'copy playlist link' in Spotify. The playlist ID will be series of letters " +
                "and numbers after 'playlist/ and before the '?'");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        directions.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel playlistInfo = new LabelTextPanel(
                new JLabel("Playlist ID"), analyzePlaylistInputField);

        JPanel buttons = new JPanel();

        mainMenu = new JButton(CreatePlaylistViewModel.MAIN_MENU_BUTTON_LABEL);
        buttons.add(mainMenu);

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("here");
                if (e.getSource().equals(mainMenu)) {
                    System.out.println("here too");
                    viewManagerModel.setActiveView("logged in");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });


        analyze = new JButton(AnalyzePlaylistViewModel.ANALYZE_PLAYLIST_BUTTON_LABEL);
        buttons.add(analyze);

        analyze.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(analyze)) {
                            String playlistID = analyzePlaylistInputField.getText(); // Retrieve playlist ID from input field
                            AnalyzePlaylistState currentState = analyzePlaylistViewModel.getState();
                            currentState.setPlaylist(playlistID); // Set the playlist ID in the state
                            analyzePlaylistController.execute(currentState.getPlaylistID()); // Pass the playlist ID to the controller
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(directions);
        this.add(playlistInfo);
        this.add(buttons);

    }

    /**
     * Handles action events triggered by user interactions with the view components.
     *
     * @param e The action event.
     */

    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    /**
     * Responds to property change events from the AnalyzePlaylistViewModel.
     * Updates the view based on changes in the model's state.
     *
     * @param evt The property change event.
     */

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == this.analyzePlaylistViewModel) {
            AnalyzePlaylistState state = (AnalyzePlaylistState) evt.getNewValue();
            if (state.getSaveError() != null){
                JOptionPane.showMessageDialog(this, state.getSaveError());
            }
        }
    }


}

