package use_case.NormalGiven;



public interface MainMenuOutputBoundary {
    void present(MainOutputData outputData);

    void navigateToLevelsPage();

    void navigateToHistoryPage(); // New navigation method

    void navigateToBattlePage(); // New navigation method
}

