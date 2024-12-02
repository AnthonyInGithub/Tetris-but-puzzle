package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModelMain is a generic base class for all ViewModels in the application.
 * It manages state and supports property change notifications.
 *
 * @param <T> The type of the state managed by the ViewModel.
 */

public class ViewModelMain<T> {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this); // Event manager
    private T state; // The state managed by this ViewModel
    private final String viewName;

    public ViewModelMain(String viewName) {
        this.viewName = viewName;
    }
    /**
     * Gets the current state.
     *
     * @return the current state
     */
    public T getState() {
        return state;
    }

    /**
     * Sets the current state and notifies listeners.
     *
     * @param state the new state
     */
    public void setState(T state) {
        T oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, state); // Notify listeners
    }

    /**
     * This is call the function in viewManager which will set the cardLayOut
     */
    public void firePropertyChanged() {
        this.support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to this ViewModel.
     *
     * @param listener the listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from this ViewModel.
     *
     * @param listener the listener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public String getViewName() {
        return this.viewName;
    }

}

