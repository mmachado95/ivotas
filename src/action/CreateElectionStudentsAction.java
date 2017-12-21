package action;

import com.opensymphony.xwork2.ActionSupport;
import model.IVotasBean;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import java.util.Map;


public class CreateElectionStudentsAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private String name = null;
  private String description = null;
  private String department = null;
  private String startDate = null;
  private String endDate = null;

  private boolean fieldsNotNull() {
    if (name == null &&
            department == null &&
            description == null &&
            startDate == null &&
            endDate == null) {
      return false;
    }

    return true;
  }

  // TODO - check regex
  public boolean checkString(String arg) {
    return Pattern.compile("^(?=\\d)(?:(?:31(?!.(?:0?[2469]|11))|(?:30|29)(?!.0?2)|29(?=.0?2.(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00)))(?:\\x20|$))|(?:2[0-8]|1\\d|0?[1-9]))([-./])(?:1[012]|0?[1-9])\\1(?:1[6-9]|[2-9]\\d)?\\d\\d(?:(?=\\x20\\d)\\x20|$))?(((0?[1-9]|1[012])(:[0-5]\\d){0,2}(\\x20[AP]M))|([01]\\d|2[0-3])(:[0-5]\\d){1,2})?$").matcher(arg).find();
  }

  @Override
  public String execute() throws ParseException {
    if (fieldsNotNull()) {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

      try {
        int pass = 1;

        if (name.equals("")) {
          addActionError("Invalid name Field");
          pass = 0;
        }
        if (description.equals("")) {
          addActionError("Invalid description Field");
          pass = 0;
        }
        if (department.equals("")) {
          addActionError("Invalid department Field");
          pass = 0;
        }
        if (!checkString(startDate)) {
          addActionError("Invalid start date Field");
          pass = 0;
        }
        if (!checkString(endDate)) {
          addActionError("Invalid end date Field");
          pass = 0;
        }
        if (pass == 0) {
          return INPUT;
        }

        long startDateLong = simpleDateFormat.parse(startDate).getTime();
        long endDateLong = simpleDateFormat.parse(endDate).getTime();

        int createElection = this.getIVotasBean().createElectionStudents(name, description, startDateLong, endDateLong, 1, department);

        if (createElection == 1) {
          return SUCCESS;
        }
        else if (createElection == 2) {
          addActionError("There isn't a department with that name");
        }
        else if (createElection == 3) {
          addActionError("Start date has to be before end date");
        }
        else if (createElection == 4) {
          addActionError("You can't create an election in the past");
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

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getStartDate() {
    return startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getEndDate() {
    return endDate;
  }
}
