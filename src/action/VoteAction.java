package action;

import Data.CandidateList;
import Data.Election;
import Data.User;
import action.facebook.Post;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class VoteAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 128L;
  private Map<String, Object> session;
  private String electionName = null;
  private String listName = null;
  private Election election = null;
  private ArrayList<CandidateList> candidateLists = null;

  @Override
  public String execute() {
    this.setElectionName(electionName);
    election = this.getIVotasBean().getElectionByName(electionName);
    candidateLists = election.getCandidateLists();

    return SUCCESS;
  }

  public String createVote() {
    String username = (String) this.session.get("username");
    CandidateList candidateList;
    if (listName.equals("blank")) {
      candidateList = null;
    } else {
      candidateList = this.getIVotasBean().getCandidateListByName(listName);
    }
    User user = this.getIVotasBean().getUserByName(username);
    Election election = this.getIVotasBean().getElectionByName(electionName);

    boolean isValid = this.getIVotasBean().vote(user, election, candidateList);

    if (!isValid) {
      addActionError("You already voted in this election");
      return ERROR;
    }

    addActionMessage("Voted successfully");

    if (user.getFacebookID() != null && user.getFacebookAccessToken() != null) {
      Post post = new Post();
      String message = "I+just+voted+for+" + candidateList.getName().replace(" ", "+") + "+at+" + electionName.replace(" ", "+");
      post.votePost(user.getFacebookID(), user.getFacebookAccessToken(), message);
    }
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

  public String getListName() { return listName; }
  public void setListName(String listName) { this.listName = listName; }

  public ArrayList<CandidateList> getCandidateLists() { return candidateLists; }
  public void setCandidateLists(ArrayList<CandidateList> candidateLists) { this.candidateLists = candidateLists; }
}