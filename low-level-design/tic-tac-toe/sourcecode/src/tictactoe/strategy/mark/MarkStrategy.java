package tictactoe.strategy.mark;

import tictactoe.Board;

public interface MarkStrategy {
	boolean setPiece(Board boardObject, int row, int col, int playerNumber);
}
