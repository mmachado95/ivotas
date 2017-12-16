package action;

import Data.CandidateList;
import Data.Election;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Map;

public class ElectionAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 125L;
  private Map<String, Object> session;
  private String electionName = null;
  private Election election = null;

  @Override
  public String execute() {
    this.setElection(this.getIVotasBean().getElectionByName(electionName));
    System.out.println(election.getCandidateLists());

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

  public Election getElection() { return election; }
  public void setElection(Election election) { this.election = election; }
}
