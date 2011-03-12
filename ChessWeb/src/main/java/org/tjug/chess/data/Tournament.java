package org.tjug.chess.data;

//
//  This class represents a Tournament Data Object.
//
//  The XmlRootEmelemt annotation activates for JAXB (Java XML Binding). 
//    This allows for automatic conversion of the DAO to and from 
//    both JSON (JavaScript Object Notation) and XML.
//

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Map;

//@Entity(name="TournamentEntity")
@XmlRootElement(name = "Tournament")
public class Tournament {

	public Tournament() {
	}

	public Tournament(String game, Date date, String place) {
		setGame(game);
		setDate(date);
		setTime(time);
		setPlace(place);
	}

	public Tournament(Date date, String place) {
		setDate(date);
		setTime(time);
		setPlace(place);
	}

	private String game;
	private Date date;
	private String time;
	private String place;
	private Map<String, Game> games;

	public void setGame(String game) {
		this.game = game;
	}

	public String getGame() {
		return game;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return time;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setGames(Map<String, Game> games) {
		this.games = games;
	}

	public Map<String, Game> getGames() {
		return games;
	}

	public void registerGameResult(GameResult gameResult) {
		String gameId = gameResult.getGameId();

		// Primary game
		if (gameId.startsWith("P")) {
			// determine the id.
			int currGame = Integer.parseInt(gameId.substring(1));
			double dblNextGame = currGame / 2.0;
			int nextGame = (int) Math.ceil(dblNextGame);
			dblNextGame = dblNextGame - Math.floor(dblNextGame);

			String player1 = dblNextGame < 0.5 ? "" : gameResult.getLooser();
			String player2 = !(dblNextGame < 0.5) ? "" : gameResult.getLooser();

			String nextId = "C1_" + nextGame;
			if (games.containsKey(nextId)) {
				if ("".equals(games.get(nextId).getPlayer1())) {
					games.get(nextId).setPlayer1(player1);
				} else {
					games.get(nextId).setPlayer2(player2);
				}
			} else {
				games.put(nextId,
						new Game(nextId, player1, player2, new Date()));
			}

			//
			// winner section
			nextId = "W1_" + nextGame;
			player1 = dblNextGame < 0.5 ? "" : gameResult.getWinner();
			player2 = !(dblNextGame < 0.5) ? "" : gameResult.getWinner();
			;
			if (games.containsKey(nextId)) {
				if ("".equals(games.get(nextId).getPlayer1())) {
					games.get(nextId).setPlayer1(player1);
				} else {
					games.get(nextId).setPlayer2(player2);
				}
			} else {
				games.put(nextId,
						new Game(nextId, player1, player2, new Date()));
			}
			// Consolation game
		} else if (gameId.startsWith("C")) {
			// determine the id.
			String tmpNums = gameId.substring(1);
			String[] nums = tmpNums.split("_");

			int column = Integer.parseInt(nums[0]);
			int currGame = Integer.parseInt(nums[1]);
			double dblNextGame = currGame / 2.0;
			int nextGame = (int) Math.ceil(dblNextGame);
			dblNextGame = dblNextGame - Math.floor(dblNextGame);

			String nextId = "C" + (column + 1) + "_" + nextGame;
			String player1 = dblNextGame < 0.5 ? "" : gameResult.getWinner();
			String player2 = !(dblNextGame < 0.5) ? "" : gameResult.getWinner();
			;

			if (games.containsKey(nextId)) {
				if ("".equals(games.get(nextId).getPlayer1())) {
					games.get(nextId).setPlayer1(player1);
				} else {
					games.get(nextId).setPlayer2(player2);
				}
			} else {
				games.put(nextId,
						new Game(nextId, player1, player2, new Date()));
			}

			// Winner game
		}
		if (gameId.startsWith("W")) {
			// determine the id.
			String tmpNums = gameId.substring(1);
			String[] nums = tmpNums.split("_");

			int column = Integer.parseInt(nums[0]);
			int currGame = Integer.parseInt(nums[1]);
			double dblNextGame = currGame / 2.0;
			int nextGame = (int) Math.ceil(dblNextGame);
			dblNextGame = dblNextGame - Math.floor(dblNextGame);

			String nextId = "W" + (column + 1) + "_" + nextGame;
			String player1 = dblNextGame < 0.5 ? "" : gameResult.getWinner();
			String player2 = !(dblNextGame < 0.5) ? "" : gameResult.getWinner();
			;

			if (games.containsKey(nextId)) {
				if ("".equals(games.get(nextId).getPlayer1())) {
					games.get(nextId).setPlayer1(player1);
				} else {
					games.get(nextId).setPlayer2(player2);
				}
			} else {
				games.put(nextId, new Game(nextId, player1, player2, new Date()));
			}
		}
	}
}
