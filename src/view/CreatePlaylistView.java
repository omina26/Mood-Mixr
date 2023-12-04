package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.create_playlist.CreatePlaylistState;
import interface_adapter.view_moods.ViewMoodsState;
import interface_adapter.view_moods.ViewMoodsViewModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.Set;

/**
 * The View class for Create Playlist
 */
public class CreatePlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Create Playlist";
    private final ViewManagerModel viewManagerModel;
    private final CreatePlaylistViewModel createPlaylistViewModel;
    private final JTextField createPlaylistInputField = new JTextField(15);

    /**
     * Constructs the CreatePlaylistView with necessary models and controller.
     *
     * @param viewManagerModel The ViewManagerModel for the application.
     * @param createPlaylistController The Controller that handles the business logic.
     * @param createPlaylistViewModel The ViewModel associated with this view
     */


    public CreatePlaylistView(ViewManagerModel viewManagerModel, CreatePlaylistViewModel createPlaylistViewModel, CreatePlaylistController createPlaylistController) {
        this.viewManagerModel = viewManagerModel;
        this.createPlaylistViewModel = createPlaylistViewModel;
        this.createPlaylistViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Get a Playlist");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel directions = new JLabel("Type the mood name for the playlist. List of available moods are below. If you see no options then go back and create a mood.");
        directions.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel playlistInfo = new LabelTextPanel(
                new JLabel(""), createPlaylistInputField);

        JLabel moodTitle = new JLabel("Moods: ");
        moodTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

//        JLabel noMoodsMessage = new JLabel("If you see no options then go back and create a mood.");
//        noMoodsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);


//        CreatePlaylistState createPlaylistState = createPlaylistViewModel.getState();
//        Set<String> moodsList = createPlaylistState.getMoodsList();
//        String moods = "";
//        for (String mood: moodsList) {
//            moods = moods + mood + ",";
//        }
//        JLabel moodOptions = new JLabel(moods);

//        CreatePlaylistState state = createPlaylistViewModel.getState();
//        Set<String> moodsList = state.getMoodsList();
//        String moods = "";
//        for (String s : state.getMoodsList()) {
//            String[] split = s.split(" ");
//            JLabel moodname = new JLabel(split[0]);
//            this.add(moodname);
//        }


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
                            String selectedMood = createPlaylistInputField.getText(); //Retrieve name of selected mood from input field
                            CreatePlaylistState currentState = createPlaylistViewModel.getState();
                            currentState.setSelectedMood(selectedMood); // Set the mood name in the state
                            createPlaylistController.execute(currentState.getSelectedMood()); // Pass the mood name to the controller
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(directions);
//        this.add(noMoodsMessage);
        this.add(playlistInfo);
        this.add(buttons);
        this.add(moodTitle);
    }

    /**
     * Deals with action events
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    /**
     * Deals with property change for view
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        if (evt.getSource() == this.createPlaylistViewModel) {
//            CreatePlaylistState state = (CreatePlaylistState) evt.getNewValue();
//            if (state.getSaveError() != null) {
//                JOptionPane.showMessageDialog(this, state.getSaveError());
//            }
//        }
        System.out.println("create playlist property change");
        CreatePlaylistState state = (CreatePlaylistState) evt.getNewValue();
        String moods = "";
        for (String s : state.getMoodsList()) {
            String[] split = s.split(" ");
            JPanel moodPanel = new JPanel(new GridLayout(0, 1, 0, 1) );
            moodPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            JLabel moodname = new JLabel(split[0]);
            moodPanel.add(moodname);
            moodPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(moodPanel);
        }

    }

}
