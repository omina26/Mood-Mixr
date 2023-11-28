package view;

import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.AnalyzePlaylistState;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import interface_adapter.login.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.chrono.JapaneseChronology;

public class AnalyzePlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Analyze Playlist";

    private final AnalyzePlaylistViewModel analyzePlaylistViewModel;
    private final JTextField analyzePlaylistInputField = new JTextField(15);

    final JButton AnalyzePlaylist;

    private final AnalyzePlaylistController analyzePlaylistController;

    public AnalyzePlaylistView(AnalyzePlaylistViewModel analyzePlaylistViewModel,
                               AnalyzePlaylistController analyzePlaylistController, JButton create) {
        this.analyzePlaylistController = analyzePlaylistController;
        this.analyzePlaylistViewModel = analyzePlaylistViewModel;

        JLabel title = new JLabel("Analyze Playlist Screen");
        JLabel directions = new JLabel("Input the playlist ID for a public playlist you would like to analyze. " +
                "You can find the ID by selecing 'copy playlist link' in Spotify. The playlist IF will be series of letters " +
                "and numbers after 'playlist/ and before the '?'");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel playlistInfo = new LabelTextPanel(
                new JLabel("Playlist ID"), analyzePlaylistInputField);

        JPanel buttons = new JPanel();
        AnalyzePlaylist = new JButton();

        AnalyzePlaylist.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(AnalyzePlaylist)) {
                            AnalyzePlaylistState currentState = AnalyzePlaylistViewModel.getState();

                            AnalyzePlaylistController.execute(
                                    currentState.getPlaylist()
                            );
                        }
                    }
                }
        );

       // cancel.addActionListener(this);

        analyzePlaylistInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                AnalyzePlaylistState currentState = analyzePlaylistViewModel.getState();
                currentState.setName(analyzePlaylistInputField.getText()+e.getKeyChar());
                analyzePlaylistViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
