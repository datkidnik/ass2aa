package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
	// Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"
	


	protected List<Cell> visitedCellsNoNeighbors = new ArrayList<Cell>();
	protected List<Cell> visitedCells = new ArrayList<Cell>();
	protected int totalCellCount;

	double threshold = 0.1;
	
	@Override
	public void generateMaze(Maze maze) {

		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		totalCellCount = sizeRow*sizeColumn;

		
		Cell entranceCell = maze.entrance;

		visitedCells.add(entranceCell);

		growingTree(maze, visitedCells, visitedCellsNoNeighbors);
	}


	public void growingTree(Maze maze, List<Cell> visitedCells, List<Cell> visitedCellsNoNeighbors){
		
		Cell currCell;

		//list of directions of neighbors
		List<Integer> directions = new ArrayList<Integer>();

		//boolean
		boolean hasNeigh = false;
		if(visitedCells.size() > 0){

			//initialising random generated number
			int rand;
			int counter = visitedCells.size();
			Random r = new Random();
			rand = r.nextInt(counter);

			currCell = visitedCells.get(rand);

			//check if random cell has any neighbors
			for(int i = 0; i < 6; i++){
				
				if(currCell.neigh[i] != null && (visitedCellsNoNeighbors.contains(currCell.neigh[i]) == false) && (visitedCells.contains(currCell.neigh[i]) == false) ){
					hasNeigh = true;
					directions.add(i);
				}

			}

			if(hasNeigh == true){
				//initialising random generated number
				int rand2;
				int counter2 = directions.size();
				Random r2 = new Random();
				rand2 = r2.nextInt(counter2);

				currCell.wall[ directions.get(rand2) ].present = false;

				visitedCells.add(currCell.neigh[ directions.get(rand2) ]);

				maze.map[currCell.r][currCell.c] = currCell;

				hasNeigh = false;
				//IMLPLEMENT LATER -------->
				growingTree(maze, visitedCells, visitedCellsNoNeighbors);
			}
			else{
				visitedCells.remove(rand);
				visitedCellsNoNeighbors.add(currCell);

				//IMLPLEMENT LATER -------->
				growingTree(maze, visitedCells, visitedCellsNoNeighbors); 

			}
		}

	}

}
