package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa1.pft.addressbook.model.GroupData;
import ru.stqa1.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
   // click(By.linkText("Logout"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']" )).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }


  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData groupData) {
    initGroupCreation();
    fillGroupForm(groupData);
    submitGroupCreation();
    groupCash = null;
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount(){
   return wd.findElements(By.name("selected[]")).size();
  }

  public void modify(GroupData groupData) {
    selectGroupById(groupData.getId());
    initGroupModification();
    fillGroupForm(groupData);
    submitGroupModification();
    groupCash = null;
    returnToGroupPage();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroups();
    groupCash = null;
    returnToGroupPage();
  }

  private Groups groupCash = null;

  public Groups all() {
    if (groupCash != null){
      return new Groups(groupCash);
    }
    groupCash = new Groups();
    List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCash.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCash);
  }
    public boolean isThereContact() {
    return isElementPresent(By.name("selected[]"));
  }

}
