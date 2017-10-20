package mazeSolver;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

/**
 * Implements WallFollowerSolver
 */

public class WallFollowerSolver implements MazeSolver {
	
	protected boolean isVisited[][];
	protected List<Cell> stackOfVisitedCells = new ArrayList<Cell>();
	protected int totalCellCount;

	@Override
	public void solveMaze(Maze maze) {
		// TODO Auto-generated method stub
		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;
		isVisited = new boolean[sizeRow][sizeColumn];
		Cell startingCell = maze.entrance;

		totalCellCount = 1;

        wallFollow(maze, startingCell, isVisited, stackOfVisitedCells);
	} // end of solveMaze()
    
    //GETTING STUCK IN LOOP BETWEEN EAST AND WEST
	public void wallFollow(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells){

		//List<Integer> neighbors = new ArrayList<Integer>();

		boolean doesNeighExist = false;

		//initialises currCell
		Cell currCell = cell;

		//COME BACK
		maze.drawFtPrt(currCell);

		//mark this cell as visited
		arrayOfMaze[currCell.r][currCell.c] = true;

		//FOUND THE END
		if( (currCell.r) == ((maze.exit.r)) && (currCell.c) == ((maze.exit.c)) ){
			System.out.println("out");
			doesNeighExist = true;
		}
		else{
			for(int i = 0; i < 6; i++){
				
				if((currCell.neigh[i] != null) && (currCell.wall[i].present == false) && (arrayOfMaze[currCell.neigh[i].r][currCell.neigh[i].c] == false) ){
					//add current cell to visited neighbors
					visitedCells.add(currCell);

					//initialise next cell
					Cell nextCell = currCell.neigh[i];

					//draw path onto maze map
					maze.drawFtPrt(currCell);

					//tell function we found a neighbor
					doesNeighExist = true;

					System.out.println("new cell");
					totalCellCount++;
					i=6;
					wallFollow(maze, nextCell, arrayOfMaze, visitedCells);		
				}
				
			}
			if(doesNeighExist == false){
				int sizeOfArray = (visitedCells.size() - 1);
				Cell backTrackCell = visitedCells.get(sizeOfArray);
				visitedCells.remove(sizeOfArray);

				wallFollow(maze, backTrackCell, arrayOfMaze, visitedCells);
			}
		}
	}



	@Override
	public boolean isSolved() {
		// TODO Auto-generated method stub
		return true;
	} // end if isSolved()
    
    
	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		return totalCellCount;
	} // end of cellsExplored()

} // end of class WallFollowerSolver
