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
		int random = 0;
		
		if(cellArray.size() > 0){
			Cell nextCell = cellArray.get(0);
			cellArray.remove(0);
			System.out.println(cellArray.size() + " 1");

			for(int i = 0; i < 6; i++){
				if(i == 1 || 1 == 4){
					continue;
				}
				else{
					if( (nextCell.neigh[i] != null) && (cellArray.contains(nextCell.neigh[i]) == false) && (arrayOfMaze[nextCell.neigh[i].r][nextCell.neigh[i].c] == false)){
						cellArray.add(nextCell.neigh[i]);		
						System.out.println(cellArray.size() + " 2");

					}
				}
			}
	

			for(int i = 0; i < 6; i++){
				if(i == 1 || 1 == 4){
					continue;
				}
				else{
					if( (nextCell.neigh[i] != null) && (arrayOfMaze[nextCell.neigh[i].r][nextCell.neigh[i].c] == true) ){

						nextCell.wall[i].present = false;	
						arrayOfMaze[nextCell.r][nextCell.c] = true;
						maze.map[nextCell.r][nextCell.c] = nextCell;
						System.out.println(cellArray.size() + " 3");
						i=6;
						primsAlgorithm(maze, arrayOfMaze, cellArray);
						
					}
				}
			}	
		}	
	}




























} // end of class ModifiedPrimsGenerator

