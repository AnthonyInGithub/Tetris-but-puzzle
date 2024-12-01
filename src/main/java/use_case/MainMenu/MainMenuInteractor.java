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
            MainOutputData mainOutputData = new MainOutputData();
            presenter.navigateToLevelsPage();
            System.out.println("Start button pressed in interactor");

        }
        if ("HistoryButton".equals(buttonName)) {
            presenter.navigateToHistoryPage();
            System.out.println("History button pressed in interactor");

        }
        if ("MyOwnUploadButton".equals(buttonName)) {
            handleMyOwnUpload();
            System.out.println("MyOwnUpload button pressed in interactor");

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

                int[][] binaryArray = generateBinaryArray(resizedImage);
                int[][][] colorMap = generateColorMap(resizedImage);

                // Log or process the binary array and color map as needed
                mainMenuDataAccessObject.setBinaryMap(binaryArray);
                mainMenuDataAccessObject.setColorMap(colorMap);
                mainMenuDataAccessObject.setBackgroundImageAddress(myOwnImagePath);

                presenter.navigateToNormalGivenPage(new MainOutputData(null, null, null, myOwnImagePath));

                presenter.present(new MainOutputData("file processed successfully: " + file.getName()));

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


    private int[][] generateBinaryArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] binaryArray = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);
                int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                // Threshold: 1 for dark pixels, 0 for light pixels
                binaryArray[y][x] = gray < 128 ? 1 : 0;
            }
        }
        return binaryArray;
    }

    private int[][][] generateColorMap(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("heihgt"+height);
        int[][][] colorMap = new int[height][width][3];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                Color color = new Color(rgb);

                colorMap[y][x][0] = color.getRed();
                colorMap[y][x][1] = color.getGreen();
                colorMap[y][x][2] = color.getBlue();
            }
        }
        return colorMap;
    }

    private void logBinaryArray(int[][] binaryArray) {
        System.out.println("Binary Array:");
        for (int[] row : binaryArray) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    private void logColorMap(int[][][] colorMap) {
        System.out.println("\nColor Map:");
        for (int y = 0; y < colorMap.length; y++) {
            for (int x = 0; x < colorMap[y].length; x++) {
                System.out.print("[");
                for (int i = 0; i < 3; i++) {
                    System.out.print(colorMap[y][x][i] + (i < 2 ? ", " : ""));
                }
                System.out.print("] ");
            }
            System.out.println();
        }
    }
}
