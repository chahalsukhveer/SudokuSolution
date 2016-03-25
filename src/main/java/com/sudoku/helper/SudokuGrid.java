package com.sudoku.helper;

import java.io.Serializable;

public class SudokuGrid  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int[][] grid;
	private int[][] fixedCellsList;

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int[][] getFixedCellsList() {
		return fixedCellsList;
	}

	public void setFixedCellsList(int[][] fixedCellsList) {
		this.fixedCellsList = fixedCellsList;
	}

}
