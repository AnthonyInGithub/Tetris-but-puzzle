package interface_adapter.EndingScene;

import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.NormalGiven.NormalGivenState;
import interface_adapter.NormalGiven.NormalGivenViewModel;
import interface_adapter.NormalGiven.ViewManagerModel;
import use_case.EndingScene.EndingSceneOutputBoundary;
import use_case.EndingScene.EndingSceneOutputData;

// for testing history purpose ----------------------------------------------------------------------
//import interface_adapter.History.HistoryState;
//import interface_adapter.History.HistoryViewModel;

public class EndingScenePresenter implements EndingSceneOutputBoundary {
    private EndingSceneViewModel endingSceneViewModel;
    private MainMenuViewModel mainMenuViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for the Presenter.
     *
     * @param endingSceneViewModel the ViewModel instance to update
     */
    public EndingScenePresenter(EndingSceneViewModel endingSceneViewModel, MainMenuViewModel mainMenuViewModel, ViewManagerModel viewManagerModel) {
        this.endingSceneViewModel = endingSceneViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void execute(EndingSceneOutputData endingSceneOutputData) {
        if(endingSceneOutputData.getIsSaveSuccess()){
            endingSceneViewModel.setSavedSuccess(endingSceneOutputData.getIsSaveSuccess());
            // for testing history purpose ----------------------------------------------------------
//            HistoryViewModel historyViewModel = new HistoryViewModel();
//            historyViewModel.firePropertyChanged();
//            viewManagerModel.setState(historyViewModel.getViewName());
//            viewManagerModel.firePropertyChanged();
        }
        if(endingSceneOutputData.getIsReturnClicked()) {
            // This set the data needed at the start of next view
            mainMenuViewModel.firePropertyChanged();

            // This cause the change of scene
            viewManagerModel.setState(mainMenuViewModel.getViewName());
            viewManagerModel.firePropertyChanged();
        }

    }


}
