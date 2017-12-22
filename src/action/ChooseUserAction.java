package action;

import Data.Election;
import Data.User;
import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Map;

public class ChooseUserAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private ArrayList<User> users;

  @Override
  public String execute() {
    if (session.get("adminUsername") == null || session.get("adminPassword") == null || session.get("adminLoggedin") == null) {
      return LOGIN;
    }
    users = this.getIVotasBean().getAllUsers();
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

  public ArrayList<User> getUsers() {
    return users;
  }
  public void setUsers(ArrayList<User> users) {
    this.users = users;
  }

  public Map<String, Object> getSession() {
    return session;
  }
}