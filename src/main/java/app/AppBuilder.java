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

    private final NormalGivenViewModel viewModel = new NormalGivenViewModel();
    private final EndingSceneViewModel endingSceneViewModel = new EndingSceneViewModel();
    private final HistoryViewModel historyViewModel = new HistoryViewModel();
    private final MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();

    // Views
    private NormalGivenView normalGivenView;
    private EndingSceneView endingSceneView;
    private HistoryView historyView;
    private MainSceneView mainSceneView;

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
        normalGivenView = new NormalGivenView(viewModel); // Controller will be set later
        cardPanel.add(normalGivenView, normalGivenView.getViewName());
        return this;
    }
    public AppBuilder addEndingSceneView(){
        endingSceneView = new EndingSceneView(endingSceneViewModel);
        cardPanel.add(endingSceneView, endingSceneView.getViewName());
        return this;
    }

    public AppBuilder addHistoryView(){
        historyView = new HistoryView(historyViewModel);
        cardPanel.add(historyView, historyView.getViewName());
        return this;
    }

    public AppBuilder addMainMenuView(){
        mainSceneView = new MainSceneView(mainMenuViewModel);
        cardPanel.add(mainSceneView, mainSceneView.getViewName());
        return this;
    }

    /**
     * Adds the NormalGiven Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addNormalGivenUseCase() {
        // Step 1: Initialize Presenter
        final NormalGivenOutputBoundary presenter = new NormalGivenPresenter(viewManagerModel, viewModel, endingSceneViewModel);

        // Step 2: Initialize Interactor
        final NormalGivenInputBoundary interactor = new NormalGivenInteractor(dataAccessObject, presenter, normalGivenView);

        // Step 3: Initialize Controller
        final NormalGivenController controller = new NormalGivenController(interactor);

        // Step 4: Bind Controller to View
        normalGivenView.setNormalGivenController(controller);
        return this;
    }
    public AppBuilder addEndingSceneUseCase() {
        final EndingSceneOutputBoundary endingScenePresenter = new EndingScenePresenter(endingSceneViewModel, viewModel, viewManagerModel);

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
        final MainMenuOutputBoundary mainMenuPresenter = new MainMenuPresenter(historyViewModel, viewManagerModel/*, levelSelectViewModel*/);

        final MainSceneInteractor mainSceneInteractor = new MainSceneInteractor(mainMenuPresenter);

        final MainMenuController mainMenuController = new MainMenuController(mainSceneInteractor);

        mainSceneView.setMainMenuController(mainMenuController);

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
        cardLayout.show(cardPanel, mainSceneView.getViewName());
        //this allows us to change the windows size later
        viewManager.setJFrame(application);

        return application;
    }

    public void TestingSwtich(){
        cardLayout.show(cardPanel, "NormalGivenView");
    }
}
