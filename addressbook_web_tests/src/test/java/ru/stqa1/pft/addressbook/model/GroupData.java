package ru.stqa1.pft.addressbook.model;

public class GroupData {
  private final String name;
  private final String header;
  private final String footer;

  public GroupData(String name, String header, String footter) {

    this.name = name;
    this.header = header;
    this.footer = footter;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }
}
