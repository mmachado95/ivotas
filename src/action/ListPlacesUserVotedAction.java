package action;

import Data.Election;
import Data.User;
import Data.Vote;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class ListPlacesUserVotedAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private ArrayList<Vote> votes = null;
  public String username = null;

  @Override
  public String execute() {
    if (session.get("adminUsername") == null || session.get("adminPassword") == null || session.get("adminLoggedin") == null) {
      return LOGIN;
    }

    User user = this.getIVotasBean().getUserByName(username);
    votes = this.getIVotasBean().getVotesOfUser(user);
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

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public ArrayList<Vote> getVotes() {
    return votes;
  }
  public void setVotes(ArrayList<Vote> votes) {
    this.votes = votes;
  }

  public Map<String, Object> getSession() {
    return session;
  }
}