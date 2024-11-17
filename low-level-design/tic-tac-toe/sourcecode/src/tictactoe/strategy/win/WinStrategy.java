package tictactoe.strategy.win;

import tictactoe.Board;

public interface WinStrategy {
	boolean validate(Board boardObject, int row, int col);
}
