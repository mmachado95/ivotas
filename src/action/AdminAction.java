package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class AdminAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private Data.Election election = null;

  @Override
  public String execute() {
    //this.setElection(this.getIVotasBean().getElectionByName(electionName));
    //System.out.println(election);

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

  public Data.Election getElection() { return election; }
  public void setElection(Data.Election election) { this.election = election; }
}
