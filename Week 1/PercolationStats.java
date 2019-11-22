
/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: Utilized https://algs4.cs.princeton.edu/code/javadoc/edu/princeton/cs/algs4/StdRandom.html#uniform-int-int-
                          https://introcs.cs.princeton.edu/java/stdlib/StdOut.java.html
 **************************************************************************** */

public class PercolationStats {

    private int n;
    private int trials;
    private double[] results;
    private Percolation perc;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        System.out.println("1");
        this.n = n;
        this.trials = trials;
        results = new double[trials];
        //perform operation #trials times
        for (int i = 0; i < trials; i++) {
            System.out.println("2");
            //make percolation
            perc = new Percolation(n);
            int count = 0;
            //keep opening
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                System.out.println(row);
                int column = StdRandom.uniform(1, n + 1);
                System.out.println(column);
                perc.open(row, column);
                //count the number of tries
                count++;
                //find the threshhold
                if (perc.percolates()) {
                    double mean = count / (n * n);
                    results[i] = mean;
                    System.out.println("percolated");
                }
            }
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        double mean = mean();
        double stddev = stddev();
        double total = mean - 1.96 * (stddev / Math.sqrt(trials));
        return total;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        double mean = mean();
        double stddev = stddev();
        double total = mean + 1.96 * (stddev / Math.sqrt(trials));
        return total;
    }

    // test client (see below)
    public static void main(String[] args) {
        //take command-line arguments n & T
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        //perform T independent computational experiments
        PercolationStats ps = new PercolationStats(n, T);
        System.out.println("mean is" + ps.mean());
        System.out.println(" stddev is " + ps.stddev());
        System.out.println("95% CI low is " + ps.confidenceLow());
        System.out.println("95% CI high is " + ps.confidenceHigh());
    }
}
