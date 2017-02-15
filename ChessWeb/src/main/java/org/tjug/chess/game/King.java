package org.tjug.chess.game;
//
//  This class represents the King object used in a game of chess.
//
public class King extends ChessPiece {
	public King(){}
	
	public King (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		
		if (neverMoved) neverMoved = false;
		
		throw new RuntimeException("No moves implemented for King.");		
		
	}
	
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:King";
		else
			return "W:King";
	}

}
