package java.app;

import java.interface_adapter.NormalGiven.NormalGivenController;
import java.data_access.InMemoryDataAccessObject;
import java.interface_adapter.NormalGiven.NormalGivenPresenter;
import java.view.NormalGivenView;
import java.use_case.NormalGiven.NormalGivenInputBoundary;
import java.use_case.NormalGiven.NormalGivenInteractor;
import java.use_case.NormalGiven.NormalGivenOutputBoundary;
import java.interface_adapter.NormalGiven.ViewModel;

import javax.swing.*;

/**
 * AppBuilder is responsible for initializing and starting the Tetris application.
 */
public class AppBuilder {
    /**
     * Builds and starts the Tetris application.
     */
    public void buildAndStartApp() {
        // Step 1: Initialize the Data Access Object (DAO)
        InMemoryDataAccessObject dataAccess = new InMemoryDataAccessObject();

        // Step 2: Create ViewModel
        ViewModel viewModel = new ViewModel();

        // Step 3: Initialize Presenter
        NormalGivenOutputBoundary presenter = new NormalGivenPresenter(viewModel);

        // Step 4: Initialize Interactor
        NormalGivenInputBoundary interactor = new NormalGivenInteractor(dataAccess, presenter);

        // Step 5: Initialize Controller
        NormalGivenController controller = new NormalGivenController(interactor);

        // Step 6: Initialize View
        NormalGivenView view = new NormalGivenView(controller, viewModel);

        // Step 7: Set up JFrame
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view); // Add the view to the frame
        frame.pack(); // Automatically size the frame to fit the components
        frame.setVisible(true); // Make the frame visible

        // Step 8: Start the View
        view.start();
    }
}