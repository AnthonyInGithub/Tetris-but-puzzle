package interface_adapter.LevelSelect;

/**
 * Output Boundary for the Level Select Use Case.
 * Defines the method to present the selected level to the user.
 */
public interface LevelSelectOutputBoundary {
    /**
     * Presents the selected level to the user.
     *
     * @param level The selected level (1, 2, or 3).
     */
    void presentLevelSelected(int level);
}