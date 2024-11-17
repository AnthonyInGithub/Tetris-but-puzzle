package java.data_access;

import java.entity.Entity;
/**
 * Interface for accessing and modifying the game state and shapes.
 */
public interface NormalGivenDataAccess {
    // Current piece information
    int[] getCurrentPiece(); // [shapeType, rotationState]
    void setCurrentPiece(int[] currentPiece);

    // Piece position
    int getX();
    void setX(int x);
    int getY();
    void setY(int y);

    // Game board
    Entity getEntity();
    void setEntity(Entity entity);

    // Shape definitions
    int[][][][] getShapes();

    // Generate a new piece
    void generateNewPiece();
}