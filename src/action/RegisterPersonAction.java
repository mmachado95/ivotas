package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.lang3.StringUtils;


import java.rmi.RemoteException;
import java.util.Map;

public class RegisterPersonAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private String username = null;
  private String password = null;
  private String contact = null;
  private String address = null;
  private String cc = null;
  private String expireDate = null;
  private String departmentName = null;
  private String facultyName = null;
  private String type = null;
  private String isAdmin = null;

  private boolean fieldsNotNull() {
    if (username == null &&
            password == null &&
            contact == null &&
            address == null &&
            cc == null &&
            expireDate == null &&
            facultyName == null &&
            type == null &&
            isAdmin == null) {
      return false;
    }

    return true;
  }

  @Override
  public String execute() {
    if (fieldsNotNull()) {
      try {
        int pass = 1;
        if (username.equals("")) {
          addActionError("Invalid username Field");
          pass = 0;
        }
        if (password.equals("")) {
          addActionError("Invalid password Field");
          pass = 0;
        }
        if (contact.equals("")) {
          addActionError("Invalid contact Field");
          pass = 0;
        }
        if (address.equals("")) {
          addActionError("Invalid address Field");
          pass = 0;
        }
        if (cc.equals("")) {
          addActionError("Invalid c Field");
          pass = 0;
        }
        if (expireDate.equals("")) {
          addActionError("Invalid expire date Field");
          pass = 0;
        }
        if (facultyName.equals("")) {
          addActionError("Invalid faculty Field");
          pass = 0;
        }
        if (!StringUtils.isNumeric(type) || Integer.parseInt(type) <= 0 || Integer.parseInt(type) > 3) {
          addActionError("Invalid type Field");
          pass = 0;
        }
        if (!isAdmin.equals("true") && !isAdmin.equals("false")) {
          addActionError("Invalid admin Field");
          pass = 0;
        }
        if (pass == 0) {
          return INPUT;
        }
        int createUser = this.getIVotasBean().createUser(username, password, departmentName, facultyName, contact, address, cc, expireDate, Integer.parseInt(type), Boolean.parseBoolean(isAdmin));
        if (createUser == 1) {
          return SUCCESS;
        }
        else if (createUser == 2) {
          addActionError("There isn't a department with that name");
        }
        else {
          addActionError("There isn't a faculty with that name");
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

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getContact() {
    return contact;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress() {
    return address;
  }

  public void setCc(String cc) {
    this.cc = cc;
  }

  public String getCc() {
    return cc;
  }

  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }

  public String getExpireDate() {
    return expireDate;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setFacultyName(String facultyName) {
    this.facultyName = facultyName;
  }

  public String getFacultyName() {
    return facultyName;
  }

  public void setType(String type) { this.type = type; }

  public String getType() {
    return type;
  }
}
