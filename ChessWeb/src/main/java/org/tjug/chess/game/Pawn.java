package org.tjug.chess.game;
//
//  This class represents the Pawn object used in a game of chess.
//
public class Pawn extends ChessPiece {
	private boolean moved = false;
	
	public Pawn(){}
	
	public Pawn (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		int directionOfMove = move.getDestination().getX()-move.getOrigin().getX();
		int deltaX = Math.abs(move.getDestination().getX()-move.getOrigin().getX());
		int deltaY = Math.abs(move.getDestination().getY()-move.getOrigin().getY());
		
		//  test driven development 
		//    pawn cannot move more than one space, two spaces on first move.
		if ( (2<deltaX) || (moved && 2==deltaX)){
			throw new RuntimeException("Invalid move for a Pawn - too many spaces - " + move.toString());
		}
		
		//  test driven development 
		//    pawns can only move in straight line
		if (0!=deltaY){
			// unless an opponents piece its there 
			if (!(1==deltaY && 1==deltaX && (this.getColor()!= board.getPiece(move.getDestination()).getColor()))){
				throw new RuntimeException("Invalid move for a Pawn - moving outside file - " + move.toString());
			}
		}
		
		//  test driven development 
		//    pawns can only move towards opponent's board end.
		if ((directionOfMove>0 && IChessPiece.Color.WHITE==color) || (directionOfMove<0 && IChessPiece.Color.BLACK==color)){
			throw new RuntimeException("Invalid move for a Pawn - wrong direction - " + move.toString());
		}
		
		// test driven development
		//   pawn reaches the opponents line - change to a queen
		if (move.getDestination().getX()==0 || move.getDestination().getX()==7){
			board.setPiece(move.getOrigin(), new Queen(this.getColor()));
		}
		
		moved = true;
		if (neverMoved) neverMoved = false;
	}
	
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Pawn";
		else
			return "W:Pawn";
	}

}
