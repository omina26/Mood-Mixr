package AnalyzePlaylist;

import interface_adapter.analyze_playlist.AnalyzePlaylistViewModel;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;

public class AnalyzePlaylistViewModelTest {
    @Test
    public void testAddPropertyChangeListener(){
        AnalyzePlaylistViewModel viewModel = new AnalyzePlaylistViewModel();
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
