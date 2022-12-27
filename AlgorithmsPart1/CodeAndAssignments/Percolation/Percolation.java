import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int top = 0;
    private final int bot; 
    private final int size;
    private final WeightedQuickUnionUF uf;
    private final boolean [][] openedSites;
    private  int numOfOpenedSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n < 1)
            throw new IllegalArgumentException();
        size = n;
        bot = size * size + 1;
        uf = new WeightedQuickUnionUF(size * size + 2);
        openedSites = new boolean[size][size];
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        findExeption(row, col);
        openedSites[row - 1][col - 1] = true;
        ++numOfOpenedSites;
        int cellIndex = getIndex(row, col);
        //checks if the opened cell is in the first row to connect it with top.
        if(row == 1) {
            uf.union(cellIndex, top);
        }
        //checks if the opened cell is in the last row to connect it with bot.
        if(row == size) {
            uf.union(cellIndex, bot);
        }

        //uniting the cell with its neighbors if they exist and if they are open.
        if(row > 1 && isOpen(row - 1, col)) {
            uf.union(cellIndex, getIndex(row - 1, col)); //cell above
        }
        if(row < size && isOpen(row + 1, col)) {
            uf.union(cellIndex, getIndex(row + 1, col)); //cell bellow
        }
        if(col > 1 && isOpen(row, col - 1)) { 
            uf.union(cellIndex, getIndex(row, col - 1)); // cell to the left
        }
        if(col < size && isOpen(row, col + 1)) {
            uf.union(cellIndex, getIndex(row, col + 1)); //cell to the right
        }

    }

    // returns the position of the box associated with row row and column col.
    private int getIndex(int row, int col) {
        return size * (row - 1) + col;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        findExeption(row, col);
        return openedSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        findExeption(row, col);
        //if there is no exception, then it will run.
        return uf.find(getIndex(row, col)) == uf.find(top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfOpenedSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bot);
    }

    private void findExeption(int row, int col) {
        if(row < 1 || row > size || col < 1 || col > size) 
            throw new IllegalArgumentException();
    }
}
