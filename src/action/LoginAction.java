/**
 * Raul Barbosa 2014-11-07
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.interceptor.SessionAware;

import java.rmi.RemoteException;
import java.util.Map;

public class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 4L;
	private Map<String, Object> session;
	private String username = null, password = null;

	@Override
	public String execute() {
    //TODO any username is accepted without confirmation (should check using RMI)
    if(this.username != null && !username.equals("") && this.password != null && !password.equals("")) {
      try {
        this.getIVotasBean().setUsername(this.username);
        this.getIVotasBean().setPassword(this.password);

        if (this.getIVotasBean().authenticateUser()) {
          session.put("username", username);
          session.put("password", password);
          session.put("loggedin", true); // this marks the user as logged in
					System.out.println("oooooiii");
					return SUCCESS;
        }
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Rmi exceptions");
      }
		}
    return LOGIN;
	}
	
	public void setUsername(String username) {
		this.username = username; // will you sanitize this input? maybe use a prepared statement?
	}

	public void setPassword(String password) {
		this.password = password; // what about this input? 
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
