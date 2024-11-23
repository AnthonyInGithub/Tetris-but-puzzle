package use_case.NormalGiven;

/**
 * shows the output data for the main menu interactor.
 * Encapsulates the result or feedback of an action performed in the main menu.
 */
public class MainOutputData {
    private final String responseMessage;  // Feedback or result message
    private final int[][] binaryArray;     // Optional binary array result
    private final int[][][] colorMap;      // Optional color map result

    /**
     * Constructor for message-only output.
     *
     * @param responseMessage The response message to be presented to the user.
     */
    public MainOutputData(String responseMessage) {
        this(responseMessage, null, null);
    }

    /**
     * Constructor for detailed output including processed data.
     *
     * @param responseMessage The response message to be presented to the user.
     * @param binaryArray     The binary array generated.
     * @param colorMap        The color map generated.
     */
    public MainOutputData(String responseMessage, int[][] binaryArray, int[][][] colorMap) {
        this.responseMessage = responseMessage;
        this.binaryArray = binaryArray;
        this.colorMap = colorMap;
    }

    /**
     * gets the response message.
     *
     * @return the response message.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * gets the binary array result.
     *
     * @return The binary array, or null if not applicable.
     */
    public int[][] getBinaryArray() {
        return binaryArray;
    }

    /**
     * gets the color map result.
     *
     * @return The color map, or null if not applicable.
     */
    public int[][][] getColorMap() {
        return colorMap;
    }
}
