package java.interface_adapter.NormalGiven;

import java.use_case.NormalGiven.NormalGivenOutputBoundary;
import java.use_case.NormalGiven.NormalGivenOutputData;
import java.interface_adapter.NormalGiven.ViewModel;

/**
 * Presenter for the Normal Given Use Case.
 * Handles output data from the Interactor and updates the ViewModel.
 */
public class NormalGivenPresenter implements NormalGivenOutputBoundary {
    private final ViewModel viewModel;

    /**
     * Constructor for the Presenter.
     *
     * @param viewModel the ViewModel instance to update
     */
    public NormalGivenPresenter(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Executes the Presenter logic, updating the ViewModel with the output data.
     *
     * @param outputData the data to be presented
     */
    @Override
    public void execute(NormalGivenOutputData outputData) {
        if (outputData == null) {
            throw new IllegalArgumentException("Output data cannot be null");
        }

        // Update the ViewModel with the map from the output data
        viewModel.setMap(outputData.getMap());
    }

    @Override
    public void gameOver() {

    }
}
