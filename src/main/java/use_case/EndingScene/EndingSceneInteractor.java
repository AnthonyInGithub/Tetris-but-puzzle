package use_case.EndingScene;

import data_access.EndingSceneDataAccessInterface;
import data_access.FileDataAccessObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class EndingSceneInteractor implements EndingSceneInputBoundary{
    public final EndingSceneDataAccessInterface endingSceneDataAccessObject;
    private final EndingSceneOutputBoundary endingScenePresenter;
    private final FileDataAccessObject endingSceneFileDataAccessObject;
    private final int maxSavedImageNumber = 3;
    private boolean savedSuccess;

    public EndingSceneInteractor(EndingSceneDataAccessInterface endingSceneDataAccessObject,
                                 EndingSceneOutputBoundary endingScenePresenter,
                                 FileDataAccessObject endingSceneFileDataAccessObject) {
        super();
        this.endingSceneDataAccessObject = endingSceneDataAccessObject;
        this.endingScenePresenter = endingScenePresenter;
        this.endingSceneFileDataAccessObject = endingSceneFileDataAccessObject;
    }


    @Override
    public void execute(EndingSceneInputData inputData) {
        if(inputData.isSaveClicked()) {
            savedSuccess = SaveEndGameImage();
        }

        endingScenePresenter.execute(new EndingSceneOutputData
                (inputData.isReturnClicked(), savedSuccess));

    }
    /**
     * Saves the end game screenshot to the file system.
     * The current screenshot is obtained from the `endingSceneDataAccessObject`,
     * and the image is saved in the "images/HistoryScreenShot" directory.
     * The method restricts the maximum number of saved images to 3,
     * overwriting the oldest image if necessary.
     *
     * @return true if the image is successfully saved, false otherwise
     */
    private boolean SaveEndGameImage() {
            try {
                BufferedImage currentScreenShot = endingSceneDataAccessObject.getEndGameScreenShot();

                int numberOfSavedImage = endingSceneFileDataAccessObject.getNumberOfSavedImages();
                String currentPath = "images/HistoryScreenShot/screenshot" + Integer.toString(numberOfSavedImage) + ".png";
                //Restrict the number of maximum saved image to 3
                numberOfSavedImage += 1;
                numberOfSavedImage %= maxSavedImageNumber;
                endingSceneFileDataAccessObject.setNumberOfSavedImages(numberOfSavedImage);

                File outputFile = new File(currentPath);
                ImageIO.write(currentScreenShot, "png", outputFile);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }

}
