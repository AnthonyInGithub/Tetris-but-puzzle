package data_access;

import java.awt.image.BufferedImage;

public interface EndingSceneDataAccessInterface {
    BufferedImage getEndGameScreenShot();
    int getNumberOfSavedImages();
    void setNumberOfSavedImages(int numberOfSavedImages);
}
