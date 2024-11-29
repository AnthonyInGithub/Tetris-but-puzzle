package use_case.NormalGiven;

/**
 * Output Data for the Normal Given Use Case.
 * Contains information about the updated game state.
 */
public class NormalGivenOutputData {
    private final int[][] map; // 2D array representing the game board (10x20)
    private final int[][] solutionMap;
    private final int[][][] colorMap;
    private final String ImgAddress;
    /**
     * Constructor for NormalGivenOutputData.
     *
     * @param map the updated game board
     */
    public NormalGivenOutputData(int[][] map, int[][] solutionMap, int[][][] colorMap, String ImgAddress) {

        if (map == null || solutionMap == null || colorMap == null || ImgAddress == null){
            throw new IllegalArgumentException("map/solutionMap cannot be null");
        }
        this.map = map;
        this.solutionMap = solutionMap;
        this.colorMap = colorMap;
        this.ImgAddress = ImgAddress;
    }

    /**
     * Gets the updated game board.
     *
     * @return the 2D array representing the game board
     */
    public int[][] getMap() {
        return map;
    }
    public int[][] getSolutionMap() {
        return solutionMap;
    }
    public int[][][] getColorMap() {
        return colorMap;
    }
    public String getImgAddress() {
        return ImgAddress;
    }
}