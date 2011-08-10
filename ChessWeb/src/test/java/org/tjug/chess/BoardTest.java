package org.tjug.chess;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tjug.chess.game.Board;
import org.tjug.chess.game.Coordinate;
import org.tjug.chess.game.IChessPiece;
import org.tjug.chess.game.Rook;
import org.tjug.chess.game.Knight;
import org.tjug.chess.game.Move;

public class BoardTest {

	Coordinate coordinate;
	Board board;
	Rook rook;
	Knight knight;

	@Before
	public void setUp() throws Exception {

		coordinate = new Coordinate("A:7");
		board = new Board();
		board.initialize();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPiece() {

		IChessPiece rook = board.getPiece(coordinate);
		Assert.assertTrue((rook.toString().equalsIgnoreCase("W:Rook")));
		coordinate = new Coordinate("A:6");
		IChessPiece pawn = board.getPiece(coordinate);
		Assert.assertTrue((pawn.toString().equalsIgnoreCase("W:Pawn")));
	}
	
	@Test
	public void testPawnNeverMoved() {

		coordinate = new Coordinate("A:6");
		IChessPiece pawn = board.getPiece(coordinate);
		Assert.assertTrue((pawn.toString().equalsIgnoreCase("W:Pawn")));
		Move move = new Move("A:6-A:4");
		Assert.assertTrue(pawn.neverMoved());
		pawn.move(move, board);
		Assert.assertFalse(pawn.neverMoved());
	}

}
