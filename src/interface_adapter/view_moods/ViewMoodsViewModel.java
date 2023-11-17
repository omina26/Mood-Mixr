package interface_adapter.view_moods;

<<<<<<< HEAD

=======
<<<<<<< HEAD
>>>>>>> ecbd1db8d74b68f709f77216f4d87229520c23b9
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
<<<<<<< HEAD

=======
=======
public class ViewMoodsViewModel {
>>>>>>> 0e14d91b4acbfe3ff49747794046439a63130d26
>>>>>>> ecbd1db8d74b68f709f77216f4d87229520c23b9
}
