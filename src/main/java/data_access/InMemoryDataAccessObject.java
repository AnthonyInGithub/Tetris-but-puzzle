package data_access;

import entity.StagedMap;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.max;


public class InMemoryDataAccessObject implements NormalGivenDataAccessInterface,
                                                    EndingSceneDataAccessInterface,
                                                    HistoryDataAccessInterface,
                                                   LevelSelectDataAccessInterface,
                                                    MainMenuDataAccessInterface{
    // Current piece information: [shapeType, rotationState]
    private int[] currentShapeState;

    private int[][] targetMap;

    BufferedImage endGameScreenShot;

    private String imageAddress;


    // Current game level (1, 2, or 3)
    private int currentLevel;

    private int[][][] colorMap;

    // Position of the current piece
    private int x;
    private int y;

    // The game board entity
    private StagedMap stagedMap;

    private final int MYOWN_IMAGE_COLOR_THRESHOLD = 200;

    private int similarityLevelSpecification = 80;

    private final int TOTAL_BLOCK = 200;

    private final int HEIGHT = 20;
    private final int WIDTH = 10;

    // Shapes definitions
    private final int[][][][] shapes = {
            // O Shape
            {
                    {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}, // Rotation state 0
                    {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}, // Rotation state 1
                    {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}, // Rotation state 2
                    {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}}  // Rotation state 3
            },
            // I Shape
            {
                    {{1, 0, 0}, {1, 0, 0}, {1, 0, 0}}, // Rotation state 0 (vertical)
                    {{0, 0, 0}, {0, 0, 0}, {1, 1, 1}}, // Rotation state 1 (horizontal)
                    {{1, 0, 0}, {1, 0, 0}, {1, 0, 0}}, // Rotation state 2 (vertical)
                    {{0, 0, 0}, {0, 0, 0}, {1, 1, 1}}  // Rotation state 3 (horizontal)
            },
            // T Shape
            {
                    {{1, 1, 1}, {0, 1, 0}, {0, 0, 0}}, // Rotation state 0
                    {{0, 1, 0}, {0, 1, 1}, {0, 1, 0}},// Rotation state 1
                    {{0, 1, 0}, {1, 1, 1}, {0, 0, 0}}, // Rotation state 2
                    {{0, 1, 0}, {1, 1, 0}, {0, 1, 0}}, // Rotation state 3

            },
            // L Shape
            {
                    {{0, 1, 0}, {0, 1, 0}, {0, 1, 1}}, // Rotation state 0
                    {{0, 0, 0}, {1, 1, 1}, {1, 0, 0}}, // Rotation state 1
                    {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}}, // Rotation state 2
                    {{0, 0, 1}, {1, 1, 1}, {0, 0, 0}}  // Rotation state 3
            },
            // Z Shape
            {
                    {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}}, // Rotation state 0
                    {{0, 0, 1}, {0, 1, 1}, {0, 1, 0}}, // Rotation state 1
                    {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}}, // Rotation state 2
                    {{0, 0, 1}, {0, 1, 1}, {0, 1, 0}}  // Rotation state 3
            }
    };


    // Constructor
    public InMemoryDataAccessObject() {
        this.stagedMap = new StagedMap();
        generateNewPiece();
    }

    @Override
    public int[] getCurrentShapeState() {
        return currentShapeState;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public StagedMap getStagedMap() {
        return stagedMap;
    }

    @Override
    public void setEntity(StagedMap stagedMap) {
        this.stagedMap = stagedMap;
    }

    @Override
    public int[][] getShape(int shape, int rotationState) {
        return shapes[shape][rotationState];
    }


    @Override
    public void generateNewPiece() {
        int shapeType = (int) (Math.random() * shapes.length);
        currentShapeState = new int[]{shapeType, 0}; // Start with rotation state 0
        x = 3; // Initial x position to center the piece
        y = 0; // Initial y position at the top of the board
    }

    @Override
    public String getImageAddress() {
        return imageAddress;
    }

    @Override
    public void setImageAddress() {
        if (currentLevel == 1) {
            imageAddress = "images/level1.png";
        }
        if (currentLevel == 2) {
            imageAddress = "images/level2.png";
        }
        if (currentLevel == 3) {
            imageAddress = "images/level3.png";
        }
    }

    public String getImageAddressLevel(){
        return imageAddress;
    }

    @Override
    public void setSelectedLevel(int selectedLevel) {
        this.currentLevel = selectedLevel;
        setImageAddress(); // Update image address based on the selected level
    }

    @Override
    public int[][][] getColorMap() {
        return colorMap;
    }


    public void updateMap(int[][] currentMap) {
        stagedMap.setGameBoard(currentMap);
    }

    public void setTargetMap(int[][] targetMap) {
        this.targetMap = targetMap;
    }

    public int[][] getTargetMap() {
        return targetMap;
    }

    public BufferedImage getEndGameScreenShot() {
        return endGameScreenShot;
    }

    @Override
    public int getNumberOfSavedImages() {
        return 0;
    }

    @Override
    public void setNumberOfSavedImages(int numberOfSavedImages) {
    }

    public void setEndGameScreenShot(BufferedImage endGameScreenShot) {
        this.endGameScreenShot = endGameScreenShot;
    }

    @Override
    public int getCurrentSimilarityLevelSpecification() {
        return similarityLevelSpecification;
    }

    @Override
    public void setColorMapAndBinaryMap() {
        {
            try {
                // Resize the image to 10x20 using Thumbnailator
                setImageAddress();
                BufferedImage resizedImage = Thumbnails.of(new File(imageAddress))
                        .forceSize(WIDTH, HEIGHT)
                        .asBufferedImage();

                // Initialize a 2D array for binary representation
                int width = resizedImage.getWidth();
                int height = resizedImage.getHeight();
                int[][] binaryArray = new int[height][width];
                colorMap = new int[height][width][3];
                System.out.println(height + " " + width);

                int counter = 0;

                // Process each pixel to determine binary value
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        // Get the RGB value of the pixel
                        int rgb = resizedImage.getRGB(x, y);
                        Color color = new Color(rgb);
                        colorMap[y][x][0] = color.getRed();
                        colorMap[y][x][1] = color.getGreen();
                        colorMap[y][x][2] = color.getBlue();
                        // Convert to grayscale for thresholding
                        int gray = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                        counter += gray < MYOWN_IMAGE_COLOR_THRESHOLD ? 1 : 0;
                        // Threshold: convert to binary (1 for dark, 0 for light)
                        binaryArray[y][x] = gray < MYOWN_IMAGE_COLOR_THRESHOLD ? 1 : 0;
                    }
                }

                setSimilarityLevelSpecification(counter);
                // Print the binary array
                setTargetMap(binaryArray);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void resetCurrentMap(){
        int[][] currentMap = stagedMap.getGameBoard();
        for(int i = 0; i < currentMap.length; i++){
            for (int j = 0; j < currentMap[i].length; j++){
                currentMap[i][j] = 0;
            }
        }
    }
    public void setBackgroundImageAddress(String backgroundImageAddress) {
        this.imageAddress = backgroundImageAddress;
    }

    @Override
    public void setColorMapAndBinaryMapMainMenu(){
        setColorMapAndBinaryMap();
    }
    public void setSimilarityLevelSpecification(int numberOf1) {
        // this is an empirical rule that we conclude after testing many input images.
        // It ensures that the difficulty of passing a level is not too high nor too low.
        // We use magic numbers here because those numbers are meaningless and are only use to
        // approximate the empirical result we get.
        int baseSimilarityLevelSpecification = 50 + (TOTAL_BLOCK - numberOf1)/4;
        int adjustment = ((TOTAL_BLOCK - numberOf1) * numberOf1)/ 1000;
        this.similarityLevelSpecification = baseSimilarityLevelSpecification + adjustment;

        System.out.println("final range: "+ similarityLevelSpecification);
        System.out.println("number of 1: "+ numberOf1);
    }
}


