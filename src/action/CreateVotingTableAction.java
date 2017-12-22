package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.lang3.StringUtils;


import java.rmi.RemoteException;
import java.util.Map;

public class CreateVotingTableAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private String electionName;
  private String departmentName;


  private boolean fieldsNotNull() {
    if (electionName == null &&
            departmentName == null) {
      return false;
    }

    return true;
  }

  @Override
  public String execute() {
    if (session.get("adminUsername") == null || session.get("adminPassword") == null || session.get("adminLoggedin") == null) {
      return LOGIN;
    }

    if (fieldsNotNull()) {
      try {
        int pass = 1;
        if (electionName.equals("")) {
          addActionError("Invalid election Field");
          pass = 0;
        }
        if (departmentName.equals("")) {
          addActionError("Invalid department Field");
          pass = 0;
        }

        if (pass == 0) {
          return INPUT;
        }
        int createVotingTable = this.getIVotasBean().createVotingTable(electionName, departmentName);
        if (createVotingTable == 1) {
          return SUCCESS;
        }
        else if (createVotingTable == 2) {
          addActionError("There isn't an election with that name");
        }
        else {
          addActionError("There isn't a department with that name");
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Rmi exceptions");
      }
    }
    return INPUT;
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

  public void setElectionName(String electionName) {
    this.electionName = electionName;
  }

  public String getElectionName() {
    return electionName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getDepartmentName() {
    return departmentName;
  }
}
