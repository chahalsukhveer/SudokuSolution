package com.sudoku.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sudoku.helper.SudokuGenerateHelper;
import com.sudoku.helper.SudokuGrid;

@Controller
public class SolveSudokuController {


private SudokuGenerateHelper sudokuGenerateHelper;
	
	@RequestMapping("/solvePuzzle")
	public ModelAndView generatePuzzle(HttpServletRequest request){
		sudokuGenerateHelper = new SudokuGenerateHelper();
		
		SudokuGrid sd = (SudokuGrid) request.getSession().getAttribute("sudokuGrid");
		
		int[][] grid= sd.getGrid();
		Map<String, Integer> fixedCellMap = sd.getFixedCellMap();
		 
		sudokuGenerateHelper.fillGrid(0, 0,grid,fixedCellMap);
		 request.getSession().setAttribute("sudokuGrid", sd);
		 
		 ModelAndView mv = new ModelAndView("solvePuzzle");
		 mv.addObject("maskGrid", sd);
		return mv;
	}
}
