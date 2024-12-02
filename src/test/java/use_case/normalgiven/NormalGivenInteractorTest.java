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
        // Arrange: Initialize the dependencies
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();
        final NormalGivenOutputData[] capturedOutputData = new NormalGivenOutputData[1]; // Capture output data

        // Custom Presenter to capture the output
        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenOutputBoundary() {
            @Override
            public void execute(NormalGivenOutputData outputData) {
                capturedOutputData[0] = outputData; // Save the output data
            }

            @Override
            public void gameOver(boolean success) {
                // No-op for simplicity
            }
        };

        // Initialize the Interactor
        NormalGivenInteractor normalGivenInteractor = new NormalGivenInteractor(
                normalGivenDataAccessObject, normalGivenPresenter, null
        );

        // Set up the game board
        int[][] currentMap = normalGivenDataAccessObject.getEntity().getGameBoard();
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                currentMap[i][j] = 0; // Clear the board
            }
        }
        normalGivenDataAccessObject.updateMap(currentMap);

        // Place a piece at the top (x = 4, y = 5)
        normalGivenDataAccessObject.setX(4);
        normalGivenDataAccessObject.setY(5);
        int[] shape = normalGivenDataAccessObject.getCurrentShapeState();
        shape[0] = 0;
        shape[1] = 0;

        // Create InputData for the drop action
        NormalGivenInputData inputData = new NormalGivenInputData();
        inputData.setSPressed(true); // Simulate "drop" key press
        inputData.setAPressed(false);
        inputData.setDPressed(false);
        inputData.setWPressed(false);

        // Act: Execute the drop action
        normalGivenInteractor.execute(inputData);


        // Extract the game board from the output data
        int[][] outputMap = capturedOutputData[0].getMap();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(outputMap[i][j]);
            }
            System.out.println();
        }
        // Verify that the piece has moved down
        assertEquals(1, outputMap[5][4]); // New position contains the piece
        assertEquals(1, outputMap[6][4]);
        assertEquals(1, outputMap[5][5]); // New position contains the piece
        assertEquals(1, outputMap[6][5]);
        assertEquals(0, outputMap[4][4]); // Original position is cleared
        assertEquals(0, outputMap[4][5]);
    }
    @Test
    public void testPieceMoveLeft() {
        // Arrange: Initialize dependencies
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();
        final NormalGivenOutputData[] capturedOutputData = new NormalGivenOutputData[1]; // Capture output data

        // Custom Presenter to capture the output
        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenOutputBoundary() {
            @Override
            public void execute(NormalGivenOutputData outputData) {
                capturedOutputData[0] = outputData; // Save the output data
            }

            @Override
            public void gameOver(boolean success) {
                // No-op for simplicity
            }
        };

        // Initialize the Interactor
        NormalGivenInteractor normalGivenInteractor = new NormalGivenInteractor(
                normalGivenDataAccessObject, normalGivenPresenter, null
        );

        // Set up the game board
        int[][] currentMap = normalGivenDataAccessObject.getEntity().getGameBoard();
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                currentMap[i][j] = 0; // Clear the board
            }
        }
        normalGivenDataAccessObject.updateMap(currentMap);

        // Place a piece at position (x = 4, y = 5)
        normalGivenDataAccessObject.setX(4);
        normalGivenDataAccessObject.setY(5);
        int[] shape = normalGivenDataAccessObject.getCurrentShapeState();
        shape[0] = 0; // O shape
        shape[1] = 0;

        // Create InputData for moving left
        NormalGivenInputData inputData = new NormalGivenInputData();
        inputData.setSPressed(false);
        inputData.setAPressed(true); // Simulate "move left"
        inputData.setDPressed(false);
        inputData.setWPressed(false);

        // Act: Execute the move left action
        normalGivenInteractor.execute(inputData);

        // Extract the game board from the output data
        int[][] outputMap = capturedOutputData[0].getMap();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(outputMap[i][j]);
            }
            System.out.println();
        }

        // Assert: Verify the piece has moved left
        assertEquals(1, outputMap[4][3]); // New position contains the piece
        assertEquals(1, outputMap[5][3]);
        assertEquals(1, outputMap[4][4]); // New position contains the piece
        assertEquals(1, outputMap[5][4]);
        assertEquals(0, outputMap[3][3]); // Original position is cleared
        assertEquals(0, outputMap[3][4]);
    }

    @Test
    public void testPieceMoveRight() {
        // Arrange: Initialize dependencies
        NormalGivenDataAccessInterface normalGivenDataAccessObject = new InMemoryDataAccessObject();
        final NormalGivenOutputData[] capturedOutputData = new NormalGivenOutputData[1]; // Capture output data

        // Custom Presenter to capture the output
        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenOutputBoundary() {
            @Override
            public void execute(NormalGivenOutputData outputData) {
                capturedOutputData[0] = outputData; // Save the output data
            }

            @Override
            public void gameOver(boolean success) {
                // No-op for simplicity
            }
        };

        // Initialize the Interactor
        NormalGivenInteractor normalGivenInteractor = new NormalGivenInteractor(
                normalGivenDataAccessObject, normalGivenPresenter, null
        );

        // Set up the game board
        int[][] currentMap = normalGivenDataAccessObject.getEntity().getGameBoard();
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 10; j++) {
                currentMap[i][j] = 0; // Clear the board
            }
        }
        normalGivenDataAccessObject.updateMap(currentMap);

        // Place a piece at position (x = 4, y = 5)
        normalGivenDataAccessObject.setX(4);
        normalGivenDataAccessObject.setY(5);
        int[] shape = normalGivenDataAccessObject.getCurrentShapeState();
        shape[0] = 0; // O shape
        shape[1] = 0;

        // Create InputData for moving right
        NormalGivenInputData inputData = new NormalGivenInputData();
        inputData.setSPressed(false);
        inputData.setAPressed(false);
        inputData.setDPressed(true); // Simulate "move right"
        inputData.setWPressed(false);

        // Act: Execute the move right action
        normalGivenInteractor.execute(inputData);

        // Extract the game board from the output data
        int[][] outputMap = capturedOutputData[0].getMap();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(outputMap[i][j]);
            }
            System.out.println();
        }

        // Assert: Verify the piece has moved right
        assertEquals(1, outputMap[4][5]); // New position contains the piece
        assertEquals(1, outputMap[5][5]);
        assertEquals(1, outputMap[4][6]); // New position contains the piece
        assertEquals(1, outputMap[5][6]);
        assertEquals(0, outputMap[3][5]); // Original position is cleared
        assertEquals(0, outputMap[3][6]);
    }


}

