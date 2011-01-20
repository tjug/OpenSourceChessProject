package org.tjug.chess;

public class Game {
	private Board board;
	
	Game(){
		board = new Board();
	}
	
	public String makeMove(String move){
		board.move(move);
		return board.getUpdatedBoard();
	}
	
	public String resetGame(){
		board.initialize();
		return board.getUpdatedBoard();
	}
	
	public String redrawGame(){
		return board.getUpdatedBoard();
	}
}
