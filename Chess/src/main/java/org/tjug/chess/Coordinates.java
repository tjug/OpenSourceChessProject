package org.tjug.chess;

public class Coordinates {
	private int x;
	private int y;
	
	public Coordinates(String coord){
		String [] coordinates = coord.split(":");
		y = "ABCDEFGH".indexOf(coordinates[0]);
		x = Integer.parseInt(coordinates[1]);
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
}
