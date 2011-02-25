package org.tjug.chess.initialize;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartupListener implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		System.out.println("*********************************************************************");
		System.out.println("*                                                                   *");
		System.out.println("*  Shhhhh  -  listen                                                *");
		System.out.println("*                                                                   *");
		System.out.println("*                                                                   *");
		System.out.println("*  This is the initialization method for the Tulsa Java User's      *");
		System.out.println("*  Group open source chess project.  This class is located in       *");
		System.out.println("*                                                                   *");
		System.out.println("*        org.tjug.chess.initialize.StartupListener                  *");
		System.out.println("*                                                                   *");
		System.out.println("*  Any initialization required for the Chess Application can be     *");
		System.out.println("*  performed from here.                                             *");
		System.out.println("*                                                                   *");
		System.out.println("*********************************************************************");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("*********************************************************************");
		System.out.println("*                                                                   *");
		System.out.println("*  Shhhhh  -  listen                                                *");
		System.out.println("*                                                                   *");
		System.out.println("*                                                                   *");
		System.out.println("*  This is the cleanup method for the Tulsa Java User's Group       *");
		System.out.println("*  open source chess project.  This class is located in             *");
		System.out.println("*                                                                   *");
		System.out.println("*        org.tjug.chess.initialize.StartupListener                  *");
		System.out.println("*                                                                   *");
		System.out.println("*  Any cleanup required for the Chess Application can be performed  *");
		System.out.println("*  from here.                                                       *");
		System.out.println("*                                                                   *");
		System.out.println("*********************************************************************");
	}
}
