package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class ModifiedPrimsGenerator implements MazeGenerator {

	protected boolean isVisited[][];
	protected List<Cell> visitedCells = new ArrayList<Cell>();
	protected List<Cell> cellArray = new ArrayList<Cell>();
	protected int total;
	protected int tally = 0;

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub

		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		total = sizeRow * sizeColumn;

		isVisited = new boolean[sizeRow][sizeColumn];
		Cell entranceCell = maze.entrance;

		isVisited[entranceCell.r][entranceCell.c] = true;

		visitedCells.add(entranceCell);
		//populates cellsWithNeighbors with the neighbors around the entrance cell
		for(int i = 0; i < 6; i++){
			if( (entranceCell.neigh[i] != null)){
				cellArray.add( entranceCell.neigh[i] );		
			}
		}
		primsAlgorithm(maze, visitedCells, cellArray);
	} // end of generateMaze()


	public void primsAlgorithm(Maze maze, List<Cell> visitedCells, List<Cell> cellArray){		

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

			visitedCells.add(currCell);

			System.out.println(cellArray.size() + " 1");

			//loops through neighbors of next cell and adds any neighbors to our list as long as its not already in the list or already visited
			for(int i = 0; i < 6; i++){			
	
				if( (currCell.neigh[i] != null) && (cellArray.contains(currCell.neigh[i]) == false) && (visitedCells.contains( currCell.neigh[i] ) == false)){
					cellArray.add(currCell.neigh[i]);	
					System.out.println(cellArray.size() + " 2");

				}	
			}
			
			//loops through neighbors of next cell and adds any neighbors to our list as long as its not already in the list or already visited
			for(int i = 0; i < 6; i++){			
	
				if( (currCell.neigh[i] != null) && ( visitedCells.contains( currCell.neigh[i] ) == true)){
					System.out.println("added dir");
					directions.add(i);

				}	
			}

			
			//initialising random generated number
			int rand;
			int counter = directions.size();
			Random r = new Random();
			rand = r.nextInt(counter);
			
			
			//checks to see neighbor is part of the maze
			if( (currCell.neigh[directions.get(rand)] != null) && ( visitedCells.contains( currCell.neigh[directions.get(rand)] ) == true) ){

				//removes the wall between current cell and the visited cell
				currCell.wall[directions.get(rand)].present = false;


				//updates the map
				maze.map[currCell.r][currCell.c] = currCell;

				System.out.println(cellArray.size() + " 3");

				//change boolean value to end the loop
				found = true;

				//call function again
				primsAlgorithm(maze, visitedCells, cellArray);					
			}
			else{
				counter--;
				directions.remove(rand);
			}	
				
		}	
	}





	
} // end of class ModifiedPrimsGenerator

