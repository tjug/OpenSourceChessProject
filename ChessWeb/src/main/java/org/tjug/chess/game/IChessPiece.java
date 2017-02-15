package org.tjug.chess.game;

public interface IChessPiece {
	enum Color{
		WHITE,
		BLACK
	};
	
	Color getColor();
	boolean neverMoved();
	void move(Move move, Board board);
	
	final static String  INVALID_MOVE = "Invalid move for a ";
	final static String  DESTINATION_NOT_EMPTY = "Destination is not empty or contains an opponent's piece.";
	final static String  PATH_NOT_CLEAR = "Path is not clear.";
}
