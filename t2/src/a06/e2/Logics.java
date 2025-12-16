package a06.e2;

public interface Logics {
    void hit (Pair<Integer,Integer> coordinates);

    boolean checkEqualsTiles(Pair<Integer,Integer> firstTile, Pair<Integer,Integer> secondTile);

    int getValueMatrix (Pair<Integer,Integer> coordinates);

    Pair<Integer, Integer> getFirstTile();

    Pair<Integer, Integer> getSecondTile();

    int getWidth();

    boolean checkAllTiles();
}
