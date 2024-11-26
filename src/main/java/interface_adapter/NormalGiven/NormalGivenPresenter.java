package interface_adapter.NormalGiven;

import use_case.NormalGiven.NormalGivenOutputBoundary;
import use_case.NormalGiven.NormalGivenOutputData;

/**
 * Presenter for the Normal Given Use Case.
 * Handles output data from the Interactor and updates the ViewModel.
 */
public class NormalGivenPresenter implements NormalGivenOutputBoundary {

    private final interface_adapter.NormalGiven.NormalGivenViewModel normalGivenViewModel;
    //private final GameOverView gameOverView;
    //private final GameSucceededView gameSucceededView;
    //private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for the Presenter.
     *
     * @param normalGivenViewModel the ViewModel instance to update
     */
    public NormalGivenPresenter(interface_adapter.NormalGiven.NormalGivenViewModel normalGivenViewModel) {
        this.normalGivenViewModel = normalGivenViewModel;
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
        normalGivenViewModel.setMap(outputData.getMap());
        normalGivenViewModel.setSolutionMap(outputData.getSolutionMap());
        normalGivenViewModel.setColorMap(outputData.getColorMap());
        normalGivenViewModel.setImgAddress(outputData.getImgAddress());

    }

    @Override
    public void gameOver() {
//        viewManagerModel.setState(gameOverView.getViewName());
//        viewManagerModel.firePropertyChanged();
        System.out.println("Game Over");
    }

    @Override
    public void gameSucceeded() {
//        viewManagerModel.setState(gameSucceededView.getViewName());
//        viewManagerModel.firePropertyChanged();
    }
}
