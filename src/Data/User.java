package Data;
import java.io.Serializable;


public class User implements Serializable {
  private String name;
  private String password;
  private Department department;
  private Faculty faculty;
  private String contact;
  private String address;
  private String cc;
  private String expireDate;
  private int type;
  private String facebookID;
  private String facebookName;
  private String facebookAccessToken;

  public User() {}

  public User(String name, String password, Department department, Faculty faculty, String contact, String address, String cc, String expireDate, int type) {
    this.name = name;
    this.password = password;
    this.department = department;
    this.faculty = faculty;
    this.contact = contact;
    this.address = address;
    this.cc = cc;
    this.expireDate = expireDate;
    this.type = type;
    this.facebookID = null;
    this.facebookName = null;
    this.facebookAccessToken = null;
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getPassword() { return password; }
  public void setPassword(String password) { this.password = password; }

  public Department getDepartment() { return department; }
  public void setDepartment(Department department) { this.department = department; }

  public Faculty getFaculty() { return faculty; }
  public void setFaculty(Faculty faculty) { this.faculty = faculty; }

  public String getContact() { return contact; }
  public void setContact(String contact) { this.contact = contact; }

  public String getAddress() { return address; }
  public void setAddress(String address) { this.address = address; }

  public String getCc() { return cc; }
  public void setCc(String cc) { this.cc = cc; }

  public String getExpireDate() { return expireDate; }
  public void setExpireDate(String expireDate) { this.expireDate = expireDate; }

  public int getType() { return type; }
  public void setType(int type) { this.type = type; }

  public String getFacebookID() {
    return facebookID;
  }
  public void setFacebookID(String facebookID) {
    this.facebookID = facebookID;
  }

  public String getFacebookName() {
    return facebookName;
  }
  public void setFacebookName(String facebookName) {
    this.facebookName = facebookName;
  }

  public String getFacebookAccessToken() {
    return facebookAccessToken;
  }
  public void setFacebookAccessToken(String facebookAccessToken) {
    this.facebookAccessToken = facebookAccessToken;
  }

  public String prettyPrint() {
    String res = "Name: " + this.name + "\nDepartment: " + this.department.getName() + "\nFaculty: " + this.faculty.getName()
            + "\nContact: " + this.contact + "\ncc: " + this.cc + "\nExpire Date: " + this.expireDate;
    if (this.type == 1)
      return "\n\nSTUDENT\n" + res;
    else if (this.type == 2)
      return "\n\nTEACHER\n" + res;
    return "\n\nSTAFF\n" + res;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", department=" + department +
            ", faculty=" + faculty +
            ", contact='" + contact + '\'' +
            ", address='" + address + '\'' +
            ", cc='" + cc + '\'' +
            ", expireDate='" + expireDate + '\'' +
            ", type=" + type +
            '}';
  }
}