package interface_adapter.NormalGiven;

import interface_adapter.EndingScene.EndingSceneState;
import interface_adapter.EndingScene.EndingSceneViewModel;
import interface_adapter.ViewManagerModel;
import use_case.NormalGiven.NormalGivenOutputBoundary;
import use_case.NormalGiven.NormalGivenOutputData;

/**
 * Presenter for the Normal Given Use Case.
 * Handles output data from the Interactor and updates the ViewModel.
 */
public class NormalGivenPresenter implements NormalGivenOutputBoundary {

    private final interface_adapter.NormalGiven.NormalGivenViewModel normalGivenViewModel;
    private final EndingSceneViewModel endingSceneViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for the Presenter.
     *
     * @param normalGivenViewModel the ViewModel instance to update
     */
    public NormalGivenPresenter(ViewManagerModel viewManagerModel,
                                interface_adapter.NormalGiven.NormalGivenViewModel normalGivenViewModel,
                                EndingSceneViewModel endingSceneViewModel) {
        this.normalGivenViewModel = normalGivenViewModel;
        this.viewManagerModel = viewManagerModel;
        this.endingSceneViewModel = endingSceneViewModel;
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
        //normalGivenViewModel.setImgAddress(outputData.getImgAddress());

    }

    @Override
    public void gameOver(boolean success) {
        EndingSceneState endingSceneState = endingSceneViewModel.getState();
        endingSceneState.setWin(success);
        endingSceneViewModel.setState(endingSceneState);

        NormalGivenState normalGivenState = normalGivenViewModel.getState();
        normalGivenState.setGamingState("end");
        normalGivenViewModel.setState(normalGivenState);
        normalGivenViewModel.firePropertyChanged();

        viewManagerModel.setState(endingSceneViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
        //System.out.println("Game Over");
    }

}
