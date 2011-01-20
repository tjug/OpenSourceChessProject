package org.tjug.chess;

public interface IChessPiece {
	enum Color{
		WHITE,
		BLACK
	};
	
	Color getColor();
	
	void move(Move move, Board board);
	
	final static String  INVALID_MOVE = "Invalid move for a ";
}
