package org.tjug.chess.game;
//
//  This class represents a Chess Game object.

import java.util.HashSet;
import java.util.Set;

public class Game {
	private Board board;
	private Set<String> moves;
	
	public Game(){
		board = new Board();
		moves = new HashSet<String>();
	}
	
	void saveGame(){
		
	}
	
	void endGame(){
		board.destroy();
	}
	
	public String makeMove(String move){
		moves.add(move);
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
	
	public Set<String> getMoves(){
		return moves;
	}
	
	public IChessPiece.Color getActivePlayer(){
		return board.getActivePlayer();
	}
	
}
