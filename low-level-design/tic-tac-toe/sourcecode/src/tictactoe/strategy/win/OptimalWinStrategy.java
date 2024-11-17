package tictactoe.strategy.win;

import tictactoe.Board;
import tictactoe.strategy.SharedContext;

public class OptimalWinStrategy implements WinStrategy {
	
	private SharedContext sharedContext;
	
	public OptimalWinStrategy(SharedContext sharedContext) {
		this.sharedContext = sharedContext;
	}
	
	@Override
	public boolean validate(Board boardObject, int row, int col) {
		int size = boardObject.getSize();
		return sharedContext.getRowCount(row) == size || sharedContext.getRowCount(row) == -size 
				|| sharedContext.getColCount(col) == size || sharedContext.getColCount(col) == -size 
				|| sharedContext.getDiagonal() == size || sharedContext.getDiagonal() == -size
				|| sharedContext.getAntiDiagonal() == size || sharedContext.getAntiDiagonal() == -size;
	}

	public SharedContext getSharedContext() {
		return sharedContext;
	}
}
