import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	
	public PercolationStats(int n, int trials)
	{
		mean = 0.0;
		stddev = 0.0;
		confidenceLo = 0.0;
		confidenceHi = 0.0;
		
		double[] x = new double[trials];
		
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("The values of n and trials must be greater than 0");
		
		for (int i = 0; i < trials; i++)
		{
			Percolation p = new Percolation(n);
			while (!p.percolates())
			{
				int randomSiteRow = StdRandom.uniform(1, n + 1);
				int randomSiteCol = StdRandom.uniform(1, n + 1);
				if (!p.isOpen(randomSiteRow, randomSiteCol))
					p.open(randomSiteRow, randomSiteCol);
			}
			x[i] = (double) p.numberOfOpenSites() / (n * n) * 1.0;
		}
		mean = StdStats.mean(x);
		stddev = StdStats.stddev(x);
		confidenceLo = (mean - (1.96 * stddev) / Math.sqrt(trials));
		confidenceHi = (mean + (1.96 * stddev) / Math.sqrt(trials));
	}
	
	public double mean()
	{
		return mean;
	}
	
	public double stddev()
	{
		return stddev;
	}
	
	public double confidenceLo()
	{
		return confidenceLo;
	}
	
	public double confidenceHi()
	{
		return confidenceHi;
	}
	
	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats ps = new PercolationStats(n, T);
		StdOut.println("mean \t \t \t= " + ps.mean());
		StdOut.println("stddev \t \t \t= " + ps.stddev());
		System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi + "]");
	}
}
