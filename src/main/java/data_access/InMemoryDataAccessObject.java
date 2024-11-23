package data_access;

import entity.Entity;


public class InMemoryDataAccessObject implements data_access.NormalGivenDataAccess {
    // Current piece information: [shapeType, rotationState]
    private int[] currentShapeState;

    private int[][] targetMap;

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
    public void updateMap(int[][] currentMap){
        entity.setGameBoard(currentMap);
    }
    public void setTargetMap(int[][] targetMap){
        this.targetMap = targetMap;
    }
    public int[][] getTargetMap(){
        return targetMap;
    }
}
