package org.tjug.chess.game;
//
//  This class represents the Queen object used in a game of chess.
//
public class Queen extends ChessPiece {
	public Queen(){}
	
	public Queen (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		//
		//  moves are diagonal (45 degrees), horizontal or vertical.
		long absX = Math.abs(move.getOrigin().getX() - move.getDestination().getX());
		long absY = Math.abs(move.getOrigin().getY() - move.getDestination().getY());
		int deltaX = move.getOrigin().getX() - move.getDestination().getX();
		int deltaY = move.getOrigin().getY() - move.getDestination().getY();
		
		if ((absX!=absY) && (0!=deltaX) && (0!=deltaY)){
			throw new RuntimeException(IChessPiece.INVALID_MOVE + "Queen.  Horizontal, vertical or diagonal moves only.");
		}
		
		//  
		//  Must check the board to verify there is a clear path from the move.Origin to the move.Destination
		//    checking diagonal
		if (absX==absY){
			if (!board.clearPath(move.getOrigin(), move.getDestination())){
				throw new RuntimeException(IChessPiece.INVALID_MOVE + "Queen.  " + IChessPiece.PATH_NOT_CLEAR);
			}else{
				if ( (null!=board.getPiece(move.getDestination()))
						&& (this.getColor()==board.getPiece(move.getDestination()).getColor())){
					throw new RuntimeException( IChessPiece.INVALID_MOVE + "Queen.  "+IChessPiece.DESTINATION_NOT_EMPTY);
				}
			}
		}
		
		//    checking horizontal - vertical
		if ((0==deltaX) || (0==deltaY)){
			if (!board.clearPath(move.getOrigin(), move.getDestination())){
				throw new RuntimeException(IChessPiece.INVALID_MOVE + "Queen.  " + IChessPiece.PATH_NOT_CLEAR);
			}else{
				if ( (null!=board.getPiece(move.getDestination()))
						&& (this.getColor()==board.getPiece(move.getDestination()).getColor())){
					throw new RuntimeException(IChessPiece.INVALID_MOVE + "Queen.  "+IChessPiece.DESTINATION_NOT_EMPTY);
				}
			}
		}
		return;		
	}
	
	@Override
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Queen";
		else
			return "W:Queen";
	}
}
