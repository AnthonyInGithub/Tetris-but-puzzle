package use_case.NormalGiven;

/**
 * Output Boundary for the Normal Given Use Case.
 * Defines the method for passing data to the Presenter.
 */
public interface NormalGivenOutputBoundary {
    /**
     * Passes the output data to the Presenter.
     *
     * @param outputData the output data containing updated game state
     */
    void execute(NormalGivenOutputData outputData);

    /**
     * Informs the Presenter that the game is over.
     */
    void gameOver(boolean success);
    //void gameSucceeded();
}