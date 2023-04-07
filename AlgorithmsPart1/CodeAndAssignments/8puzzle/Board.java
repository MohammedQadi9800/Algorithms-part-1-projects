import java.util.Iterator;

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

    // all neighboring boards
    public Iterable<Board> neighbors() {return new boardIterable(); }

    private class boardIterable implements Iterable<Board> {

        @Override
        public Iterator<Board> iterator() {
            return boardIterator();
        }

        private class boardIterator implements Iterator<Board> {

            public void boardIterable(Board tiles) {
                
            }

            @Override
            public boolean hasNext() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'hasNext'");
            }

            @Override
            public Board next() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'next'");
            }

        }

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
