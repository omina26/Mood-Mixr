package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistState;
import interface_adapter.analyzed_playlist.AnalyzedPlaylistViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AnalyzedPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "view analyzed playlist";

    private final AnalyzedPlaylistViewModel analyzedPlaylistViewModel;
    private final ViewManagerModel viewManagerModel;

    public AnalyzedPlaylistView(AnalyzedPlaylistViewModel analyzedPlaylistViewModel,
                                ViewManagerModel viewManagerModel) {
        this.analyzedPlaylistViewModel = analyzedPlaylistViewModel;
        this.viewManagerModel = viewManagerModel;
        this.analyzedPlaylistViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(analyzedPlaylistViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

        this.add(menu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("playlist analyzed property change");
        AnalyzedPlaylistState state = (AnalyzedPlaylistState) evt.getNewValue();
        for (String s : state.getPlaylistAudioFeaturesList()) {
            String[] split = s.split(" ");

            JPanel moodPanel = new JPanel(new GridLayout(0, 1));
            moodPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel titleLabel = new JLabel(split[0]);
            titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
            moodPanel.add(titleLabel);
            moodPanel.add(new JLabel("average acousticness: " + split[1]));
            moodPanel.add(new JLabel("average danceability: " + split[2]));
            moodPanel.add(new JLabel("average energy: " + split[3]));
            moodPanel.add(new JLabel("average instrumentalness: " + split[4]));
            moodPanel.add(new JLabel("average liveness: " + split[5]));
            moodPanel.add(new JLabel("average speechiness: " + split[6]));
            moodPanel.add(new JLabel("average valence: " + split[7]));
            moodPanel.add(Box.createVerticalStrut(20));
            this.add(moodPanel);
        }
    }
}
