package interface_adapter.MainMenu;

import interface_adapter.LevelSelect.LevelSelectViewModel;
import interface_adapter.NormalGiven.NormalGivenState;
import interface_adapter.NormalGiven.NormalGivenViewModel;
import use_case.MainMenu.MainMenuOutputBoundary;
import use_case.MainMenu.MainOutputData;
import interface_adapter.History.HistoryViewModel;
import interface_adapter.ViewManagerModel;
// import interface_adapter.LevelSelect.LevelSelectViewModel;


public class MainMenuPresenter implements MainMenuOutputBoundary {

    private final HistoryViewModel historyViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LevelSelectViewModel levelSelectViewModel;
    private final NormalGivenViewModel normalGivenViewModel;


    public MainMenuPresenter(HistoryViewModel historyViewModel, ViewManagerModel viewManagerModel,
                                                LevelSelectViewModel levelSelectViewModel, NormalGivenViewModel normalGivenViewModel) {

        this.historyViewModel = historyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.levelSelectViewModel = levelSelectViewModel;
        this.normalGivenViewModel = normalGivenViewModel;
    }

    @Override
    public void present(MainOutputData outputData) {
        // view.displayMessage(outputData.getResponseMessage());
    }
//
    @Override
    public void navigateToLevelsPage(MainOutputData outputData) {
        // view.navigateTo("LevelsPage");
        levelSelectViewModel.firePropertyChanged();
        viewManagerModel.setState(levelSelectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void navigateToHistoryPage(MainOutputData outputData){
        // System.out.println("11111111111111111111");
        historyViewModel.firePropertyChanged();
        viewManagerModel.setState(historyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    @Override
    public void navigateToNormalGivenPage(MainOutputData outputData) {
        NormalGivenState state = normalGivenViewModel.getState();
        state.setGamingState("playing");
        state.setImgAddress(outputData.getImgAddress());

        normalGivenViewModel.setState(state);
        normalGivenViewModel.firePropertyChanged();

        viewManagerModel.setState(normalGivenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
