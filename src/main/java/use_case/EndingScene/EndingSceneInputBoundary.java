package use_case.EndingScene;

import use_case.NormalGiven.NormalGivenInputData;

public interface EndingSceneInputBoundary {
    /**
     * Executes the logic of the Normal Given Use Case based on the input data.
     *
     * @param inputData the input data from the Controller
     */

    void execute(EndingSceneInputData inputData);

}
