package use_case.MainMenu;



public interface MainMenuOutputBoundary {
    void present(MainOutputData outputData);

    void navigateToLevelsPage(MainOutputData outputData);

    void navigateToHistoryPage(MainOutputData outputData); // New navigation method

    void uploadClicked(MainOutputData outputData);
}

