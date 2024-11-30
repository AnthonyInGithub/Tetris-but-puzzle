package data_access;

import entity.Entity;
import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class InMemoryDataAccessObject implements NormalGivenDataAccessInterface,
                                                    EndingSceneDataAccessInterface,
                                                    HistoryDataAccessInterface,
                                                   LevelSelectDataAccessInterface{
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
    private Entity entity;

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
        this.entity = new Entity();
        setColorMapAndBinaryMap();
        generateNewPiece();
    }

    @Override
    public int[] getCurrentShapeState() {
        return currentShapeState;
    }

    @Override
    public void setCurrentShapeState(int[] currentShapeState) {
        this.currentShapeState = currentShapeState;
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
    public Entity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
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
        entity.setGameBoard(currentMap);
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


    public void setColorMapAndBinaryMap() {
        {
            try {
                // Resize the image to 10x20 using Thumbnailator
                currentLevel = 1;
                setImageAddress();
                BufferedImage resizedImage = Thumbnails.of(new File(imageAddress))
                        .forceSize(10, 20)
                        .asBufferedImage();

                // Initialize a 2D array for binary representation
                int width = resizedImage.getWidth();
                int height = resizedImage.getHeight();
                int[][] binaryArray = new int[height][width];
                colorMap = new int[height][width][3];
                System.out.println(height + " " + width);

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

                        // Threshold: convert to binary (1 for dark, 0 for light)
                        binaryArray[y][x] = gray < 128 ? 1 : 0;
                    }
                }

                // Print the binary array
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        System.out.print(binaryArray[y][x] + " ");
                    }
                    System.out.println();
                }
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        System.out.print("[");
                        for (int i = 0; i < 3; i++) {
                            System.out.print(colorMap[y][x][i] + " ");
                        }
                        System.out.println("]");

                    }
                    System.out.println();
                }
                setTargetMap(binaryArray);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

