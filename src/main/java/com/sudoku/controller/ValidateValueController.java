package com.sudoku.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sudoku.helper.SudokuGenerateHelper;
import com.sudoku.helper.SudokuGrid;
import com.sudoku.helper.Validate;

@RestController
public class ValidateValueController {
	
	@RequestMapping(value="/validateCellInput",method=RequestMethod.POST)
	public Validate validatePuzzle(HttpServletRequest request){
		System.out.println("Inside validate controller");
		
		SudokuGrid sd = (SudokuGrid) request.getSession().getAttribute("sudokuGrid");
		
			int[][] grid= sd.getGrid();
		
		String value = request.getParameter("value");
		String rowIndex = request.getParameter("rowIndex");
		String colIndex = request.getParameter("colIndex");
		
		SudokuGenerateHelper helper =new SudokuGenerateHelper();
		Validate obj = new Validate();
		obj.setIsValid(false);
		System.out.println("Value : "+value);
		
		if(value.equals("0") || (!helper.checkCol(Integer.parseInt(colIndex), Integer.parseInt(value), grid) && 
				!helper.checkRow(Integer.parseInt(rowIndex), Integer.parseInt(value), grid) &&
				!helper.validGrid(Integer.parseInt(rowIndex), Integer.parseInt(colIndex), Integer.parseInt(value), grid))){
			obj.setIsValid(true);
			grid[Integer.parseInt(rowIndex)][Integer.parseInt(colIndex)] =Integer.parseInt(value);
			request.getSession().setAttribute("maskGrid", grid);
		}
		
		
		return obj;
		
	}

}
