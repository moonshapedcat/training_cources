package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd;

  public ContactHelper contactHelper;
  public SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  public GroupHelper groupHelper;
  public String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {

    if (browser.equals(BrowserType.CHROME)) {
     wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE))
      wd = new InternetExplorerDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    contactHelper = new ContactHelper(wd);
    sessionHelper = new SessionHelper(wd);
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper.login("admin", "secret");
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

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e){
      return false;
    }
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }
}
