package org.tjug.chess;

public class Bishop extends ChessPiece {
	public Bishop(){}
	
	public Bishop (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		throw new RuntimeException("No moves implemented for Bishop.");
	}
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Bishop";
		else
			return "W:Bishop";
	}

}
