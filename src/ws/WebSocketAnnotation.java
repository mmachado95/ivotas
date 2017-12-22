package ws;

import rmiserver.RMIServerInterface;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;


@ServerEndpoint(value = "/ws")
public class WebSocketAnnotation implements WebSocketInterface, Serializable {
  private RMIServerInterface rmi;
  private Session session;
  private static final CopyOnWriteArrayList<Session> sessions = new CopyOnWriteArrayList<>();
  private WebSocketHelper aux;

  @OnOpen
  public void start(Session session) {
    this.session = session;
    sessions.add(session);

    try {
      this.rmi = (RMIServerInterface) LocateRegistry.getRegistry("localhost", 8000).lookup("ivotas");
      aux = new WebSocketHelper(this);
      this.rmi.subscribeWeb(aux);

    } catch (RemoteException e) {
      e.printStackTrace();
    } catch (NotBoundException e) {
      e.printStackTrace();
    }
  }

  @OnClose
  public void end() {
    try {
      this.session.close();
      this.rmi.unsubscribeWeb(aux);
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
    System.out.println("oi");
    System.out.println(text);
    // uses *this* object's session to call sendText()
    try {
      System.out.println(sessions.size());
      for (Session session : sessions) {
        session.getBasicRemote().sendText(text);
      }
    } catch (IOException e) {
      System.out.println(e);
      // clean up once the WebSocket connection is closed
      try {
        for (Session session : sessions) {
          this.session.close();
          this.rmi.unsubscribeWeb(aux);
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

  public void print_on_client(String s) throws RemoteException {
    try {
      session.getBasicRemote().sendText(s);
    } catch (IOException e) {
      System.out.println(e);
      // clean up once the WebSocket connection is closed
      try {
        this.session.close();
        this.rmi.unsubscribeWeb(aux);
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  public RMIServerInterface getRmi() {
    return rmi;
  }

  public void setRmi(RMIServerInterface rmi) {
    this.rmi = rmi;
  }

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }
}

