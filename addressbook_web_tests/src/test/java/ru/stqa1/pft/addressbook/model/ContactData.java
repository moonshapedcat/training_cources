package ru.stqa1.pft.addressbook.model;

public class ContactData {
  private final String name;
  private final String middlename;
  private final String lastname;
  private final String nickname;
  private final String title;
  private final String companyName;

  public ContactData(String name, String middlename, String lastname, String nickname, String title, String companyName) {
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.companyName = companyName;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompanyName() {
    return companyName;
  }
}
