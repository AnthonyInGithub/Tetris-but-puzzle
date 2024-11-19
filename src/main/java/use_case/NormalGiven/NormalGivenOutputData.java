package use_case.NormalGiven;

/**
 * Output Data for the Normal Given Use Case.
 * Contains information about the updated game state.
 */
public class NormalGivenOutputData {
    private final int[][] map; // 2D array representing the game board (10x20)

    /**
     * Constructor for NormalGivenOutputData.
     *
     * @param map the updated game board
     */
    public NormalGivenOutputData(int[][] map) {
        if (map == null) {
            throw new IllegalArgumentException("Map cannot be null");
        }
        this.map = map;
    }

    /**
     * Gets the updated game board.
     *
     * @return the 2D array representing the game board
     */
    public int[][] getMap() {
        return map;
    }
}