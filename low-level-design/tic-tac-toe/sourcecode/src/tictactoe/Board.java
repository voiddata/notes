package tictactoe;

public class Board {
	private int size;
	private PieceType[][] board;
	
	public Board(int size) {
		this.size = size;
		board = new PieceType[this.size][this.size];
	}
	
	public int getSize() {
		return this.size;
	}
	public PieceType[][] getBoard() {
		return this.board;
	}
}
