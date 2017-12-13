/**
 * Raul Barbosa 2014-11-07
 */
package model;

import Data.CandidateList;
import Data.Election;
import Data.User;
import rmiserver.RMIServerInterface;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

public class IVotasBean {
	private RMIServerInterface server;
	private String username; // username and password supplied by the user
	private String password;
  private static final String FILENAME = "~/ivotas/src/config.txt";
  private int mainPort;
  private int backupPort;
  private String mainIP;
  private String backupIP;


  public IVotasBean() {

      try {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/config.txt")));
        String sCurrentLine;

        String line;
        while ((line = br.readLine()) != null) {
          String[] info = line.split("=");
          switch (info[0]) {
            case "main-IP":
              mainIP = info[1];
              break;
            case "backup-IP":
              backupIP = info[1];
              break;
            case "main-port":
              mainPort = Integer.parseInt(info[1]);
              break;
            case "backup-port":
              backupPort = Integer.parseInt(info[1]);
              break;
          }
        }

      } catch (IOException e) {
        System.out.println("Falhou");
      }

    try {
      server = (RMIServerInterface) LocateRegistry.getRegistry(mainIP, mainPort).lookup("ivotas");
		} catch(NotBoundException|RemoteException e) {
      System.out.println("Error connecting to rmi");
    }
	}

	public ArrayList<User> getAllUsers() throws RemoteException {
		return server.getAllUsers();
	}

  public ArrayList<String> getAllElections() throws RemoteException {
    return server.getValidElections(this.username);
  }

  public ArrayList<CandidateList> getAllCandidateLists(String election) throws RemoteException {
    return server.getAllCandidateLists();
  }

	public boolean authenticateUser() throws RemoteException {
    return server.authenticateUser(this.username, this.password);
	}

  public User getUserByName(String username) throws RemoteException {
    return server.getUserByName(username);
  }

  public Election getElectionByName(String electionName) throws RemoteException {
    return server.getElectionByName(electionName);
  }

  public CandidateList getCandidateListByName(String candidateListName) throws RemoteException {
    return server.getCandidateListByName(candidateListName);
  }

  public void vote(User user, Election election, CandidateList candidateList) throws RemoteException {
	  server.vote(user, election, candidateList);
  }
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
