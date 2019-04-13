import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	
	private WeightedQuickUnionUF uf;
	private int gridSize;
	private int[] openSites;
	private int numberOfSites;
	private int numberOfOpenSites;
	private int topVirtualSite;
	private int bottomVirtualSite;
	
	public Percolation(int n)
	{
		if (n <= 0)
			throw new IllegalArgumentException("n must be greater than 0");
		gridSize = n + 1;
		numberOfSites = gridSize * gridSize;
		uf = new WeightedQuickUnionUF(numberOfSites);
		openSites = new int[numberOfSites]; // Initialized to default value 0: closed
		numberOfOpenSites = 0;
		topVirtualSite = 0;
		bottomVirtualSite = (gridSize - 1) * gridSize;
		openSites[topVirtualSite] = 1;
		openSites[bottomVirtualSite] = 1;
	}
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		
		System.out.println("");
		int n = 5;
		Percolation p = new Percolation(n);
		int row = 1, col = 1;
		p.open(row, col);
		row = 5;
		col = 5;
		p.open(row, col);
	}
	
	private boolean validateIndices(int row, int col)
	{
		if ((row >= 1 && row <= gridSize - 1) && (col >= 1 && col <= gridSize - 1))
			return true;
		else
			// throw new IllegalArgumentException("The values of row and column must be
			// between 1
			// and " + (this.gridSize - 1));
			return false;
	}
	
	private int calculateIndex(int row, int col)
	{
		// Validate Indices
		if (!this.validateIndices(row, col))
			throw new IllegalArgumentException("The values of row and column must be between 1 and " + (gridSize - 1));
		int index = row * this.gridSize + col;
		return index;
	}
	
	public void open(int row, int col)
	{
		// Validate Indices
		if (!this.validateIndices(row, col))
			throw new IllegalArgumentException("The values of row and column must be between 1 and " + (gridSize - 1));
		
		// Calculate Index
		int site = calculateIndex(row, col);
		
		// Add to open sites
		openSites[site] = 1;
		numberOfOpenSites += 1;
		
		// if in top row, connect to top virtualSite
		if (row == 1)
		{
			uf.union(topVirtualSite, site);
		}
		// if in bottom row, connect to bottom virtualSite
		if (row == gridSize - 1)
		{
			uf.union(bottomVirtualSite, site);
		}
		// Check adjacent sites and connect if open
		// Top neighbor
		int adjacentSite;
		if (this.validateIndices(row - 1, col))
		{
			if (this.isOpen(row - 1, col))
			{
				adjacentSite = this.calculateIndex(row - 1, col);
				uf.union(site, adjacentSite);
			}
		}
		// Left neighbor
		if (this.validateIndices(row, col - 1))
		{
			if (this.isOpen(row, col - 1))
			{
				adjacentSite = this.calculateIndex(row, col - 1);
				uf.union(site, adjacentSite);
			}
		}
		// Bottom neighbor
		if (this.validateIndices(row + 1, col))
		{
			if (this.isOpen(row + 1, col))
			{
				adjacentSite = this.calculateIndex(row + 1, col);
				uf.union(site, adjacentSite);
			}
		}
		// Right neighbor
		if (this.validateIndices(row, col + 1))
		{
			if (this.isOpen(row, col + 1))
			{
				adjacentSite = this.calculateIndex(row, col + 1);
				uf.union(site, adjacentSite);
			}
		}
		
	}
	
	public boolean isOpen(int row, int col)
	{
		if (openSites[calculateIndex(row, col)] == 1)
			return true;
		else
			return false;
		
	}
	
	public int numberOfOpenSites()
	{
		return numberOfOpenSites;
	}
	
	public boolean isFull(int row, int col)
	{
		int site = calculateIndex(row, col);
		if (uf.connected(topVirtualSite, site))
			return true;
		else
			return false;
	}
	
	public boolean percolates()
	{
		if (uf.connected(topVirtualSite, bottomVirtualSite))
			return true;
		else
			return false;
	}
}
