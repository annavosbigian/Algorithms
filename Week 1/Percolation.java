/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {
    private boolean[] grid;
    private WeightedQuickUnionUF wuf;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        this.n = n;
        grid = new boolean[n * n];
        wuf = new WeightedQuickUnionUF(n * n);
        for (int i = 0; i < n * n; i++) {
            grid[i] = false;
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int space = row * col;
        if (row < 0 || row > n) {
            throw new IllegalArgumentException("row not in range");
        }
        if (col < 0 || col > n) {
            throw new IllegalArgumentException("collumn not in range");
        }
        if (grid[row * col] = false) {
            grid[row * col] = true;
            if ((space - n > 0) && (grid[space - n] = true)) {
                wuf.union(row * col, row * col - n);
            }
            if (space % n > 0 && (grid[space - 1] = true)) {
                wuf.union(row * col, row * col - 1);
            }
            if (space % n < n && (grid[space + 1] = true)) {
                wuf.union(row * col, row * col + 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > n) {
            throw new IllegalArgumentException("row not in range");
        }
        if (col < 0 || col > n) {
            throw new IllegalArgumentException("collumn not in range");
        }
        return grid[row * col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 0 || row > n) {
            throw new IllegalArgumentException("row not in range");
        }
        if (col < 0 || col > n) {
            throw new IllegalArgumentException("collumn not in range");
        }
        for (int i = 0; i < n; i++) {
            if (grid[i] = true) {
                if (wuf.connected(i, row * col)) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i <= n * n - 1; i++) {
            if (grid[i] = true) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (grid[i] = true) {
                for (int j = n * n - n; j < n * n; j++)
                    if (wuf.connected(i, j)) {
                        return true;
                    }
            }
        }
        return false;
    }


    // test client (optional)
    public static void main(String[] args) {

    }
}
