package java.interface_adapter.NormalGiven;

import java.use_case.NormalGiven.NormalGivenInputBoundary;
import java.use_case.NormalGiven.NormalGivenInputData;

/**
 * Controller for the Normal Given Use Case.
 * Handles input from the View layer and forwards it to the Interactor.
 */
public class NormalGivenController {
    private final NormalGivenInputBoundary inputBoundary;

    /**
     * Constructor for NormalGivenController.
     *
     * @param inputBoundary the input boundary for the use case
     */
    public NormalGivenController(NormalGivenInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Executes the Normal Given Use Case with the specified input.
     *
     * @param isWPressed whether the 'W' key is pressed (rotate)
     * @param isAPressed whether the 'A' key is pressed (move left)
     * @param isSPressed whether the 'S' key is pressed (speed up falling)
     * @param isDPressed whether the 'D' key is pressed (move right)
     */
    public void execute(boolean isWPressed, boolean isAPressed, boolean isSPressed, boolean isDPressed) {
        // Create input data object
        final NormalGivenInputData inputData = new NormalGivenInputData();
        inputData.setWPressed(isWPressed);
        inputData.setAPressed(isAPressed);
        inputData.setSPressed(isSPressed);
        inputData.setDPressed(isDPressed);

        // Pass input data to the use case
        inputBoundary.execute(inputData);
    }

    /**
     * Overloaded execute method for when no keys are pressed (used for regular updates).
     */
    public void execute() {
        // Create input data object with no keys pressed
        final NormalGivenInputData inputData = new NormalGivenInputData();
        inputBoundary.execute(inputData);
    }
}