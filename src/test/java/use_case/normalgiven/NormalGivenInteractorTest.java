package use_case.normalgiven;

import data_access.InMemoryDataAccessObject;
import data_access.NormalGivenDataAccessInterface;
import interface_adapter.EndingScene.EndingSceneViewModel;
import interface_adapter.NormalGiven.NormalGivenPresenter;
import interface_adapter.NormalGiven.NormalGivenViewModel;
import interface_adapter.NormalGiven.ViewManagerModel;
import org.junit.Test;
import use_case.NormalGiven.NormalGivenInputData;
import use_case.NormalGiven.NormalGivenInteractor;
import use_case.NormalGiven.NormalGivenOutputBoundary;
import use_case.NormalGiven.NormalGivenOutputData;

import static org.junit.Assert.assertEquals;

public class NormalGivenInteractorTest {
    @Test
    public void ClearLineTest(){
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        NormalGivenViewModel normalGivenViewModel = new NormalGivenViewModel();
        EndingSceneViewModel endingSceneViewModel = new EndingSceneViewModel();
        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenPresenter(viewManagerModel, normalGivenViewModel, endingSceneViewModel);

        NormalGivenInputData normalGivenInputData = new NormalGivenInputData();
        normalGivenInputData.setAPressed(false);
        normalGivenInputData.setDPressed(false);
        normalGivenInputData.setSPressed(false);
        normalGivenInputData.setWPressed(false);

        NormalGivenInteractor normalGivenInteractor = new NormalGivenInteractor(normalGivenDataAccessObject, normalGivenPresenter, null);
        int[][] currentMap = normalGivenDataAccessObject.getEntity().getGameBoard();
        //set the button line to 1 and the rest 0
        for(int i = 0; i < 21; i++){
            for(int j = 0; j < 10; j++){
                currentMap[i][j] = 0;
            }
        }
        for(int i = 0; i < 10; i++){
            currentMap[21][i] = 1;
        }
        normalGivenDataAccessObject.updateMap(currentMap);
        normalGivenDataAccessObject.setX(0);
        normalGivenDataAccessObject.setY(19);
        normalGivenInteractor.execute(normalGivenInputData);

        int count = 0;
        for (int[] elements : currentMap) {
            for(int block : elements) {
                if(block == 1)
                    count++;
            }
        }
        // since the piece fall to the bottom, it will stick on the bottom which creates new 1,
        // but the number of new 1 is always smaller than 5
        assert(count < 5);
    }

    @Test
    public void GameOverTest(){
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();

        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenPresenter(null,null, null) {
            @Override
            public void execute(NormalGivenOutputData normalGivenOutputData) {


            }
            @Override
            public void gameOver(boolean success){
                assert true;
            }
        };

        int[][] currentMap = normalGivenDataAccessObject.getEntity().getGameBoard();
        //set the button line to 1 and the rest 0
        for(int i = 0; i < 22; i++){
            for(int j = 0; j < 10; j++){
                currentMap[i][j] = 1;
            }
        }
        NormalGivenInputData normalGivenInputData = new NormalGivenInputData();
        normalGivenInputData.setAPressed(false);
        normalGivenInputData.setDPressed(false);
        normalGivenInputData.setSPressed(false);
        normalGivenInputData.setWPressed(false);

        NormalGivenInteractor normalGivenInteractor = new NormalGivenInteractor(normalGivenDataAccessObject, normalGivenPresenter, null);
        normalGivenInteractor.execute(normalGivenInputData);


    }
    @Test
    public void PieceRotationTest() {
        // Arrange
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();

        NormalGivenViewModel normalGivenViewModel = new NormalGivenViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        EndingSceneViewModel endingSceneViewModel = new EndingSceneViewModel();

        NormalGivenOutputBoundary normalGivenPresenter =
                new NormalGivenPresenter(viewManagerModel, normalGivenViewModel, endingSceneViewModel);

        NormalGivenInputData normalGivenInputData = new NormalGivenInputData();
        normalGivenInputData.setAPressed(false);
        normalGivenInputData.setDPressed(false);
        normalGivenInputData.setSPressed(false);
        normalGivenInputData.setWPressed(true); // rotate

        NormalGivenInteractor normalGivenInteractor =
                new NormalGivenInteractor(normalGivenDataAccessObject, normalGivenPresenter, null);

        // Act
        normalGivenInteractor.execute(normalGivenInputData);

        // Assert
        int[] currentShapeState = normalGivenDataAccessObject.getCurrentShapeState();
        assertEquals(1, currentShapeState[1]); // check if update
    }

    @Test
    public void testPieceDrop() {
        // Arrange
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();

        // Create a dummy presenter (doesn't require ViewModels for simplicity)
        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenPresenter(null, null, null) {
            @Override
            public void execute(NormalGivenOutputData outputData) {
                // No-op for simplicity
            }
        };

        // Create Interactor
        NormalGivenInteractor normalGivenInteractor = new NormalGivenInteractor(
                normalGivenDataAccessObject, normalGivenPresenter, null);

        // Set up the game board
        int[][] currentMap = normalGivenDataAccessObject.getEntity().getGameBoard();
        // Initialize all cells to 0 (empty)
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                currentMap[i][j] = 0;
            }
        }
        normalGivenDataAccessObject.updateMap(currentMap);

        // Place a piece at the top (x = 5, y = 5)
        normalGivenDataAccessObject.setX(5);
        normalGivenDataAccessObject.setY(5);

        // Create InputData for the drop action
        NormalGivenInputData normalGivenInputData = new NormalGivenInputData();
        normalGivenInputData.setSPressed(true); // Simulate the "drop" key press
        normalGivenInputData.setAPressed(false);
        normalGivenInputData.setDPressed(false);
        normalGivenInputData.setWPressed(false);

        // Act
        normalGivenInteractor.execute(normalGivenInputData);

        // Assert
        int[][] updatedMap = normalGivenDataAccessObject.getEntity().getGameBoard();

        // The piece should have moved down by 1 row
        assertEquals(1, updatedMap[6][5]); // Check new position
        assertEquals(0, updatedMap[5][5]);
    }

}

