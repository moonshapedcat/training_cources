package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.AdminHelper;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.MantisUser;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {
    @BeforeMethod
    public void StartMailServer()
    {
        app.mail().start();
    }

    @Test
    public void ResetPasswordTest() throws IOException {
        MantisUser user = app.GetTestUser();
        app.AdminHelper().Login();
        app.AdminHelper().ResetUserPassord(user.username);

        long uniqueId = System.currentTimeMillis();
        String newPassword = String.format("password%s", uniqueId);
        List<MailMessage> mails = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mails, user.email);
        app.UserHelper().ResetPassword(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user.username, newPassword));
    }

    private String findConfirmationLink(List<MailMessage> mails, String email) {
        MailMessage mail = mails.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mail.text);
    }

    @AfterMethod(alwaysRun =  true)
    public void StopMailServer() {
        app.mail().stop();
    }
}
