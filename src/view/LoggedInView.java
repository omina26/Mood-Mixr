package view;

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

    final JButton logOut;
    final JButton createMood;
    final JButton getPlaylist;
    final JButton analyzePlaylist;
    final JButton groupPlaylist;


    public LoggedInView(LoggedInViewModel loggedInViewModel) {

        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);


        JLabel title = new JLabel("Logged In Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameInfo = new JLabel("Welcome: ");
        name = new JLabel();

        JPanel buttons = new JPanel();
        logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
        buttons.add(logOut);
        logOut.addActionListener(this);

        createMood = new JButton(loggedInViewModel.CREATE_MOOD_BUTTON_LABEL);
        buttons.add(createMood);


        getPlaylist = new JButton(loggedInViewModel.GET_PLAYLIST_BUTTON_LABEL);
        buttons.add(getPlaylist);
        getPlaylist.addActionListener(this);

        analyzePlaylist = new JButton(loggedInViewModel.ANALYZE_PLAYLIST_BUTTON_LABEL);
        buttons.add(analyzePlaylist);
        analyzePlaylist.addActionListener(this);

        groupPlaylist = new JButton(loggedInViewModel.GROUP_PLAYLIST_BUTTON_LABEL);
        buttons.add(groupPlaylist);
        groupPlaylist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == groupPlaylist){
                   // Update View to be GroupPlaylistView
                }
            }
        });

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

