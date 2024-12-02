package data_access;

import entity.StagedMap;

import java.awt.image.BufferedImage;

/**
 * Interface for accessing and modifying the game state and shapes.
 */
public interface NormalGivenDataAccessInterface {
    // Current piece information
    int[] getCurrentShapeState(); // [shapeType, rotationState]

    // Piece position
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);

    // Game board
    StagedMap getStagedMap();
    void setEntity(StagedMap stagedMap);
    void updateMap(int[][] currentMap);

    // Shape definitions
    int[][] getShape(int shape, int rotationState);

    // Generate a new piece
    void generateNewPiece();

    int[][] getTargetMap();

    void setTargetMap(int[][] targetMap);

    void setImageAddress();

    String getImageAddress();

    int[][][] getColorMap();

    void setEndGameScreenShot(BufferedImage endgameScreenShot);

    int getCurrentSimilarityLevelSpecification();

    void resetCurrentMap();
}