package hw3;
import api.*;
import java.io.*;
import java.util.*;
public class JewelFactory {
	
	private int max;
	private java.util.Random r;

	/**
	 * Constructs a JewelFactory that will create Jewel objects with types 0 through givenMaxType - 1.
	 * @param givenMaxType
	 * number of types of Jewels
	 */
	public JewelFactory(int givenMaxType)
	{
		max = givenMaxType;
		this.r = new Random();
	}
	/**
	 * Constructs a JewelFactory that will create jewels with types 0 through givenMaxType - 1. using the given Random instance.
	 * @param givenMaxType
	 * number of types of jewels
	 * @param rand
	 * random number generator to use
	 */
	public JewelFactory(int givenMaxType, java.util.Random rand)
	{
		max = givenMaxType;
		r = rand;
	}
	/**
	 * Creates a grid of the given width and height that is initialized with random Jewels with type less than this factory's maximum type. The Jewels are produced using this factory's random number generator, where the random selection is restricted so that the resulting grid has no runs.
	 * @param width
	 * number of columns for the created grid
	 * @param height
	 * number of rows for the created grid
	 * @return
	 * a 2D array of randomly selected Jewel objects that contains no runs
	 */
	public Jewel[][] createGrid(int width, int height)
	{
		Jewel[][] newGrid = new Jewel[height][width];
		for(int i=0; i<height; i++)
		{
			for(int j=0; j<width; j++)
			{
				Jewel newJewel;
				while(checkRun(newGrid, j, i, (newJewel = generate()).getType())); 
				newGrid[i][j] = newJewel;
			}
		}
		return newGrid;
	}
	
	private boolean checkRun(Jewel[][] grid, int col, int row, int val)
	{
		int up1, up2, left1, left2, right1, right2, down1, down2;
		up1 = (row-1 >= 0 && grid[row-1][col] != null) ? grid[row-1][col].getType() : -1;
		up2 = (row-2 >= 0 && grid[row-2][col] != null) ? grid[row-2][col].getType() : -1;
		down1 = (row+1 < grid.length && grid[row+1][col] != null) ? grid[row+1][col].getType() : -1;
		down2 = (row+2 < grid.length && grid[row+2][col] != null) ? grid[row+2][col].getType() : -1;
		left1 = (col-1 >= 0 && grid[row][col-1] != null) ? grid[row][col-1].getType() : -1;
		left2 = (col-2 >= 0 && grid[row][col-2] != null) ? grid[row][col-2].getType() : -1;
		right1 = (col+1 < grid[0].length && grid[row][col+1] != null) ? grid[row][col+1].getType() : -1;
		right2 = (col+2 < grid[0].length && grid[row][col+2] != null) ? grid[row][col+2].getType() : -1;
		
		if(val == left1 && (left1 == left2 || left1 == right1) ) return true;
		if(val == right1 && right1 == right2) return true;
		if(val == up1 && (up1 == up2 || up1 == down1) ) return true;
		if(val == down1 && down1 == down2) return true;
		return false;
	}
	
	
	/**
	 * Returns a random instance of Jewel with a type that is less than this factory's maximum value.
	 * @return
	 * a Jewel object with randomly selected type
	 */
	public Jewel generate()
	{
		Jewel j  = new Jewel(r.nextInt(this.max));
		return j;
	}
}
