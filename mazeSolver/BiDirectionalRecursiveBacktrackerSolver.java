package mazeSolver;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {


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

        biRecurSolver(maze, startingCell, isVisited, stackOfVisitedCells);

	} // end of solveMaze()




	public void biRecurSolver(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells){

		Cell currentCell = cell;
		List<Integer> neighbors = new ArrayList<Integer>();

		//initialise random generator
		Random rand = new Random();
		int randomNumber;

		//boolean used to track whether or not the current cell has univisited neighbors
		boolean doesNeighExist = false;

		//draw path onto maze map
		maze.drawFtPrt(currentCell);

		arrayOfMaze[currentCell.r][currentCell.c] = true;


		if( (currentCell.r) == ((maze.exit.r)) && (currentCell.c) == ((maze.exit.c)) ){
			System.out.println("out");
			doesNeighExist = true;
		}
		else{	
			//CHECK IF IT HAS UNVISITED NEIGHBORS
			for(int i = 0; i < 6; i++){		
				if( (currentCell.neigh[i] != null) && (arrayOfMaze[currentCell.neigh[i].r][currentCell.neigh[i].c] == false) && (currentCell.wall[i].present == false)){
					neighbors.add(i);
					doesNeighExist = true;
				}
			}

			//CHOOSE NEIGHBOR IF ANY
			if(doesNeighExist == true){
					//random number generated to choose a random unvisited neighbor of current cell
					randomNumber = rand.nextInt(neighbors.size());

					//initialising the neighbor of the current cell that we've chosen at random 
					Cell nextCurrCell = currentCell.neigh[neighbors.get(randomNumber)];

					//draw path onto maze map
					maze.drawFtPrt(currentCell);

					//adding current cell to list of visited cells
					visitedCells.add(currentCell);

					//total count iterate up
					totalCellCount++;

					//pass in next cell into function to repeat process
					biRecurSolver(maze, nextCurrCell, arrayOfMaze, visitedCells);
			}
			else{
				//gets the index of the last cell in the visitedCells list
				int lastCellInArray = (visitedCells.size() - 1);

				//if there is still cells left with potential unvisited neighbors, we do this
				if(lastCellInArray >= 0){
					Cell lastVisitedCell = visitedCells.get(lastCellInArray);
					visitedCells.remove(lastCellInArray);

					biRecurSolver(maze, lastVisitedCell, arrayOfMaze, visitedCells);
				}
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

} // end of class BiDirectionalRecursiveBackTrackerSolver
