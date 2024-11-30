package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.FileDataAccessObject;
import interface_adapter.EndingScene.EndingSceneController;
import interface_adapter.EndingScene.EndingScenePresenter;
import interface_adapter.EndingScene.EndingSceneViewModel;
import interface_adapter.History.HistoryController;
import interface_adapter.History.HistoryViewModel;
import interface_adapter.History.HistoryPresenter;
import interface_adapter.LevelSelect.LevelSelectController;
import interface_adapter.LevelSelect.LevelSelectPresenter;
import interface_adapter.LevelSelect.LevelSelectViewModel;
import interface_adapter.MainMenu.MainMenuController;
import interface_adapter.MainMenu.MainMenuPresenter;
import interface_adapter.MainMenu.MainMenuViewModel;
import interface_adapter.NormalGiven.NormalGivenController;
import data_access.InMemoryDataAccessObject;
import interface_adapter.NormalGiven.NormalGivenPresenter;
import interface_adapter.NormalGiven.ViewManagerModel;
import use_case.EndingScene.EndingSceneInteractor;
import use_case.EndingScene.EndingSceneOutputBoundary;
import use_case.History.HistoryInterator;
import use_case.History.HistoryOutputBoundary;
import use_case.LevelSelect.LevelSelectInteractor;
import use_case.LevelSelect.LevelSelectOutputBoundary;
import use_case.MainScene.MainMenuOutputBoundary;
import use_case.MainScene.MainSceneInteractor;
import view.*;
import use_case.NormalGiven.NormalGivenInputBoundary;
import use_case.NormalGiven.NormalGivenInteractor;
import use_case.NormalGiven.NormalGivenOutputBoundary;
import interface_adapter.NormalGiven.NormalGivenViewModel;

/**
 * The AppBuilder class is responsible for assembling and configuring
 * the components of the Tetris application based on Clean Architecture.
 */
public class AppBuilder {

    // CardLayout and JPanel for managing multiple views
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private ViewManagerModel viewManagerModel = new ViewManagerModel();
    private ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel, null);

    // Core components
    private final InMemoryDataAccessObject dataAccessObject = new InMemoryDataAccessObject();
    private final FileDataAccessObject fileAccessObject = new FileDataAccessObject();

    private final NormalGivenViewModel normalGivenViewModel = new NormalGivenViewModel();
    private final EndingSceneViewModel endingSceneViewModel = new EndingSceneViewModel();
    private final HistoryViewModel historyViewModel = new HistoryViewModel();
    private final MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
    private final LevelSelectViewModel levelSelectViewModel = new LevelSelectViewModel();

    // Views
    private NormalGivenView normalGivenView;
    private EndingSceneView endingSceneView;
    private HistoryView historyView;
    private MainSceneView mainMenuView;
    private LevelSelectView levelSelectView;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the NormalGivenView to the application.
     *
     * @return this builder
     */
    public AppBuilder addNormalGivenView() {
        // Initialize the NormalGivenView
        normalGivenView = new NormalGivenView(normalGivenViewModel); // Controller will be set later
        cardPanel.add(normalGivenView, normalGivenViewModel.getViewName());
        return this;
    }
    public AppBuilder addEndingSceneView(){
        endingSceneView = new EndingSceneView(endingSceneViewModel);
        cardPanel.add(endingSceneView, endingSceneViewModel.getViewName());
        return this;
    }

    public AppBuilder addHistoryView(){
        historyView = new HistoryView(historyViewModel);
        cardPanel.add(historyView, historyViewModel.getViewName());
        return this;
    }

    public AppBuilder addMainMenuView(){
        mainMenuView = new MainSceneView(mainMenuViewModel);
        cardPanel.add(mainMenuView, mainMenuViewModel.getViewName());
        return this;
    }
    public AppBuilder addLevelSelectView(){
        levelSelectView = new LevelSelectView(levelSelectViewModel);
        cardPanel.add(levelSelectView, levelSelectViewModel.getViewName());
        return this;
    }

    /**
     * Adds the NormalGiven Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addNormalGivenUseCase() {
        // Step 1: Initialize Presenter
        final NormalGivenOutputBoundary presenter = new NormalGivenPresenter(viewManagerModel, normalGivenViewModel, endingSceneViewModel);

        // Step 2: Initialize Interactor
        final NormalGivenInputBoundary interactor = new NormalGivenInteractor(dataAccessObject, presenter, normalGivenView);

        // Step 3: Initialize Controller
        final NormalGivenController controller = new NormalGivenController(interactor);

        // Step 4: Bind Controller to View
        normalGivenView.setNormalGivenController(controller);
        return this;
    }
    public AppBuilder addEndingSceneUseCase() {
        final EndingSceneOutputBoundary endingScenePresenter = new EndingScenePresenter(endingSceneViewModel, mainMenuViewModel, viewManagerModel);

        final EndingSceneInteractor endingSceneInteractor = new EndingSceneInteractor(dataAccessObject, endingScenePresenter, fileAccessObject);

        final EndingSceneController endingSceneController = new EndingSceneController(endingSceneInteractor);

        endingSceneView.setEndingSceneController(endingSceneController);

        return this;
    }
    public AppBuilder addHistoryUseCase() {
        final HistoryOutputBoundary historyPresenter = new HistoryPresenter(viewManagerModel, historyViewModel, mainMenuViewModel);

        final HistoryInterator historyInterator = new HistoryInterator(dataAccessObject, historyPresenter, fileAccessObject);

        final HistoryController historyController = new HistoryController(historyInterator);

        historyView.setHistoryController(historyController);

        return this;
    }
    public AppBuilder addMainMenuUseCase(){
        final MainMenuOutputBoundary mainMenuPresenter = new MainMenuPresenter(historyViewModel, viewManagerModel, levelSelectViewModel);

        final MainSceneInteractor mainSceneInteractor = new MainSceneInteractor(mainMenuPresenter);

        final MainMenuController mainMenuController = new MainMenuController(mainSceneInteractor);

        mainMenuView.setMainMenuController(mainMenuController);

        return this;
    }
    public AppBuilder addLevelSelectUseCase(){
        final LevelSelectOutputBoundary levelSelectPresenter = new LevelSelectPresenter(normalGivenViewModel, viewManagerModel);

        final LevelSelectInteractor levelSelectInteractor = new LevelSelectInteractor(levelSelectPresenter, dataAccessObject);

        final LevelSelectController levelSelectController = new LevelSelectController(levelSelectInteractor);

        levelSelectView.setLevelSelectController(levelSelectController);

        return this;
    }

    /**
     * Builds the JFrame for the application and shows the initial view.
     *
     * @return the configured JFrame
     */
    public JFrame build() {
        // Initialize the JFrame
        final JFrame application = new JFrame("Tetris - Build Shapes");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.add(cardPanel);
        // Set the initial view
        cardLayout.show(cardPanel, mainMenuViewModel.getViewName());
        //this allows us to change the windows size later
        viewManager.setJFrame(application);

        return application;
    }

    public void TestingSwtich(){
        cardLayout.show(cardPanel, "NormalGivenView");
    }
}
