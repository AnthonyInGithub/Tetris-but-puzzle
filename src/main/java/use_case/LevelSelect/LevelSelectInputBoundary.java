package use_case.LevelSelect;

/**
 * Input Boundary for the Level Select Use Case.
 * Defines the method to process level selection.
 */
public interface LevelSelectInputBoundary {
    /**
     * Processes the level selection.
     *
     * @param level The selected level (1, 2, or 3).
     */
    void selectLevel(int level);
}