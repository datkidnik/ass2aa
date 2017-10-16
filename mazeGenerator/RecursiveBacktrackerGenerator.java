package mazeGenerator;

import java.util.*;
import maze.Maze;
import maze.Cell;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	//2D array to track which cells the algorithm has visited
	protected boolean isVisited[][];

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub

		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		isVisited = new boolean[sizeRow][sizeColumn];
		Stack stackOfVisitedCells = new Stack();

	} // end of generateMaze()

} // end of class RecursiveBacktrackerGenerator
