package action.facebook;

import Data.User;
import action.facebook.Post;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class ShareElectionAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 4231L;
  private Map<String, Object> session;
  public String electionName = null;

  @Override
  public String execute() {
    // get user that wants to share
    String username = (String) session.get("username");
    User user = this.getIVotasBean().getUserByName(username);

    if (user.getFacebookAccessToken() == null || user.getFacebookID() == null) {
      addActionError("A tua conta não está associada ao Facebook");
      return ERROR;
    }

    // Class to call share method
    Post post = new Post();

    // build link to share
    String link = "http://127.0.0.1:8080/election.action?electionName=" + electionName.replace(" ", "+");

    // share election
    post.shareElection(user.getFacebookID(), user.getFacebookAccessToken(), link);

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

  public String getElectionName() {
    return electionName;
  }
  public void setElectionName(String electionName) {
    this.electionName = electionName;
  }

  @Override
  public void setSession(Map<String, Object> session) {
    this.session = session;
  }
}
