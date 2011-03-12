package org.tjug.chess.data;

//
//  This class is the single point of entry for the 
//    application to retrieve data objects and collections
//    of data objects.
//
//  The enum declared in this manner operates similar
//    to a singleton design pattern, with less code.
//
//     example of usage:
//       List<Tournament> list = ChessDAO.instance.getTournaments();
//
//  Currently these methods just provide dummy data.
//
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public enum ChessDAO {
	instance;

	public List<Tournament> getTournaments() {
		List<Tournament> list = new ArrayList<Tournament>();
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 1, 8, 10, 0, 0);
		String place = "Bookstore";

		for (int i = 0; i < 100; i++) {
			cal.add(Calendar.DATE, 7);

			list.add(new Tournament("One " + i, cal.getTime(), place));

			if ("Bookstore".equals(place)) {
				place = "Library";
			} else {
				place = "Bookstore";
			}
		}

		return list;
	}

	public List<Game> getGames(String tournamentId) {
		List<Game> list = new ArrayList<Game>();
		Calendar time = Calendar.getInstance();
		time.set(2011, 1, 8, 10, 0, 0);

		for (int i = 0; i < 10; i++) {
			time.add(Calendar.MINUTE, 30);
			list.add(new Game("P_1", "player " + i, "player 1" + i, time
					.getTime()));
		}

		return list;
	}

	public Player getPlayer(String playerId) {
		if ("player 1".equals(playerId)) {
			return new Player("Andrew", "5", "1", "5");
		} else {
			return new Player("Jim", "1", "4", "5");
		}
	}

	static int i = 0;
	static String[] players = { "Andrew", "Wesley", "Jessica", "Joseph", "Mel", "Mike", "Abby", "Chris",
		                        "Jeff", "Ryan", "Shawn", "Morgan", "Laurie", "Bonnie", "Colleen", "Mary"};
	static Tournament tournament = null;

	public Tournament getTournament(String tournamentId) {

		if (ChessDAO.tournament == null) {
			initTournamentDemo();
		}
		
		Tournament tourney = tournament;
		
		if (2 == i) tourney.registerGameResult(new GameResult("P2", "Jessica", "Joseph"));
		if (3 == i) tourney.registerGameResult(new GameResult("P4", "Chris", "Abby"));
		if (2 == i) tourney.registerGameResult(new GameResult("P1", "Wesley", "Andrew"));
		if (4 == i) tourney.registerGameResult(new GameResult("P3", "Mel", "Mike"));
		
		if (2 == i) tourney.registerGameResult(new GameResult("P6", "Shawn", "Morgan"));
		if (3 == i) tourney.registerGameResult(new GameResult("P7", "Laurie", "Bonnie"));
		if (2 == i) tourney.registerGameResult(new GameResult("P8", "Colleen", "Mary"));
		if (4 == i) tourney.registerGameResult(new GameResult("P5", "Jeff", "Ryan"));
		
		if (6 == i) tourney.registerGameResult(new GameResult("C1_1", "Joseph", "Andrew"));
		if (7 == i) tourney.registerGameResult(new GameResult("C1_2", "Abby", "Mike"));
		if (6 == i) tourney.registerGameResult(new GameResult("C1_3", "Ryan", "Morgan"));
		if (7 == i) tourney.registerGameResult(new GameResult("C1_4", "Bonnie", "Marry"));

		if (7 == i) tourney.registerGameResult(new GameResult("W1_2", "Mel", "Chris"));
		if (8 == i) tourney.registerGameResult(new GameResult("W1_1", "Jessica", "Wesley"));
		if (7 == i) tourney.registerGameResult(new GameResult("W1_4", "Colleen", "Laurie"));
		if (8 == i) tourney.registerGameResult(new GameResult("W1_3", "Jeff", "Shawn"));
		
		if (9 == i) tourney.registerGameResult(new GameResult("W2_1", "Jessica", "Mel"));
		if (10 == i) tourney.registerGameResult(new GameResult("W2_2", "Colleen", "Jeff"));
		
		if (10 == i) tourney.registerGameResult(new GameResult("C2_1", "Joseph", "Mike"));
		if (9 == i) tourney.registerGameResult(new GameResult("C2_2", "Bonnie", "Ryan"));
		
		if (12 == i) tourney.registerGameResult(new GameResult("C3_1", "Joseph", "Bonnie"));
		if (12 == i) tourney.registerGameResult(new GameResult("W3_1", "Jessica", "Colleen"));
		
		if (i > 15){
			i = 0;
			ChessDAO.tournament = null;
			initTournamentDemo();
		}

		i++;

		return tournament;
	}
	
	private void initTournamentDemo(){
		tournament = new Tournament(new Date(), "bookstore");
		Map<String, Game> games = new HashMap<String, Game>();

		Game game = null;
		int gameNumber = 1;

		for (int i = 0; i < players.length; i += 2) {
			String gameId = "P" + Integer.toString(gameNumber++);
			game = new Game(gameId, players[i], players[i+1], new Date());
			games.put(gameId, game);
		}
		tournament.setGames(games);
		
	}
}
