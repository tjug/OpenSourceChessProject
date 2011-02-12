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
import java.util.List;
import java.util.ArrayList;

public enum ChessDAO {
	instance;

	public List<Tournament> getTournaments() {
		List<Tournament> list = new ArrayList<Tournament>();
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 1, 8, 10, 0, 0);
		String place = "Bookstore";
		
		for (int i = 0; i < 100; i++) {
			cal.add(Calendar.DATE, 7);
			
			list.add(new Tournament("One "+ i, cal.getTime(), place));
			
			if ("Bookstore".equals(place)){
				place = "Library";
			}else{
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
			list.add(new Game("opponent "+i, "opponent 1"+i, time.getTime()));
		}

		return list;
	}
	
	public Player getPlayer(String playerId){
		if ("opponent 1".equals(playerId)){
			return new Player("Andrew", "5", "1", "5");
		}else{
			return new Player("Jim", "1", "4", "5");
		}
	}
	
}
