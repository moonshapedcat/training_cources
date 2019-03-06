package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void StartMailServer()
    {
        app.mail().start();
    }

    @Test
    public void testResistration() throws IOException {
        long userId = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", userId);
        String user = String.format("user%s", userId);
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mails = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mails, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(user, password));
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
