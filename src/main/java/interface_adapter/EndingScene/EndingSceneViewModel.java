package interface_adapter.EndingScene;

import interface_adapter.NormalGiven.ViewModelMain;

public class EndingSceneViewModel extends ViewModelMain<EndingSceneState> {
    public EndingSceneViewModel() {
        super("EndingSceneView");
        setState(new EndingSceneState());
    }
}
