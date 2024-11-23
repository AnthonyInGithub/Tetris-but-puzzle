package interface_adapter.NormalGiven;

import use_case.MainScene.MainMenuInputBoundary;
import use_case.MainScene.MainInputData;

public class MainMenuController {

    private final MainMenuInputBoundary interactor;

    /**
     * Constructor for MainMenuController.
     *
     * @param interactor The interactor to handle main menu logic.
     */
    public MainMenuController(MainMenuInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Handles actions triggered by button clicks in the view.
     *
     * @param buttonName The name of the button clicked.
     */
    public void handleMainMenuAction(String buttonName) {
        MainInputData inputData = new MainInputData(buttonName);
        interactor.handleMainMenuAction(inputData);
    }
}