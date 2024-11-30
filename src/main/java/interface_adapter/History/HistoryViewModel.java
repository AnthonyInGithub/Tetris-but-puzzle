package interface_adapter.History;

import interface_adapter.NormalGiven.ViewModelMain;

public class HistoryViewModel extends ViewModelMain<HistoryState>{

    public HistoryViewModel() {
        super("History");
        setState(new HistoryState());
    }
}
