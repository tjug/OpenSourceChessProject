package org.tjug.chess.game;
//
//  This class represents a coordinate of the form A2
//    where valid values of A are A..H inclusive
//    and valid values of 2 are 0..7 inclusive
//
public class Coordinate {
	private int x;
	private int y;
	
	public Coordinate(String coord){
		String [] coordinates = coord.split(":");
		x = "ABCDEFGH".indexOf(coordinates[0]);
		y = Integer.parseInt(coordinates[1]);
	}
	
	public int getX(){return y;}
	public int getY(){return x;}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}
