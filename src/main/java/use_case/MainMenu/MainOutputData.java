package use_case.MainMenu;

/**
 * shows the output data for the main menu interactor.
 * Encapsulates the result or feedback of an action performed in the main menu.
 */
public class MainOutputData {
    private final String responseMessage;  // Feedback or result message
    private final int[][] binaryArray;     // Optional binary array result
    private final int[][][] colorMap;      // Optional color map result
    private final String ImgAddress;

    /**
     * Constructor for message-only output.
     *
     * @param responseMessage The response message to be presented to the user.
     */
    public MainOutputData(String responseMessage) {
        this(responseMessage, null, null, null);
    }

    /**
     * Constructor for detailed output including processed data.
     *
     * @param responseMessage The response message to be presented to the user.
     * @param binaryArray     The binary array generated.
     * @param colorMap        The color map generated.
     */
    public MainOutputData(String responseMessage, int[][] binaryArray, int[][][] colorMap, String ImgAddress) {
        this.responseMessage = responseMessage;
        this.binaryArray = binaryArray;
        this.colorMap = colorMap;
        this.ImgAddress = ImgAddress;
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

    public String getImgAddress() {
        return ImgAddress;
    }
}
