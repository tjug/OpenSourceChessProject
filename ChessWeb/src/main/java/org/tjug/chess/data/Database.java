package org.tjug.chess.data;

import java.sql.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public enum Database {
	instance;

	private static EntityManagerFactory factory = null;
	public static Connection conn;
	
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}

	public void initialize(String path) {
		System.setProperty("derby.system.home", path);
		try{
			factory = Persistence.createEntityManagerFactory("chessWeb");
		}catch (Throwable th){
			th.printStackTrace();
		}
	}

	public void shutdown(){
		if (factory.isOpen()){
			factory.close();
		}
	}
}
