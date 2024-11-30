package use_case.LevelSelect;

import data_access.LevelSelectDataAccessInterface;
import use_case.LevelSelect.LevelSelectOutputBoundary;
import use_case.LevelSelect.LevelSelectOutputData;

/**
 * Interactor for the Level Select Use Case.
 * Handles level selection and updates the data access layer and presenter.
 */
public class LevelSelectInteractor implements LevelSelectInputBoundary {
    private final LevelSelectOutputBoundary presenter;
    private final LevelSelectDataAccessInterface dataAccess;

    /**
     * Constructor for LevelSelectInteractor.
     *
     * @param presenter the presenter to send output data to.
     * @param dataAccess the data access interface to update game state and image address.
     */
    public LevelSelectInteractor(LevelSelectOutputBoundary presenter, LevelSelectDataAccessInterface dataAccess) {
        this.presenter = presenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void selectLevel(int level) {
        // Validate the selected level
        if (level < 1 || level > 3) {
            throw new IllegalArgumentException("Invalid level: " + level);
        }

        // Update the current level in the data access layer
        dataAccess.setSelectedLevel(level);
        dataAccess.setImageAddress(); // Set the corresponding image address for the level
        dataAccess.setColorMapAndBinaryMap();

        dataAccess.resetCurrentMap();

        // Prepare output data with the selected level and image address
        String imageAddress = dataAccess.getImageAddressLevel();
        LevelSelectOutputData outputData = new LevelSelectOutputData(level, imageAddress);

        // Pass the full output data object to the Presenter
        presenter.presentLevelSelected(outputData);
    }
}
