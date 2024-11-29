package use_case.LevelSelect;

/**
 * Output Boundary for the Level Select Use Case.
 * Defines the method to present the selected level to the user.
 */
public interface LevelSelectOutputBoundary {
    void presentLevelSelected(LevelSelectOutputData outputData);

    /**
     * Presents the selected level to the user.
     *
     * @param level The selected level (1, 2, or 3).
     */
}