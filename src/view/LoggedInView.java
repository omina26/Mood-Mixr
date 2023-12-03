package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;

    JLabel name;

    final JButton createMood;
    final JButton getPlaylist;
    final JButton analyzePlaylist;
    final JButton groupPlaylist;


    public LoggedInView(LoggedInViewModel loggedInViewModel, ViewManagerModel viewManagerModel) {

        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameInfo = new JLabel("Welcome: ");
        name = new JLabel();

        JPanel buttons = new JPanel();

        createMood = new JButton(loggedInViewModel.CREATE_MOOD_BUTTON_LABEL);
        buttons.add(createMood);
        createMood.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource() == createMood){
                            viewManagerModel.setActiveView("Create Mood");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        getPlaylist = new JButton(loggedInViewModel.GET_PLAYLIST_BUTTON_LABEL);
        buttons.add(getPlaylist);
        getPlaylist.addActionListener(this);

        analyzePlaylist = new JButton(loggedInViewModel.ANALYZE_PLAYLIST_BUTTON_LABEL);
        buttons.add(analyzePlaylist);
        analyzePlaylist.addActionListener(this);

        groupPlaylist = new JButton(loggedInViewModel.GROUP_PLAYLIST_BUTTON_LABEL);
        buttons.add(groupPlaylist);
        groupPlaylist.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(nameInfo);
        this.add(name);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        name.setText(state.getName());
    }


}

