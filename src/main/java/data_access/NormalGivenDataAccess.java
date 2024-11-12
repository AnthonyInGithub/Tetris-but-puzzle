package java.data_access;

import java.entity.Entity;

public interface NormalGivenDataAccess {
    /**
     * Pass the current falling piece to the Interactor.
     * @return An array representing the current piece, [shapeType, rotationState].
     */
    int[] getCurrentPiece();

    /**
     * Update the current piece according to the Interactor.
     * @param currentPiece An array representing the new current piece, [shapeType, rotationState].
     */
    void setCurrentPiece(int[] currentPiece);

    /**
     * Pass the current x position to the Interactor.
     * @return The current x position of the falling piece.
     */
    int getX();

    /**
     * Update the x position according to the Interactor.
     * @param x The new x position of the falling piece.
     */
    void setX(int x);

    /**
     * Pass the current y position to the Interactor.
     * @return The current y position of the falling piece.
     */
    int getY();

    /**
     * Update the y position according to the Interactor.
     * @param y The new y position of the falling piece.
     */
    void setY(int y);

    /**
     * Pass the Entity to the Interactor.
     * @return The Entity instance representing the game board.
     */
    Entity getEntity();

    /**
     * Update the Entity according to the Interactor.
     * @param entity The new Entity instance.
     */
    void setEntity(Entity entity);

    /**
     * Generate a new piece.
     */
    void generateNewPiece();

    /**
     * Get the shapes definitions.
     * @return A 4D array representing all shapes and their rotation states.
     */
    int[][][][] getShapes();
}