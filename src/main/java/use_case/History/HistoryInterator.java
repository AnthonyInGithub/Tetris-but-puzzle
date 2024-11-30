package use_case.History;

import data_access.HistoryDataAccessInterface;
import data_access.FileDataAccessObject;

public class HistoryInterator implements HistoryInputBoundary {

    private final HistoryDataAccessInterface historyDataAccessObject;
    private final HistoryOutputBoundary historyPresenter;
    private final FileDataAccessObject historyFileDataAccessObject;

    public HistoryInterator(HistoryDataAccessInterface historyDataAccessObject,
                            HistoryOutputBoundary historyPresenter,
                            FileDataAccessObject historyFileDataAccessObject) {
        this.historyDataAccessObject = historyDataAccessObject;
        this.historyPresenter = historyPresenter;
        this.historyFileDataAccessObject = historyFileDataAccessObject;
    }

    @Override
    public void execute(HistoryInputData inputData) {
        final HistoryOutputData historyOutputData = new HistoryOutputData();
        historyPresenter.switchToHomepage(historyOutputData);
    }
}
