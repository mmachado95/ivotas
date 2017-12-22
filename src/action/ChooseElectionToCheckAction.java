package action;

import Data.Election;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class ChooseElectionToCheckAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private ArrayList<Election> elections;
  private String election;

  @Override
  public String execute() {
    if (session.get("adminUsername") == null || session.get("adminPassword") == null || session.get("adminLoggedin") == null) {
      return LOGIN;
    }

    elections = this.getIVotasBean().listElections();
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

  public ArrayList<Election> getElections() {
    return elections;
  }

  public void setElections(ArrayList<Election> elections) {
    this.elections = elections;
  }

  public void setElection(String election) {
    this.election = election;
  }

  public String getElection() {
    return election;
  }
}