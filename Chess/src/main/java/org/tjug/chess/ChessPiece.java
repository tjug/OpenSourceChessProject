package org.tjug.chess;

public abstract class ChessPiece implements IChessPiece {

	IChessPiece.Color color;
	
	public ChessPiece(){}
	
	public ChessPiece(IChessPiece.Color chessPieceColor){
		color = chessPieceColor;
	}
	
	
	@Override
	public void move(Move move, Board board) {
		// TODO Auto-generated method stub
	}

	
	@Override
	public Color getColor(){return color;}
}
