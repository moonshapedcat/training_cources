package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        getWd().get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String password) {
        getWd().get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
