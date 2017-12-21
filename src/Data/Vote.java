package Data;

import java.io.Serializable;

public class Vote implements Serializable {
  private User user;
  private Election election;
  private CandidateList candidateList;
  private Department department;
  private String moment;

  public Vote() {}

  // blank vote
  public Vote(User user, Election election, Department department, String moment) {
    this.user = user;
    this.election = election;
    this.candidateList = null;
    this.department = department;
    this.moment = moment;
  }

  // vote on list
  public Vote(User user, Election election, CandidateList candidateList, Department department, String moment) {
    this.user = user;
    this.election = election;
    this.candidateList = candidateList;
    this.department = department;
    this.moment = moment;
  }

  // vote web
  public Vote(User user, Election election, CandidateList candidateList, String moment) {
    this.user = user;
    this.election = election;
    this.candidateList = candidateList;
    this.moment = moment;
  }

  // vote web blank
  public Vote(User user, Election election, String moment) {
    this.user = user;
    this.election = election;
    this.moment = moment;
  }

  public User getUser() { return user; }
  public void setUser(User user) { this.user = user; }

  public Election getElection() { return election; }
  public void setElection(Election election) { this.election = election; }

  public CandidateList getCandidateList() { return candidateList; }
  public void setCandidateList(CandidateList candidateList) { this.candidateList = candidateList; }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public String getMoment() {
    return moment;
  }
  public void setMoment(String moment) {
    this.moment = moment;
  }

  @Override
  public String toString() {
    return "Vote{" +
            "user=" + user +
            ", election=" + election +
            ", candidateList=" + candidateList +
            ", department=" + department +
            ", moment='" + moment + '\'' +
            '}';
  }
}
