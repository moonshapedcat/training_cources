package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Navigationhelper extends Helperbase {

  public Navigationhelper(ChromeDriver wd) {

    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }
}
