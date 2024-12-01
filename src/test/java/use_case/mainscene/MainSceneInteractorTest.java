package use_case.mainscene;

import data_access.FileDataAccessObject;
import data_access.InMemoryDataAccessObject;
import org.junit.Test;
import use_case.MainMenu.MainInputData;
import use_case.MainMenu.MainMenuOutputBoundary;
import use_case.MainMenu.MainOutputData;
import use_case.MainMenu.MainMenuInteractor;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class MainSceneInteractorTest {

    @Test
    public void successStartTest(){
        InMemoryDataAccessObject endingSceneDataAccessObject = new InMemoryDataAccessObject();
        FileDataAccessObject fileDataAccessObject = new FileDataAccessObject();
        InMemoryDataAccessObject mainMenuDataAccessObject = new InMemoryDataAccessObject();

        MainMenuOutputBoundary mainScenePresenter1 = new MainMenuOutputBoundary() {
            @Override
            public void present(MainOutputData outputData) {

            }

            @Override
            public void navigateToLevelsPage(MainOutputData outputData) {
                assertEquals(true, outputData.getIsNormalGivenClicked());
            }

            @Override
            public void navigateToHistoryPage(MainOutputData outputData) {}

            @Override
            public void uploadClicked(MainOutputData outputData) {}
        };

        MainMenuOutputBoundary mainScenePresenter2 = new MainMenuOutputBoundary() {
            @Override
            public void present(MainOutputData outputData) {

            }

            @Override
            public void navigateToLevelsPage(MainOutputData outputData) {}

            @Override
            public void navigateToHistoryPage(MainOutputData outputData){
                assertEquals(true, outputData.getIsHistoryClicked());
            }

            @Override
            public void uploadClicked(MainOutputData outputData) {}

        };

        MainMenuOutputBoundary mainScenePresenter3 = new MainMenuOutputBoundary() {
            @Override
            public void present(MainOutputData outputData) {
                String folderPath = "images/UploadedImage";
                File folder = new File(folderPath);
                File[] files = folder.listFiles();
                System.out.println("file length: " + files.length);
                assertEquals(true, files.length > 1);
                File file = new File("images/UploadedImage/temp.png");
                file.delete();
                files = folder.listFiles();
                assertEquals(true, files.length == 1);
            }

            @Override
            public void navigateToLevelsPage(MainOutputData outputData) {}

            @Override
            public void navigateToHistoryPage(MainOutputData outputData) {}

            @Override
            public void uploadClicked(MainOutputData outputData) {
                assertEquals(true, outputData.getIsUploadClicked());
            }
        };

        MainMenuInteractor mainSceneInteractor1 = new MainMenuInteractor(mainScenePresenter1, mainMenuDataAccessObject);
        MainMenuInteractor mainSceneInteractor2 = new MainMenuInteractor(mainScenePresenter2, mainMenuDataAccessObject);
        MainMenuInteractor mainSceneInteractor3 = new MainMenuInteractor(mainScenePresenter3, mainMenuDataAccessObject);
        MainInputData mainInputData1 = new MainInputData("StartButton");
        MainInputData mainInputData2 = new MainInputData("HistoryButton");
        MainInputData mainInputData3 = new MainInputData("MyOwnUploadButton");
        mainInputData1.setNormalGivenClicked(true);
        mainInputData2.setHistoryClicked(true);
        mainInputData3.setUploadClicked(true);
        mainSceneInteractor1.execute(mainInputData1);
        mainSceneInteractor2.execute(mainInputData2);
        mainSceneInteractor3.execute(mainInputData3);
    }
}
