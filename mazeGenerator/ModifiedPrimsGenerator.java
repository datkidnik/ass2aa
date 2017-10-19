package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class ModifiedPrimsGenerator implements MazeGenerator {

	protected boolean isVisited[][];
	protected List<Cell> cellsWithNeighbors = new ArrayList<Cell>();
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

		for(int i = 0; i < 6; i++){
			if(i == 1 || 1 == 4){
				continue;
			}
			else{
				if( (entranceCell.neigh[i] != null)){
					cellsWithNeighbors.add(entranceCell.neigh[i]);		
				}
			}
		}
		primsAlgorithm(maze, isVisited, cellsWithNeighbors);
	} // end of generateMaze()


	public void primsAlgorithm(Maze maze, boolean arrayOfMaze[][], List<Cell> cellArray){		
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

			//loops through neighbors of next cell and adds any neighbors to our list as long as its not already in the list or already visited
			for(int i = 0; i < 6; i++){
				if(i == 1 || i == 4){
					continue;
				}
				else{
					if( (currCell.neigh[i] != null) && (cellArray.contains(currCell.neigh[i]) == false) && (arrayOfMaze[currCell.neigh[i].r][currCell.neigh[i].c] == false)){
						cellArray.add(currCell.neigh[i]);	
						
						System.out.println(cellArray.size() + " 2");

					}
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

					//updates the map
					maze.map[currCell.r][currCell.c] = currCell;
					System.out.println(cellArray.size() + " 3");

					//change boolean value to end the loop
					found = true;

					//call function again
					primsAlgorithm(maze, arrayOfMaze, cellArray);					
				}
				else{
					counter--;
					directions.remove(rand);
				}	
			}		
		}	
	}
} // end of class ModifiedPrimsGenerator

