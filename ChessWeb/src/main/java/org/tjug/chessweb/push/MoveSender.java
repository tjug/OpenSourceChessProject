package org.tjug.chessweb.push;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletResponse;

public class MoveSender implements Runnable{

	static private MoveSender sender;
	static public MoveSender getInstance(){
		if (null==sender) sender = new MoveSender();
		
		return sender;
	}
	
	protected boolean running = true;
	protected final List<String> messages = new ArrayList<String>();
	private Set<ServletResponse> connections = new HashSet<ServletResponse>();
	private boolean newMove = false;
	
	void addConnection(ServletResponse connection){
		synchronized (connections) {
			if (!connections.contains(connection)){
				System.out.println("Adding connection." + connection + "\n count " + connections.size());
				connections.add(connection);
			}
		}
	}
	
	void removeConnection(ServletResponse connection){
	    if (connections.contains(connection)){
			System.out.println("Removing connection." + connection);
	    	connections.remove(connection);
	    }
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
		          for (ServletResponse connection: connections){
			          writer = connection.getWriter();
			          writer.println(sb.toString());
		          }
				  connections.clear();
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
