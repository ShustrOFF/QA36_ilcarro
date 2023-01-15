package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().withName("Vovk").withLastName("Maslopupemko").withEmail("vovk"+ i + "@gmail.com").withPassword("Vovk54321$");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        pause();
        Assert.assertEquals(app.getHelperUser().getMessage(), "You are logged in success");
    }

    @Test
    public void registrationWrongEmail(){
        User user = new User().withName("Vovk").withLastName("Maslopupemko").withEmail("vovkgmail.com").withPassword("Vovk12345$");
        pause();
        app.getHelperUser().openRegistrationForm();
        pause();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        pause();
        Assert.assertEquals(app.getHelperUser().getErrorRegistrationText(), "Wrong email format");
    }

    @Test
    public void registrationShortPassword(){
        User user = new User().withName("Vovk").withLastName("Maslopupemko").withEmail("vovk@gmail.com").withPassword("Zx$13");
        app.getHelperUser().openRegistrationForm();
        pause();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        pause();
        Assert.assertEquals(app.getHelperUser().getErrorShortPasswordText(), "Password must contain minimum 8 symbols");
    }
    @Test
    public void registrationWrongPassword(){
        User user = new User().withName("Vovk").withLastName("Maslopupemko").withEmail("vovk@gmail.com").withPassword("Vovk12345");
        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        pause();
        Assert.assertEquals(app.getHelperUser().getErrorPasswordText(), "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
    }


    public void pause() {
        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }
}