package interface_adapter.LevelSelect;

import interface_adapter.NormalGiven.ViewModelMain;
import inter.NormalGiven.ViewModelAll;

/**
 * ViewModel for the Level Select Use Case.
 * Stores the selected level.
 */
public class LevelSelectViewModel extends ViewModelMain<Integer> {

    public LevelSelectViewModel(String viewName) {
        super("LevelSelectView");
    }
}
