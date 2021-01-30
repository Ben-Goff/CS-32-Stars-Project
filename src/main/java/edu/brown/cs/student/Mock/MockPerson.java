package edu.brown.cs.student.Mock;

import java.util.ArrayList;

public class MockPerson {

  private String firstName;
  private String lastName;
  private String dateTime;
  private String email;
  private String gender;
  private String address;
  private String chineseName;

  private static ArrayList<MockPerson> mockPeople = new ArrayList<>();

  public static void resetMockPeople() {
    mockPeople = new ArrayList<>();
  }

  public static void addMockPerson(MockPerson mp) {
    mockPeople.add(mp);
  }

  public static int mockPeopleCount() {
    return mockPeople.size();
  }

  public static MockPerson getMockPerson(int i) {
    return mockPeople.get(i);
  }

  public MockPerson(String fn, String ln, String dt, String e, String g, String a, String cn) {
    if (fn.equals("")) {
      firstName = "N/A";
    } else {
      firstName = fn;
    }
    if (ln.equals("")) {
      lastName = "N/A";
    } else {
      lastName = ln;
    }
    if (dt.equals("")) {
      dateTime = "N/A";
    } else {
      dateTime = dt;
    }
    if (e.equals("")) {
      email = "N/A";
    } else {
      email = e;
    }
    if (g.equals("")) {
      gender = "N/A";
    } else {
      gender = g;
    }
    if (a.equals("")) {
      address = "N/A";
    } else {
      address = a;
    }
    if (cn.equals("")) {
      chineseName = "N/A";
    } else {
      chineseName = cn;
    }
  }

  public String toString() {
    return "First Name: " + firstName + "; "
        + "Last Name: " + lastName + "; "
        + "Date and Time: " + dateTime + "; "
        + "Email: " + email + "; "
        + "Gender: " + gender + "; "
        + "Address: " + address + "; "
        + "Chinese Name: " + chineseName;
  }

}
