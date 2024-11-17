package tictactoe;

import java.util.Arrays;

import tictactoe.strategy.mark.MarkStrategy;
import tictactoe.strategy.win.WinStrategy;

public class Board {
	private int size;
	private int[][] board;
	private int filledSpaces;
	
	private WinStrategy winStrategy;
	private MarkStrategy markStrategy;
	
	public Board(int size, WinStrategy winStrategy, MarkStrategy markStrategy) {
		this.size = size;
		board = new int[this.size][this.size];
		this.filledSpaces = 0;
		
		this.winStrategy = winStrategy;
		this.markStrategy = markStrategy;
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
	
	public void setWinStrategy(WinStrategy winStrategy) {
		this.winStrategy = winStrategy;
	}
	
	public void setMarkStrategy(MarkStrategy markStrategy) {
		this.markStrategy = markStrategy;
	}
	
	public boolean validRowOrColumn(int row, boolean flag) {
		if (row > 0 && row <= size)
			return true;
		else {
			String colOrRow = flag == true ? "row" : "column";
			System.out.println("not a valid "+ colOrRow +"!!");
			return false;
		}
	}
	
	public boolean setPiece(int row, int col, int playerNumber) {
		boolean marked = markStrategy.setPiece(this, row, col, playerNumber);
		filledSpaces = marked == true ? filledSpaces + 1 : filledSpaces;
		return marked;
	}
	
	public void print() {
		System.out.println("---------------");
		for (int[] arr : board)
			System.out.println(Arrays.toString(arr));
		System.out.println("---------------");
	}
	
	public boolean validate(int row, int col) {
		return winStrategy.validate(this, row, col);
	}
}
