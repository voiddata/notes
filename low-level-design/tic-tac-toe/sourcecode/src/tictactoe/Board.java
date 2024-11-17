package tictactoe;

import java.util.Arrays;

public class Board {
	private int size;
	private int[][] board;
	private int filledSpaces;
	
	private int[] rowCount;
	private int[] colCount;
	private int diagonal;
	private int antiDiagonal;
	
	public Board(int size) {
		this.size = size;
		board = new int[this.size][this.size];
		this.filledSpaces = 0;
		
		this.colCount = new int[this.size];
		this.rowCount = new int[this.size];
		this.diagonal = 0;
		this.antiDiagonal = 0;
	}
	
	public int getSize() {
		return this.size;
	}
	public int getPiece(int row, int col) {
		return this.board[row][col];
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
			if (playerNumber == 1) {
				rowCount[row]++;
				colCount[col]++;
				if (row == col) diagonal++;
				if (row + col == this.size) antiDiagonal++;
			} else {
				rowCount[row]--;
				colCount[col]--;
				if (row == col) diagonal--;
				if (row + col == this.size) antiDiagonal--;
			}
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
		return rowCount[row] == this.size || rowCount[row] == -this.size 
				|| colCount[col] == this.size || colCount[col] == -this.size 
				|| diagonal == this.size || diagonal == -this.size
				|| antiDiagonal == this.size || antiDiagonal == -this.size;
	}
}
