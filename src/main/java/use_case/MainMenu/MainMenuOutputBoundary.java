package use_case.MainMenu;



public interface MainMenuOutputBoundary {
    void present(MainOutputData outputData);

    void navigateToLevelsPage();

    void navigateToHistoryPage(); // New navigation method

    void navigateToNormalGivenPage(MainOutputData outputData);
}

