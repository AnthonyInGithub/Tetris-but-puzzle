package interface_adapter.History;

import use_case.History.HistoryOutputBoundary;
import use_case.History.HistoryOutputData;
import interface_adapter.NormalGiven.ViewManagerModel;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.MainMenu.MainMenuState;

public class HistoryPresenter implements HistoryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final MainMenuViewModel mainMenuViewModel;
    private final HistoryViewModel historyViewModel;

    public HistoryPresenter(ViewManagerModel viewManagerModel,
                            HistoryViewModel historyViewModel, MainMenuViewModel mainMenuViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.historyViewModel = historyViewModel;
        this.mainMenuViewModel = mainMenuViewModel;
    }

    @Override
    public void switchToHomepage(HistoryOutputData historyOutputData) {
        System.out.println("switched To Homepage");
        viewManagerModel.setState(mainMenuViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
