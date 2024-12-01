package use_case.history;

import data_access.FileDataAccessObject;
import data_access.InMemoryDataAccessObject;
import org.junit.Test;
import use_case.History.HistoryInputData;
import use_case.History.HistoryInterator;
import use_case.History.HistoryOutputBoundary;
import use_case.History.HistoryOutputData;



import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.*;

public class HistoryInteractorTest {

//    @Test
//    public void displaySuccessTest(){
//        InMemoryDataAccessObject endingSceneDataAccessObject = new InMemoryDataAccessObject();
//        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
//
//        HistoryOutputBoundary historyPresenter = new HistoryOutputBoundary() {
//            @Override
//            public void switchToHomepage(HistoryOutputData historyOutputData) {
//
//            }
//
//        };
//
//        HistoryInterator historyInterator = new HistoryInterator(endingSceneDataAccessObject, historyPresenter, fileDataAccessObject);
//        HistoryInputData historyInputData = new HistoryInputData();
//
//        // Verify the images are displayed correctly
//        File expectedFile = new File("images/HistoryScreenShot/screenshot2.png");
//        assertTrue(expectedFile.exists());
//        expectedFile.delete(); // Clean up the test file
//    }

    @Test
    public void successBackTest(){
        InMemoryDataAccessObject endingSceneDataAccessObject = new InMemoryDataAccessObject();
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();

        HistoryOutputBoundary endingScenePresenter = new HistoryOutputBoundary() {
            @Override
            public void switchToHomepage(HistoryOutputData historyOutputData) {
                assertEquals(true, historyOutputData.getIsBackClicked());
            }
        };

        HistoryInterator historyInteractor = new HistoryInterator(endingSceneDataAccessObject, endingScenePresenter, fileDataAccessObject);
        HistoryInputData historyInputData = new HistoryInputData();
        historyInputData.setBackClicked(true);
        historyInteractor.execute(historyInputData);
    }


}

