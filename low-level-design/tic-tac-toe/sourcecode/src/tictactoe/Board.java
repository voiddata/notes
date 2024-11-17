package tictactoe;

import java.util.Arrays;

public class Board {
	private int size;
	private int[][] board;
	private int filledSpaces;
	
	public Board(int size) {
		this.size = size;
		board = new int[this.size][this.size];
		this.filledSpaces = 0;
	}
	
	public int getSize() {
		return this.size;
	}
	public boolean filled() {
		return this.filledSpaces == this.size * this.size;
	}
	public int[][] getBoard() {
		return this.board;
	}
	
	public boolean validRowOrColumn(int row, boolean flag) {
		if (row == 1 || row == 2 || row == 3)
			return true;
		else {
			String colOrRow = flag == true ? "row" : "column";
			System.out.println("not a valid "+ colOrRow +"!!");
			return false;
		}
	}
	
	public boolean setPiece(int row, int col, int playerNumber) {
		if (board[row][col] != 0) {
			System.out.println("not a valid position. choose position again");
			return false;
		} else {
			board[row][col] = playerNumber;
			filledSpaces++;
			return true;
		}
	}
	
	public void print() {
		System.out.println("---------------");
		for (int[] arr : board)
			System.out.println(Arrays.toString(arr));
		System.out.println("---------------");
	}
	
	public boolean validate(int row, int col) {
		boolean rowMatch = false, colMatch = false, upperDiagonal = false, lowerDiagonal = false;
		
		for (int i = 0; i < size; i++) {
			if (i == col) continue;
			if (board[row][i] != board[row][col]) {
				rowMatch = true;
				break;
			}
		}
		
		for (int i = 0; i < size; i++) {
			if (i == row) continue;
			if (board[i][col] != board[row][col]) {
				colMatch = true;
				break;
			}
		}
		
		for (int i = 0, j = 0; i < size && j < size; i++,j++) {
			if (i == row && j == col) continue;
			if (board[i][j] != board[row][col]) {
				upperDiagonal = true;
				break;
			}
		}
		
		for (int i = 0, j = size - 1; i < size && j >= 0; i++,j--) {
			if (i == row && j == col) continue;
			if (board[i][j] != board[row][col]) {
				lowerDiagonal = true;
				break;
			}
		}
		
		return rowMatch && colMatch && upperDiagonal && lowerDiagonal;
	}
}
