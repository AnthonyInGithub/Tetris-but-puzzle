package interface_adapter.MainMenu;

import interface_adapter.EndingScene.EndingSceneState;
import interface_adapter.NormalGiven.ViewModelMain;

public class MainMenuViewModel extends ViewModelMain<MainMenuState> {

    public MainMenuViewModel() {
        super("MainMenu");
        setState(new MainMenuState());
    }

}
