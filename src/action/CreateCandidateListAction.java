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
  private String name = null;
  private String electionType = null;
  private String type = null;
  private String users = null;
  private ArrayList<Election> elections;

  private boolean fieldsNotNull() {
    if (electionName == null &&
            name == null &&
            electionType == null &&
            type == null &&
            users == null) {
      return false;
    }

    return true;
  }


  @Override
  public String execute() throws ParseException {
    elections = this.getIVotasBean().chooseElectionToChange();

    /*
    System.out.println(electionName);
    System.out.println(name);
    System.out.println(type);
    System.out.println(users); */

    if (fieldsNotNull()) {

      try {
        int pass = 1;

        if (electionName.equals("")) {
          addActionError("Invalid name Field");
          pass = 0;
        }
        if (name.equals("")) {
          addActionError("Invalid description Field");
          pass = 0;
        }
        if (users.equals("")) {
          addActionError("Invalid end date Field");
          pass = 0;
        }
        if (!StringUtils.isNumeric(electionType) || Integer.parseInt(electionType) <= 0 || Integer.parseInt(electionType) > 2) {
          addActionError("Invalid election type Field");
          pass = 0;
        }
        if (!StringUtils.isNumeric(type) || Integer.parseInt(type) <= 0 || Integer.parseInt(type) > 3) {
          addActionError("Invalid type Field");
          pass = 0;
        }
        if (pass == 0) {
          return INPUT;
        }

        ArrayList<User> usersArraylist = new ArrayList<>();

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
          if (Integer.parseInt(electionType) == 1 && user.getType() != 1) {
            addActionError("User " + userName + "has to be a student");
            return INPUT;
          }

          // Conselho Geral
          if (Integer.parseInt(electionType) == 2 && user.getType() != Integer.parseInt(type)) {
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

        Election election = this.getIVotasBean().getElectionByName(electionName);

        if (Integer.parseInt(electionType) == 1) {
          this.getIVotasBean().createCandidateList(name, usersArraylist, election);
          System.out.println("Created candidate list nucleo");
        }
        else {
          this.getIVotasBean().createCandidateListCouncil(name, usersArraylist, election, Integer.parseInt(electionType));
          System.out.println("Created candidate list Conselho Geral");
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

  public ArrayList<Election> getElections() {
    return elections;
  }

  public void setElections(ArrayList<Election> elections) {
    this.elections = elections;
  }
}
