package view;

import interface_adapter.analyze_playlist.AnalyzePlaylistController;
import interface_adapter.analyze_playlist.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnalyzePlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Analyze Playlist";

    private final AnalyzePlaylistViewModel analyzePlaylistViewModel;
    private final JTextField analyzePlaylistInputField = new JTextField(15);

    final JButton analyze;
    final JButton back;

    private final AnalyzePlaylistController analyzePlaylistController;

    public AnalyzePlaylistView(AnalyzePlaylistViewModel analyzePlaylistViewModel,
                               AnalyzePlaylistController analyzePlaylistController) {
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

        back = new JButton(AnalyzePlaylistViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        analyze = new JButton(AnalyzePlaylistViewModel.ANALYZE_PLAYLIST_BUTTON_LABEL);
        buttons.add(analyze);

        analyze.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(analyze)) {
                            AnalyzePlaylistState currentState =
                                    analyzePlaylistViewModel.getState();

//                            analyzePlaylistController.execute(
//                                    currentState.getPlaylist());
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
    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == this.analyzePlaylistViewModel) {
            AnalyzePlaylistState state = (AnalyzePlaylistState) evt.getNewValue();
//            if (state.getSaveError() != null){
//                JOptionPane.showMessageDialog(this, state.getSaveError());
//            }
        }
    }


}

