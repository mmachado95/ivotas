package action;

import Data.Election;
import Data.ElectionResult;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import java.util.Map;


public class DetailsOfPastElectionsAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private String election;
  private Election electionObject;
  private ElectionResult details;


  public ElectionResult getDetails() {
    return details;
  }

  public void setDetails(ElectionResult details) {
    this.details = details;
  }

  @Override
  public String execute() throws ParseException {
    electionObject = this.getIVotasBean().getElectionByName(election);
    details = this.getIVotasBean().detailsOfPastElections(election);
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
}
