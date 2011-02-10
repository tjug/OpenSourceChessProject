package org.tjug.chess.game;
//
//  This class represents a move made by an opponent during
//    the course of a chess game.
//
//  The format of the move is:
//                             A1-A3  -- pawn first move
//
public class Move {
	String origin;
	String destination;
	
	public Move (String move){
		String[] moves = move.split("-");
		origin = moves[0];
		destination = moves[1];
	}
	
	public Coordinate getOrigin(){
		return new Coordinate(origin);
	}
	
	public Coordinate getDestination(){
		return new Coordinate(destination);
	}
	
	public String toString(){
		return "Move : orgin("+ origin + ") dest("+destination+")";
	}
}

