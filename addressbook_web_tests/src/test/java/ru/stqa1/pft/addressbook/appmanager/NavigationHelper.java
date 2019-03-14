package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    click(By.linkText("groups"));
  }


  public void GroupForAddition(String groupName) {

    Select value = new Select(wd.findElement(By.name("group")));
    value.selectByVisibleText(groupName);
  }

  public void backToLogo() {
    click(By.id("logo"));
  }

  public void homePage() {
    if (isElementPresent(By.name("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));

    }
  }
}

