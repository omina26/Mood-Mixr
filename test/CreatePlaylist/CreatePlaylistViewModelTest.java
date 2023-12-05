package CreatePlaylist;

import interface_adapter.create_playlist.CreatePlaylistViewModel;
import org.junit.Test;
import view.CreatePlaylistView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;


public class CreatePlaylistViewModelTest {

    @Test
    public void testAddPropertyChangeListener(){
        CreatePlaylistViewModel viewModel = new CreatePlaylistViewModel();

        PropertyChangeListener listener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };

        viewModel.addPropertyChangeListener(listener);
        assertEquals(viewModel.getSupport().getPropertyChangeListeners().length, 1);
        assertEquals(viewModel.getSupport().getPropertyChangeListeners()[0], listener);
    }
}
