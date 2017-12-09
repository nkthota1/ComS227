
package mini2;

import api.Maze;
import api.MazeCell;
import api.Status;

/**
 * Utility class for searching a maze described by a collection
 * of MazeCell objects.
 */
public class Searcher
{
  /**
   * Recursively searches a given MazeCell and all of its unexplored 
   * neighbors.  Returns true if the current cell is the goal or if
   * the goal is found in a search initiated from this cell by 
   * searching a neighbor.  Returns false if this cell is not
   * unexplored and is not the goal.
   * 
   * @param maze
   *   the maze to be searched
   * @param row
   *   the row for the current cell being searched
   * @param col
   *   the column for the current cell being searched
   * @return
   *   true if a search from the current cell has reached the goal,
   *   false otherwise
   */
  public static boolean search(Maze maze, int row, int col)
  {
	  MazeCell cell = maze.getCell(row, col);
	  if(cell.getStatus() == Status.GOAL) {
		  return true;
	  }
	  else if(cell.getStatus() != Status.UNEXPLORED) {
		  return false;
	  }
	  else {
		if(cell.getStatus() == Status.UNEXPLORED) {
			cell.setStatus(Status.EXPLORING_UP);
			if(search(maze, row - 1 , col)) {
				cell.setStatus(Status.SUCCEEDED);
				return true;
			}
		}
		if(cell.getStatus() == Status.EXPLORING_UP) {
			cell.setStatus(Status.EXPLORING_DOWN);
			if(search(maze, row + 1, col)) {
				cell.setStatus(Status.SUCCEEDED);
				return true;
			}
		}
		if(cell.getStatus() == Status.EXPLORING_DOWN) {
			cell.setStatus(Status.EXPLORING_LEFT);
			if(search(maze, row, col - 1)) {
				cell.setStatus(Status.SUCCEEDED);
				return true;
			}
		}
		if(cell.getStatus() == Status.EXPLORING_LEFT) {
			cell.setStatus(Status.EXPLORING_RIGHT);
			if(search(maze, row, col + 1)) {
				cell.setStatus(Status.SUCCEEDED);
				return true;
			}
		}		
	  }
	  if(cell.getStatus() == Status.SUCCEEDED) {
		  return true;
	  }
	  cell.setStatus(Status.FAILED);

    return false;
  }

}
