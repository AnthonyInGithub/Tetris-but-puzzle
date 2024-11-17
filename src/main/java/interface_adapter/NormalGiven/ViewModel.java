package interface_adapter.NormalGiven;

/**
 * ViewModel holds the data that the View will read from.
 * It provides methods to get and set the game map data.
 */
public class ViewModel {
    private int[][] map; // Represents the game screen, including static blocks and the falling piece.

    /**
     * Gets the current game map.
     * @return the current game map as a 2D array.
     */
    public int[][] getMap() {
        return map;
    }

    /**
     * Sets the current game map.
     * @param map the new game map to be displayed by the View.
     */
    public void setMap(int[][] map) {
        this.map = map;
    }
}