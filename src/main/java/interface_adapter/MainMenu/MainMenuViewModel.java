package interface_adapter.MainMenu;

import interface_adapter.ViewModelMain;

public class MainMenuViewModel extends ViewModelMain<MainMenuState> {

    public MainMenuViewModel() {
        super("MainMenu");
        setState(new MainMenuState());
    }

}
