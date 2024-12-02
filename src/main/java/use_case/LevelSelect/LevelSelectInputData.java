package use_case.LevelSelect;

/**
 * Input Data for the Level Select Use Case.
 * Encapsulates the data required to process level selection.
 */
public class LevelSelectInputData {
    private final int selectedLevel;

    public LevelSelectInputData(int selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }
}