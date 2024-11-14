package tictactoe;

public class PieceType {
	private PieceEnum piece;
	
	public PieceType(PieceEnum piece) {
		this.piece = piece;
	}
	
	public PieceEnum getPiece() {
		return this.piece;
	}
}
