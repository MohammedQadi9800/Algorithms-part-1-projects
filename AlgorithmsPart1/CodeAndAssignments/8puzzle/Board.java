import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {

    private int[][] correctTiles;
    private int[][] tiles;
    private int n; //board size
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        n = tiles.length;

        correctTiles = new int[n][n];
        int counter = 0;
        for(int i = 0; i < tiles.length; i++) {
            for(int j : tiles[i]) {
                if(i == n-1 && j == n-1)
                    counter = -1;
                correctTiles[i][j] = ++counter;
            }
        }
    }
                                           
    // string representation of this board
    public String toString() {
        String string = "" + tiles.length; //make int to string
        for(int i = 0; i < tiles.length; i++) {
            string = string + "\n";// adding a new line for each column
            for(int j : tiles[i]) {
                string = string + tiles[i].toString();
                if(j != tiles[i].length -1) 
                    string = string +" "; //adding spaces
            }
        }
        return string;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hammingCounter = 0;
        for(int i = 0; i < tiles.length; i++) {
            for(int j : tiles[i]) {
                //board tile == correct board tile is correct ? then add 0, otherwise: add 1
                hammingCounter +=  tiles[i][j] == correctTiles[i][j] ? 0 : 1;
            }
        }
        return hammingCounter;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanCounter = 0;
        for(int i = 0; i < tiles.length; i++) {
            for(int j : tiles[i]) {
                if(tiles[i][j] == correctTiles[i][j]) continue;
                //using the int rounding function when dividing we can use a formula to determin the corrosponding row
                double rowDbl = tiles[i][j] / n;
                int rowInt = tiles[i][j] / n;
                int rowReal = rowDbl > rowInt ? rowInt : rowInt - 1;
                
                //if the number remainder the divinsion = 0, then its in the right most index, other wise we must decrement by 1 so it can work on our 0 based array indexing.
                int columnReal = tiles[i][j] % n;
                columnReal += columnReal == 0 ? n - 1 : - 1;
                 
                int rowDiff = rowReal - i, columnDiff = columnReal - j;
                if(rowDiff < 0) rowDiff *= -1;
                if(columnDiff < 0) columnDiff *= -1;
                manhattanCounter += rowReal + columnDiff;
            }
        }

        return manhattanCounter;
    }

    // is this board the goal board?
    public boolean isGoal() {
        Board b = new Board(this.correctTiles);
        return this.equals(b);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        Board b = (Board) y;
        if (this.dimension() != b.dimension()) 
            return false;
        for(int i = 0; i < tiles.length; i++) {
            for(int j : tiles[i]) {
                if(tiles[i][j] != b.tiles[i][j])
                    return false;
            }
        }
        return true;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(int i1, int j1, int i2, int j2) {
        Board b = new Board(tiles);
        int temp;
        temp = b.tiles[i1][j1];
        b.tiles[i1][j1] = b.tiles[i2][j2];
        b.tiles[i2][j2] = temp;

        return b;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {return new neighborsIterable(); }

    private class neighborsIterable implements Iterable<Board> {

        @Override
        public Iterator<Board> iterator() {
            return new neighborsIterator();
        }

        private class neighborsIterator implements Iterator<Board> {

            ArrayList<Board> list  = new ArrayList<Board>();

            public void neighborsIterator() {
                int zeroI = 0;
                int zeroJ = 0;

                for(int i = 0; i < tiles.length; i++) {
                    for(int j : tiles[i]) {
                        if(tiles[i][j] == 0) {
                            zeroI = i;
                            zeroJ = j;
                            break;
                        }            
                    }
                }

                //if its not on the heighest row
                if(zeroI - 1 >= 0) {
                    list.add(twin(zeroI, zeroJ, zeroI - 1, zeroJ));
                }
                //if its not on the lowest row
                if(zeroI + 1 < n) {
                    list.add(twin(zeroI, zeroJ, zeroI + 1, zeroJ));
                }
                //if its not on the left most row
                if(zeroJ - 1 >= 0) {
                    list.add(twin(zeroI, zeroJ, zeroI, zeroJ - 1));
                }
                //if its not on right most row
                if(zeroJ + 1 < n) {
                    list.add(twin(zeroI, zeroJ, zeroI, zeroJ + 1));
                }

            }

            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public Board next() {
                if(list.isEmpty()) throw new NoSuchElementException();
                Board b = list.get(list.size() - 1);
                list.remove(list.size() - 1);
                return b;
            }

        }

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
