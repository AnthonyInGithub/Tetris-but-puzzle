package use_case.MainScene;



public interface MainMenuOutputBoundary {
    void present(MainOutputData outputData);

    void navigateToLevelsPage();

    void navigateToHistoryPage(); // New navigation method

    void navigateToBattlePage(); // New navigation method
}

