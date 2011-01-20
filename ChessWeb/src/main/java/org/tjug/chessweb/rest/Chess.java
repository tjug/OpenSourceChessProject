package org.tjug.chessweb.rest;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.tjug.chess.Controller;
import org.tjug.chessweb.push.MoveSender;

@Path("/chess/")
public class Chess {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("makeMove/{gameId}/{move}/")
	public String makeMove(@PathParam("gameId") String gameId, @PathParam("move") String move){
		MoveSender.getInstance().send(gameId, move);
		
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
		return Controller.instance.getGame(gameId).redrawGame();
	}
	
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	@Path("games/")
	public String getGames(){
		Set<String> gameIds = Controller.instance.getGames();
		StringBuffer sb = new StringBuffer();
		
		for (String id: gameIds){
			sb.append(id);
			sb.append("<br/>");
		}
		
		return sb.toString();
	}
	

}
