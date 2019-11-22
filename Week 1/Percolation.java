/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description: Percolation uses Weighted Union Find and tests whether the top row of a grid is connected to the bottom row. 
                 https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php
 **************************************************************************** */

public class Percolation {
    private int n;
    private WeightedQuickUnionUF wqu;
    private boolean[][] grid;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }
        wqu = new WeightedQuickUnionUF(n * n + 2);
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        System.out.println("complete");
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n) {
            throw new IllegalArgumentException("row not in range");
        }
        if (col <= 0 || col > n) {
            throw new IllegalArgumentException("collumn not in range");
        }
        if (isOpen(row, col)) {
            return;
        }
        grid[row - 1][col - 1] = true;
        //union it with any other sites around it
        if (row == 1) {
            wqu.union(0, col);
        }
        if (row == n) {
            wqu.union(n * n + 1, row * col + col - row);
        }
        if (row > 1 && isOpen(row - 1, col)) {
            wqu.union(col * (row - 1) + col - row, col * (row - 1) + col);
        }
        else if (row + 1 <= n && isOpen(row + 1, col)) {
            wqu.union(col * row + col, col * row + col - row);
        }
        else if (col - 1 >= 1 && isOpen(row, col)) {
            wqu.union(col * row + col - 1 - row, col * row + col - row);
        }
        else if (col + 1 <= n && isOpen(row + 1, col)) {
            wqu.union(col * row + col + 1 - row, col * row + col - row);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return wqu.connected(0, (row - 1) * col + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isOpen(i + 1, j + 1)) {
                    count++;
                }
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqu.connected(0, n * n + 1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 5);
        System.out.println(p.numberOfOpenSites());
        p.open(2, 3);
        System.out.println(p.percolates());
        p.open(3, 2);
        System.out.println(p.isFull(3, 2));
        System.out.println(p.isFull(3, 3));
        p.open(4, 2);
        p.open(5, 2);
        System.out.println(p.percolates());
    }
}
