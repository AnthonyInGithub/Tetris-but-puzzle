package interface_adapter.mainScene;

import use_case.MainScene.MainMenuOutputBoundary;
import use_case.MainScene.MainOutputData;
import view.MainSceneView;

public class MainMenuPresenter implements MainMenuOutputBoundary {

    private final MainSceneView view;

    public MainMenuPresenter(MainSceneView view) {
        this.view = view;
    }

    @Override
    public void present(MainOutputData outputData) {
        view.displayMessage(outputData.getResponseMessage());
    }

    @Override
    public void navigateToLevelsPage() {
        view.navigateTo("LevelsPage");
    }

    @Override
    public void navigateToHistoryPage() {
        view.navigateTo("HistoryPage");
    }

    @Override
    public void navigateToBattlePage() {
        view.navigateTo("BattlePage");
    }
}
