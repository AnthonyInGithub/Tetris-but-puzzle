package interface_adapter.EndingScene;

import interface_adapter.NormalGiven.NormalGivenViewModel;
import use_case.EndingScene.EndingSceneOutputBoundary;
import use_case.EndingScene.EndingSceneOutputData;

public class EndingScenePresenter implements EndingSceneOutputBoundary {
    EndingSceneViewModel endingSceneViewModel;
    /**
     * Constructor for the Presenter.
     *
     * @param endingSceneViewModel the ViewModel instance to update
     */
    public EndingScenePresenter(EndingSceneViewModel endingSceneViewModel, NormalGivenViewModel normalGivenViewModel) {
        this.endingSceneViewModel = endingSceneViewModel;
    }

    @Override
    public void execute(EndingSceneOutputData endingSceneOutputData) {
        endingSceneViewModel.setIsWin(endingSceneOutputData.getIsWin());
        if(endingSceneOutputData.getIsReturnClicked()) {

        }
    }


}
