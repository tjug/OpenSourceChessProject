package org.tjug.chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
	private IChessPiece.Color lastPlayer;
	private List<IChessPiece> removedPieces = new ArrayList<IChessPiece>();
	
	public static void main(String[] args){
		Board board = new Board();
		board.initialize();
		
		// valid moves for knight taking pawn ....
		board.move("B:0-C:2");
		board.move("D:6-D:4");
		board.move("C:2-D:4");
		
		board.log("Removed pieces : " + board.removedPieces);
		
	}
	
	private IChessPiece[][] board = new IChessPiece[8][8];

	void clearBoard(){
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				board[i][j] = null;
			}
		}
	}
	
	void initialize(){
		clearBoard();
		setup(IChessPiece.Color.WHITE);
		setup(IChessPiece.Color.BLACK);
		removedPieces.clear();
		lastPlayer = null;
		
		log(this.toString());
	}
	
	void move(String strMove){
		try{
			Move move = new Move(strMove);
			IChessPiece piece = getPiece(move.getOrigin());
			IChessPiece removedPiece = null;
			
			// test driven development
			//    players must alternate turns
			if(piece.getColor()==lastPlayer){
				throw new RuntimeException("Player out of order.");
			}
			piece.move(move, this);
			removedPiece = board[move.getDestination().getX()][move.getDestination().getY()];
			board[move.getDestination().getX()][move.getDestination().getY()] = piece;
			board[move.getOrigin().getX()][move.getOrigin().getY()] = null;
			lastPlayer = piece.getColor();

			log("Valid move - " + move);
			if (null!=removedPiece){
				log("Chess piece removed: " + removedPiece.toString());
				removedPieces.add(removedPiece);
			}
			log(this.toString());
			
		}catch(RuntimeException r){
			err(r.getMessage());
		}
	}

	IChessPiece getPiece(Coordinates coordinates){
		return board[coordinates.getX()][coordinates.getY()];
	}
	
	private void setup(IChessPiece.Color color)
	{
		int file = color == IChessPiece.Color.BLACK ? 0 : 7;
		
		board[file][0] = new Rook(color);
		board[file][1] = new Knight(color); 
		board[file][2] = new Bishop(color); 
		board[file][3] = new King(color); 
		board[file][4] = new Queen(color); 
		board[file][5] = new Bishop(color); 
		board[file][6] = new Knight(color); 
		board[file][7] = new Rook(color);
		file = (file==7) ? 6 : 1;
		board[file][0] = new Pawn(color); 
		board[file][1] = new Pawn(color); 
		board[file][2] = new Pawn(color); 
		board[file][3] = new Pawn(color); 
		board[file][4] = new Pawn(color); 
		board[file][5] = new Pawn(color); 
		board[file][6] = new Pawn(color); 
		board[file][7] = new Pawn(color); 
	}
	
	@Override
	public String toString(){
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2;
		String headerLine = "   -----A-------------B-------------C-------------D-------------E-------------F-------------G-------------H-----\n";
		String line = "  |-------------------------------------------------------------------------------------------------------------|\n";
		sb1.append(headerLine);
		for(int i=0; i<8; i++){
			sb2 = new StringBuffer();
			for(int j=0; j<8; j++){
				String piece;
				piece = (null==board[i][j])? "" : board[i][j].toString();
				piece = " | " + piece + "            ".substring(0, 10-piece.length());
				piece = (0==j ? Integer.toString(i) : " ")+ piece;
				sb2.append(piece);
			}
			sb2.append("|\n");
			sb1.append(sb2.toString());
			if (7>i) sb1.append(line);
		}
		sb1.append(headerLine);
		
		return sb1.toString(); 
	}
	
	public String getUpdatedBoard(){
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				sb.append( (null==board[i][j])?"":(board[i][j]).toString());
				if(j<7) sb.append("|");
			}
			if(i<7) sb.append("*");
		}
		
		return sb.toString(); 
	}

	void log(String msg){
		System.out.println(msg);
	}
	
	void err(String msg){
		System.err.println(msg);
	}
}
