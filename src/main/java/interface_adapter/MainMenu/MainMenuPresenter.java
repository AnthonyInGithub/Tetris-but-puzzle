package interface_adapter.MainMenu;

import interface_adapter.LevelSelect.LevelSelectViewModel;
import use_case.MainScene.MainMenuOutputBoundary;
import use_case.MainScene.MainOutputData;
import view.MainSceneView;
import interface_adapter.History.HistoryViewModel;
import interface_adapter.History.HistoryState;
import interface_adapter.NormalGiven.ViewManagerModel;
// import interface_adapter.LevelSelect.LevelSelectViewModel;


public class MainMenuPresenter implements MainMenuOutputBoundary {

    private final HistoryViewModel historyViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LevelSelectViewModel levelSelectViewModel;


    public MainMenuPresenter(HistoryViewModel historyViewModel, ViewManagerModel viewManagerModel,
                                                LevelSelectViewModel levelSelectViewModel) {

        this.historyViewModel = historyViewModel;
        this.viewManagerModel = viewManagerModel;
        this.levelSelectViewModel = levelSelectViewModel;
    }

    @Override
    public void present(MainOutputData outputData) {
        // view.displayMessage(outputData.getResponseMessage());
    }
//
    @Override
    public void navigateToLevelsPage() {
        // view.navigateTo("LevelsPage");
        levelSelectViewModel.firePropertyChanged();
        viewManagerModel.setState(levelSelectViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void navigateToHistoryPage(){
        // System.out.println("11111111111111111111");
        historyViewModel.firePropertyChanged();
        viewManagerModel.setState(historyViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void navigateToBattlePage() {
        // view.navigateTo("BattlePage");
    }
}
