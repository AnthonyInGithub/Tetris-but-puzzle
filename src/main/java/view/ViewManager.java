package view;

import interface_adapter.NormalGiven.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private final ViewManagerModel viewManagerModel;
    private JFrame frame;

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel, JFrame frame) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;

        this.viewManagerModel.addPropertyChangeListener(this);
        this.frame = frame;
    }

    //the initialization of frame is later than initialization of ViewManager
    public void setJFrame(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewModelName);

            // Dynamically find current view, then
            // Force the container to resize based on the new panel's preferred size
            Component currentView = null;
            for (Component comp : views.getComponents()) {
                if (comp.isVisible()) {
                    currentView = comp;
                    break;
                }
            }

            // Adjust the size dynamically if the current view is found
            if (currentView != null) {
                views.setPreferredSize(currentView.getPreferredSize());
                frame.pack(); // Adjust the frame size
            }

            frame.pack();
        }
    }
}
