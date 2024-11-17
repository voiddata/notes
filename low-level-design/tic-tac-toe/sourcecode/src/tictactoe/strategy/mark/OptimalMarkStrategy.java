package tictactoe.strategy.mark;

import tictactoe.Board;
import tictactoe.strategy.SharedContext;

public class OptimalMarkStrategy implements MarkStrategy {
	private SharedContext sharedContext;
	public OptimalMarkStrategy(SharedContext sharedContext) {
		this.sharedContext = sharedContext;
	}
	public SharedContext getSharedContext() {
		return this.sharedContext;
	}
	@Override
	public boolean setPiece(Board boardObject, int row, int col, int playerNumber) {
		int[][] board = boardObject.getBoard();
		
		if (board[row][col] != 0) {
			System.out.println("not a valid position. choose position again");
			return false;
		} else {
			if (playerNumber == 1) {
				this.sharedContext.incRowCount(row, 1);
				this.sharedContext.incColCount(col, 1);
				if (row == col) this.sharedContext.incDiagonal(1);
				if (row + col == boardObject.getSize()) this.sharedContext.incAntiDiagonal(1);
			} else {
				this.sharedContext.incRowCount(row, -1);
				this.sharedContext.incColCount(col, -1);
				if (row == col) this.sharedContext.incDiagonal(-1);
				if (row + col == boardObject.getSize()) this.sharedContext.incAntiDiagonal(-1);
			}
			board[row][col] = playerNumber;
			return true;
		}
	}

}
