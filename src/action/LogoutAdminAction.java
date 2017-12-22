package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class LogoutAdminAction extends ActionSupport implements SessionAware {
  private static final long serialVersionUID = 123123123L;
  private Map<String, Object> session;

  @Override
  public String execute() {
    try {
      session.remove("adminUsername");
      session.remove("adminPassword");
      session.remove("adminLoggedin");

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