package use_case.LevelSelect;

import data_access.LevelSelectDataAccessInterface;
import interface_adapter.LevelSelect.LevelSelectOutputBoundary;
import data_access.LevelSelectDataAccessInterface;

/**
 * Interactor for the Level Select Use Case.
 * Implements the core logic for processing level selection.
 */
public class LevelSelectInteractor implements LevelSelectInputBoundary {
    private final LevelSelectOutputBoundary presenter;
    private final LevelSelectDataAccessInterface dataAccess;

    public LevelSelectInteractor(LevelSelectOutputBoundary presenter, LevelSelectDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void selectLevel(int level) {
        // Save the selected level
        dataAccess.setSelectedLevel(level);

        // Prepare output data
        LevelSelectOutputData outputData = new LevelSelectOutputData(level);

        // Pass the data to the Presenter
        presenter.presentLevelSelected(outputData.getSelectedLevel());
    }
}