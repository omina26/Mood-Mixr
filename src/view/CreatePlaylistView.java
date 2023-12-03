package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import interface_adapter.create_playlist.CreatePlaylistState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreatePlaylistView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "Get Playlist";
    private final JFrame frame = new JFrame("Select a mood:");
    private final ViewManagerModel viewManagerModel;
    private final CreatePlaylistViewModel createPlaylistViewModel;
    private final CreatePlaylistController createPlaylistController;
    private JButton get;
    private JButton mainMenu;

    public CreatePlaylistView(ViewManagerModel viewManagerModel, CreatePlaylistViewModel createPlaylistViewModel, CreatePlaylistController createPlaylistController) {
        this.viewManagerModel = viewManagerModel;
        this.createPlaylistController = createPlaylistController;
        this.createPlaylistViewModel = createPlaylistViewModel;

        JLabel title = new JLabel("Get a Playlist");
        JLabel directions = new JLabel("Select the mood for the playlist:");
        JLabel noMoodsMessage = new JLabel("If you see no options then go back and create a mood.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        directions.setAlignmentX(Component.CENTER_ALIGNMENT);
        noMoodsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);


        CreatePlaylistState createPlaylistState = createPlaylistViewModel.getState();
        String[] moodsList = createPlaylistState.getMoodsList();
        JComboBox<String> dropdown = new JComboBox<>(moodsList);
        dropdown.setEditable(true);

        dropdown.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            String selectedItem = (String)cb.getSelectedItem();
            CreatePlaylistState currentState = createPlaylistViewModel.getState();
            currentState.setSelectedMood(selectedItem);
            createPlaylistViewModel.setState(currentState);
        });


        JPanel buttons = new JPanel();

        mainMenu = new JButton(CreatePlaylistViewModel.MAIN_MENU_BUTTON_LABEL);
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

        get = new JButton(CreatePlaylistViewModel.GET_BUTTON_LABEL);
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
        this.add(dropdown);
        this.add(noMoodsMessage);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent e) {System.out.println("Click" + e.getActionCommand());}

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == this.createPlaylistViewModel) {
            CreatePlaylistState state = (CreatePlaylistState) evt.getNewValue();
            if (state.getSaveError() != null){
                JOptionPane.showMessageDialog(this, state.getSaveError());
            }
        }
    }

}
