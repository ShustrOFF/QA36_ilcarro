package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();

        }

    }

    @Test
    public void loginSuccess() {
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("maslopup@gmail.com", "Pup54321$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");

    }

    @Test
    public void loginSuccessModel(){
        User user=new User().withEmail("maslopup@gmail.com").withPassword("Pup54321$");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");

    }

    @Test
    public void loginWrongEmail() {
        User user = new User().withEmail("maslopupgmal.com").withPassword("Pup54321$");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
    }

    @Test
    public void loginWrongPassword() {
        User user = new User().withEmail("maslopup@gmail.com").withPassword("12$dD");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");

    }

    @Test(enabled = false)
    public void loginUnregisteredUser() {
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("dasdasdas@gmail.com", "PuP54321$");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
    }

    @AfterMethod
    public void postCondition(){
        app.getHelperUser().closeDialogContainer();
    }

    public void pause() {

        try {
            Thread.sleep(2000);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}