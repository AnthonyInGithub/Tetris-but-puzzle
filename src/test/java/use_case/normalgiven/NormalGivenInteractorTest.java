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
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        NormalGivenViewModel normalGivenViewModel = new NormalGivenViewModel();
        EndingSceneViewModel endingSceneViewModel = new EndingSceneViewModel();

        NormalGivenOutputBoundary normalGivenPresenter = new NormalGivenPresenter(null, null) {
            @Override
            protected void gameOver(){
                assert true;
            }
        };



    }
}
