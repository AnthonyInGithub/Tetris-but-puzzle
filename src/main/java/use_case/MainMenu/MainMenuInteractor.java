package use_case.MainMenu;
import data_access.MainMenuDataAccessInterface;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainMenuInteractor implements MainMenuInputBoundary {
    private final MainMenuOutputBoundary presenter;
    private final MainMenuDataAccessInterface mainMenuDataAccessObject;
    private String myOwnImagePath = "images/UploadedImage/temp.png";

    public MainMenuInteractor(MainMenuOutputBoundary presenter, MainMenuDataAccessInterface mainMenuDataAccessObject) {
        this.presenter = presenter;
        this.mainMenuDataAccessObject = mainMenuDataAccessObject;
    }

    public void execute(MainInputData inputData) {
        String buttonName = inputData.getButtonName();

        if ("StartButton".equals(buttonName)) {
            MainOutputData mainOutputData = new MainOutputData("startButton Clicked");
            mainOutputData.setIsNormalGivenClicked(true);
            presenter.navigateToLevelsPage(mainOutputData);
        }
        if ("HistoryButton".equals(buttonName)) {
            MainOutputData mainOutputData = new MainOutputData("historyButton Clicked");
            mainOutputData.setIsHistoryClicked(true);
            presenter.navigateToHistoryPage(mainOutputData);
        }
        if ("MyOwnUploadButton".equals(buttonName)) {
            boolean uploadSuccess = handleMyOwnUpload();
            if(uploadSuccess){
                MainOutputData mainOutputData = new MainOutputData("uploadButton Clicked", null, null, myOwnImagePath);
                mainOutputData.setIsUploadClicked(true);
                presenter.uploadClicked(mainOutputData);
            }

        }
    }

    public boolean handleMyOwnUpload() {
        File file = selectFile();

        if (file != null) {
            try {
                // Resize and process the uploaded file
                BufferedImage resizedBackgroundImage = Thumbnails.of(file)
                        .forceSize(300, 600)
                        .asBufferedImage();
                File outputFile = new File(myOwnImagePath);
                ImageIO.write(resizedBackgroundImage, "png", outputFile);

                mainMenuDataAccessObject.setBackgroundImageAddress(myOwnImagePath);
                mainMenuDataAccessObject.setColorMapAndBinaryMapMainMenu();

                presenter.present(new MainOutputData("file processed successfully: " + file.getName()));
                return true;
            } catch (Exception e) {
                presenter.present(new MainOutputData("error processing file: " + e.getMessage()));
            }
        } else {
            presenter.present(new MainOutputData("no file selected."));
        }
        return false;
    }


    private File selectFile() {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Select a file to upload");
        int result = fileChooser.showOpenDialog(null);

        return (result == javax.swing.JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }
}
