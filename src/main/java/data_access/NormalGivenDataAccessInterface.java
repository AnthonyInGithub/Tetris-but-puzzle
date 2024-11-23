package data_access;

import entity.Entity;
/**
 * Interface for accessing and modifying the game state and shapes.
 */
public interface NormalGivenDataAccessInterface {
    // Current piece information
    int[] getCurrentShapeState(); // [shapeType, rotationState]
    void setCurrentShapeState(int[] currentShapeState);

    // Piece position
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);

    // Game board
    Entity getEntity();
    void setEntity(Entity entity);
    void updateMap(int[][] currentMap);

    // Shape definitions
    int[][] getShape(int shape, int rotationState);

    // Generate a new piece
    void generateNewPiece();

    int[][] getTargetMap();

    void setTargetMap(int[][] targetMap);
}