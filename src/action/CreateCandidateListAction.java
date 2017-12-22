package action;

import Data.Election;
import Data.User;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Map;


public class CreateCandidateListAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private String electionName = null;
  public int electionType;
  private String name = null;
  private String type = null;
  private String users = null;
  private String election;

  private boolean fieldsNotNull() {
    if (name == null &&
            type == null &&
            users == null) {
      return false;
    }

    return true;
  }


  @Override
  public String execute() throws ParseException {
    if (session.get("adminUsername") == null || session.get("adminPassword") == null || session.get("adminLoggedin") == null) {
      return LOGIN;
    }

    if (electionName != null) {
      Election election = this.getIVotasBean().getElectionByName(electionName);
      System.out.println(election);
      session.put("candidateListElectionName", electionName);
      session.put("electionType", election.getType());
      this.setElectionType(election.getType());
      this.setElectionName(electionName);
    }
    else {
      this.setElectionName(session.get("candidateListElectionName").toString());
      this.setElectionType((int) session.get("electionType"));
    }

    if (fieldsNotNull()) {

      try {
        int pass = 1;

        if (name.equals("")) {
          addActionError("Invalid description Field");
          pass = 0;
        }
        if (users.equals("")) {
          addActionError("Can't create election without users");
          pass = 0;
        }
        if (electionType == 2) {
          if (!StringUtils.isNumeric(type) || Integer.parseInt(type) <= 0 || Integer.parseInt(type) > 3) {
            addActionError("Invalid type Field");
            pass = 0;
          }
        }
        if (pass == 0) {
          return INPUT;
        }


        ArrayList<User> usersArraylist = new ArrayList<>();

        Election election = this.getIVotasBean().getElectionByName(session.get("candidateListElectionName").toString());

        String[] usersArray = users.split(",");
        for (String userName : usersArray) {

          System.out.println(userName);
          User user = this.getIVotasBean().getUserByName(userName);
          System.out.println(user);
          if (user == null) {
            addActionError("There isn't a user with the name " + userName);
            return INPUT;
          }

          // Nucleo
          if (election.getType() == 1 && user.getType() != 1) {
            addActionError("User " + userName + " has to be a student");
            return INPUT;
          }

          // Nucleo
          if (election.getType() == 1 && !user.getDepartment().getName().equals(election.getDepartment().getName())) {
            addActionError("User " + userName + " has to be in the department " + election.getDepartment());
            return INPUT;
          }

          // Conselho Geral
          if (election.getType() == 2 && user.getType() != Integer.parseInt(type)) {
            addActionError("User " + userName + "has to be of type" + type);
            return INPUT;
          }
          else {
            usersArraylist.add(user);
          }
        }

        if (usersArraylist.size() == 0) {
          addActionError("You can't create a Candidate List without users.");
          return INPUT;
        }

        if (election.getType() == 1) {
          this.getIVotasBean().createCandidateList(name, usersArraylist, election);
          System.out.println("Created candidate list nucleo");
          return SUCCESS;
        }
        else {
          this.getIVotasBean().createCandidateListCouncil(name, usersArraylist, election, election.getType());
          System.out.println("Created candidate list Conselho Geral");
          return SUCCESS;
        }

      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Rmi exceptions");
      }
    }
    return INPUT;
  }

  public IVotasBean getIVotasBean() {
    if(!session.containsKey("iVotasBean")) {
      this.setHeyBean(new IVotasBean());
    }
    return (IVotasBean) session.get("iVotasBean");
  }

  public void setHeyBean(IVotasBean iVotasBean) {
    this.session.put("iVotasBean", iVotasBean);
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }

  public Map<String, Object> getSession() {
    return session;
  }

  public String getElectionName() {
    return electionName;
  }

  public void setElectionName(String electionName) {
    this.electionName = electionName;
  }

  public int getElectionType() {
    return electionType;
  }

  public void setElectionType(int electionType) {
    this.electionType = electionType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getUsers() {
    return users;
  }

  public void setUsers(String users) {
    this.users = users;
  }

  public void setElection(String election) {
    this.election = election;
  }

  public String getElection() {
    return election;
  }

}
