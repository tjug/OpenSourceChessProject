package org.tjug.chess.push;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.ServletResponse;

public class MoveSender implements Runnable{

	static private MoveSender sender;
	static public MoveSender getInstance(){
		if (null==sender) sender = new MoveSender();
		
		return sender;
	}
	
	protected boolean running = true;
	protected final List<String> messages = new ArrayList<String>();
	private ConcurrentMap<Long, ServletResponse> connections = new ConcurrentHashMap<Long, ServletResponse>();
	private boolean newMove = false;
	
	void addConnection(ServletResponse connection){
		synchronized (connections) {
				System.out.println("Adding connection." + connection + "\n count " + connections.size());
				connections.put(Long.valueOf(System.currentTimeMillis()), connection);
		}
	}
	
	void removeConnection(ServletResponse connection){
			System.out.println("Removing connection." + connection);
	    	connections.remove(connection);
	}
	
	public void stop(){
		 running = false;
	 }
	 
	 //  called by the data provider
	 //    run on a thread ....
	 public void send(String gameId, String message) {
		 synchronized (messages) {
	     	messages.add(message);
	     	newMove = true;
	        messages.notify();
	     }
	 }
	    
	 public void run() {
	    while (running) {
	       if (!newMove) {
	          try {
	             synchronized (messages) {
	                messages.wait();
	             }
	          } catch (InterruptedException e) {
	                    // Ignore
	          }
	       }
	       newMove = false;
	       
	       PrintWriter writer = null;
	       try {
	          StringBuffer sb;
	          sb = new StringBuffer();
	          for (String message: messages) {
	             sb.append(message + "<br>");
	          }
	          
			  synchronized(connections){
				  System.out.println("there are "+connections.size() + " connections");
		          for (ServletResponse connection: connections.values()){
			          writer = connection.getWriter();
			          writer.println(sb.toString());
			          connection.flushBuffer();
		          }
			  }
			  
	       } catch (IOException e) {
	    	   throw new RuntimeException(e.getMessage());
	       } finally {
	    	   if (null!=writer){
			      writer.flush();
			      writer.close();
	    	   }
	       }
	    }
	 }
}
