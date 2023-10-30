package interface_adapter;

import view.ViewManager;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {

    private String activeView;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setActiveView(String activeView){this.activeView = activeView;}

    public void firePropertyChanged(){support.firePropertyChange("view", null, this.activeView);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
