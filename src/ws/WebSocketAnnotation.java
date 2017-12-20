package ws;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/ws")
public class WebSocketAnnotation {
  private Session session;
  private static final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();

  public WebSocketAnnotation() { }

  @OnOpen
  public void start(Session session) {
    this.session = session;
    sessions.add(session);
  }

  @OnClose
  public void end() {
    try {
      this.session.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // user da login -> login:username-password
  // user da logout -> logout:username-password
  @OnMessage
  public void receiveMessage(String message) {
    // one should never trust the client, and sensitive HTML
    // characters should be replaced with &lt; &gt; &quot; &amp;
    Map<String, String> messageValues = parseMessage(message);
    if (messageValues.get("operation").equals("login")) {
      sendMessage("User logged in " + messageValues.get("username"));
    } else if (messageValues.get("operation").equals("logout")) {
      sendMessage("User " + messageValues.get("username") + " logged out");
    }
  }

  @OnError
  public void handleError(Throwable t) {
    t.printStackTrace();
  }

  private void sendMessage(String text) {
    // uses *this* object's session to call sendText()
    try {
      for (Session session : sessions) {
        session.getBasicRemote().sendText(text);
      }
    } catch (IOException e) {
      // clean up once the WebSocket connection is closed
      try {
        for (Session session : sessions) {
          this.session.close();
        }
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  private Map<String, String> parseMessage(String message) {
    System.out.println("....message....");
    System.out.println(message);
    System.out.println("....message....");
    Map<String, String> messageValues = new HashMap<>();
    String [] cleanMessage = message.split("[:-]");
    messageValues.put("operation", cleanMessage[0]);
    messageValues.put("username", cleanMessage[1]);
    messageValues.put("password", cleanMessage[2]);

    return messageValues;
  }
}

