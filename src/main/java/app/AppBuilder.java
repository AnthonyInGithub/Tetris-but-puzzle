package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import interface_adapter.NormalGiven.NormalGivenController;
import data_access.InMemoryDataAccessInterfaceObject;
import interface_adapter.NormalGiven.NormalGivenPresenter;
import view.NormalGivenView;
import use_case.NormalGiven.NormalGivenInputBoundary;
import use_case.NormalGiven.NormalGivenInteractor;
import use_case.NormalGiven.NormalGivenOutputBoundary;
import interface_adapter.NormalGiven.ViewModel;

/**
 * The AppBuilder class is responsible for assembling and configuring
 * the components of the Tetris application based on Clean Architecture.
 */
public class AppBuilder {

    // CardLayout and JPanel for managing multiple views
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    // Core components
    private final InMemoryDataAccessInterfaceObject dataAccessObject = new InMemoryDataAccessInterfaceObject();
    private final ViewModel viewModel = new ViewModel();

    // Views
    private NormalGivenView normalGivenView;

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
        cardPanel.add(normalGivenView, "NormalGivenView");
        return this;
    }

    /**
     * Adds the NormalGiven Use Case to the application.
     *
     * @return this builder
     */
    public AppBuilder addNormalGivenUseCase() {
        // Step 1: Initialize Presenter
        final NormalGivenOutputBoundary presenter = new NormalGivenPresenter(viewModel);

        // Step 2: Initialize Interactor
        final NormalGivenInputBoundary interactor = new NormalGivenInteractor(dataAccessObject, presenter);

        // Step 3: Initialize Controller
        final NormalGivenController controller = new NormalGivenController(interactor);

        // Step 4: Bind Controller to View
        normalGivenView.setNormalGivenController(controller);
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
        cardLayout.show(cardPanel, "NormalGivenView");

        return application;
    }
}
