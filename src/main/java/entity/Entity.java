package java.entity;

public class Entity {
    private final int width = 10;
    private final int height = 22; // Includes extra space above the visible area

    // The game board, including both fixed blocks and the falling piece
    private int[][] gameBoard;

    // Current shape information: [shapeType, rotationState]
    private int[] currentShape;

    // The shapes are organized this way: each shape contains its rotations
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
                    {{1,0,0},{1,0,0},{1,0,0}}, // Rotation state 0
                    {{0,0,0},{0,0,0},{1,1,1}}, // Rotation state 1
                    {{1,0,0},{1,0,0},{1,0,0}}, // Rotation state 2
                    {{0,0,0},{0,0,0},{1,1,1}}  // Rotation state 3
            },
            // T Shape
            {
                    {{1,1,1},{0,1,0},{0,0,0}}, // Rotation state 0
                    {{0,1,0},{0,1,1},{0,1,0}}, // Rotation state 1
                    {{0,1,0},{1,1,0},{0,1,0}}, // Rotation state 2
                    {{0,1,0},{1,1,1},{0,0,0}}  // Rotation state 3
            },
            // L Shape
            {
                    {{0,1,0},{0,1,0},{0,1,1}}, // Rotation state 0
                    {{0,0,0},{1,1,1},{1,0,0}}, // Rotation state 1
                    {{1,1,0},{0,1,0},{0,1,0}}, // Rotation state 2
                    {{0,0,1},{1,1,1},{0,0,0}}  // Rotation state 3
            },
            // Z Shape
            {
                    {{1,1,0},{0,1,1},{0,0,0}}, // Rotation state 0
                    {{0,0,1},{0,1,1},{0,1,0}}, // Rotation state 1
                    {{1,1,0},{0,1,1},{0,0,0}}, // Rotation state 2
                    {{0,0,1},{0,1,1},{0,1,0}}  // Rotation state 3
            }
            // Add more shapes if needed
    };

    // Position of the current shape
    private int x, y; // (0,0) position is at the top-left corner of the map. The position corresponds to the top-left of the shape.

    // Constructor
    public Entity() {
        initializeGameBoard();
        generateNewPiece(); // Initialize the first piece
    }

    // Initialize the game board
    private void initializeGameBoard() {
        gameBoard = new int[height][width];
        // Initialize all cells to 0 (empty)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                gameBoard[i][j] = 0;
            }
        }
    }

    // Method to generate a new piece
    public void generateNewPiece() {
        int shapeType = (int) (Math.random() * shapes.length);
        currentShape = new int[]{shapeType, 0}; // Start with rotation state 0
        x = 3; // Initial x position to center the piece
        y = 0; // Initial y position at the top of the board
    }

    // Getters and setters for the game board
    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    // Getters and setters for the current shape
    public int[] getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(int[] currentShape) {
        this.currentShape = currentShape;
    }

    // Getters and setters for x and y positions
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getter for shapes
    public int[][][][] getShapes() {
        return shapes;
    }
}