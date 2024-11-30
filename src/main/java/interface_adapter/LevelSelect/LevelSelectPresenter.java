package interface_adapter.LevelSelect;

import interface_adapter.NormalGiven.NormalGivenState;
import interface_adapter.NormalGiven.NormalGivenViewModel;
import interface_adapter.NormalGiven.ViewManagerModel;
import use_case.LevelSelect.LevelSelectOutputBoundary;
import use_case.LevelSelect.LevelSelectOutputData;

/**
 * Presenter for the Level Select Use Case.
 * Updates the NormalGivenState and triggers a view transition.
 */
public class LevelSelectPresenter implements LevelSelectOutputBoundary {
    private final NormalGivenViewModel normalGivenViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor for LevelSelectPresenter.
     *
     * @param normalGivenViewModel the ViewModel to update with the selected level and image.
     * @param viewManagerModel the ViewManager to handle view transitions.
     */
    public LevelSelectPresenter(NormalGivenViewModel normalGivenViewModel, ViewManagerModel viewManagerModel) {
        this.normalGivenViewModel = normalGivenViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentLevelSelected(LevelSelectOutputData outputData) {
        // Update the NormalGivenState with the selected level and image address
        NormalGivenState state = normalGivenViewModel.getState();
        state.setGamingState("playing");
        state.setImgAddress(outputData.getImageAddress());
        normalGivenViewModel.setState(state);
        normalGivenViewModel.firePropertyChanged();

        // Transition to the NormalGivenView
        viewManagerModel.setState(normalGivenViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}

