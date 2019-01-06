package ru.stqa1.pft.addressbook;

public class AddressBookMethods {

  public static void main(String args[]) {
    click();
    clear();

    GroupData info = new GroupData("Some text");
    sendKeys(info);
  }


  public static void click() {

  }

  public static void clear() {

  }

  public static String sendKeys(GroupData info) {
    System.out.println(info);
    String s = "Information has been added";
    return s;


  }
}

