package action;

import Data.CandidateList;
import Data.Election;
import Data.User;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

import Data.Election;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class VoteAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 127L;
  private Map<String, Object> session;
  private String electionName = null;
  private String candidateListName = null;

  @Override
  public String execute() {
    try {
      User user = this.getIVotasBean().getUserByName((String) session.get("username"));
      Election election = this.getIVotasBean().getElectionByName(electionName);
      CandidateList candidateList = this.getIVotasBean().getCandidateListByName(candidateListName);
      this.getIVotasBean().vote(user, election, candidateList);
    } catch (RemoteException e) {
      System.out.println("Exception retrieving election.");
      return ERROR;
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
}