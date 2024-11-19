package use_case.NormalGiven;

/**
 * Input Data class for the Normal Given Use Case.
 * Contains information about the user's inputs (W, A, S, D keys).
 */
public class NormalGivenInputData {
    private boolean isWPressed; // Whether the 'W' key is pressed (rotate)
    private boolean isAPressed; // Whether the 'A' key is pressed (move left)
    private boolean isSPressed; // Whether the 'S' key is pressed (speed up falling)
    private boolean isDPressed; // Whether the 'D' key is pressed (move right)

    /**
     * Gets whether the 'W' key is pressed.
     *
     * @return true if 'W' is pressed, false otherwise
     */
    public boolean isWPressed() {
        return isWPressed;
    }

    /**
     * Sets whether the 'W' key is pressed.
     *
     * @param isWPressed true if 'W' is pressed, false otherwise
     */
    public void setWPressed(boolean isWPressed) {
        this.isWPressed = isWPressed;
    }

    /**
     * Gets whether the 'A' key is pressed.
     *
     * @return true if 'A' is pressed, false otherwise
     */
    public boolean isAPressed() {
        return isAPressed;
    }

    /**
     * Sets whether the 'A' key is pressed.
     *
     * @param isAPressed true if 'A' is pressed, false otherwise
     */
    public void setAPressed(boolean isAPressed) {
        this.isAPressed = isAPressed;
    }

    /**
     * Gets whether the 'S' key is pressed.
     *
     * @return true if 'S' is pressed, false otherwise
     */
    public boolean isSPressed() {
        return isSPressed;
    }

    /**
     * Sets whether the 'S' key is pressed.
     *
     * @param isSPressed true if 'S' is pressed, false otherwise
     */
    public void setSPressed(boolean isSPressed) {
        this.isSPressed = isSPressed;
    }

    /**
     * Gets whether the 'D' key is pressed.
     *
     * @return true if 'D' is pressed, false otherwise
     */
    public boolean isDPressed() {
        return isDPressed;
    }

    /**
     * Sets whether the 'D' key is pressed.
     *
     * @param isDPressed true if 'D' is pressed, false otherwise
     */
    public void setDPressed(boolean isDPressed) {
        this.isDPressed = isDPressed;
    }

    /**
     * Checks if any key (W, A, S, D) is pressed.
     *
     * @return true if at least one key is pressed, false otherwise
     */
    public boolean isAnyKeyPressed() {
        return isWPressed || isAPressed || isSPressed || isDPressed;
    }
}