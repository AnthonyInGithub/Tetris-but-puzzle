package interface_adapter.LevelSelect;

import main.java.interface_adapter.ViewModelAll;

/**
 * ViewModel for the Level Select Use Case.
 * Stores the selected level.
 */
public class LevelSelectViewModel extends ViewModelAll<Integer> {

    public void setSelectedLevel(int level) {
        setState(level); // Update the state in ViewModelAll
    }

    public int getSelectedLevel() {
        return getState(); // Return the current state
    }
}