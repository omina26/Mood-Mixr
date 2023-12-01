package view;

import interface_adapter.view_moods.ViewMoodsState;
import interface_adapter.view_moods.ViewMoodsViewModel;

import javax.swing.*;
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

    /**
     * A constructor for this view class
     * @param viewMoodsViewModel The view model for the ViewMoods view
     */
    public ViewMoodsView(ViewMoodsViewModel viewMoodsViewModel) {

        this.viewMoodsViewModel = viewMoodsViewModel;
        this.viewMoodsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(viewMoodsViewModel.TITLE_LABEL);
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
        System.out.println("view moods property change");
        ViewMoodsState state = (ViewMoodsState) evt.getNewValue();
        for (String s : state.getMoodsList()) {
            this.add(new JLabel(s));
        }
    }
}

