package interface_adapter.NormalGiven;

import interface_adapter.NormalGiven.ViewModelMain;

/**
 * NormalGivenViewModel manages the state for the Normal Given Use Case.
 * It inherits from the generic ViewModelMain.
 */
public class NormalGivenViewModel extends ViewModelMain<NormalGivenState> {

    private int[][] map;
    private int[][] solutionMap;
    private int[][][] colorMap;
    private String imgAddress;
    public NormalGivenViewModel() {
        super("NormalGivenView");
        setState(new NormalGivenState());
    }
    public void setMap(int[][] map) {
        this.map = map;
    }
    public int[][] getMap(){
        return map;
    }
    public void setSolutionMap(int[][] solutionMap) {
        this.solutionMap = solutionMap;
    }
    public int[][] getSolutionMap(){
        return solutionMap;
    }
    public void setColorMap(int[][][] colorMap) {
        this.colorMap = colorMap;
    }
    public int[][][] getColorMap(){
        return colorMap;
    }
    public void setImgAddress(String imgAddress) {
        this.imgAddress = imgAddress;
    }
    public String getImgAddress() {
        return imgAddress;
    }
}