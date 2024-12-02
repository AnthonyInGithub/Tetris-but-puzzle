package use_case.NormalGiven;

/**
 * Input Boundary for the Normal Given Use Case.
 * Defines the method that the Interactor must implement.
 */
public interface NormalGivenInputBoundary {
    /**
     * Executes the logic of the Normal Given Use Case based on the input data.
     *
     * @param inputData the input data from the Controller
     */
    void execute(NormalGivenInputData inputData);
}