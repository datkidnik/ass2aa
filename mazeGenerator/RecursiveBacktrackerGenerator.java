package mazeGenerator;

import java.util.*;
import java.util.Random;
import maze.Maze;
import maze.Cell;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	//2D array to track which cells the algorithm has visited
	
	protected boolean isVisited[][];
	protected Queue<Cell> stackOfVisitedCells = new LinkedList<Cell>();
	protected int totalCellCount;

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		int sizeRow = maze.sizeR;
		int sizeColumn = maze.sizeC;

		totalCellCount = sizeRow*sizeColumn;

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
	public void depthFirtstSearch(Maze maze, Cell cell, boolean arrayOfMaze[][], Queue<Cell> visitedCells){
		int currentRowCoord = cell.r;
		int currentColumnCoord = cell.c;
		Random rand = new Random();
		int randomNumber;
		Cell currentCell = cell;
		boolean foundNextNeigh = false;

		arrayOfMaze[currentRowCoord][currentColumnCoord] = true;
		visitedCells.add(currentCell);
		
		for(int i = 0; i < 6; i++){
			randomNumber = rand.nextInt(6);
			if(i == 1 || 1 == 4){
				continue;
			}
			else{
				if( (currentCell.neigh[i] != null) && (arrayOfMaze[currentCell.neigh[i].r][currentCell.neigh[i].c] == false) ){
					//REMEMBER THIS ------------------------
					int neighborWallDirection = -1;

					Cell nextCurrCell = currentCell.neigh[i];

					/*switch(i){
						case 0 :
							neighborWallDirection = 3;
						case 2 :
							neighborWallDirection = 5;
						case 3 :
							neighborWallDirection = 0;
						case 5 :
							neighborWallDirection = 2;	
						default :
							System.out.println("error in RecursiveBacktrackerGenerator");
					}*/


					currentCell.wall[i].present = false;
					//nextCurrCell.wall[neighborWallDirection].present = false;

					maze.map[currentRowCoord][currentColumnCoord] = currentCell;

					depthFirtstSearch(maze, nextCurrCell, arrayOfMaze, visitedCells);
				}

			}
		}







	}


















} // end of class RecursiveBacktrackerGenerator
