package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.mantis.model.MantisUser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private WebDriver wd;
  private RegistrationHelper registrationHelper;

  public String browser;

  private Properties properties;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private AdminHelper adminHelper;
  private UserHelper userHelper;
  private SoapHelper soapHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() throws IOException {
    properties = new Properties();
    properties.load(new FileReader(new File("src/test/resources/local.properties")));
  }

  public void stop() {
    if (wd != null) wd.quit();
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

  public void closeAlert() {
    wd.switchTo().alert().accept();
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null)
      registrationHelper = new RegistrationHelper(this);
    return registrationHelper;
  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)){
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE))
        wd = new InternetExplorerDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
    }
    return wd;
  }

  public FtpHelper ftp() {
    if (ftp == null)
      ftp = new FtpHelper(this);
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public AdminHelper AdminHelper() {
    if (adminHelper == null) {
      adminHelper = new AdminHelper(this);
    }
    return adminHelper;
  }

  public UserHelper UserHelper() {
    if (userHelper == null) {
      userHelper = new UserHelper(this);
    }
    return userHelper;
  }

  public MantisUser GetTestUser() {
    // stub, here we should get something from db
    return new MantisUser(2, "user1551629138176", "user1551629138176@localhost.localdomain");
  }

  public SoapHelper SoapHelper() {
    if (soapHelper == null) {
      soapHelper = new SoapHelper(this);
    }
    return soapHelper;
  }
}
