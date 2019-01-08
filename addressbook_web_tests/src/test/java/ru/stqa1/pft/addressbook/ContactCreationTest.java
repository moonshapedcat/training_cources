package ru.stqa1.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTest {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testNewContactCreation() throws Exception {
    wd.get("http://localhost/addressbook/delete.php?part=2;");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Password:'])[1]/following::input[2]")).click();
    wd.findElement(By.linkText("add new")).click();
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys("Ivan");
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys("Ivanovich");
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys("Ivanov");
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys("Ivan");
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys("none");
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys("none");
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys("none");
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys("none");
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys("none");
    wd.findElement(By.name("work")).clear();
    wd.findElement(By.name("work")).sendKeys("none");
    wd.findElement(By.name("fax")).clear();
    wd.findElement(By.name("fax")).sendKeys("none");
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys("none");
    wd.findElement(By.name("email2")).clear();
    wd.findElement(By.name("email2")).sendKeys("none");
    wd.findElement(By.name("email3")).clear();
    wd.findElement(By.name("email3")).sendKeys("none");
    wd.findElement(By.name("homepage")).clear();
    wd.findElement(By.name("homepage")).sendKeys("none");
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("-");
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("1");
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("2");
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("3");
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("4");
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("5");
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("January");
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("February");
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("March");
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("April");
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys("2000");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("-");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("1");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("2");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("3");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("4");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("5");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("6");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("7");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("8");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("9");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("10");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("11");
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("12");
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("January");
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("February");
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("March");
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("April");
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("May");
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys("2002");
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
    wd.findElement(By.name("address2")).clear();
    wd.findElement(By.name("address2")).sendKeys("none");
    wd.findElement(By.name("phone2")).clear();
    wd.findElement(By.name("phone2")).sendKeys("none");
    wd.findElement(By.name("notes")).clear();
    wd.findElement(By.name("notes")).sendKeys("none");
    wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])[1]/following::input[1]")).click();
    wd.findElement(By.linkText("Logout")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
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
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
