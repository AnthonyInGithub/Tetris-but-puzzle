package use_case.NormalGiven;


public class NormalGivenInteractor {
    private final NormalGivenDataAccessInterface normalGivenDataAccessObject;
    private final NormalGivenOutputBoundary normalGivenPresenter;
    private int[][] currentMap; //first index height, second index width. 10*22 in size
    //IMPORTANT: the outputMap is different from currentMap, where outputMap is 10*20 in size and current map is
    //10*22 in size. This is for ensure that we can put the current shape into map at once when the shape is newly generated.

    private int[][] outputMap; //10*22 in size

    private int[] currentShape; //an array of length 2, where 0th position specify the type of shape, 1st specify the rotation state.
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


    public NormalGivenInteractor(NormalGivenDataAccessInterface normalGivenDataAccessObject, NormalGivenOutputBoundary normalGivenPresenter) {
        this.normalGivenDataAccessObject = normalGivenDataAccessObject;
        this.normalGivenPresenter = normalGivenPresenter;
        currentMap = normalGivenDataAccessObject.getCurrentMap();
        generateNewPiece();
    }

    public void execute(NormalGivenInputData normalGivenInputData){
        currentMap = normalGivenDataAccessObject.getCurrentMap();
        x = normalGivenDataAccessObject.getX();
        y = normalGivenDataAccessObject.getY();
        handleInput(normalGivenInputData); // for process WASD input
        //TODO: check WASD pressed and update x, y.(when S pressed, double the falling speed.)
        pieceFall(); //calculate the new piece's position y change.

        if (!canMove(x, y + 1)) { // If piece can't fall further (I changed the code to use some helper functions instead)
            lockPieceInPlace();
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

        normalGivenPresenter.execute(new normalGivenOutputData(outputMap));
        private void updateOutputMap() {
            outputMap = new int[currentMap.length - 2][currentMap[0].length];
            for (int i = 2; i < currentMap.length; i++) {
                System.arraycopy(currentMap[i], 0, outputMap[i - 2], 0, currentMap[i].length);
            }
        }



    }
    private void handleInput(NormalGivenInputData inputData) {
        char keyPressed = inputData.getKeyPress();

        if (keyPressed == 'A') {
            moveLeft();
        } else if (keyPressed == 'D') {
            moveRight();
        } else if (keyPressed == 'W') {
            rotatePiece();
        } else if (keyPressed == 'S') {
            accelerateFall();
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
        int originalRotation = currentShape[1];
        currentShape[1] = (currentShape[1] + 1) % 4; // advance to the next rotation state
        if (!canMove(x, y)) { // revert rotation if there's a collision
            currentShape[1] = originalRotation;
        }
    }
    // accelerates the piece's fall speed by attempting to move it down by two units
    private void accelerateFall() {
        //
    }
    // checks if the shape can be moved to the specified (newX, newY) position
    private boolean canMove(int newX, int newY) {
        int[][] shapeMatrix = shapes[currentShape[0]][currentShape[1]];
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
    }
    // locks the current shape in place by updating currentMap with the shape cells
    private void lockPieceInPlace() {
        int[][] shapeMatrix = shapes[currentShape[0]][currentShape[1]];
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
        int[][] shapeMatrix = shapes[currentShape[0]][currentShape[1]];
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
        currentShape = normalGivenDataAccessObject.getNextShape();
        x = 4;
        y = 0;
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
}