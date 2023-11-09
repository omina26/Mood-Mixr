package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class LogInViewModel {

    private String viewName;

    public LogInViewModel(String viewName){this.viewName = viewName;}

    public String getViewName(){return this.viewName;}

    public abstract void firePropertyChanged();

    public abstract void addPropertyChangeListener(PropertyChangeListener listener);

}
