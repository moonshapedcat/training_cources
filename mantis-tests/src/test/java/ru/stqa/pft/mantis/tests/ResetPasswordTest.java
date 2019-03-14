package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.MantisUsers;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void ResetPasswordTest() throws IOException {
        List<UserData> users = app.db().users();
        UserData user = users.iterator().next();
        app.AdminHelper().Login();
        app.AdminHelper().ResetUserPassord(user.username);

        long uniqueId = System.currentTimeMillis();
        String newPassword = String.format("password%s", uniqueId);
        List<MailMessage> mails = app.mail().waitForMail(1, 10000);
        String confirmationLink = findResetLink(mails, user.email);
        app.UserHelper().ResetPassword(confirmationLink, newPassword);
        assertTrue(app.newSession().login(user.username, newPassword));
    }

    private String findResetLink(List<MailMessage> messages, String email) {
        MailMessage message = messages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}

