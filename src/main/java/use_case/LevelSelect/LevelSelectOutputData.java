package use_case.LevelSelect;

/**
 * Output Data for the Level Select Use Case.
 * Encapsulates the data to be presented to the user.
 */
public class LevelSelectOutputData {
    private final int selectedLevel;

    public LevelSelectOutputData(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }
}