package interface_adapter.LevelSelect;

import use_case.LevelSelect.LevelSelectInputBoundary;
import use_case.LevelSelect.LevelSelectInputData;

/**
 * Controller for the Level Select Use Case.
 * Handles user input and forwards it to the Interactor.
 */
public class LevelSelectController {
    private final LevelSelectInputBoundary interactor;

    public LevelSelectController(LevelSelectInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void selectLevel(int level) {
        // Create input data
        LevelSelectInputData inputData = new LevelSelectInputData(level);

        // Pass input data to the Interactor
        interactor.selectLevel(inputData.getSelectedLevel());
    }
}