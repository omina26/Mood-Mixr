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

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;


public class CreatePlaylistViewTest {

    @Test
    public void testCreatePlaylistView(){
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MockInteractor interactor = new MockInteractor();
        CreatePlaylistController controller = new CreatePlaylistController(interactor);
        CreatePlaylistViewModel viewModel = new CreatePlaylistViewModel();

        JPanel view = new CreatePlaylistView(viewManagerModel, viewModel, controller);
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


    }
}

class MockInteractor implements CreatePlaylistInputBoundary{
    String execution = "";
    @Override
    public void execute(CreatePlaylistInputData createPlaylistInputData) {
        execution = createPlaylistInputData.getSelectedMoodName();}
}
