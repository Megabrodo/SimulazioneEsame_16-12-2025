package a06.e2;

import java.util.Random;

public class LogicsImpl implements Logics {

    private final int[][] matrix;
    private final boolean[][] guessed;
    private final Random random = new Random();
    private final int width;
    private boolean memoryCounter;
    private Pair<Integer, Integer> previousTile;
    private Pair<Integer, Integer> secondTile;

    public LogicsImpl(int width) {
        this.matrix = new int[width][width];
        this.guessed = new boolean[width][width];
        this.width = width;
        setMatrix();
        memoryCounter = false;
    }

    private void setMatrix() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = random.nextInt(6) + 1;
            }
        }
    }

    @Override
    public void hit(Pair<Integer,Integer> coordinates) {
        if (!memoryCounter) {
            previousTile = coordinates;
            memoryCounter = true;
        } else {    
            secondTile = coordinates;
            memoryCounter = false;
        }
    }

    @Override
    public boolean checkEqualsTiles(Pair<Integer,Integer> firstTile, Pair<Integer,Integer> secondTile) {
        memoryCounter = false;
        if (getValueMatrix(firstTile) != getValueMatrix(secondTile)) {
            return false;
        }

        guessed[firstTile.getX()][firstTile.getY()] = true;
        guessed[secondTile.getX()][secondTile.getY()] = true;
        return true;
    }

    @Override
    public int getValueMatrix(Pair<Integer, Integer> coordinates) {
        return matrix[coordinates.getX()][coordinates.getY()];
    }

    @Override
    public Pair<Integer, Integer> getFirstTile() {
        return previousTile;
    }

    @Override
    public Pair<Integer, Integer> getSecondTile() {
        return secondTile;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public boolean checkAllTiles() {
        for (int x1 = 0; x1 < width; x1++) {
            for (int y1 = 0; y1 < width; y1++) {

                if (!guessed[x1][y1]) {

                    for (int x2 = x1; x2 < width; x2++) {
                        for (int y2 = 0; y2 < width; y2++) {

                            if (x1 != x2 || y1 != y2) {
                                if (!guessed[x2][y2]) {
                                    if (matrix[x1][y1] == matrix[x2][y2]) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
}
