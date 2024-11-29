package use_case.EndingScene;

public class EndingSceneInputData {
    private boolean isSaveClicked; //check whether save button clicked
    private boolean isReturnClicked;

    public boolean isSaveClicked() {

        return isSaveClicked;
    }
    public void setSaveClicked(boolean isSaveClicked) {
        /**
         * Sets whether the 'W' key is pressed.
         *
         * @param isWPressed true if 'W' is pressed, false otherwise
         */
        this.isSaveClicked = isSaveClicked;
    }
    public boolean isReturnClicked() {
        return isReturnClicked;
    }
    public void setReturnClicked(boolean isReturnClicked) {
        this.isReturnClicked = isReturnClicked;
    }
}
