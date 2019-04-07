import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	
	private WeightedQuickUnionUF uf;
	private int gridSize;
	private int[] openSites;
	private int numberOfSites;
	
	public Percolation(int n)
	{
		if (n <= 0)
			throw new IllegalArgumentException("n must be greater than 0");
		this.gridSize = n + 1;
		this.numberOfSites = this.gridSize * this.gridSize;
		uf = new WeightedQuickUnionUF(this.numberOfSites);
		openSites = new int[this.numberOfSites];
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		System.out.println("");
		Percolation p = new Percolation(5);
		System.out.println("Number of components: " + p.uf.count());
		p.uf.union(1, 23);
		p.uf.union(7, 23);
		p.uf.union(5, 6);
		p.uf.union(5, 8);
		p.uf.union(6, 9);
		p.uf.union(7, 6);
		System.out.println("Number of components: " + p.uf.count());
		System.out.println(p.uf.find(23));
		System.out.println(p.uf.find(6));
		System.out.println(p.uf.find(7));
	}
	
}
