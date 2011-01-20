package org.tjug.chess;

public class Queen extends ChessPiece {
	public Queen(){}
	
	public Queen (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		throw new RuntimeException("No moves implemented for Queen.");
	}
	
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Queen";
		else
			return "W:Queen";
	}
}
