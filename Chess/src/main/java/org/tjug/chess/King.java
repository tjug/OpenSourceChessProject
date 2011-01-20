package org.tjug.chess;

public class King extends ChessPiece {
	public King(){}
	
	public King (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		throw new RuntimeException("No moves implemented for King.");
	}
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:King";
		else
			return "W:King";
	}

}
