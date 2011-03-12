package org.tjug.chess.rest;
//
//  This class represents a REST access point for the Chess game.
//
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tjug.chess.data.ChessDAO;
import org.tjug.chess.data.Tournament;
import org.tjug.chess.game.Controller;

@Path("/chess/")
public class Chess {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("tournament")
	public String turnament(){
		return "";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("makeMove/{gameId}/{move}/")
	public String makeMove(@PathParam("gameId") String gameId, @PathParam("move") String move){
		//MoveSender.getInstance().send(gameId, move);
		
		return Controller.instance.getGame(gameId).makeMove(move);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("resetGame/{gameId}/")
	public String resetGame(@PathParam("gameId") String gameId){
		return Controller.instance.getGame(gameId).resetGame();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("createGame/{gameId}/")
	public String createGame(@PathParam("gameId") String gameId){
		return Controller.instance.newGame(gameId).resetGame();
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("redrawGame/{gameId}/")
	public String redrawGame(@PathParam("gameId") String gameId){
		String board = "";
		try{
			board = Controller.instance.getGame(gameId).redrawGame();
		}catch (Throwable th){
			System.out.println("Error getting game: " + gameId);
		}
		return board;
	}
	
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	@Path("games/")
	public String getGames(){
		Set<String> gameIds = Controller.instance.getGames();
		StringBuffer sb = new StringBuffer();
		boolean once = true;
		for (String id: gameIds){
			if (once){
				sb.append(id);
				once = false;
			}else{
				sb.append("|" + id);
			}
		}
		
		return sb.toString();
	}
	
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	@Path("moves/{gameId}/")
	public String getMoves(@PathParam("gameId") String gameId){
		Set<String> gameIds = Controller.instance.getMoves(gameId);
		StringBuffer sb = new StringBuffer();
		
		for (String id: gameIds){
			sb.append(id);
			sb.append("<br/>");
		}
		
		return sb.toString();
	}

	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	@Path("activePlayer/{gameId}/")
	public String activePlayer(@PathParam("gameId") String gameId){
		return Controller.instance.getActivePlayer(gameId);
	}

	@GET
	@Produces("application/json")
	@Path("redrawTournament/{tournamentId}/")
	public Tournament redrawTournament(@PathParam("tournamentId") String tournamentId){
		Tournament tournament = null;
		try{
			tournament = ChessDAO.instance.getTournament(tournamentId);
		}catch (Throwable th){
			System.out.println("Error getting Tournament: " + tournamentId);
			th.printStackTrace();
		}
		return tournament;
	}
}
