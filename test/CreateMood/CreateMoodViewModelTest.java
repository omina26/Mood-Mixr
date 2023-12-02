package CreateMood;

import interface_adapter.create_mood.CreateMoodViewModel;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;

public class CreateMoodViewModelTest {

    @Test
    public void testAddPropertyChangeListener(){
        CreateMoodViewModel viewModel = new CreateMoodViewModel();
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
