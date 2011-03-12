package org.tjug.chess.data;

public class GameResult {
	private String gameId;
	private String winner;
	private String looser;
	
	public GameResult(String gameId, String winnerPlayerId, String looserPlayerId){
		setGameId(gameId);
		setWinner(winnerPlayerId);
		setLooser(looserPlayerId);
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getWinner() {
		return winner;
	}
	public void setLooser(String looser) {
		this.looser = looser;
	}
	public String getLooser() {
		return looser;
	}
}
