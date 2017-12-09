package hw3;
import api.*;
import java.io.*;
import java.util.*;
public class Game {
	
	/**
	 * generator for new jewels
	 */
	private JewelFactory jf;
	/**
	 * current score of game
	 */
	private int score;
	/**
	 * current game grid
	 */
	private Jewel[][] grid;
	
	/**
	 * Constructs a game with the given number of columns and rows that will use the given JewelFactory instance to create new jewels. The grid is initialized according to the factory's createGrid method.
	 * @param width
	 * number of columns
	 * @param height
	 * number of rows
	 * @param generator
	 * generator for new jewels
	 */
	public Game(int width, int height, JewelFactory generator)
	{
		jf = generator;
		grid = jf.createGrid(width, height);
		score = 0;
	}
	/**
	 * Constructs a game whose size and initial configuration are determined by the given string array and that will use the given JewelFactory instance to create new jewels. Each string in the given array consists of whitespace-separated integers corresponding to the initial jewel type for each cell.
	 * @param descriptor
	 * array of strings
	 * @param generator
	 * generator for new jewels
	 */
	public Game(java.lang.String[] descriptor, JewelFactory generator)
	{
		grid = Util.createFromStringArray(descriptor);
		jf = generator;
		score = 0;
	}
	/**
	 * Replaces each null cell in the grid with a new Jewel created by a call to this Game's JewelFactory.
	 * @return
	 * list of GridPosition objects for the filled cells
	 */
	public java.util.ArrayList<GridPosition> fillAll()
	{
		ArrayList<GridPosition> filledPositions = new ArrayList<>();
		for(int i=0; i<getHeight(); i++)
		{
			for(int j=0; j<getWidth(); j++)
			{
				if(grid[i][j] == null)
				{
					grid[i][j] = jf.generate();
					filledPositions.add(new GridPosition(i, j, grid[i][j]));
				}
			}
		}
		return filledPositions;
	}
	/**
	 * Finds all horizontal and vertical runs, and returns a list containing a GridPosition object for all cells that are part of a run.
	 * @return
	 * list of GridPosition objects for all cells that are part of a run
	 */
	public java.util.ArrayList<GridPosition> findAndMarkAllRuns()
	{
		ArrayList<GridPosition> allRuns = new ArrayList<>(); 
		for(int i=0; i<getWidth();i++)
		{
			allRuns.addAll(findVerticalRuns(i));
		}
		for(int j = 0; j<getHeight();j++)
		{
			allRuns.addAll(findHorizontalRuns(j));
		}
		
		for(GridPosition g: allRuns)
		{
			grid[g.row()][g.col()] = null;
		}
		
		GridPosition prevPosition = null;
		if(allRuns.size() >= 1) prevPosition = allRuns.get(0);
		for(int k = 1; k < allRuns.size(); k++)
		{
			GridPosition curPosition = allRuns.get(k);
			if(( (Math.abs(curPosition.row() - prevPosition.row()) == 1 && curPosition.col() == prevPosition.col()) 
				|| (Math.abs(curPosition.col() - prevPosition.col()) == 1 && curPosition.row() == prevPosition.row()) 
				|| (curPosition.getJewel().equals(prevPosition.getJewel())) ))
			{
				score=score+1;
				break;
			}
			prevPosition = curPosition;
		}
		return allRuns;
	}
	/**
	 * Finds runs in the given row of the grid using the method Util.findRuns The return value is a list containing a GridPosition object for each cell that is part of a run, in left-to-right order.
	 * @param row
	 * the row in which to find runs
	 * @return
	 * list of GridPosition objects for cells that are part of a run
	 */
	public java.util.ArrayList<GridPosition> findHorizontalRuns(int row)
	{
		ArrayList<GridPosition> positions = new ArrayList<GridPosition>();
		Jewel[] thisRow = getRow(row);
		ArrayList<Integer> runIndices = Util.findRuns(thisRow);
		for(Integer i: runIndices)
		{
			GridPosition g = new GridPosition(row, i, thisRow[i]);
			positions.add(g);
		}
		return positions;
	}
	
	/**
	 * Finds runs in the given column of the grid using the method Util.findRuns The return value is a list containing a GridPosition object for each cell that is part of a run, in top-to-bottom order. This method does not modify the grid or update the score.
	 * @param col
	 * the column in which to find runs
	 * @return
	 * list of GridPosition objects for cells that are part of a run
	 */
	public java.util.ArrayList<GridPosition> findVerticalRuns(int col)
	{
		ArrayList<GridPosition> positions = new ArrayList<GridPosition>();
		Jewel[] thisCol = getCol(col);
		ArrayList<Integer> runIndices = Util.findRuns(thisCol);
		for(Integer i: runIndices)
		{
			GridPosition g = new GridPosition(i, col, thisCol[i]);
			positions.add(g);
		}
		return positions;
	}
	/**
	 * Returns a copy of the given column of the grid.
	 * @param col
	 * given col
	 * @return
	 * a new array containing the Jewels from the given col
	 */
	public Jewel[] getCol(int col)
	{
		Jewel[] thisColumn = new Jewel[getHeight()];
		for(int i = 0; i <getHeight();i++)
		{
			thisColumn[i] = grid[i][col];
		}
		
		return thisColumn;
	}
	/**
	 * Returns the height of the grid for this game (number of rows).
	 * @return
	 * number of rows in the grid for this game
	 */
	public int getHeight()
	{
		return grid.length;
	}
	/**
	 * Returns the Jewel at the given row and column.
	 * @param row
	 * given row in the grid
	 * @param col
	 * given column in the grid
	 * @return
	 * Jewel at the given row and column
	 */
	public Jewel getJewel(int row, int col)
	{
		Jewel j = grid[row][col];
		return j;
	}
	/**
	 * Returns a copy of the given row of the grid.
	 * @param row
	 * given row
	 * @return
	 * a new array containing the Jewels from the given row
	 */
	public Jewel[] getRow(int row)
	{
		Jewel[] thisRow = new Jewel[getWidth()];
		for(int i = 0; i <getWidth();i++)
		{
			thisRow[i] = grid[row][i];
		}
		
		return thisRow;
	}
	/**
	 * Returns the current score for this game.
	 * @return
	 * current score
	 */
	public int getScore()
	{
		return score;
	}
	/**
	 * Returns the width of the grid for this game (number of columns).
	 * @return
	 * width of the grid
	 */
	public int getWidth()
	{
		return grid[0].length;
	}
	/**
	 * Exchanges the Jewels described by the given GridPositions, if possible. The caller is responsible for ensuring that the given positions are horizontally or vertically adjacent. The exchange is allowed only if one of the affected cells forms part of a run after the jewels are swapped. If so, the jewels for the two cells are exchanged and the method returns true; otherwise, the method returns false. No other aspects of the game state are modified. (Note that only the row and column of the given GridPositions are used; the Jewel attribute is ignored.)
	 * @param c0
	 * grid position
	 * @param c1
	 * grid position adjacent to c0
	 * @return
	 * true if the two given cells are exchanged, false otherwise
	 */
	public boolean select(GridPosition c0, GridPosition c1)
	{
		GridPosition c0_new = new GridPosition(c1.row(), c1.col(), c0.getJewel());
		GridPosition c1_new = new GridPosition(c0.row(), c0.col(), c1.getJewel());
		grid[c0_new.row()][c0_new.col()] = c0_new.getJewel();
		grid[c1_new.row()][c1_new.col()] = c1_new.getJewel();
		
		for(GridPosition g : findVerticalRuns(c0_new.col()))
				if(g.equals(c0_new) || g.equals(c1_new)) return true;
		for(GridPosition g : findHorizontalRuns(c0_new.col()))
			if(g.equals(c0_new) || g.equals(c1_new)) return true;

		for(GridPosition g : findVerticalRuns(c1_new.col()))
			if(g.equals(c0_new) || g.equals(c1_new)) return true;
		for(GridPosition g : findHorizontalRuns(c1_new.col()))
			if(g.equals(c0_new) || g.equals(c1_new)) return true;
		
		grid[c0_new.row()][c0_new.col()] = c1_new.getJewel();
		grid[c1_new.row()][c1_new.col()] = c0_new.getJewel();
		
		return false;
	}
	/**
	 * Copies the given array values into the specified column of the grid. given array.
	 * @param col
	 * given col
	 * @param arr
	 * array of Jewel containing the values for the column
	 */
	public void setCol(int col, Jewel[] arr)
	{
		for(int i=0; i < arr.length; i++)
		{
			grid[i][col] = arr[i];
		}
	}
	/**
	 * Sets the Jewel at the given row and column.
	 * @param row
	 * given row in the grid
	 * @param col
	 * given column in the grid
	 * @param jewel
	 * value to be set
	 */
	public void setJewel(int row, int col, Jewel jewel)
	{
		grid[row][col] = jewel;
	}
	/**
	 * Copies the given array values into the specified row of the grid.
	 * @param row
	 * given row
	 * @param arr
	 * array of Jewel containing the values for the row
	 */
	public void setRow(int row, Jewel[] arr)
	{
		grid[row] = arr;
	}
	/**
	 * Shifts the Jewels in a given column downward using the method Util.shiftNonNullElements. After executing this method the null cells, if any, are at the top of the column. The order of the Jewels is not changed. The return value is a list containing a GridPosition object for each Jewel that was moved by this operation. The GridPosition's row and column should be the position of the moved Jewel when the operation completes.
	 * @param col
	 * the given column
	 * @return
	 * list of GridPosition objects for elements that are moved
	 */
	public java.util.ArrayList<GridPosition> shiftColumnDown(int col)
	{
		ArrayList<Integer> newIndices = Util.shiftNonNullElements(getCol(col));
		ArrayList<Jewel> movedElems = new ArrayList<>();
		ArrayList<GridPosition> positions = new ArrayList<>();
		for (int i = getHeight()-1; i>=0 ;i--)
		{
			if(grid[i][col] == null)
			{
				for(int j=i; j >= 0; j--)
				{
					if(grid[j][col] != null)
						movedElems.add(0, grid[j][col]);
				}
				break;
			}
		}
		for(int k = 0; k < newIndices.size(); k++)
		{
			grid[newIndices.get(k)][col] = movedElems.get(k);
			positions.add(new GridPosition(newIndices.get(k), col, movedElems.get(k)));
		}
		return positions;
	}
	/**
	 * Returns a String representation of the grid for this game, with rows delimited by newlines.
	 * 
	 */
	public java.lang.String toString()
	{
		return Util.convertToString(grid);

	}
}

