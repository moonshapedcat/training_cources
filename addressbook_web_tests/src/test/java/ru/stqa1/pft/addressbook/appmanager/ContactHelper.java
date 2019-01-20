package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa1.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(ChromeDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    //wd.findElement(By.linkText("add new")).click();
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    click(By.name("theform"));
    type(By.name("lastname"), contactData.getLastname());
    click(By.name("theform"));
    type(By.name("nickname"), contactData.getNickname());
    click(By.name("theform"));
    type(By.name("title"), contactData.getTitle());
    click(By.name("theform"));
    type(By.name("company"), contactData.getCompanyName());
  }
}
