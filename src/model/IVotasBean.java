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
import java.util.concurrent.TimeUnit;

public class IVotasBean {
	private RMIServerInterface rmiServer;
	private String username = null; // username and password supplied by the user
	private String password = null;
  private static final String FILENAME = "~/ivotas/src/config.txt";
  private String ip;
  private int port;

  public IVotasBean() {

      try {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/config.txt")));
        String sCurrentLine;
        String line;

        while ((line = br.readLine()) != null) {
          String[] info = line.split("=");
          switch (info[0]) {
            case "main-IP":
              this.ip = info[1];
              break;
            case "main-port":
              this.port = Integer.parseInt(info[1]);
              break;
          }
        }
      } catch (IOException e) {
        System.out.println("File reading exception");
      }
    try {
      rmiServer = (RMIServerInterface) LocateRegistry.getRegistry(ip, port).lookup("ivotas");
		} catch(NotBoundException|RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
      System.out.println("Exception connecting to rmi");
    }
	}

  private RMIServerInterface connectRMIInterface() {
    RMIServerInterface rmi = null;
    boolean passed = false;

    while (true) {
      try {
        // primeiro arg é o ip
        rmi = (RMIServerInterface) LocateRegistry.getRegistry(this.getIp(), this.getPort()).lookup("ivotas");
        //r.addAdmin(a);
        rmi.remote_print("New client");
        passed = true;
      } catch (Exception e) {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException es) {
          System.out.println("Error sleep: " + es.getMessage());
        }
      }

      if (passed) {
        break;
      }
    }

    return rmi;
  }

  public int createUser(String name, String password, String departmentName, String facultyName, String contact, String address, String cc, String expireDate, int type) {
    int createUser = 0;

    try {
      createUser = rmiServer.createUser(name, password, departmentName, facultyName, contact, address, cc, expireDate, type);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return createUser;
  }

  public int createElection(String name, String description, long startDate, long endDate, int type) {
    int createElection = 0;

    try {
      createElection = rmiServer.createElection(name, description, startDate, endDate, type);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return createElection;
  }

  public int createVotingTable(String electionName, String departmentName) {
    int createVotingTable = 0;

    try {
      createVotingTable = rmiServer.createVotingTable(electionName, departmentName);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    System.out.println(createVotingTable);
    return createVotingTable;
  }


  public ArrayList<User> getAllUsers(){
    System.out.println("Was called");
    ArrayList<User> users = new ArrayList<>();

    try {
      System.out.println("didnt pass");
      users = rmiServer.getAllUsers();
      System.out.println("pass");
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return users;
	}

  public ArrayList<String> getAllElections() {
    ArrayList<String> validElections = new ArrayList<>();

    try {
      validElections = rmiServer.getValidElections(this.username);
    } catch (RemoteException e) {
       this.rmiServer = this.connectRMIInterface();
    }

    return validElections;
  }

  public ArrayList<CandidateList> getAllCandidateLists(String election) {
    ArrayList<CandidateList> candidateLists = new ArrayList<>();

    try {
      candidateLists = rmiServer.getAllCandidateLists();
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return candidateLists;
  }

	public boolean authenticateUser() {
    boolean authUser = false;

    try {
      authUser = rmiServer.authenticateUser(this.username, this.password);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return authUser;
  }

  public User getUserByName(String username) {
    User user = null;

    try {
      user = rmiServer.getUserByName(username);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return user;
  }

  public User getUserByFacebookID(String facebookId) {
    User user = null;

    try {
      user = rmiServer.getUserByFacebookID(facebookId);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return user;
  }

  public void connectFacebookWithUser(String username, String facebookID, String accessToken) {
    try {
      rmiServer.connectFacebookWithUser(username, facebookID, accessToken);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }
  }

  public Election getElectionByName(String electionName) {
    Election election = null;

    try {
      election = rmiServer.getElectionByName(electionName);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return election;
  }

  public CandidateList getCandidateListByName(String candidateListName) {
    CandidateList candidateList = null;

    try {
       candidateList = rmiServer.getCandidateListByName(candidateListName);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return candidateList;
  }

  public boolean vote(User user, Election election, CandidateList candidateList) {
    try {
      if (!rmiServer.webVoteIsValid(user, election, candidateList)) {
        return false;
      }

      rmiServer.webVote(user, election, candidateList);
    } catch (RemoteException e) {
      this.rmiServer = this.connectRMIInterface();
    }

    return true;
  }
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

  public int getPort() {
    return port;
  }
  public void setPort(int port) {
    this.port = port;
  }

  public String getIp() {
    return ip;
  }
  public void setIp(String ip) {
    this.ip = ip;
  }

}
