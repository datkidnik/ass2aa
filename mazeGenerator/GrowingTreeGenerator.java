package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
	// Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"
	

	protected boolean isVisited[][];
	protected List<Cell> stackOfVisitedCells = new ArrayList<Cell>();
	protected List<Cell> cellsWithNeighbors = new ArrayList<Cell>();
	protected int totalCellCount;
	protected int holder;
	double threshold = 0.1;
	
	@Override
	public void generateMaze(Maze maze) {

		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		totalCellCount = sizeRow*sizeColumn;

		//initialising both the 2d array and the entrance cell 
		isVisited = new boolean[sizeRow][sizeColumn];
		Cell entranceCell = maze.entrance;

		//marking the entrance as visited
		isVisited[entranceCell.r][entranceCell.c] = true;


		//populates cellsWithNeighbors with the neighbors around the entrance cell
		for(int i = 0; i < 6; i++){
			if( (entranceCell.neigh[i] != null)){
				cellsWithNeighbors.add(entranceCell.neigh[i]);		
			}
		}

		growingTree(maze, entranceCell, isVisited, stackOfVisitedCells, cellsWithNeighbors);
	}


	public void growingTree(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells, List<Cell> cellArray){

		//initialise random generator
		Random rand = new Random();
		int randomNumber;
		randomNumber = rand.nextInt(10);

		if(randomNumber > 0){
			nearest(maze, cell, arrayOfMaze, visitedCells, cellArray);
		}
		else{
			random(maze, cell, arrayOfMaze, visitedCells, cellArray);
		}

	}

	public void nearest(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells, List<Cell> cellArray){
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
		
		//remove current cell from list of potential neighbors for random
		if( cellArray.contains(currentCell) ){
			int index = cellArray.indexOf(currentCell);
			cellArray.remove(index);
		}

		//CHECK IF IT HAS UNVISITED NEIGHBORS
		for(int i = 0; i < 6; i++){
			if( (currentCell.neigh[i] != null) && (arrayOfMaze[currentCell.neigh[i].r][currentCell.neigh[i].c] == false) ){
				if( (cellArray.contains(currentCell.neigh[i]) == false) ){
					//adds neighbors into neighbors list for random
					cellArray.add(currentCell.neigh[i]);
				}
				
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
				growingTree(maze, nextCurrCell, arrayOfMaze, visitedCells, cellArray);
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

				growingTree(maze, lastVisitedCell, arrayOfMaze, visitedCells, cellArray);
			}
		}
	}

	public void random(Maze maze, Cell cell, boolean arrayOfMaze[][], List<Cell> visitedCells, List<Cell> cellArray){
		//list of directions of neighbors
		List<Integer> directions = new ArrayList<Integer>();

		//initialising int
		int random = 0;

		//initialising found
		boolean found = false;
		

		if(cellArray.size() > 0){

			//the next cell is chosen from the top of the list of available neighbors
			Cell currCell = cellArray.get(0);

			//remove the next cell from the list of available neighbors
			cellArray.remove(0);
			System.out.println(cellArray.size() + " 1");

			//loops through neighbors of curr cell and adds any neighbors to our list as long as its not already in the list or already visited
			for(int i = 0; i < 6; i++){
				
				if( (currCell.neigh[i] != null) && (cellArray.contains(currCell.neigh[i]) == false) && (arrayOfMaze[currCell.neigh[i].r][currCell.neigh[i].c] == false)){
					cellArray.add(currCell.neigh[i]);	
					
					System.out.println(cellArray.size() + " 2");

				}
			}
	
			while(found == false){
				//initialising random generated number
				int rand;
				int counter = 4;
				Random r = new Random();
				rand = r.nextInt(counter);
				
				//adding potential directions for visisted cells to join the maze to
				directions.add(0);
				directions.add(2);
				directions.add(3);
				directions.add(5);
				
				//checks to see neighbor is part of the maze
				if( (currCell.neigh[directions.get(rand)] != null) && (arrayOfMaze[currCell.neigh[directions.get(rand)].r][currCell.neigh[directions.get(rand)].c] == true) ){

					//removes the wall between current cell and the visited cell
					currCell.wall[directions.get(rand)].present = false;

					//marks this cell as visited	
					arrayOfMaze[currCell.r][currCell.c] = true;

					//adding current cell to list of visited cells
					visitedCells.add(currCell);

					//updates the map
					maze.map[currCell.r][currCell.c] = currCell;
					System.out.println(cellArray.size() + " 3");

					//change boolean value to end the loop
					found = true;

					//call function again
					growingTree(maze, currCell, arrayOfMaze, visitedCells, cellArray);					
				}
				else{
					counter--;
					directions.remove(rand);
				}	
			}		
		}

	}

}
