package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	//2D array to track which cells the algorithm has visited
	
	protected boolean isVisited[][];
	protected List<Cell> stackOfVisitedCells = new ArrayList<Cell>();
	protected int totalCellCount;
	protected int holder;

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		totalCellCount = sizeRow*sizeColumn;


		if(maze.type == 1){
			holder = 11;
		}
		if(maze.type == 2){
			holder = 12;
		}
		if(maze.type == 0){
			holder = 10;
		}

		isVisited = new boolean[sizeRow][sizeColumn];
		Cell entranceCell = maze.entrance;

		depthFirtstSearch(maze, entranceCell, isVisited, stackOfVisitedCells);
	} // end of generateMaze()

	/*maze - the maze that we are generating 
	*cell - the cell that we are currently at
	*arrayOfMaze - array to check which cells have been visited and which have not
	*visitedCells - a list of order of cells that have been visited
	*
	*/
	public void depthFirtstSearch(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells){
		if(holder == 11){
			System.out.println("tunnel");
		}
		if(holder == 12){
			//holder = 0;
			System.out.println("1");
			hex(maze, cell, arrayOfMaze, visitedCells);
		}
		if(holder == 10){
			//holder = 0;
			normal(maze, cell, arrayOfMaze, visitedCells);
		}
	}

	public void normal(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells){
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

		//marking this cell as visited in our array to keep track of progress
		arrayOfMaze[currentRowCoord][currentColumnCoord] = true;
		
		
		//CHECK IF IT HAS UNVISITED NEIGHBORS
		for(int i = 0; i < 6; i++){
			if(i == 1 || 1 == 4){
				continue;
			}
			else{
				if( (currentCell.neigh[i] != null) && (arrayOfMaze[currentCell.neigh[i].r][currentCell.neigh[i].c] == false) ){
					neighbors.add(i);
					doesNeighExist = true;
				}
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

				//adding current cell to list of visited cells
				visitedCells.add(currentCell);

				int shhhh = visitedCells.size();
				System.out.println(shhhh + " does");

				//pass in next cell into function to repeat process
				normal(maze, nextCurrCell, arrayOfMaze, visitedCells);
		}
		else{
			//gets the index of the last cell in the visitedCells list
			int lastCellInArray = (visitedCells.size() - 1);

			//if there is still cells left with potential unvisited neighbors, we do this
			if(lastCellInArray >= 0){
				Cell lastVisitedCell = visitedCells.get(lastCellInArray);
				visitedCells.remove(lastCellInArray);

				int shhhh2 = visitedCells.size();
				System.out.println(shhhh2 + " does not");

				normal(maze, lastVisitedCell, arrayOfMaze, visitedCells);
			}
		}
	}


	public void hex(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells){
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

		//marking this cell as visited in our array to keep track of progress
		arrayOfMaze[currentRowCoord][currentColumnCoord] = true;
		
		
		//CHECK IF IT HAS UNVISITED NEIGHBORS
		for(int i = 0; i < 6; i++){
			if( (currentCell.neigh[i] != null) && (arrayOfMaze[currentCell.neigh[i].r][currentCell.neigh[i].c] == false) ){
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

				//adding current cell to list of visited cells
				visitedCells.add(currentCell);

				int shhhh = visitedCells.size();
				System.out.println(shhhh + " does");

				//pass in next cell into function to repeat process
				hex(maze, nextCurrCell, arrayOfMaze, visitedCells);
		}
		else{
			//gets the index of the last cell in the visitedCells list
			int lastCellInArray = (visitedCells.size() - 1);

			//if there is still cells left with potential unvisited neighbors, we do this
			if(lastCellInArray >= 0){
				Cell lastVisitedCell = visitedCells.get(lastCellInArray);
				visitedCells.remove(lastCellInArray);

				int shhhh2 = visitedCells.size();
				System.out.println(shhhh2 + " does not");

				hex(maze, lastVisitedCell, arrayOfMaze, visitedCells);
			}
		}
	}
} // end of class RecursiveBacktrackerGenerator
