package view;

import interface_adapter.create_mood.CreateMoodController;
import interface_adapter.create_mood.CreateMoodState;
import interface_adapter.create_mood.CreateMoodViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The View class for the Create a new mood
 */
public class CreateMoodView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Mood";

    private final JTextField moodNameInputField = new JTextField(15);

    private final CreateMoodViewModel createMoodViewModel;
    private final CreateMoodController createMoodController;

    final JButton create;


    /**
     * The costructor for the Create Mood View
     * @param createMoodViewModel The view model for this view
     * @param createMoodController The controller for this view
     */
    public CreateMoodView(CreateMoodViewModel createMoodViewModel, CreateMoodController createMoodController){

        this.createMoodController = createMoodController;
        this.createMoodViewModel = createMoodViewModel;
        //this.createMoodViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CreateMoodViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel moodInfo = new LabelTextPanel(
                new JLabel("Mood name"), moodNameInputField);

        moodNameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
//                LoginState currentState = loginViewModel.getState();
//                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
//                loginViewModel.setState(currentState)
                CreateMoodState currentState = createMoodViewModel.getState();
                currentState.setName(moodNameInputField.getText() + e.getKeyChar());
                createMoodViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        JPanel setAcousticnessPanel = new JPanel();
        setAcousticnessPanel.setLayout(new BoxLayout(setAcousticnessPanel, BoxLayout.Y_AXIS));

        JLabel setAcousticnessLabel = new JLabel(CreateMoodViewModel.SET_ACOUSTICNESS_LABEL + " (" + CreateMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider acousticnessSlider = new JSlider(0, 100, CreateMoodViewModel.DEFAULT_SLIDER_VALUE);
        JLabel acousticnessInfo = new JLabel(CreateMoodViewModel.ACOUSTICNESS_INFO + "\n");
        acousticnessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setAcousticness(acousticnessSlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setAcousticnessLabel.setText(CreateMoodViewModel.SET_ACOUSTICNESS_LABEL + " (" + acousticnessSlider.getValue() + ")");
                    }
                }
        );
        setAcousticnessPanel.add(setAcousticnessLabel);
        setAcousticnessPanel.add(acousticnessSlider);
        setAcousticnessPanel.add(acousticnessInfo);
        setAcousticnessPanel.add(Box.createVerticalStrut(50));

        JPanel setDanceabilityPanel = new JPanel();
        setDanceabilityPanel.setLayout(new BoxLayout(setDanceabilityPanel, BoxLayout.Y_AXIS));

        JLabel setDanceabilityLabel = new JLabel(CreateMoodViewModel.SET_DANCEABILITY_LABEL + " (" + CreateMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider danceabilitySlider = new JSlider(0, 100, CreateMoodViewModel.DEFAULT_SLIDER_VALUE );
        JLabel danceabilityInfo = new JLabel(CreateMoodViewModel.DANCEABILITY_INFO + "\n");
        danceabilitySlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setDanceability(danceabilitySlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setDanceabilityLabel.setText(CreateMoodViewModel.SET_DANCEABILITY_LABEL + " (" + danceabilitySlider.getValue() + ")");
                    }
                }
        );
        setDanceabilityPanel.add(setDanceabilityLabel);
        setDanceabilityPanel.add(danceabilitySlider);
        setDanceabilityPanel.add(danceabilityInfo);
        setDanceabilityPanel.add(Box.createVerticalStrut(50));

        JPanel setEnergyPanel = new JPanel();
        setEnergyPanel.setLayout(new BoxLayout(setEnergyPanel, BoxLayout.Y_AXIS));

        JLabel setEnergyLabel = new JLabel(CreateMoodViewModel.SET_ENERGY_LABEL + " (" + CreateMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider energySlider = new JSlider(0, 100, CreateMoodViewModel.DEFAULT_SLIDER_VALUE);
        JLabel energyInfo = new JLabel(CreateMoodViewModel.ENERGY_INFO + "\n");
        energySlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setEnergy(energySlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setEnergyLabel.setText(CreateMoodViewModel.SET_ENERGY_LABEL + " (" + energySlider.getValue() + ")");
                    }
                }
        );
        setEnergyPanel.add(setEnergyLabel);
        setEnergyPanel.add(energySlider);
        setEnergyPanel.add(energyInfo);
        setEnergyPanel.add(Box.createVerticalStrut(50));

        JPanel setInstrumentalnessPanel = new JPanel();
        setInstrumentalnessPanel.setLayout(new BoxLayout(setInstrumentalnessPanel, BoxLayout.Y_AXIS));

        JLabel setInstrumentalnessLabel = new JLabel(CreateMoodViewModel.SET_INSTRUMENTALNESS + " (" + CreateMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider instrumentalnessSlider = new JSlider(0, 100, CreateMoodViewModel.DEFAULT_SLIDER_VALUE);
        JLabel instrumentalnessInfo = new JLabel(CreateMoodViewModel.INSTRUMENTALNESS_INFO + "\n");
        instrumentalnessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setInstrumentalness(instrumentalnessSlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setInstrumentalnessLabel.setText(CreateMoodViewModel.SET_INSTRUMENTALNESS + " (" + instrumentalnessSlider.getValue() + ")");
                    }
                }
        );
        setInstrumentalnessPanel.add(setInstrumentalnessLabel);
        setInstrumentalnessPanel.add(instrumentalnessSlider);
        setInstrumentalnessPanel.add(instrumentalnessInfo);
        setInstrumentalnessPanel.add(Box.createVerticalStrut(50));

        JPanel setLivenessPanel = new JPanel();
        setLivenessPanel.setLayout(new BoxLayout(setLivenessPanel, BoxLayout.Y_AXIS));

        JLabel setLivenessLabel = new JLabel(CreateMoodViewModel.SET_LIVENESS + " (" + createMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider livenessSlider = new JSlider(0, 100, createMoodViewModel.DEFAULT_SLIDER_VALUE);
        JLabel livenessInfo = new JLabel(CreateMoodViewModel.LIVENESS_LABEL + "\n");
        livenessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setLiveness(livenessSlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setLivenessLabel.setText(CreateMoodViewModel.SET_LIVENESS + " (" + livenessSlider.getValue() + ")");
                    }
                }
        );
        setLivenessPanel.add(setLivenessLabel);
        setLivenessPanel.add(livenessSlider);
        setLivenessPanel.add(livenessInfo);
        setLivenessPanel.add(Box.createVerticalStrut(50));

        JPanel setSpeechinessPanel = new JPanel();
        setSpeechinessPanel.setLayout(new BoxLayout(setSpeechinessPanel, BoxLayout.Y_AXIS));

        JLabel setSpeechinessLabel = new JLabel(CreateMoodViewModel.SET_SPEECHINESS + " (" + CreateMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider speechinessSlider = new JSlider(0, 100, CreateMoodViewModel.DEFAULT_SLIDER_VALUE);
        JLabel speechinessInfo = new JLabel(CreateMoodViewModel.SPEECHINESS_INFO + "\n");
        speechinessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setSpeechiness(speechinessSlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setSpeechinessLabel.setText(CreateMoodViewModel.SET_SPEECHINESS + " (" + speechinessSlider.getValue() + ")");
                    }
                }
        );
        setSpeechinessPanel.add(setSpeechinessLabel);
        setSpeechinessPanel.add(speechinessSlider);
        setSpeechinessPanel.add(speechinessInfo);
        setSpeechinessPanel.add(Box.createVerticalStrut(50));

        JPanel setValencePanel = new JPanel();
        setValencePanel.setLayout(new BoxLayout(setValencePanel, BoxLayout.Y_AXIS));

        JLabel setValenceLabel = new JLabel(CreateMoodViewModel.SET_VALENCE + " (" + CreateMoodViewModel.DEFAULT_SLIDER_VALUE + ")");
        JSlider valenceSlider = new JSlider(0, 100, CreateMoodViewModel.DEFAULT_SLIDER_VALUE);
        JLabel valenceInfo = new JLabel(CreateMoodViewModel.VALENCE_INFO + "\n");
        valenceSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        CreateMoodState currentState = createMoodViewModel.getState();
                        currentState.setValence(valenceSlider.getValue());
                        createMoodViewModel.setState(currentState);
                        setValenceLabel.setText(CreateMoodViewModel.SET_VALENCE + " (" + valenceSlider.getValue() + ")");
                    }
                }
        );
        setValencePanel.add(setValenceLabel);
        setValencePanel.add(valenceSlider);
        setValencePanel.add(valenceInfo);
        setValencePanel.add(Box.createVerticalStrut(50));

        JPanel buttons = new JPanel();

        create = new JButton(CreateMoodViewModel.CREATE_BUTTON_LABEL);
        buttons.add(create);

        create.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(create)) {
                            CreateMoodState currentState = createMoodViewModel.getState();
                            createMoodController.execute(
                                    currentState.getName(),
                                    currentState.getAcousticness(),
                                    currentState.getDanceability(),
                                    currentState.getEnergy(),
                                    currentState.getInstrumentalness(),
                                    currentState.getLiveness(),
                                    currentState.getSpeechiness(),
                                    currentState.getValence()
                            );
                        }
                    }

                }


        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(moodInfo);
        this.add(setAcousticnessPanel);
        this.add(setDanceabilityPanel);
        this.add(setEnergyPanel);
        this.add(setInstrumentalnessPanel);
        this.add(setLivenessPanel);
        this.add(setSpeechinessPanel);
        this.add(setValencePanel);
        this.add(buttons);
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
        if (evt.getSource() == this.createMoodViewModel){
            CreateMoodState state = (CreateMoodState) evt.getNewValue();
            if (state.getSaveError() != null){
                JOptionPane.showMessageDialog(this, state.getSaveError());
            }
        }
    }

}

