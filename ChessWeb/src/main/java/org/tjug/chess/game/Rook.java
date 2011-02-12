package org.tjug.chess.game;
//
//  This class represents the Rook object used in a game of chess.
//
public class Rook extends ChessPiece {
	public Rook(){}
	
	public Rook (IChessPiece.Color color){
		super(color);
	}
	
	@Override
	public void move(Move move, Board board) {
		
		//
		//  Valid moves are horizontal and vertical
		if (!((0==(move.getOrigin().getX() - move.getDestination().getX())) || (0==(move.getOrigin().getY() - move.getDestination().getY())))){
			throw new RuntimeException(IChessPiece.INVALID_MOVE + "Rook.  Horizontal or Vertical only.");
		}
		
		//  
		//  Must check the board to verify there is a clear path from the move.Origin to the move.Destination
		if (!board.clearPath(move.getOrigin(), move.getDestination())){
			throw new RuntimeException(IChessPiece.INVALID_MOVE + "Rook.  " + IChessPiece.PATH_NOT_CLEAR);
		}else{
			if ( (null!=board.getPiece(move.getDestination()))
					&& (this.getColor()==board.getPiece(move.getDestination()).getColor())){
				throw new RuntimeException(IChessPiece.INVALID_MOVE + "Rook.  " + IChessPiece.DESTINATION_NOT_EMPTY);
			}
		}
		
		return;
	}
	
	@Override
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Rook";
		else
			return "W:Rook";
	}
}
