package com.sudoku.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;


public class SudokuGenerateHelper {
	 int[][] grid = new int[9][9];

	/**
	 * Create candidate numbers for Sudoko from 1 to 9 randomly
	 * @return
	 */
	public  Integer[] createNumbers() {
		ArrayList<Integer> randoms = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			randoms.add(i);
		}
		Collections.shuffle(randoms);

		return randoms.toArray(new Integer[9]);
	}

	/**
	 * Create a grid and fill it using Sudoko rules
	 * @param row
	 * @param col
	 * @return
	 */
	public  boolean fillGrid(int row, int col,int[][] grid,Map<String, Integer> fixedCellMap) {
		if (row == 9) {
			return true;
		}
		
		if(fixedCellMap !=null){//Solving puzzle and skipping fixed cells
			displayGrid(grid);
			String key =row+""+col;
				if(fixedCellMap.containsKey(key) ){
					//move to next cell
					if (fillGrid(col == 8 ? (row + 1) : row, (col + 1) % 9,grid, fixedCellMap))
						return true;
				}
				else{
					return guessAndFill(row,col,grid,fixedCellMap);
				}
		}
		else{ // creating new grid for Input Puzzle
			return guessAndFill(row,col,grid,fixedCellMap);
		}
		
		return false;
	}
	
	/**
	 * Fill in grid with random number and check its valid
	 * @param row
	 * @param col
	 * @param grid
	 * @param fixedCellMap
	 * @return
	 */
	
	private boolean guessAndFill(int row, int col,int[][] grid,Map<String, Integer> fixedCellMap){
		
		Integer[] random = createNumbers();
		for (int i = 0; i < 9; i++) {
			if (!checkRow(row, random[i],grid) && !checkCol(col, random[i],grid) && !validGrid(row, col, random[i],grid)) {
				grid[row][col] = random[i];

				if (fillGrid(col == 8 ? (row + 1) : row, (col + 1) % 9,grid, fixedCellMap))
					return true;
				else { // Backtracking
					grid[row][col] = 0;
				}
			}
		}
		return false;
		
	}

	/**
	 * Check if value is valid in Row
	 * @param row
	 * @param val
	 * @return
	 */
	public  boolean checkRow(int row, int val,int[][] grid) {
		for (int i = 0; i < 9; i++) {
			if (grid[row][i] == val) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if value is valid in Column
	 * @param col
	 * @param val
	 * @return
	 */
	public  boolean checkCol(int col, int val,int[][] grid) {
		for (int i = 0; i < 9; i++) {
			if ( grid[i][col] == val) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if a 3*3 grid is valid as per Sudoku rules
	 * @param row
	 * @param col
	 * @param value
	 * @return
	 */
	public  boolean validGrid(int row, int col, int value,int[][] grid) {

		int startRow = row / 3 * 3;
		int startCol = col / 3 * 3;

		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (!(i == row && j == col)) {
					if (grid[i][j] == value)
						return true;
				}
			}
		}
		return false;
	}

	/** 
	 * For a valid grid, it will mask few cells to create a valid sudoku
	 * @param grid
	 * @param masks
	 * @return 
	 */
	public  int[][] maskGrid(int[][] grid, int masks) {
		double pendingCells = 81;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				double randomCell = masks / pendingCells;
				if (Math.random() <= randomCell) {
					grid[i][j] = 0;
					masks--;
				}
				pendingCells--;
			}
		}
System.out.println("prnding cells :"+pendingCells);
		displayGrid(grid);
		return grid;

	}

	/**
	 * Create a List of filled cells, so that they aren't change by User or algo
	 * @param grid
	 */
	public  Map<String, Integer> getFixedCellList(int[][] grid) {

		int filledCells = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (grid[i][j] != 0)
					filledCells++;

		System.out.println("filledCells :::: "+filledCells);
		// Store indexes of filled cells in a List so that user can't change
		Map<String, Integer> valueMap = new HashMap<String, Integer>(0);
		for(int row=0;row<9;row++){
			for(int col=0;col<9;col++){
				if(grid[row][col] !=0){
					String key = row+""+col;
					valueMap.put(key, grid[row][col]);
				}
			}
		}

		return valueMap;
	}

	/**
	 * Display Sudoku grid
	 * @param grid
	 */
	public  void displayGrid(int[][] grid) {
		System.out.println(" -------------------------------");
		for (int row = 0; row < 9; row++) {

			for (int col = 0; col < 9; col++) {

				String value = grid[row][col] > 0 ? grid[row][col] + "" : " ";

				if (col % 3 == 0) {
					System.out.print(" | " + value);
				} else {
					System.out.print("  " + value);
				}

			}
			System.out.println(" |");
			if ((row + 1) % 3 == 0) {
				System.out.println(" -------------------------------");
			}
		}
	}

	public int[][]  generatePuzzle() {

		if (!fillGrid(0, 0,grid,null)) {
			System.out.println("this can't be solved");
		} else {
			System.out.println("solved");
		}
		return grid;

	}


}
