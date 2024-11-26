package interface_adapter.EndingScene;

import use_case.EndingScene.EndingSceneInputBoundary;
import use_case.EndingScene.EndingSceneInputData;

public class EndingSceneController {
    private final EndingSceneInputBoundary endingSceneInteractor;
    public EndingSceneController(EndingSceneInputBoundary endingSceneInteractor) {
        this.endingSceneInteractor = endingSceneInteractor;
    }

    public void execute(boolean isSaveClicked, boolean isReturnClicked){
        final EndingSceneInputData endingSceneInputData = new EndingSceneInputData();
        endingSceneInputData.setSaveClicked(isSaveClicked);
        endingSceneInputData.setReturnClicked(isReturnClicked);
        endingSceneInteractor.execute(endingSceneInputData);
    }
}
