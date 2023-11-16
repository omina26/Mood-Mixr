package interface_adapter.view_moods;

<<<<<<< HEAD
import interface_adapter.ViewModel;
import interface_adapter.create_mood.CreateMoodState;

import javax.swing.text.View;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewMoodsViewModel extends ViewModel {

    public final String TITLE_LABEL = "View Moods";

    private ViewMoodsState state = new ViewMoodsState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public ViewMoodsViewModel(){super("view moods");}

    public void setState(ViewMoodsState state){this.state = state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ViewMoodsState getState(){return state;}
=======
public class ViewMoodsViewModel {
>>>>>>> 0e14d91b4acbfe3ff49747794046439a63130d26
}
