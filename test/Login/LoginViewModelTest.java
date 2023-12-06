package Login;
import interface_adapter.login.LoginViewModel;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;

public class LoginViewModelTest {
    @Test
    public void testAddPropertyChangeListener(){
        LoginViewModel viewModel = new LoginViewModel();
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
