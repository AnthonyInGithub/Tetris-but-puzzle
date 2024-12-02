package interface_adapter.History;

import interface_adapter.ViewModelMain;

public class HistoryViewModel extends ViewModelMain<HistoryState>{

    public HistoryViewModel() {
        super("History");
        setState(new HistoryState());
    }
}
