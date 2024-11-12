package java.data_access;

import java.entity.Entity;
import java.data_access.NormalGivenDataAccess;


public class InMemoryDataAccessObject implements NormalGivenDataAccess {
    // Current falling piece information: [shapeType, rotationState]
    private int[] currentPiece;

    // Current position of the falling piece
    private int x;
    private int y;

    // The game board entity
    private Entity entity;

    // Shape definitions
    private final int[][][][] shapes = {
            // O Shape
            {
                    {{0,0,0},{1,1,0},{1,1,0}}, // Rotation state 0
                    {{0,0,0},{1,1,0},{1,1,0}}, // Rotation state 1
                    {{0,0,0},{1,1,0},{1,1,0}}, // Rotation state 2
                    {{0,0,0},{1,1,0},{1,1,0}}  // Rotation state 3
            },
            // I Shape
            {
                    {{0,1,0},{0,1,0},{0,1,0}}, // Rotation state 0
                    {{0,0,0},{1,1,1},{0,0,0}}, // Rotation state 1
                    {{0,1,0},{0,1,0},{0,1,0}}, // Rotation state 2
                    {{0,0,0},{1,1,1},{0,0,0}}  // Rotation state 3
            }
    };

    // Constructor
    public InMemoryDataAccessObject() {
        this.entity = new Entity(); // The game board with size 22x10
        generateNewPiece(); // Generate the first piece
    }

    @Override
    public int[] getCurrentPiece() {
        return currentPiece;
    }

    @Override
    public void setCurrentPiece(int[] currentPiece) {
        this.currentPiece = currentPiece;
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
    public void generateNewPiece() {
        // Randomly select a shape type
        int shapeType = (int) (Math.random() * shapes.length);
        currentPiece = new int[]{shapeType, 0}; // Start with rotation state 0
        x = 3; // Initial x position to center the piece
        y = 0; // Initial y position at the top of the board
    }

    @Override
    public int[][][][] getShapes() {
        return shapes;
    }
}
