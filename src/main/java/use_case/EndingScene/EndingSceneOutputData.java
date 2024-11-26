package use_case.EndingScene;

public class EndingSceneOutputData {
    private final boolean isReturnClicked;
    private final boolean isSaveClicked;
    private final boolean isWin;
    public EndingSceneOutputData(boolean isReturnClicked, boolean isSaveClicked, boolean isWin) {
        this.isReturnClicked = isReturnClicked;
        this.isSaveClicked = isSaveClicked;
        this.isWin = isWin;
    }
    public boolean getIsReturnClicked() {
        return isReturnClicked;
    }
    public boolean getIsSaveClicked() {
        return isSaveClicked;
    }
    public boolean getIsWin() {
        return isWin;
    }
}
