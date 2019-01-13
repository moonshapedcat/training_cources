package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  ChromeDriver wd;

  private Sessionhelper sessionhelper;
  private Navigationhelper navigationhelper;
  private Grouphelper grouphelper;

  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    grouphelper = new Grouphelper(wd);
    navigationhelper = new Navigationhelper(wd);
    sessionhelper = new Sessionhelper(wd);
    sessionhelper.login("admin", "secret");
  }


  public void stop() {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


  public Grouphelper getGrouphelper() {
    return grouphelper;
  }

  public Navigationhelper getNavigationhelper() {
    return navigationhelper;
  }
}
