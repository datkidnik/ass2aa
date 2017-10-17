package mazeGenerator;

import java.util.*;
import maze.Maze;
import maze.Cell;

/**
 * Interface of a maze generator.
 * 
 * @author Youhan Xia
 * @author Jeffrey Chan
 * @author Yongli Ren
 */
public interface MazeGenerator
{
	/**
	 * Function that generate a perfect maze from an all-wall initialized maze.
	 * @param maze The reference of Maze object to generate. 
	 */
	public void generateMaze(Maze maze);


	public void depthFirtstSearch(Maze maze, Cell cell, boolean arrayOfMaze[][], Queue<Cell> visitedCells);
} // end of interface mazeGenerator