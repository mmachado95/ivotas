package action;

import Data.Election;
import Data.User;
import Data.Vote;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import model.IVotasBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListElectionsAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private Election electionObject;
  private String election;
  private ArrayList <Map<String, Object>> details;


  @Override
  public String execute() {
    if (session.get("adminUsername") == null || session.get("adminPassword") == null || session.get("adminLoggedin") == null) {
      return LOGIN;
    }
    setElectionObject(this.getIVotasBean().getElectionByName(election));

    details = new ArrayList<>();


    ArrayList<User> users = this.getIVotasBean().getAllUsers();

    for (int i = 0; i < users.size(); i++) {
      ArrayList<Vote> votes = this.getIVotasBean().getVotesOfUser(users.get(i));

      for (int j = 0; j < votes.size(); j++) {
        if (votes.get(j).getElection().getName().equals(electionObject.getName())) {
          Map<String, Object> info = new HashMap<>();

          info.put("name", users.get(i).getName());

          info.put("type", users.get(i).getType());

          if (votes.get(j).getDepartment() == null)
            info.put("place", "web");
          else
            info.put("place", "mesa de voto");
          details.add(info);
        }
      }
    }
    
    setDetails(details);

    return SUCCESS;
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


  public void setElection(String election) {
    this.election = election;
  }

  public String getElection() {
    return election;
  }

  public Election getElectionObject() {
    return electionObject;
  }

  public void setElectionObject(Election electionObject) {
    this.electionObject = electionObject;
  }

  public ArrayList<Map<String, Object>> getDetails() {
    return details;
  }

  public void setDetails(ArrayList<Map<String, Object>> details) {
    this.details = details;
  }
}