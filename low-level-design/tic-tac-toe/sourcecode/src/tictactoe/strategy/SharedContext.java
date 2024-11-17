package tictactoe.strategy;

public class SharedContext {
	private int[] rowCount;
	private int[] colCount;
	private int diagonal;
	private int antiDiagonal;
	
	public SharedContext(int size) {
		this.colCount = new int[size];
		this.rowCount = new int[size];
		this.diagonal = 0;
		this.antiDiagonal = 0;
	}

	public int getRowCount(int index) {
		return rowCount[index];
	}
	
	public void incRowCount(int index, int quantity) {
		rowCount[index] += quantity;
	}

	public int getColCount(int index) {
		return colCount[index];
	}
	
	public void incColCount(int index, int quantity) {
		colCount[index] += quantity;
	}

	public int getDiagonal() {
		return diagonal;
	}
	
	public void incDiagonal(int quantity) {
		diagonal += quantity;
	}

	public int getAntiDiagonal() {
		return antiDiagonal;
	}
	public void incAntiDiagonal(int quantity) {
		antiDiagonal += quantity;
	}
}
