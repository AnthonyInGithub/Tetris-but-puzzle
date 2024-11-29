package interface_adapter.EndingScene;

import interface_adapter.NormalGiven.NormalGivenState;
import interface_adapter.NormalGiven.NormalGivenViewModel;
import interface_adapter.NormalGiven.ViewManagerModel;
import use_case.EndingScene.EndingSceneOutputBoundary;
import use_case.EndingScene.EndingSceneOutputData;

public class EndingScenePresenter implements EndingSceneOutputBoundary {
    private EndingSceneViewModel endingSceneViewModel;
    private NormalGivenViewModel normalGivenViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for the Presenter.
     *
     * @param endingSceneViewModel the ViewModel instance to update
     */
    public EndingScenePresenter(EndingSceneViewModel endingSceneViewModel, NormalGivenViewModel normalGivenViewModel, ViewManagerModel viewManagerModel) {
        this.endingSceneViewModel = endingSceneViewModel;
        this.normalGivenViewModel = normalGivenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void execute(EndingSceneOutputData endingSceneOutputData) {
        if(endingSceneOutputData.getIsSaveSuccess()){
            endingSceneViewModel.setSavedSuccess(endingSceneOutputData.getIsSaveSuccess());
        }
        if(endingSceneOutputData.getIsReturnClicked()) {
            final NormalGivenState normalGivenState = normalGivenViewModel.getState();
            // This set the data needed at the start of next view
            normalGivenState.setGamingState("playing");
            normalGivenViewModel.setState(normalGivenState);
            normalGivenViewModel.firePropertyChanged();

            // This cause the change of scene
            viewManagerModel.setState(normalGivenViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

    }


}
