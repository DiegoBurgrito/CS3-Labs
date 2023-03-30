import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.Arrays;

public class Percolation {

    // true = open
    // false = blocked
    //A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
    private boolean[][] mat;
    private WeightedQuickUnionUF unionUF;
    public Percolation(int n)                // create n-by-n mat, with all sites blocked
    {
        // n mat size
        mat = new boolean[n][n];
        unionUF = new WeightedQuickUnionUF(n);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = false;
            }
        }
    }

    public void open(int row, int col)       // open site (row, col) if it is not open already
    {
        if(checkRange(row, col) && isOpen(row, col)) mat[row - 1][col - 1] = true;
    }

    private int getID(int r, int c) {            // helper method to calculate ID
        return r;
    }

    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        return checkRange(row, col) && !mat[row - 1][col - 1];
    }

    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        return checkRange(row, col) && mat[row - 1][col - 1];
    }

    private boolean checkRange(int row, int col) {    // validate input
        return row - 1 >= 0 && row - 1 < mat.length && col - 1 >= 0 && col - 1 < mat.length;
    }

    public boolean percolates()              // does the system percolate?, this means top row touches bottom row
    {

        return false;
    }

    private boolean percolates(int row, int col) {
        if(isOpen(row, col ))
    }

    //WeightedQuickUnionUF unionfind thing to use
    private void print() {                   // prints boolean[][] called mat
        for (boolean[] row : mat) {
            for (boolean col : row) {
                System.out.print((col ? 1 : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)   // test client (optional){
    {
        Percolation test = new Percolation(2);    // simple test case
        test.print();
        test.open(1, 1);
        test.print();
        test.open(2, 1);
        test.print();

        System.out.println(test.percolates());
    }
}
