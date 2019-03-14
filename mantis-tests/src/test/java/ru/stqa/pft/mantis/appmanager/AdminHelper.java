package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminHelper extends HelperBase {
    private WebDriver wd;

    public AdminHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void Login() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[value='Login']"));
    }

    public void ResetUserPassord(String username) {
        click(By.linkText("Manage"));
        click(By.linkText("Manage Users"));
        click(By.linkText(username));
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
