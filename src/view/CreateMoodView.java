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

public class CreateMoodView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Mood";
    final JTextField moodNameInputField = new JTextField();
    public final int DEFAULT_SLIDER_VALUE = 50;

    public final CreateMoodViewModel createMoodViewModel;

    final JButton create;

    //private final CreateMoodController createMoodController;
    public CreateMoodView(CreateMoodViewModel createMoodViewModel, CreateMoodController createMoodController){
        //this.createMoodController = createMoodController;
        this.createMoodViewModel = createMoodViewModel;
        //this.createMoodViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Create Mood Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel moodInfo = new LabelTextPanel(
                new JLabel("Mood name"), moodNameInputField);

        moodNameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
//                LoginState currentState = loginViewModel.getState();
//                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
//                loginViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        JPanel setAcousticnessPanel = new JPanel();
        JLabel setAcousticnessLabel = new JLabel(CreateMoodViewModel.SET_ACOUSTICNESS_LABEL + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider acousticnessSlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        acousticnessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setAcousticnessLabel.setText(CreateMoodViewModel.SET_ACOUSTICNESS_LABEL + " (" + acousticnessSlider.getValue() + ")");
                    }
                }
        );
        setAcousticnessPanel.add(setAcousticnessLabel);
        setAcousticnessPanel.add(acousticnessSlider);

        JPanel setDanceabilityPanel = new JPanel();
        JLabel setDanceabilityLabel = new JLabel(CreateMoodViewModel.SET_DANCEABILITY_LABEL + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider danceabilitySlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        danceabilitySlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setDanceabilityLabel.setText(CreateMoodViewModel.SET_DANCEABILITY_LABEL + " (" + danceabilitySlider.getValue() + ")");
                    }
                }
        );
        setDanceabilityPanel.add(setDanceabilityLabel);
        setDanceabilityPanel.add(danceabilitySlider);

        JPanel setEnergyPanel = new JPanel();
        JLabel setEnergyLabel = new JLabel(CreateMoodViewModel.SET_ENERGY_LABEL + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider energySlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        energySlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setEnergyLabel.setText(CreateMoodViewModel.SET_ENERGY_LABEL + " (" + energySlider.getValue() + ")");
                    }
                }
        );
        setEnergyPanel.add(setEnergyLabel);
        setEnergyPanel.add(energySlider);

        JPanel setInstrumentalnessPanel = new JPanel();
        JLabel setInstrumentalnessLabel = new JLabel(CreateMoodViewModel.SET_INSTRUMENTALNESS + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider instrumentalnessSlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        instrumentalnessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setInstrumentalnessLabel.setText(CreateMoodViewModel.SET_INSTRUMENTALNESS + " (" + instrumentalnessSlider.getValue() + ")");
                    }
                }
        );
        setInstrumentalnessPanel.add(setInstrumentalnessLabel);
        setInstrumentalnessPanel.add(instrumentalnessSlider);

        JPanel setLivenessPanel = new JPanel();
        JLabel setLivenessLabel = new JLabel(CreateMoodViewModel.SET_LIVENESS + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider livenessSlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        livenessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setLivenessLabel.setText(CreateMoodViewModel.SET_LIVENESS + " (" + livenessSlider.getValue() + ")");
                    }
                }
        );
        setLivenessPanel.add(setLivenessLabel);
        setLivenessPanel.add(livenessSlider);

        JPanel setSpeechinessPanel = new JPanel();
        JLabel setSpeechinessLabel = new JLabel(CreateMoodViewModel.SET_SPEECHINESS + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider speechinessSlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        speechinessSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setSpeechinessLabel.setText(CreateMoodViewModel.SET_SPEECHINESS + " (" + speechinessSlider.getValue() + ")");
                    }
                }
        );
        setSpeechinessPanel.add(setSpeechinessLabel);
        setSpeechinessPanel.add(speechinessSlider);

        JPanel setValencePanel = new JPanel();
        JLabel setValenceLabel = new JLabel(CreateMoodViewModel.SET_VALENCE + " (" + DEFAULT_SLIDER_VALUE + ")");
        JSlider valenceSlider = new JSlider(0, 100, DEFAULT_SLIDER_VALUE);
        valenceSlider.addChangeListener(
                new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        setValenceLabel.setText(CreateMoodViewModel.SET_VALENCE + " (" + valenceSlider.getValue() + ")");
                    }
                }
        );
        setValencePanel.add(setValenceLabel);
        setValencePanel.add(valenceSlider);

        JPanel buttons = new JPanel();

        create = new JButton(CreateMoodViewModel.CREATE_BUTTON_LABEL);
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
                            //CreateMoodState currentState = createMoodViewModel.getState();
                            //createMoodController.execute();
                            System.out.println(acousticnessSlider.getValue());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click" + e.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void setFields(CreateMoodState state) {
    }
}

