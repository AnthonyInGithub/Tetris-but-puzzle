package use_case.endingscene;

import data_access.FileDataAccessObject;
import data_access.InMemoryDataAccessObject;
import org.junit.Test;
import use_case.EndingScene.EndingSceneInputData;
import use_case.EndingScene.EndingSceneInteractor;
import use_case.EndingScene.EndingSceneOutputBoundary;
import use_case.EndingScene.EndingSceneOutputData;



import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class endingSceneInteractorTest {

    @Test
    public void saveSuccessTest(){
        InMemoryDataAccessObject endingSceneDataAccessObject = new InMemoryDataAccessObject();
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        fileDataAccessObject.setNumberOfSavedImages(2);
        endingSceneDataAccessObject.setEndGameScreenShot(new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB));

        EndingSceneOutputBoundary endingScenePresenter = new EndingSceneOutputBoundary() {
            @Override
            public void execute(EndingSceneOutputData outputData) {

            }
        };

        EndingSceneInteractor endingSceneInteractor = new EndingSceneInteractor(endingSceneDataAccessObject, endingScenePresenter, fileDataAccessObject);

        EndingSceneInputData endingSceneInputData = new EndingSceneInputData();
        endingSceneInputData.setSaveClicked(true);
        endingSceneInputData.setReturnClicked(false);
        endingSceneInteractor.execute(endingSceneInputData);

        // Verify the file was saved to the correct location
        File expectedFile = new File("images/HistoryScreenShot/screenshot2.png");
        assertTrue(expectedFile.exists());
        expectedFile.delete(); // Clean up the test file
    }
    @Test
    public void saveFailExceptionTest(){
        InMemoryDataAccessObject endingSceneDataAccessObject = new InMemoryDataAccessObject();
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        fileDataAccessObject.setNumberOfSavedImages(2);
        endingSceneDataAccessObject.setEndGameScreenShot(null);

        EndingSceneOutputBoundary endingScenePresenter = new EndingSceneOutputBoundary() {
            @Override
            public void execute(EndingSceneOutputData outputData) {
                assertEquals(false, outputData.getIsSaveSuccess());

            }
        };

        EndingSceneInteractor endingSceneInteractor = new EndingSceneInteractor(endingSceneDataAccessObject, endingScenePresenter, fileDataAccessObject);

        // if the input image is null, it will assert an exception that will be caught by try-catch first.
        EndingSceneInputData endingSceneInputData = new EndingSceneInputData();
        endingSceneInputData.setSaveClicked(true);
        endingSceneInputData.setReturnClicked(false);
        endingSceneInteractor.execute(endingSceneInputData);
    }

    @Test
    public void successReturnTest(){
        InMemoryDataAccessObject endingSceneDataAccessObject = new InMemoryDataAccessObject();
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();

        EndingSceneOutputBoundary endingScenePresenter = new EndingSceneOutputBoundary() {
            @Override
            public void execute(EndingSceneOutputData outputData) {
                assertEquals(true, outputData.getIsReturnClicked());

            }
        };

        EndingSceneInteractor endingSceneInteractor = new EndingSceneInteractor(endingSceneDataAccessObject, endingScenePresenter, fileDataAccessObject);

        // if the input image is null, it will assert an exception that will be caught by try-catch first.
        EndingSceneInputData endingSceneInputData = new EndingSceneInputData();
        endingSceneInputData.setSaveClicked(false);
        endingSceneInputData.setReturnClicked(true);
        endingSceneInteractor.execute(endingSceneInputData);

    }


}
