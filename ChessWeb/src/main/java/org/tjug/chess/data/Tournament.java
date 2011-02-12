package org.tjug.chess.data;
//
//  This class represents a Tournament Data Object.
//
//  The XmlRootEmelemt annotation activates for JAXB (Java XML Binding). 
//    This allows for automatic conversion of the DAO to and from 
//    both JSON (JavaScript Object Notation) and XML.
//

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement 
public class Tournament {

	public Tournament() {
	}

	public Tournament(String game, Date date, String place) {
		setGame(game);
		setDate(date);
		setTime(time);
		setPlace(place);
	}

	private String game;
	private Date date;
	private String time;
	private String place;

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

}
