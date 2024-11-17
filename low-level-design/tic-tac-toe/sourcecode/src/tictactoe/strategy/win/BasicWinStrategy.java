package tictactoe.strategy.win;

import tictactoe.Board;

public class BasicWinStrategy implements WinStrategy {

	@Override
	public boolean validate(Board boardObject, int row, int col) {
		boolean rowMatch = false, colMatch = false, upperDiagonal = false, lowerDiagonal = false;
		
		int[][] board = boardObject.getBoard();
		int size = boardObject.getSize();
		
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
		
		return !rowMatch || !colMatch || !upperDiagonal || !lowerDiagonal;
	}

}
