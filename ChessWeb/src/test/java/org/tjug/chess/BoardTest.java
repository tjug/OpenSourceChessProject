package org.tjug.chess;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tjug.chess.game.Board;
import org.tjug.chess.game.Coordinate;
import org.tjug.chess.game.IChessPiece;
import org.tjug.chess.game.Rook;

public class BoardTest {

	Coordinate coordinate;
	Board board;
	Rook rook;

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

}
