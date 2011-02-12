package org.tjug.chess.game;
//
//  This is the single point of access to the Chess Game from all points external.
//
//  The enum declared in this manner operates similar
//    to a singleton design pattern, with less code.
//
//    example of usage:
//      Game game = Controller.instance.getGame(gameId);
//
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Controller {
	instance;
	
	private Map<String, Game> games = new HashMap<String, Game>();
	
	public Game getGame(String gameId){
		if (games.containsKey(gameId)) return games.get(gameId);
		
		throw new RuntimeException("Game "+gameId+" not found.");
	}
	
	public Game newGame(String gameId){
		if (games.containsKey(gameId)) throw new RuntimeException("Game " + gameId + " already exists.");
		
		Game game = new Game();
		games.put(gameId, game);
		return game;
	}
	
	public Set<String> getGames(){
		return games.keySet();
	}
	
	public Set<String> getMoves(String gameId){
		return games.get(gameId).getMoves();
	}
	
	public String getActivePlayer(String gameId){
		try{
		if (games.get(gameId).getActivePlayer() == IChessPiece.Color.BLACK)
			return "Black";
		else
			return "White";
		}catch(Throwable th){
			return "";
		}
	}
}
