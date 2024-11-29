package interface_adapter.History;

import use_case.History.HistoryOutputBoundary;
import use_case.History.HistoryOutputData;
import interface_adapter.NormalGiven.ViewManagerModel;
// import HomepageViewState here
// import HomepageViewModel here

public class HistoryPresenter implements HistoryOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    // private final HomepageViewModel homepageViewModel;
    private final HistoryViewModel historyViewModel;

    public HistoryPresenter(ViewManagerModel viewManagerModel,
                            HistoryViewModel historyViewModel/*, HomepageViewModel homepageViewModel*/) {
        this.viewManagerModel = viewManagerModel;
        this.historyViewModel = historyViewModel;
        // this.homepageViewModel = homepageViewModel
    }

    @Override
    public void switchToHomepage(HistoryOutputData historyOutputData) {

    }
}
