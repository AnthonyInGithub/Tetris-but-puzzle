package use_case.LevelSelect;

/**
 * Output Data for the Level Select Use Case.
 * Encapsulates the data to be presented to the user.
 */
public class LevelSelectOutputData {
    private final int selectedLevel;
    private final String imagePath;

    public LevelSelectOutputData(int selectedLevel, String imagePath1) {
        this.selectedLevel = selectedLevel;
        this.imagePath = imagePath1;
    }

    public int getSelectedLevel() {
        return selectedLevel;
    }
    public String getImageAddress(){
        return imagePath;
    }


}