package use_case.EndingScene;

import data_access.EndingSceneDataAccessInterface;

public class EndingSceneInteractor implements EndingSceneInputBoundary{
    public final EndingSceneDataAccessInterface endingSceneDataAccessObject;
    private final EndingSceneOutputBoundary endingScenePresenter;
    public EndingSceneInteractor(EndingSceneDataAccessInterface endingSceneDataAccessObject,
                                 EndingSceneOutputBoundary endingScenePresenter) {
        super();
        this.endingSceneDataAccessObject = endingSceneDataAccessObject;
        this.endingScenePresenter = endingScenePresenter;
    }


    @Override
    public void execute(EndingSceneInputData inputData) {
        endingScenePresenter.execute(new EndingSceneOutputData
                (inputData.isReturnClicked(), inputData.isSaveClicked(),
                        endingSceneDataAccessObject.getIsWin()));
    }
}
