package interface_adapter.NormalGiven;

import interface_adapter.NormalGiven.ViewModelMain;

/**
 * NormalGivenViewModel manages the state for the Normal Given Use Case.
 * It inherits from the generic ViewModelMain.
 */
public class NormalGivenViewModel extends ViewModelMain<int[][]> {

    /**
     * Gets the current game map.
     *
     * @return the current game map as a 2D array.
     */
    public int[][] getMap() {
        return getState(); // Use the inherited getState method
    }

    /**
     * Sets the current game map and notifies listeners.
     *
     * @param map the new game map to be displayed by the View.
     */
    public void setMap(int[][] map) {
        setState(map); // Use the inherited setState method
    }
}