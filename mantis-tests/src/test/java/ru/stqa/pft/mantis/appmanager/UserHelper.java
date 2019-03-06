package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase {
    private WebDriver wd;

    public UserHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void ResetPassword(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}
