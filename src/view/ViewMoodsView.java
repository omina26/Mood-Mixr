package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_mood.CreateMoodState;
import interface_adapter.create_mood.CreateMoodViewModel;
import interface_adapter.view_moods.ViewMoodsState;
import interface_adapter.view_moods.ViewMoodsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The View class for viewing the created moods
 */
public class ViewMoodsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "view moods";
    private final ViewMoodsViewModel viewMoodsViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * A constructor for this view class
     * @param viewMoodsViewModel The view model for the ViewMoods view
     */
    public ViewMoodsView(ViewMoodsViewModel viewMoodsViewModel, ViewManagerModel viewManagerModel) {

        this.viewMoodsViewModel = viewMoodsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.viewMoodsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(viewMoodsViewModel.TITLE_LABEL);
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
     * Deals with action events
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * Deals with property change for view
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("view moods property change");
        ViewMoodsState state = (ViewMoodsState) evt.getNewValue();
        for (String s : state.getMoodsList()) {
            String[] split = s.split(" ");

            JPanel moodPanel = new JPanel(new GridLayout(0, 1) );
            moodPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

            JLabel titleLabel = new JLabel(split[0]);
            titleLabel.setFont(new Font("Calibri", Font.BOLD, 20));
            moodPanel.add(titleLabel);
            moodPanel.add(new JLabel("acousticness: "+ split[1]));
            moodPanel.add(new JLabel("danceability: "+ split[2]));
            moodPanel.add(new JLabel("energy: "+ split[3]));
            moodPanel.add(new JLabel("instrumentalness: "+ split[4]));
            moodPanel.add(new JLabel("liveness: "+ split[5]));
            moodPanel.add(new JLabel("speechiness: "+ split[6]));
            moodPanel.add(new JLabel("valence: "+ split[7]));
            moodPanel.add(Box.createVerticalStrut(20));
            this.add(moodPanel);
        }
    }
}

