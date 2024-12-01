package use_case.MainMenu;

public interface MainMenuInputBoundary {

    /**
     * Handles actions triggered in the main menu, such as button clicks.
     *
     * @param inputData The input data encapsulating the button action and optional file path.
     */
    void execute(MainInputData inputData);
}
