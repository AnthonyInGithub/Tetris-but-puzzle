package use_case.NormalGiven;

public class NormalGivenMainSceneInteractor {

    private final MainMenuOutputBoundary presenter;


    public NormalGivenMainSceneInteractor(MainMenuOutputBoundary presenter) {
        this.presenter = presenter;
    }

    public void handleMainMenuAction(MainInputData inputData) {
        String buttonName = inputData.getButtonName();

        if ("StartButton".equals(buttonName)) {
            presenter.navigateToLevelsPage();
        }
        if ("HistoryButton".equals(buttonName)) {
            presenter.navigateToHistoryPage();
        }
        if ("BattleButton".equals(buttonName)) {
            presenter.navigateToBattlePage();
        }
        if ("MyOwnUploadButton".equals(buttonName)) {
            handleUpload();
        }
        else {
            presenter.present(new MainOutputData("Invalid action: " + buttonName));
        }
    }

    public void handleLevelSelection(int level) {
        if (level == 1) {
            presenter.present(new MainOutputData("Starting Level 1"));
        }if (level == 2) {
            presenter.present(new MainOutputData("Starting Level 2"));
        }if (level == 3) {
            presenter.present(new MainOutputData("Starting Level 3"));
        }
       else {
          presenter.present(new MainOutputData("Invalid level: " + level));
      }
    }

    public void handleHistoryAction(String buttonName) {
        if ("ReturnButton".equals(buttonName)) {
            presenter.present(new MainOutputData("Returning to Main Menu"));
        }
        if ("NextHistoryButton".equals(buttonName)) {
            presenter.present(new MainOutputData("Navigating to the next history page"));
        }
        if ("LastHistoryButton".equals(buttonName)) {
            presenter.present(new MainOutputData("Navigating to the previous history page"));
        }
        else {
          presenter.present(new MainOutputData("Invalid history action: " + buttonName));
      }
    }

    public void handleManualEnd() {
        presenter.present(new MainOutputData("Ending the game manually"));
    }

    private boolean handleFileUpload() {
        return true; // Assume upload is successful
    }
}

