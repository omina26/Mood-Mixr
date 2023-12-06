package CreatePlaylist;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_playlist.CreatePlaylistController;
import interface_adapter.create_playlist.CreatePlaylistState;
import interface_adapter.create_playlist.CreatePlaylistViewModel;
import org.junit.Test;
import use_case.create_playlist.CreatePlaylistInputBoundary;
import use_case.create_playlist.CreatePlaylistInputData;
import view.CreatePlaylistView;
import view.LabelTextPanel;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;


public class CreatePlaylistViewTest {

    @Test
    public void testCreatePlaylistView(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MockInteractor interactor = new MockInteractor();
        CreatePlaylistController controller = new CreatePlaylistController(interactor);
        CreatePlaylistViewModel viewModel = new CreatePlaylistViewModel();


        CreatePlaylistView view = new CreatePlaylistView(viewManagerModel, viewModel, controller);
//        JFrame jf = new JFrame();
//
//        jf.setContentPane(view);
//        jf.pack();
//        jf.setVisible(true);

        JPanel buttons = (JPanel) view.getComponent(3);
        JButton menuButton = (JButton) buttons.getComponent(0);
        menuButton.doClick();
        assertEquals("logged in", viewManagerModel.getActiveView());

        LabelTextPanel playlistInfo = (LabelTextPanel) view.getComponent(2);
        JTextField moodField = (JTextField) playlistInfo.getComponent(1);

//        KeyEvent event = new KeyEvent(
//                moodField,
//                KeyEvent.KEY_TYPED,
//                System.currentTimeMillis(),
//                0,
//                KeyEvent.VK_UNDEFINED,
//                'm'
//        );
//        playlistInfo.dispatchEvent(event);

        moodField.setText("m");

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assertEquals("m", new String(moodField.getText()));

        JButton getButton = (JButton) buttons.getComponent(1);
        getButton.doClick();
        assertEquals("m", viewModel.getState().getSelectedMood());
        assertEquals(interactor.execution, "m");
//        assertEquals("playlist created", viewManagerModel.getActiveView());

 //       assertNotNull(view);

        CreatePlaylistState mockState = new CreatePlaylistState("m");
        Set<String> moods = new HashSet<>();
        moods.add("Happy");
        moods.add("Sad");
        moods.add("Energetic");
        mockState.setMoodsList(moods);

        PropertyChangeEvent mockEvent = new PropertyChangeEvent(this, "stateChange", null, mockState);
        view.propertyChange(mockEvent);
        JPanel moodPanel = (JPanel) view.getComponent(5);

        assertEquals(3, moodPanel.getComponentCount());
        assertEquals("Happy", ((JLabel) moodPanel.getComponent(0)).getText());
        assertEquals("Sad", ((JLabel) moodPanel.getComponent(2)).getText());
        assertEquals("Energetic", ((JLabel) moodPanel.getComponent(1)).getText());
    }
}

class MockInteractor implements CreatePlaylistInputBoundary{
    String execution = "";
    @Override
    public void execute(CreatePlaylistInputData createPlaylistInputData) {
        execution = createPlaylistInputData.getSelectedMoodName();}
}

class MockState extends CreatePlaylistState {
    Set<String> moodsList = null;

    /**
     * A constructor for the CreatePlaylistState object which sets selectedMood to the input
     *
     * @param moodName The selected mood
     */
    public MockState(String moodName) {
        super(moodName);
    }

    @Override
    public void setMoodsList(Set<String> moodsList) {
        this.moodsList = moodsList;
    }

    @Override
    public Set<String> getMoodsList() {
        return moodsList;
    }
}
