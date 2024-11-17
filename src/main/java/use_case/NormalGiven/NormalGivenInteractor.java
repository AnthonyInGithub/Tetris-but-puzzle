package use_case.NormalGiven;

import data_access.InMemoryDataAccessObject;
import entity.Entity;


public class NormalGivenInteractor implements NormalGivenInputBoundary{
    public final data_access.InMemoryDataAccessObject normalGivenDataAccessObject;
    private final NormalGivenOutputBoundary normalGivenPresenter;
    private int[][] currentMap; //first index height, second index width. 10*22 in size
    //IMPORTANT: the outputMap is different from currentMap, where outputMap is 10*20 in size and current map is
    //10*22 in size. This is for ensure that we can put the current shape into map at once when the shape is newly generated.

    private int[][] outputMap; //10*22 in size

    int[][] shapeMatrix;

    private int[] currentShapeState; //an array of length 2, where 0th position specify the type of shape, 1st specify the rotation state.

    //The shape is organized this way: for example, the I shape contains I and its rotated state. You need to get shape from DAO
    //private final int[][][][] shapes = {
    //        {{{0,0,0},{1,1,0},{1,1,0}}, {{0,0,0},{1,1,0},{1,1,0}}, {{0,0,0},{1,1,0},{1,1,0}}, {{0,0,0},{1,1,0},{1,1,0}}}, // O Shapes
    //        {{{1,0,0},{1,0,0},{1,0,0}}, {{0,0,0},{0,0,0},{1,1,1}}, {{1,0,0},{1,0,0},{1,0,0}}, {{0,0,0},{0,0,0},{1,1,1}}}, //I shape
    //        {{{1,1,1},{0,1,0},{0,0,0}}, {{0,1,0},{0,1,1},{0,1,0}}, {{0,1,0},{1,1,0},{0,1,0}}, {{0,1,0},{1,1,1},{0,0,0}}}, // T Shape
    //        {{{0,1,0},{0,1,0},{0,1,1}}, {{0,0,0},{1,1,1},{1,0,0}}, {{1,1,0},{0,1,0},{0,1,0}}, {{0,0,1},{1,1,1},{0,0,0}}}, // L Shape
    //        {{{1,1,0},{0,1,1},{0,0,0}}, {{0,0,1},{0,1,1},{0,1,0}}, {{1,1,0},{0,1,1},{0,0,0}}, {{0,0,1},{0,1,1},{0,1,0}}} //Z Shape
    //};
    private final int[][][][] shapes = {};

    int x, y; //(0,0) position is in left top corner for map. the position that corresponds to x, y is at the top left of the shape.


    public NormalGivenInteractor(InMemoryDataAccessObject normalGivenDataAccessObject, NormalGivenOutputBoundary normalGivenPresenter) {
        this.normalGivenDataAccessObject = normalGivenDataAccessObject;
        this.normalGivenPresenter = normalGivenPresenter;
        Entity currentEntity = normalGivenDataAccessObject.getEntity();
        currentMap = currentEntity.getGameBoard();

        generateNewPiece();

    }

    public void execute(NormalGivenInputData normalGivenInputData){
        Entity currentEntity = normalGivenDataAccessObject.getEntity();
        currentMap = currentEntity.getGameBoard();
        x = normalGivenDataAccessObject.getX();
        y = normalGivenDataAccessObject.getY();
        shapeMatrix = normalGivenDataAccessObject.getShape(currentShapeState[0],currentShapeState[1]);
        handleInput(normalGivenInputData); // for process WASD input
        System.out.println("handleInput: " + y);
        //TODO: check WASD pressed and update x, y.(when S pressed, double the falling speed.)
        pieceFall(); //calculate the new piece's position y change.
        System.out.println("piecefall: " + y);
        if (!canMove(x, y + 1)) { // If piece can't fall further (I changed the code to use some helper functions instead)
            lockPieceInPlace();
            System.out.println("lock: " + y);
            clearLines();
            generateNewPiece();
        }
        // TODO: using OR operation to correspond map and shape. For example, assume shape is O.
        // x, y = 0. Then currentMap[0][0] will be determine by (currentMap[0][0] or shape[0][0][0][0]),
        // and currentMap[1][0] determine by (currentMap[1][0] or shape[0][0][1][0])

        // TODO: Check for whether shape reach bottom. If it reachs, generate a new shape and pass it to data access object.
        // reset and  And update the current map to DAO permanently.
        // Also, don't forget to check if any of the line that is complete(which will then be deleted)

        // TODO: pass to DAO new x, y value, the updated shape.(rotated or a new one)
        updateCurrentMap();
        outputMap = new int[currentMap.length - 2][currentMap[0].length];
        for (int i = 2; i < currentMap.length; i++) {
            System.arraycopy(currentMap[i], 0, outputMap[i - 2], 0, currentMap[i].length);
        }
        for(int i = 0; i< currentMap.length; i++){
            for(int j = 0; j < currentMap[0].length; j++){
                System.out.print(currentMap[i][j] + " ");
            }
            System.out.print("\n");
        }
        normalGivenDataAccessObject.setX(x);
        normalGivenDataAccessObject.setY(y);
        normalGivenPresenter.execute(new NormalGivenOutputData(outputMap));


    }
    private void handleInput(NormalGivenInputData inputData) {

        if (inputData.isAPressed()) {
            moveLeft();
            System.out.println("A pressed");
        }
        if (inputData.isDPressed()) {
            moveRight();
        }
        if (inputData.isWPressed()) {
            rotatePiece();
        }
        // for testing, delete after
        if (inputData.isSPressed()) {
            y ++;
            System.out.println("S pressed");

        }

    }

    private void moveLeft() {

        if (canMove(x - 1, y)) {
            x--;
        }
    }
    private void moveRight() {
        if (canMove(x + 1, y)) {
            x++;
        }
    }
    // Rotates the piece to the next rotation state, reverting if collision occurs
    private void rotatePiece() {
        int originalRotation = currentShapeState[1];
        currentShapeState[1] = (currentShapeState[1] + 1) % 4; // advance to the next rotation state
        if (!canMove(x, y)) { // revert rotation if there's a collision
            currentShapeState[1] = originalRotation;
        }
    }
    // accelerates the piece's fall speed by attempting to move it down by two units
    private void accelerateFall() {
        //
    }
    // checks if the shape can be moved to the specified (newX, newY) position
    private boolean canMove(int newX, int newY) {
        for (int i = 0; i < shapeMatrix.length; i++) {
            for (int j = 0; j < shapeMatrix[0].length; j++) {
                if (shapeMatrix[i][j] != 0) {
                    int mapX = newX + j;
                    int mapY = newY + i;
                    // check bounds and collision with existing pieces in currentMap
                    if (mapX < 0 || mapX >= 10 || mapY >= 22 || (mapY >= 0 && currentMap[mapY][mapX] != 0)) {
                        return false; // out of bounds
                    }
                }
            }
        }
        //System.out.println("can move");
        return true;
    }
    // locks the current shape in place by updating currentMap with the shape cells
    private void lockPieceInPlace() {
        int[][] shapeMatrix = normalGivenDataAccessObject.getShape(currentShapeState[0],currentShapeState[1]);//
        for (int i = 0; i < shapeMatrix.length; i++) {
            for (int j = 0; j < shapeMatrix[0].length; j++) {
                if (shapeMatrix[i][j] != 0) {
                    currentMap[y + i][x + j] |= shapeMatrix[i][j]; // using OR operation merge
                }
            }
        }
        normalGivenDataAccessObject.updateMap(currentMap); // Save to DAO
    }
    // clears any full lines in the currentMap and shifts rows down
    private void clearLines() {
        for (int i = 0; i < currentMap.length; i++) {
            boolean fullLine = true;

            for (int j = 0; j < currentMap[i].length; j++) {
                if (currentMap[i][j] == 0) {
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                removeLine(i);
            }
        }
    }
    private void removeLine(int row) {
        // Shift all rows above the specified row down by one
        for (int i = row; i > 0; i--) {
            System.arraycopy(currentMap[i - 1], 0, currentMap[i], 0, currentMap[i].length);
        }
    }
    private boolean isOutOfBounds() {
        int[][] shapeMatrix = normalGivenDataAccessObject.getShape(currentShapeState[0],currentShapeState[1]);//
        for (int i = 0; i < shapeMatrix.length; i++) {
            for (int j = 0; j < shapeMatrix[0].length; j++) {
                if (shapeMatrix[i][j] != 0) {
                    int mapX = x + j;
                    int mapY = y + i;
                    if (mapX < 0 || mapX >= 10 || mapY >= 22) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private void generateNewPiece() {
        normalGivenDataAccessObject.generateNewPiece();
        currentShapeState = normalGivenDataAccessObject.getCurrentShapeState();
        if (isOutOfBounds()) {
            normalGivenPresenter.gameOver();
        }
    }

    // Updates the y-coordinate to make the piece fall down one unit
    private void pieceFall() {
        if (canMove(x, y + 1)) {
            y++;
        }
    }

    private void updateCurrentMap(){
        int[][] tempMap = new int[currentMap.length][];
        for (int i = 0; i < currentMap.length; i++) {
            tempMap[i] = currentMap[i].clone();
        }

        for(int i = 0; i < shapeMatrix.length; i++){
            for (int b = 0; b < shapeMatrix[0].length; b++){
                tempMap[x+i][y+b] = shapeMatrix[i][b] | tempMap[x+i][y+b];
            }
        }
        currentMap = tempMap;
    }
}