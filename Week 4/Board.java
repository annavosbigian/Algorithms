import edu.princeton.cs.algs4.In;

public class Board {

    private int[][] board;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        board = tiles;
        n = board.length;
    }

    // string representation of this board
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append(n);
        for (int i = 0; i < n; i++) {
            build.append("\n");
            for (int j = 0; j < n; j++) {
                build.append(board[i][j] + " ");
            }
            //return n, then each line of the grid
        }
        return (build.toString());

    }

        // board dimension n
        public int dimension () {
            return n;
        }

        // number of tiles out of place
        public int hamming () {
            int count = 0;
            int currentTile = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == n - 1 && j == n - 1) {
                        break;
                    }
                    if (board[i][j] != currentTile) {
                        count++;
                    }
                    currentTile++;
                }
            }
            return count;

        }

        // sum of Manhattan distances between tiles and goal
        public int manhattan () {
            int sum = 0;
            int currentTile = 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == n - 1 && j == n - 1) {
                        break;
                    }
                    if (board[i][j] < currentTile) {
                        sum += (currentTile - board[i][j]);
                    }
                    else if (board[i][j] > currentTile) {
                        sum += (board[i][j] - currentTile);
                    }
                    currentTile++;
                }
            }
            return sum;
        }

        // is this board the goal board?
        public boolean isGoal () {
            if (this.manhattan() == 0) {
                return true;
            }
            return false;
        }

        // does this board equal y?
        public boolean equals (Object y){
            if (y instanceof Board) {
                if (board.toString() == y.toString()) {
                    return true;
                }
            }
            return false;
        }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        Board twin = new Board(board);
        return twin;
    }


        // unit testing (not graded)
        public static void main (String[]args){
            for (String filename : args) {
                In in = new In(filename);
                int n = in.readInt();
                int[][] tiles = new int[n][n];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        tiles[i][j] = in.readInt();
                    }
                }
                Board board = new Board(tiles);
                System.out.println(board.toString());
                System.out.println(board.dimension());
                System.out.println(board.hamming());
                System.out.println(board.manhattan());
                System.out.println(board.isGoal());
            }
        }
    }

