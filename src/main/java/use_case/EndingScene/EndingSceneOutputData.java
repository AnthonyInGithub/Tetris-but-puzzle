package use_case.EndingScene;

public class EndingSceneOutputData {
    private final boolean isReturnClicked;
    private final boolean isSaveClicked;
    public EndingSceneOutputData(boolean isReturnClicked, boolean isSaveClicked) {
        this.isReturnClicked = isReturnClicked;
        this.isSaveClicked = isSaveClicked;
    }
}
