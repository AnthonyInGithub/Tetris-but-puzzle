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
        pieceFall(); //calculate the new piece's position y change.
        normalGivenDataAccessObject.setX(x);
        normalGivenDataAccessObject.setY(y);
        if (!canMove(x, y + 1)) { // If piece can't fall further (I changed the code to use some helper functions instead)
            lockPieceInPlace();
            generateNewPiece();
            clearLines();

        }

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

        normalGivenPresenter.execute(new NormalGivenOutputData(outputMap));


    }
private void handleInput(NormalGivenInputData inputData) {
    if (inputData.isAPressed()) {
        moveLeft();
        if (x < 0) {
            x = 0;
            for (int i = 0; i < shapeMatrix.length; i++) {
                for (int j = 1; j < shapeMatrix[0].length; j++) { // Start from 1 to avoid out-of-bounds
                    if (shapeMatrix[i][j] == 1) {
                        shapeMatrix[i][j] = 0;
                        shapeMatrix[i][j - 1] = 1;
                    }
                }
            }
        }
    }

    if (inputData.isDPressed()) {
        moveRight();
        if (x > 7) {
            x = 7;
            for (int i = 0; i < shapeMatrix.length; i++) {
                for (int j = shapeMatrix[0].length - 2; j >= 0; j--) { // Start from the second last column
                    if (shapeMatrix[i][j] == 1) {
                        shapeMatrix[i][j] = 0;
                        shapeMatrix[i][j + 1] = 1;
                    }
                }
            }
        }
    }

    if (inputData.isWPressed()) {
        rotatePiece();
    }

    // For testing, remove later
    if (inputData.isSPressed()) {
        y++;
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
        shapeMatrix = normalGivenDataAccessObject.getShape(currentShapeState[0],currentShapeState[1]);
        System.out.println("x:" + x);
        System.out.println("y:" + y);
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
    // Checks if the current piece is out of bounds on the game map
    private boolean isOutOfBounds() {
        int[][] shapeMatrix = normalGivenDataAccessObject.getShape(currentShapeState[0], currentShapeState[1]);
        for (int i = 0; i < shapeMatrix.length; i++) {
            for (int j = 0; j < shapeMatrix[0].length; j++) {
                if (shapeMatrix[i][j] != 0) {
                    // Check if the shape is out of bounds
                    if (x + j < 0 || x + j >= 10 || y + i >= 22) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // Generates a new game piece by interacting with the data access object.
    // Updates the current shape's state and coordinates (x, y).
    private void generateNewPiece() {
        normalGivenDataAccessObject.generateNewPiece();
        currentShapeState = normalGivenDataAccessObject.getCurrentShapeState();
        if (isOutOfBounds()) {
            normalGivenPresenter.gameOver();
        }
        x = normalGivenDataAccessObject.getX();
        y = normalGivenDataAccessObject.getY();
    }

    // Updates the y-coordinate to make the piece fall down one unit
    private void pieceFall() {
        if (canMove(x, y + 1)) {
            y++;
        }
    }
    // Updates the current game map to include the current piece's position.
    private void updateCurrentMap(){
        int[][] tempMap = new int[currentMap.length][];
        for (int i = 0; i < currentMap.length; i++) {
            tempMap[i] = currentMap[i].clone();
        }
        // Add the shape of the current piece to the map
        for(int i = 0; i < shapeMatrix.length; i++){
            for (int b = 0; b < shapeMatrix[0].length; b++){
                if(y+b < 22 && x+i < 10){ // Ensure indices are within bounds
                    tempMap[y+b][x+i] = shapeMatrix[b][i] | tempMap[y+b][x+i];
                }
            }
        }
        currentMap = tempMap; // Update the current map
    }
}
