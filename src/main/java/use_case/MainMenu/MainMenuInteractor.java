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
            presenter.navigateToLevelsPage();
        }
        if ("HistoryButton".equals(buttonName)) {
            presenter.navigateToHistoryPage();
        }
        if ("MyOwnUploadButton".equals(buttonName)) {
            handleMyOwnUpload();
        }
//        else {
//            presenter.present(new MainOutputData("Invalid action: " + buttonName));
//        }
    }

//    public void handleLevelSelection(int level) {
//        if (level == 1) {
//            presenter.present(new MainOutputData("Starting Level 1"));
//        }if (level == 2) {
//            presenter.present(new MainOutputData("Starting Level 2"));
//        }if (level == 3) {
//            presenter.present(new MainOutputData("Starting Level 3"));
//        }
//       else {
//          presenter.present(new MainOutputData("Invalid level: " + level));
//      }
//    }

//    public void handleHistoryAction(String buttonName) {
//        if ("ReturnButton".equals(buttonName)) {
//            presenter.present(new MainOutputData("Returning to Main Menu"));
//        }
//        if ("NextHistoryButton".equals(buttonName)) {
//            presenter.present(new MainOutputData("Navigating to the next history page"));
//        }
//        if ("LastHistoryButton".equals(buttonName)) {
//            presenter.present(new MainOutputData("Navigating to the previous history page"));
//        }
//        else {
//          presenter.present(new MainOutputData("Invalid history action: " + buttonName));
//      }
//    }

    public void handleManualEnd() {
        presenter.present(new MainOutputData("Ending the game manually"));
    }


    public void handleMyOwnUpload() {
        File file = selectFile();

        if (file != null) {
            try {
                // Resize and process the uploaded file
                BufferedImage resizedImage = Thumbnails.of(file)
                        .forceSize(10, 20)
                        .asBufferedImage();
                BufferedImage resizedBackgroundImage = Thumbnails.of(file)
                        .forceSize(300, 600)
                        .asBufferedImage();
                File outputFile = new File(myOwnImagePath);
                ImageIO.write(resizedBackgroundImage, "png", outputFile);

                mainMenuDataAccessObject.setBackgroundImageAddress(myOwnImagePath);

                mainMenuDataAccessObject.setColorMapAndBinaryMapMainMenu();

                presenter.navigateToNormalGivenPage(new MainOutputData(null, null, null, myOwnImagePath));

            } catch (Exception e) {
                presenter.present(new MainOutputData("error processing file: " + e.getMessage()));
            }
        } else {
            presenter.present(new MainOutputData("no file selected."));
        }
    }


    private File selectFile() {
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Select a file to upload");
        int result = fileChooser.showOpenDialog(null);

        return (result == javax.swing.JFileChooser.APPROVE_OPTION) ? fileChooser.getSelectedFile() : null;
    }

}
