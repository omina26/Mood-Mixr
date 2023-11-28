package view;

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
    public final String viewName = "Create Mood";
    private final JFrame frame = new JFrame("Select a mood:");
    private final CreatePlaylistViewModel createPlaylistViewModel;
    private final CreatePlaylistController createPlaylistController;
    private final CreatePlaylistState createPlaylistState;
    private JButton create;
    private JButton back;

    public CreatePlaylistView(CreatePlaylistViewModel createPlaylistViewModel, CreatePlaylistController createPlaylistController, CreatePlaylistState createPlaylistState) {
        this.createPlaylistController = createPlaylistController;
        this.createPlaylistViewModel = createPlaylistViewModel;
        this.createPlaylistState = createPlaylistState;

        JLabel title = new JLabel("Create a Playlist");
        JLabel directions = new JLabel("Select the mood for the playlist:");
        JLabel noMoodsMessage = new JLabel("If you see no options then go back and create a mood.");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        directions.setAlignmentX(Component.CENTER_ALIGNMENT);
        noMoodsMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

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
        frame.add(dropdown);
        frame.setVisible(true);

        JPanel buttons = new JPanel();

        back = new JButton(CreatePlaylistViewModel.BACK_BUTTON_LABEL);
        buttons.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        create = new JButton(CreatePlaylistViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);

        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(create)) {

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
