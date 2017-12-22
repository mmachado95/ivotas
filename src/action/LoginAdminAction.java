package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAdminAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private Data.Election election = null;
  private String username = null, password = null;

  @Override
  public String execute() {
    if(this.username != null && !username.equals("") && this.password != null && !password.equals("")) {
      try {
        this.getIVotasBean().setUsername(this.username);
        this.getIVotasBean().setPassword(this.password);

        if (this.getIVotasBean().authenticateAdmin()) {
          session.put("adminUsername", username);
          session.put("adminPassword", password);
          session.put("adminLoggedin", true);
          return SUCCESS;
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Rmi exceptions");
      }
    }
    return LOGIN;
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

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
