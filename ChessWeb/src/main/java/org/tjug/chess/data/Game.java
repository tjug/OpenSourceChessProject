package org.tjug.chess.data;
//
//  This class represents a Game Data Object.
//
//  The XmlRootEmelemt annotation activates for JAXB (Java XML Binding).
//    This allows for automatic conversion of the DAO to and from 
//    both JSON (JavaScript Object Notation) and XML.
//

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

//@Entity(name="GameEntity")
@XmlRootElement(name="Game")
public class Game {
	private String id;
	private String player1;
	private String player2;
	private String winner;
	private String looser;
	private Date startTime;
	
	public Game(){}
	
	public Game(String id, String player1, String player2, Date startTime){
		setId(id);
		setPlayer1(player1);
		setPlayer2(player2);
		setStartTime(startTime);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}
	public String getPlayer1() {
		return player1;
	}
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}
	public String getPlayer2() {
		return player2;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStartTime() {
		return startTime;
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
