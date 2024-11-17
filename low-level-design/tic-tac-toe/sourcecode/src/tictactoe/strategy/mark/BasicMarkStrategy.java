package tictactoe.strategy.mark;

import tictactoe.Board;

public class BasicMarkStrategy implements MarkStrategy {

	@Override
	public boolean setPiece(Board boardObject, int row, int col, int playerNumber) {
		
		int[][] board = boardObject.getBoard();
		
		if (board[row][col] != 0) {
			System.out.println("not a valid position. choose position again");
			return false;
		} else {
			board[row][col] = playerNumber;
			return true;
		}
	}

}
