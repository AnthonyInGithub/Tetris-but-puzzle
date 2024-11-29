package use_case.EndingScene;

public class EndingSceneOutputData {
    private final boolean isReturnClicked;
    private final boolean isSaveSuccess;
    public EndingSceneOutputData(boolean isReturnClicked, boolean isSaveSuccess) {
        this.isReturnClicked = isReturnClicked;
        this.isSaveSuccess = isSaveSuccess;
    }
    public boolean getIsReturnClicked() {
        return isReturnClicked;
    }
    public boolean getIsSaveSuccess() {
        return isSaveSuccess;
    }
}
