package org.tjug.chessweb.push;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.comet.CometEvent;
import org.apache.catalina.comet.CometProcessor;

/**
 * Servlet implementation class Plays
 */
@WebServlet("/Moves")
public class Moves extends HttpServlet implements CometProcessor {
	private static final long serialVersionUID = 1L;

	private MoveSender sender;

	@Override
	public void destroy() {
		sender.stop();
		sender = null;
	}

	@Override
	public void init() throws ServletException {
		sender = MoveSender.getInstance();
		Thread senderThread = new Thread(sender, "MoveSender[" + getServletContext().getContextPath() + "]");
		senderThread.setDaemon(true);
		senderThread.start();
	}

	@Override
	public void event(final CometEvent event) throws IOException, ServletException {
        HttpServletRequest request = event.getHttpServletRequest();
        HttpServletResponse response = event.getHttpServletResponse();

          if (event.getEventType() == CometEvent.EventType.BEGIN) {
        	  event.setTimeout(60*60*1000);
        	  String html = request.getParameter("html");
        	  if("on".equals(html)){
	        	  sender.addConnection(response);
        	  }
          } else if (event.getEventType() == CometEvent.EventType.END) {
              log("End for session: " + request.getSession(true).getId());
        	  sender.removeConnection(response);
              
              event.close();
        } else if (event.getEventType() == CometEvent.EventType.ERROR) {
 
            log("Error for session: " + request.getSession(true).getId());
       	    sender.removeConnection(response);
            event.close();
            
        } else if (event.getEventType() == CometEvent.EventType.READ) {
            throw new UnsupportedOperationException("This servlet does not accept data");
        }

	}
}
