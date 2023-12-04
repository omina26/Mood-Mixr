package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.create_playlist.CreatePlaylistState;
import interface_adapter.view_moods.ViewMoodsState;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Set;

public class CreatePlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Get Playlist";
    private final ViewManagerModel viewManagerModel;
    private final CreatePlaylistViewModel createPlaylistViewModel;

    public CreatePlaylistView(ViewManagerModel viewManagerModel, CreatePlaylistViewModel createPlaylistViewModel, CreatePlaylistController createPlaylistController) {
        this.viewManagerModel = viewManagerModel;
        this.createPlaylistViewModel = createPlaylistViewModel;

        JLabel title = new JLabel("Get a Playlist");
        JLabel directions = new JLabel("Select the mood for the playlist:");
        JLabel noMoodsMessage = new JLabel("If you see no options then go back and create a mood.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        directions.setAlignmentX(Component.CENTER_ALIGNMENT);
        noMoodsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);


//        CreatePlaylistState createPlaylistState = createPlaylistViewModel.getState();
//        Set<String> moodsList = createPlaylistState.getMoodsList();
//        String moods = "";
//        for (String mood: moodsList) {
//            moods = moods + mood + ",";
//        }
//        JLabel moodOptions = new JLabel(moods);


        JPanel buttons = new JPanel();

        JButton mainMenu = new JButton(CreatePlaylistViewModel.MAIN_MENU_BUTTON_LABEL);
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

        JButton get = new JButton(CreatePlaylistViewModel.GET_BUTTON_LABEL);
        buttons.add(get);

        get.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(get)) {

//                            LoginState currentState = loginViewModel.getState();
//                            try {
//                                loginController.executeUseCase();
//                            } catch (IOException ex) {
//                                throw new RuntimeException(ex);
//                            } catch (URISyntaxException ex) {
//                                throw new RuntimeException(ex);
//                            }
                            CreatePlaylistState currentState = createPlaylistViewModel.getState();
                            createPlaylistController.execute(currentState.getSelectedMood());
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(directions);
        this.add(noMoodsMessage);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getSource() == this.createPlaylistViewModel) {
//            CreatePlaylistState state = (CreatePlaylistState) evt.getNewValue();
//            if (state.getSaveError() != null){
//                JOptionPane.showMessageDialog(this, state.getSaveError());
//            }
        System.out.println("create playlist property change");
        CreatePlaylistState state = (CreatePlaylistState) evt.getNewValue();
        String moods = "";
        for (String s : state.getMoodsList()) {
            String[] split = s.split(" ");
            JLabel moodname = new JLabel(split[0]);
            this.add(moodname);
        }

    }

}
