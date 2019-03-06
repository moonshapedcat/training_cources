package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.*;

public class LoginTests extends TestBase {

    @Test
    public void Login() throws IOException, ServiceException {
        skipIfNotFixed(3);
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}
