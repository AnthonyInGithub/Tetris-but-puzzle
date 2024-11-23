package interface_adapter.EndingScene;

import use_case.EndingScene.EndingSceneInputBoundary;
import use_case.EndingScene.EndingSceneInputData;

public class EndingSceneController {
    private final EndingSceneInputBoundary endingSceneInputBoundary;
    public EndingSceneController(EndingSceneInputBoundary endingSceneInputBoundary1) {
        this.endingSceneInputBoundary = endingSceneInputBoundary1;
    }

    public void execute(boolean isSaveClicked, boolean isReturnClicked){
        final EndingSceneInputData endingSceneInputData = new EndingSceneInputData();
        endingSceneInputData.setSaveClicked(isSaveClicked);
        endingSceneInputData.setReturnClicked(isReturnClicked);
        endingSceneInputBoundary.execute(endingSceneInputData);
    }
}
