package ru.stqa1.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String midleName;
  private final String lastName;
  private final String nickname;
  private final String usertitle;
  private final String userCompany;

  public ContactData(String name, String midleName, String lastName, String nickname, String usertitle, String userCompany) {
    this.name = name;
    this.midleName = midleName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.usertitle = usertitle;
    this.userCompany = userCompany;
  }

  public String getName() {
    return name;
  }

  public String getMidleName() {
    return midleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickname() {
    return nickname;
  }

  public String getUsertitle() {
    return usertitle;
  }

  public String getUserCompany() {
    return userCompany;
  }
}