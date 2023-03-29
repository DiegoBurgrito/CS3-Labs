import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {
   private double[]ary;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
   	
   	
   	
   	
   }
   public double mean()                          // sample mean of percolation threshold
   {
   	return 0.0;
   }
   
   public double stddev()                        // sample standard deviation of percolation threshold
   {
    return 0.0;
   }
   public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
      return 0.0;
   }
   public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
   	return 0.0;
   }
   public static void main(String[] args)    // test client (described below)
   {
    int n = Integer.parseInt(args[0]);         // n-by-n percolation system
    int T = Integer.parseInt(args[1]);		 	// T trials
    PercolationStats ps = new PercolationStats(n,T);
   }
}