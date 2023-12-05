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
import java.util.Map;

/**
 * AnalyzedPlaylistView is a JPanel that displays the analyzed audio features of a playlist.
 * It listens for changes in the AnalyzedPlaylistViewModel and updates its UI components accordingly.
 * This class also provides a button to navigate back to the main menu.
 */
public class AnalyzedPlaylistView extends JPanel implements ActionListener, PropertyChangeListener {

    /**
     * Name of the view, used for identifying this panel in the view manager.
     */
    public final String viewName = "view analyzed playlist";

    private final AnalyzedPlaylistViewModel analyzedPlaylistViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs an AnalyzedPlaylistView panel.
     *
     * @param analyzedPlaylistViewModel The view model containing the analyzed playlist data.
     * @param viewManagerModel The view manager model for managing navigation between views.
     */
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

    /**
     * Handles action events triggered in the view.
     *
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    /**
     * Responds to property change events. This method is triggered when the analyzed playlist state changes.
     * It updates the UI components to reflect the new state of the analyzed playlist.
     *
     * @param evt The property change event containing the new state of the analyzed playlist.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("playlist analyzed property change");
        AnalyzedPlaylistState state = (AnalyzedPlaylistState) evt.getNewValue();
        Map<String, Double> averageFeatures = state.getAverageAudioFeatures();

        for (Map.Entry<String, Double> entry : averageFeatures.entrySet()) {
            JPanel featurePanel = new JPanel(new GridLayout(0, 1));
            featurePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel featureLabel = new JLabel("Average " + entry.getKey() + ": " + entry.getValue());
            featureLabel.setFont(new Font("Calibri", Font.BOLD, 20));
            featurePanel.add(featureLabel);
            featurePanel.add(Box.createVerticalStrut(20));

            this.add(featurePanel);
        }
    }
}
