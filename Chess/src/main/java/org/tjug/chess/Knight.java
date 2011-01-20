package org.tjug.chess;

public class Knight extends ChessPiece {
	public Knight(){}
	
	public Knight (IChessPiece.Color color){
		super(color);
	}

	@Override
	public void move(Move move, Board board) {
		//  test driven development
		//    rooks move 2-1 squares in any direction.
		int deltaX = Math.abs(move.getOrigin().getX() - move.getDestination().getX());
		int deltaY = Math.abs(move.getOrigin().getY() - move.getDestination().getY());
		if (!(((1==deltaX) && (2==deltaY)) || ((2==deltaX) && (1==deltaY)))){
			throw new RuntimeException(INVALID_MOVE+"Rook. " + move);
		}
		
		//  test driven development
		//    rooks move 2-1 squares in any direction.
		IChessPiece piece = board.getPiece(move.getDestination());
		
		if ((null!=piece) && (piece.getColor()==getColor())){
			throw new RuntimeException(INVALID_MOVE+"Knight. Space Occupied. " + move);
		}
	}
	public String toString(){
		if (IChessPiece.Color.BLACK == color)
			return "B:Knight";
		else
			return "W:Knight";
	}

}
