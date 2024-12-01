package use_case.MainScene;



public interface MainMenuOutputBoundary {
    void present(MainOutputData outputData);

    void navigateToLevelsPage(MainOutputData outputData);

    void navigateToHistoryPage(MainOutputData outputData); // New navigation method

    void navigateToBattlePage(MainOutputData outputData); // New navigation method
}

