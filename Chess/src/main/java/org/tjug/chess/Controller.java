package org.tjug.chess;

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
	
}
