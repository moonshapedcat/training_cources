package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HelperBase {
  protected ApplicationManager app;

  public HelperBase(ApplicationManager app) {
    this.app = app;
  }

  public void click(By locator) {
    getWd().findElement(locator).click();
  }

  public void type(By locator, String text) {
    click(locator);
    getWd().findElement(locator).clear();
    getWd().findElement(locator).sendKeys(text);
  }

  public boolean isElementPresent(By locator) {
    try {
      getWd().findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }

  }

  protected WebDriver getWd() {
    return app.getDriver();
  }
}
