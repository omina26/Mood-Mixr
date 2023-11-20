import interface_adapter.create_mood.CreateMoodController;
import interface_adapter.create_mood.CreateMoodViewModel;
import org.junit.Test;
import use_case.create_mood.CreateMoodInputBoundary;
import use_case.create_mood.CreateMoodInteractor;
import view.CreateMoodView;
import view.LabelTextPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.KeyEvent;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

public class CreateMoodViewTest {

    @Test
    public void testCreateMoodView(){
        CreateMoodInputBoundary cmib = null;
        CreateMoodController controller = new CreateMoodController(cmib);
        CreateMoodViewModel viewModel = new CreateMoodViewModel();

        JPanel view = new CreateMoodView(viewModel, controller);
        JFrame jf = new JFrame();

        jf.setContentPane(view);
        jf.pack();
        jf.setVisible(true);

        LabelTextPanel panel = (LabelTextPanel) view.getComponent(1);
        JTextField moodField = (JTextField) panel.getComponent(1);

        KeyEvent event = new KeyEvent(
                moodField,
                KeyEvent.KEY_TYPED,
                System.currentTimeMillis(),
                0,
                KeyEvent.VK_UNDEFINED,
                'm'
        );

        panel.dispatchEvent(event);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals("m", new String(moodField.getText()));
        assertEquals("m", viewModel.getState().getName());

        JPanel acousticnessPanel = (JPanel) view.getComponent(2);
        JLabel acousticnessLabel = (JLabel) acousticnessPanel.getComponent(0);
        JSlider acousticnessSlider = (JSlider) acousticnessPanel.getComponent(1);

        acousticnessSlider.setValue(1);
        assertEquals(1, acousticnessSlider.getValue());
        assertEquals(CreateMoodViewModel.SET_ACOUSTICNESS_LABEL + " (1)", acousticnessLabel.getText());
        assertEquals(1, viewModel.getState().getAcousticness());

        JPanel danceabilityPanel = (JPanel) view.getComponent(3);
        JLabel danceabilityLabel = (JLabel) danceabilityPanel.getComponent(0);
        JSlider danceabilitySlider = (JSlider) danceabilityPanel.getComponent(1);

        danceabilitySlider.setValue(2);
        assertEquals(2, danceabilitySlider.getValue());
        assertEquals(CreateMoodViewModel.SET_DANCEABILITY_LABEL + " (2)", danceabilityLabel.getText());
        assertEquals(2, viewModel.getState().getDanceability());

        JPanel energyPanel = (JPanel) view.getComponent(4);
        JLabel energyLabel = (JLabel) energyPanel.getComponent(0);
        JSlider energySlider = (JSlider) energyPanel.getComponent(1);

        energySlider.setValue(3);
        assertEquals(3, energySlider.getValue());
        assertEquals(CreateMoodViewModel.SET_ENERGY_LABEL + " (3)", energyLabel.getText());
        assertEquals(3, viewModel.getState().getEnergy());

        JPanel instrumentalnessPanel = (JPanel) view.getComponent(5);
        JLabel instrumentalnessLabel = (JLabel) instrumentalnessPanel.getComponent(0);
        JSlider instrumentalnessSlider = (JSlider) instrumentalnessPanel.getComponent(1);

        instrumentalnessSlider.setValue(4);
        assertEquals(4, instrumentalnessSlider.getValue());
        assertEquals(CreateMoodViewModel.SET_INSTRUMENTALNESS + " (4)", instrumentalnessLabel.getText());
        assertEquals(4, viewModel.getState().getInstrumentalness());

        JPanel livenessPanel = (JPanel) view.getComponent(6);
        JLabel livenessLabel = (JLabel) livenessPanel.getComponent(0);
        JSlider livenessSlider = (JSlider) livenessPanel.getComponent(1);

        livenessSlider.setValue(5);
        assertEquals(5, livenessSlider.getValue());
        assertEquals(CreateMoodViewModel.SET_LIVENESS + " (5)", livenessLabel.getText());
        assertEquals(5, viewModel.getState().getLiveness());

        JPanel speechinessPanel = (JPanel) view.getComponent(7);
        JLabel speechinessLabel = (JLabel) speechinessPanel.getComponent(0);
        JSlider speechinessSlider = (JSlider) speechinessPanel.getComponent(1);

        speechinessSlider.setValue(6);
        assertEquals(6, speechinessSlider.getValue());
        assertEquals(CreateMoodViewModel.SET_SPEECHINESS + " (6)", speechinessLabel.getText());
        assertEquals(6, viewModel.getState().getSpeechiness());

        JPanel valencePanel = (JPanel) view.getComponent(8);
        JLabel valenceLabel = (JLabel) valencePanel.getComponent(0);
        JSlider valenceSlider = (JSlider) valencePanel.getComponent(1);

        valenceSlider.setValue(7);
        assertEquals(7, valenceSlider.getValue());
        assertEquals(CreateMoodViewModel.SET_VALENCE + " (7)", valenceLabel.getText());
        assertEquals(7, viewModel.getState().getValence());
    }
}
