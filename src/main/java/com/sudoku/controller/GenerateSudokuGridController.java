package com.sudoku.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sudoku.helper.SudokuGenerateHelper;
import com.sudoku.helper.SudokuGrid;

@Controller
public class GenerateSudokuGridController{


	private SudokuGenerateHelper sudokuGenerateHelper;
	
	@RequestMapping("/puzzleGenerator")
	public ModelAndView generatePuzzle(HttpServletRequest request){
		sudokuGenerateHelper = new SudokuGenerateHelper();
		
		 int[][] grid = sudokuGenerateHelper.generatePuzzle();
		 sudokuGenerateHelper.displayGrid(grid);
		 int[][] maskGrid = new int[9][9];
		 //copy array
		 for(int i=0;i<9;i++){
			 for(int j=0;j<9;j++){
				 maskGrid[i][j] = grid[i][j] ;
			 }
		 }
		 maskGrid =sudokuGenerateHelper.maskGrid(maskGrid, 50);
		
		ModelAndView mv = new ModelAndView("generatePuzzle");
		mv.addObject("grid", grid);
		SudokuGrid sd = new SudokuGrid();
		sd.setGrid(maskGrid);
		Map<String, Integer> fixedCellMap=sudokuGenerateHelper.getFixedCellList(maskGrid);
		sd.setFixedCellMap(fixedCellMap);
		mv.addObject("maskGrid", sd);

		sudokuGenerateHelper.displayGrid(grid);
		 
		 request.getSession().setAttribute("sudokuGrid", sd);
		return mv;
	}

}
