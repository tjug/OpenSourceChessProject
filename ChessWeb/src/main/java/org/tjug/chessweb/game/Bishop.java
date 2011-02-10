package org.tjug.chess.game;
//
//  This class represents the Bishop object used in a game of chess.
//
public class Bishop extends ChessPiece {
	public Bishop(){}
	
	public Bishop (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		//
		//  moves are only diagonal - 45 degrees.
		long absX = Math.abs(move.getOrigin().getX() - move.getDestination().getX());
		long absY = Math.abs(move.getOrigin().getY() - move.getDestination().getY());
		
		if (absX!=absY){
			throw new RuntimeException(IChessPiece.INVALID_MOVE + "Bishop.  Diagonal moves only.");
		}
		
		//  
		//  Must check the board to verify there is a clear path from the move.Origin to the move.Destination
		if (!board.clearPath(move.getOrigin(), move.getDestination())){
			throw new RuntimeException(IChessPiece.INVALID_MOVE + "Bishop.  " + IChessPiece.PATH_NOT_CLEAR);
		}else{
			//
			//  Destination must be empty or contain opponent's piece.
			if ( (null!=board.getPiece(move.getDestination()))
					&& (this.getColor()==board.getPiece(move.getDestination()).getColor())){
				throw new RuntimeException(IChessPiece.INVALID_MOVE + "Bishop.  "+IChessPiece.DESTINATION_NOT_EMPTY);
			}
		}
		
		return;
	}
	
	@Override
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Bishop";
		else
			return "W:Bishop";
	}

}
