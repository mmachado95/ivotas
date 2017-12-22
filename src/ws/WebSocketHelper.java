package ws;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WebSocketHelper extends UnicastRemoteObject implements WebSocketInterface{
  private WebSocketAnnotation webSocketAnnotation;

  public WebSocketHelper(WebSocketAnnotation w) throws RemoteException {
    super();
    this.webSocketAnnotation = w;
  }

  public void print_on_client(String m) throws RemoteException {
    try {
      this.webSocketAnnotation.print_on_client(m);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
