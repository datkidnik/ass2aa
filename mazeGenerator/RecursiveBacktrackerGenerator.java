package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	//2D array to track which cells the algorithm has visited
	
	protected boolean isVisited[][];
	protected List<Cell> visitedCells = new ArrayList<Cell>();
	protected List<Cell> orderOfCells = new ArrayList<Cell>();
	protected int totalCellCount;
	protected int holder;

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		totalCellCount = sizeRow*sizeColumn;


		Cell entranceCell = maze.entrance;

		depthFirtstSearch(maze, entranceCell, orderOfCells, visitedCells);
	} // end of generateMaze()

	/*maze - the maze that we are generating 
	*cell - the cell that we are currently at
	*
	*visitedCells - a list of order of cells that have been visited
	*
	*
	*
	*
	*
	*
	*
	*
	*
	*
	*
	*
	*/
	public void depthFirtstSearch(Maze maze, Cell cell, List<Cell> orderOfCells, List<Cell> visitedCells){
		
		normal(maze, cell, orderOfCells, visitedCells);
		
	}

	public void normal(Maze maze, Cell cell, List<Cell> orderOfCells, List<Cell> visitedCells){
		//list of directions in which neighbors exist
		List<Integer> neighbors = new ArrayList<Integer>();

		//coordinates of current cell
		int currentRowCoord = cell.r;
		int currentColumnCoord = cell.c;

		//initialise random generator
		Random rand = new Random();
		int randomNumber;

		//the current cell that we are stationed at
		Cell currentCell = cell;

		//boolean used to track whether or not the current cell has univisited neighbors
		boolean doesNeighExist = false;

		if(visitedCells.contains(currentCell) == false){
			//adding current cell to list of visited cells
			visitedCells.add(currentCell);
		}
		

		//CHECK IF IT HAS UNVISITED NEIGHBORS
		for(int i = 0; i < 6; i++){
			
			if( (currentCell.neigh[i] != null) && (visitedCells.contains(currentCell.neigh[i]) == false) ){
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

				//removing the wall between current cell and next cell
				currentCell.wall[neighbors.get(randomNumber)].present = false;

				//updating the map to show new cell
				maze.map[currentRowCoord][currentColumnCoord] = currentCell;

				
				

				orderOfCells.add(currentCell);

				int shhhh = visitedCells.size();
				System.out.println(shhhh + " does");

				//pass in next cell into function to repeat process
				normal(maze, nextCurrCell, orderOfCells, visitedCells);
		}
		else{
			//gets the index of the last cell in the visitedCells list
			int lastCellInArray = (orderOfCells.size() - 1);

			//if there is still cells left with potential unvisited neighbors, we do this
			if(lastCellInArray >= 0){
				Cell lastVisitedCell = orderOfCells.get(lastCellInArray);
				orderOfCells.remove(lastCellInArray);

				int shhhh2 = visitedCells.size();
				System.out.println(shhhh2 + " does not");

				normal(maze, lastVisitedCell, orderOfCells, visitedCells);
			}
		}
	}


	


} // end of class RecursiveBacktrackerGenerator
