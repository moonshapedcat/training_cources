package ru.stqa1.pft.addressbook.model;

public class ContactData {
  private final String yourName;
  private final String yourMidlename;
  private final String yourLastname;
  private final String yourNickname;
  private final String yourTitle;
  private final String yourCompanyName;

  public ContactData(String yourName, String yourMidlename, String yourLastname, String yourNickname, String yourTitle, String yourCompanyName) {
    this.yourName = yourName;
    this.yourMidlename = yourMidlename;
    this.yourLastname = yourLastname;
    this.yourNickname = yourNickname;
    this.yourTitle = yourTitle;
    this.yourCompanyName = yourCompanyName;
  }

  public String getYourName() {
    return yourName;
  }

  public String getYourMidlename() {
    return yourMidlename;
  }

  public String getYourLastname() {
    return yourLastname;
  }

  public String getYourNickname() {
    return yourNickname;
  }

  public String getYourTitle() {
    return yourTitle;
  }

  public String getYourCompanyName() {
    return yourCompanyName;
  }
}
