package action;

import Data.CandidateList;
import Data.Election;
import Data.User;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class VoteAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 128L;
  private Map<String, Object> session;
  private String electionName = null;
  private User user = null;
  private Election election = null;
  private ArrayList<CandidateList> candidateLists = null;

  @Override
  public String execute() {
    String username = (String) this.session.get("username");
    this.setUser(this.getIVotasBean().getUserByName(username));
    this.setElection(this.getIVotasBean().getElectionByName(electionName));
    this.setCandidateLists(election.getCandidateLists());
    System.out.println("entrou aqui");
    System.out.println(user);
    System.out.println(candidateLists);

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

  public String getElectionName() { return electionName; }
  public void setElectionName(String electionName) { this.electionName = electionName; }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Election getElection() { return election; }
  public void setElection(Election election) { this.election = election; }

  public ArrayList<CandidateList> getCandidateLists() { return candidateLists; }
  public void setCandidateLists(ArrayList<CandidateList> candidateLists) { this.candidateLists = candidateLists; }
}