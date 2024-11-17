package java.entity;

public class Entity {
    private final int width = 10;
    private final int height = 22; // Includes extra space above the visible area

    // The game board, including both fixed blocks and any static pieces
    private int[][] gameBoard;

    // Constructor
    public Entity() {
        initializeGameBoard();
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

    // Getters and setters for the game board
    public int[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(int[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}