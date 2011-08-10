package org.tjug.chess.game;
//
//  This is an abstract class providing common functionality for 
//    chess pieces.
//
public abstract class ChessPiece implements IChessPiece {

	IChessPiece.Color color;
	boolean neverMoved = true;
	
	public ChessPiece(){}
	
	public ChessPiece(IChessPiece.Color chessPieceColor){
		color = chessPieceColor;
	}
	
	@Override
	public boolean neverMoved() {return neverMoved;}
	
	@Override
	public Color getColor(){return color;}
}
