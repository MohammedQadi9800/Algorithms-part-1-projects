import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double trialResults [];
    private int numOfTrials;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if(n <= 0 || trials <= 0) 
            throw new IllegalArgumentException();
        numOfTrials = trials;
        trialResults = new double[trials];
        Percolation p;
        for(int i = 0; i < trials; i++) {
            p = new Percolation(n);
            while(!p.percolates()){
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if(!p.isOpen(row, col))
                    p.open(row, col);
            }
            trialResults[i] = (double) p.numberOfOpenSites() / (n * n);
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trialResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trialResults);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev())/Math.sqrt(numOfTrials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev())/Math.sqrt(numOfTrials));
    }
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(n, t);
        StdOut.println("mean\t\t\t\t\t= " + p.mean());
        StdOut.println("stddev\t\t\t\t  = " + p.stddev());
        StdOut.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
    }
}
