package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LogoutAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 123L;
  private Map<String, Object> session;

  @Override
  public String execute() {
    try {
      this.getIVotasBean().logout(session.get("username").toString());
      this.getIVotasBean().setUsername("");
      this.getIVotasBean().setPassword("");
      session.remove("username");
      session.remove("password");
      session.remove("loggedin");

      return SUCCESS;
    } catch (Exception e) {
      System.out.println("Rmi exceptions");
    }
    return ERROR;
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
