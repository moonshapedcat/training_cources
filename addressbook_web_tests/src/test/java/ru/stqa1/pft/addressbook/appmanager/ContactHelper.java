package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    //wd.findElement(By.linkText("add new")).click();
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    //click(By.name("theform"));
    type(By.name("lastname"), contactData.getLastname());
    // click(By.name("theform"));
    type(By.name("nickname"), contactData.getNickname());
    //  click(By.name("theform"));
    type(By.name("title"), contactData.getTitle());
    //  click(By.name("theform"));
    type(By.name("company"), contactData.getCompanyName());
  }

  public void initContact() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }

  private void selectContactByID(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']" )).click();
    // click(By.name("selected[]"));
  }

  public void submitContact() {
    click(By.name("submit"));
  }

  public void editContact() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }

  public void editContact(By locator) {
    click(locator);
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void create(ContactData contactData) {
    initContact();
    fillContactForm(contactData);
    submitContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount(int i) {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void modify(ContactData contactData) {
    selectContactByID(contactData.getId());
    editContact();
    fillContactForm(contactData);
    updateContact();
  }

  public void delete(int index) {
   selectContact(index);
   deleteContact();
  }

  public void delete(ContactData contact) {
    selectContactByID(contact.getId());
    deleteContact();
  }


  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      String name = element.findElement(By.xpath("//tr[@name='entry']/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//tr[@name='entry']/td[2]")).getText();

      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
    }

    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      String name = element.findElement(By.xpath("//tr[@name='entry']/td[3]")).getText();
      String lastname = element.findElement(By.xpath("//tr[@name='entry']/td[2]")).getText();

      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname));
    }

    return contacts;
  }
}

