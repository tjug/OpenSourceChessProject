package org.tjug.chess.rest.dataProvider;
//
//  This class represents a REST access point for the Tournament data table component. 
//
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.tjug.chess.data.Game;
import org.tjug.chess.data.Player;
import org.tjug.chess.data.ChessDAO;
import org.tjug.chess.data.Tournament;

@Path("/TableData")
public class TableDataSource {

	@GET
	@Path("tournaments")
	@Produces("application/json")
	public String Tournaments(){
		List<Tournament> tourneys = ChessDAO.instance.getTournaments();
		DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm");
		
		StringBuilder sb = new StringBuilder();
		boolean once = false;
		for (Tournament tournament : tourneys){
			if (!once){
				once = true;
			}else{
				sb.append(",");
			}
			sb.append("[\""+tournament.getGame()+"\",\""+dateFormat.format(tournament.getDate())+"\",\""+tournament.getPlace()+"\"]");
		}
		
		
		return wrapDataForTableControl(sb.toString());
	}
	
	@GET
	@Path("games/{tournamentId}")
	@Produces("application/json")
	public String games(@PathParam("tournamentId") String tournamentId){
		List<Game> list = ChessDAO.instance.getGames(tournamentId);
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		
		StringBuilder sb = new StringBuilder();
		boolean once = false;
		for (Game game : list){
			if (!once){
				once = true;
			}else{
				sb.append(",");
			}
			sb.append("[\""+game.getPlayer1()+"\",\""+game.getPlayer2()+"\",\""+dateFormat.format(game.getStartTime())+"\"]");
		}
		
		return wrapDataForTableControl(sb.toString());
	}
	
	@GET
	@Path("player/{playerId}")
	@Produces("application/json")
	public Player player(@PathParam("playerId") String playerId){
		return ChessDAO.instance.getPlayer(playerId);
	}
	
	private String wrapDataForTableControl(String data){
		return "{\"aaData\":[" + data + "]}";	
	}
}
