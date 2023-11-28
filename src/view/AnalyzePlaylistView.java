package view;

import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.AnalyzePlaylistState;
import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
        this.analyzePlaylistViewModel.addpropertyChangeListener(this);


        JLabel title = new JLabel("Analyze Playlist Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel playlistInfo = new LabelTextPanel(
                new JLabel("Playlist ID"), analyzePlaylistInputField);


        //LabelTextPanel playlistInfo = new LabelTextPanel(
               // new JLabel("Playlist Name"), analyzePlaylistInputField);

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
