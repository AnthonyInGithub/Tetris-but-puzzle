package interface_adapter.LevelSelect;

import interface_adapter.LevelSelect.LevelSelectViewModel;

/**
 * Presenter for the Level Select Use Case.
 * Updates the ViewModel with the selected level.
 */
public class LevelSelectPresenter implements LevelSelectOutputBoundary {
    private final LevelSelectViewModel viewModel;

    public LevelSelectPresenter(LevelSelectViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentLevelSelected(int level) {
        viewModel.setSelectedLevel(level); // Update the ViewModel
    }
}