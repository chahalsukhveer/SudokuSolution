package com.sudoku.helper;

import java.io.Serializable;
import java.util.Map;

public class SudokuGrid  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int[][] grid;
	private Map<String, Integer>  fixedCellMap;

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public Map<String, Integer> getFixedCellMap() {
		return fixedCellMap;
	}

	public void setFixedCellMap(Map<String, Integer> fixedCellMap) {
		this.fixedCellMap = fixedCellMap;
	}

	

}
