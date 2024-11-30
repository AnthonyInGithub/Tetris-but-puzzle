package interface_adapter.History;

import use_case.History.HistoryInputBoundary;
import use_case.History.HistoryInputData;

public class HistoryController {
    private final HistoryInputBoundary historyInputBoundary;

    public HistoryController(HistoryInputBoundary historyInputBoundary) {
        this.historyInputBoundary = historyInputBoundary;
    }

    public void execute() {
        final HistoryInputData historyInputData = new HistoryInputData();

        historyInputBoundary.execute(historyInputData);
    }

}
