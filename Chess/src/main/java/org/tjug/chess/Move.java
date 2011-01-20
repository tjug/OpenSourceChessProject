package org.tjug.chess;

public class Move {
	String origin;
	String destination;
	public Move (String move){
		String[] moves = move.split("-");
		origin = moves[0];
		destination = moves[1];
	}
	
	public Coordinates getOrigin(){
		return new Coordinates(origin);
	}
	
	public Coordinates getDestination(){
		return new Coordinates(destination);
	}
	
	public String toString(){
		return "Move : orgin("+ origin + ") dest("+destination+")";
	}
}

