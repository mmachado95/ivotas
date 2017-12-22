package ws;

import java.rmi.Remote;

public interface WebSocketInterface extends Remote {
  void print_on_client(String s) throws java.rmi.RemoteException;
}
