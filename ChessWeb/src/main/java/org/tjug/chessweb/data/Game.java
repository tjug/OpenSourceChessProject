package org.tjug.chess.data;
//
//  This class represents a Game Data Object.
//
//  The XmlRootEmelemt annotation activates for JAXB (Java XML Binding).
//    This allows for automatic conversion of the DAO to and from 
//    both JSON (JavaScript Object Notation) and XML.
//

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class Game {
	private String opponent1;
	private String opponent2;
	private Date startTime;
	
	public Game(){}
	
	public Game(String opponent1, String opponent2, Date startTime){
		setOpponent1(opponent1);
		setOpponent2(opponent2);
		setStartTime(startTime);
	}
	
	public void setOpponent1(String opponent1) {
		this.opponent1 = opponent1;
	}
	public String getOpponent1() {
		return opponent1;
	}
	public void setOpponent2(String opponent2) {
		this.opponent2 = opponent2;
	}
	public String getOpponent2() {
		return opponent2;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStartTime() {
		return startTime;
	}
}
