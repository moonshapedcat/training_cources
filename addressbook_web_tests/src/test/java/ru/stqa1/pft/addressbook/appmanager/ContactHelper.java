package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.Contacts;

import java.util.*;

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

  public void delete(ContactData contact) {
    selectContactByID(contact.getId());
    deleteContact();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
      String name = element.findElement(By.xpath("./td[3]")).getText();
      String lastname = element.findElement(By.xpath("./td[2]")).getText();
      String address = element.findElement(By.xpath("./td[4]")).getText();
      String allEmails = element.findElement(By.xpath("./td[5]")).getText();
      String allPhones = element.findElement(By.xpath("./td[6]")).getText();


      contacts.add(new ContactData().withId(id).withName(name).withLastname(lastname)
              .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
    }

    return contacts;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute(("value"));
    String email = wd.findElement(By.name("email")).getAttribute(("value"));
    String email2 = wd.findElement(By.name("email2")).getAttribute(("value"));
    String email3 = wd.findElement(By.name("email3")).getAttribute(("value"));
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
            .withEmail(email).withEmail2(email2).withEmail3(email3);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
  }
}

