package org.example.NormalGiven;


public class NormalGivenInteractor {
    private final NormalGivenDataAccessInterface normalGivenDataAccessObject;
    private final NormalGivenOutputBoundary normalGivenPresenter;
    private int[][] currentMap; //first index height, second index width. 10*22 in size
    //IMPORTANT: the outputMap is different from currentMap, where outputMap is 10*20 in size and current map is
    //10*22 in size. This is for ensure that we can put the current shape into map at once when the shape is newly generated.

    private int[][] outputMap; //10*22 in size

    private int[] currentShape; //an array of length 2, where 0th position specify the type of shape, 1st specify the rotation state.
    //The shape is organized this way: for example, the I shape contains I and its rotated state
    private final int[][][][] shapes = {
            {{{0,0,0},{1,1,0},{1,1,0}}, {{0,0,0},{1,1,0},{1,1,0}}, {{0,0,0},{1,1,0},{1,1,0}}, {{0,0,0},{1,1,0},{1,1,0}}}, // O Shapes
            {{{1,0,0},{1,0,0},{1,0,0}}, {{0,0,0},{0,0,0},{1,1,1}}, {{1,0,0},{1,0,0},{1,0,0}}, {{0,0,0},{0,0,0},{1,1,1}}}, //I shape
            {{{1,1,1},{0,1,0},{0,0,0}}, {{0,1,0},{0,1,1},{0,1,0}}, {{0,1,0},{1,1,0},{0,1,0}}, {{0,1,0},{1,1,1},{0,0,0}}}, // T Shape
            {{{0,1,0},{0,1,0},{0,1,1}}, {{0,0,0},{1,1,1},{1,0,0}}, {{1,1,0},{0,1,0},{0,1,0}}, {{0,0,1},{1,1,1},{0,0,0}}}, // L Shape
            {{{1,1,0},{0,1,1},{0,0,0}}, {{0,0,1},{0,1,1},{0,1,0}}, {{1,1,0},{0,1,1},{0,0,0}}, {{0,0,1},{0,1,1},{0,1,0}}} //Z Shape
    };
    int x, y; //(0,0) position is in left top corner for map. the position that corresponds to x, y is at the top left of the shape.


    public NormalGivenInteractor(NormalGivenDataAccessInterface normalGivenDataAccessObject, NormalGivenOutputBoundary normalGivenPresenter) {
        this.normalGivenDataAccessObject = normalGivenDataAccessObject;
        this.normalGivenPresenter = normalGivenPresenter;
        currentMap = normalGivenDataAccessObject.getCurrentMap();
    }

    public void execute(NormalGivenInputData normalGivenInputData){
        currentMap = normalGivenDataAccessObject.getCurrentMap();
        x = normalGivenDataAccessObject.getX();
        y = normalGivenDataAccessObject.getY();
        //TODO: check WASD pressed and update x, y.(when S pressed, double the falling speed.)
        pieceFall(); //calculate the new piece's position y change.

        if(x >= 8){ // check for whether piece can rotate at the right edge.(only on right edge because x,y correspond to top left of a shape)

            if(x>=9){

            }
        }
        // TODO: using OR operation to correspond map and shape. For example, assume shape is O.
        // x, y = 0. Then currentMap[0][0] will be determine by (currentMap[0][0] or shape[0][0][0][0]),
        // and currentMap[1][0] determine by (currentMap[1][0] or shape[0][0][1][0])

        // TODO: Check for whether shape reach bottom. If it reachs, generate a new shape and pass it to data access object.
        // reset and  And update the current map to DAO permanently.
        // Also, don't forget to check if any of the line that is complete(which will then be deleted)

        // TODO: pass to DAO new x, y value, the updated shape.(rotated or a new one)

        normalGivenPresenter.execute(new normalGivenOutputData(currentMap));

    }
    private void pieceFall() {
        //TODO: update y

    }


}