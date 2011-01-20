package org.tjug.chess;

public class Rook extends ChessPiece {
	public Rook(){}
	
	public Rook (IChessPiece.Color color){
		super(color);
	}
	
	@Override
	public void move(Move move, Board board) {
		throw new RuntimeException("No moves implemented for Rook.");
	}
	
	@Override
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Rook";
		else
			return "W:Rook";
	}
}
