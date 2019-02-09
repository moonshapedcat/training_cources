package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.util.ArrayList;
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

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }


  public void initGroupModification() {
    click(By.name("edit"));
  }


  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupData groupData) {
    initGroupCreation();
    fillGroupForm(groupData);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount(){
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> getGroupList() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List <WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      String name = element.getText();
      GroupData groupData = new GroupData(name, null, null);
      groups.add(groupData);
    }
    return groups;
  }
}
