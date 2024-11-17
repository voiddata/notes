package tictactoe;

import java.util.Scanner;

public class Game {
	private Board board;
	
	public Game(int boardSize) {
		this.board = new Board(boardSize);
	}
	
	public static void main(String[] args) {
		Game game = new Game(3);
		System.out.println("initialized game");
		game.run();
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean flag = true;
		int row = -1, column = -1;
		
		do {
			int playerNumber = flag == true ? 1 : 2;
			System.out.println("player " + playerNumber + " has to play. choose the position");
			
			do {
				board.print();
				do {
					System.out.println("choose row 1 / 2 / 3 ?");
					row = sc.nextInt();
				} while (!board.validRowOrColumn(row, true));
				
				do {
					System.out.println("choose column 1 / 2 / 3 ?");
					column = sc.nextInt();
				} while (!board.validRowOrColumn(column, false));
				
			} while (!board.setPiece(row - 1, column - 1, playerNumber));
			flag = !flag;
		} while (!board.validate(row - 1, column - 1) && !board.filled());
		if (board.filled()) System.out.println("Game Over - DRAW");
		else System.out.println("Game Over - " + board.getPiece(row-1, column-1) + " Wins!!");
		sc.close();
	}
	
	

	public Board getBoard() {
		return this.board;
	}
}
