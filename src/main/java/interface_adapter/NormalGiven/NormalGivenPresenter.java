package interface_adapter.NormalGiven;

import use_case.NormalGiven.NormalGivenOutputBoundary;
import use_case.NormalGiven.NormalGivenOutputData;

/**
 * Presenter for the Normal Given Use Case.
 * Handles output data from the Interactor and updates the ViewModel.
 */
public class NormalGivenPresenter implements NormalGivenOutputBoundary {
    private final ViewModel viewModel;
    private final GameOverView gameOverView;
    private final GameSucceededView gameSucceededView;
    private final ViewManagerModel viewManagerModel;

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
        viewManagerModel.setState(gameOverView.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void gameSucceeded() {
        viewManagerModel.setState(gameSucceededView.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
