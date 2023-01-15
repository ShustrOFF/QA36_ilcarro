package manager;


import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }


    public void openFormLogin() {
        click(By.xpath("//a[text()=' Log in ']"));
    }

    public void fillLoginForm(String email, String password) {
        type(By.id("email"),email);
        type(By.id("password"),password);
    }
    public void fillLoginForm(User user) {
        type(By.id("email"), user.getEmail());
        type(By.id("password"),user.getPassword());
    }

    public void submit() {
        //click(By.xpath("//button[text()='Y’alla!']"));
        click(By.xpath("//button[@type='submit']"));

    }

    public String getMessage() {
        return wd.findElement(By.cssSelector("div.dialog-container>h2")).getText();
    }

    public void closeDialogContainer() {
        if(isElementPresent(By.xpath("//button[text()='Ok']"))) {
            click(By.xpath("//button[text()='Ok']"));
        }
    }

    public boolean isLogged() {
        //return isElementPresent(By.xpath("//button[text()=' Logout ']"));
        return isElementPresent(By.cssSelector("div.header a:nth-child(5)"));
    }

    public void logout() {
        // click(By.xpath("//button[text()=' Logout ']"));
        click(By.cssSelector("div.header a:nth-child(5)"));
    }

    public String getErrorText() {
        return wd.findElement(By.cssSelector("div.error")).getText();
    }

    public boolean isYallaButtonNotActive() {
        // return isElementPresent(By.cssSelector("button[disabled]"));
        return !wd.findElement(By.cssSelector("button[disabled]")).isEnabled();
    }

    public void openRegistrationForm() {
        click(By.xpath("//a[text()=' Sign up ']"));
    }

    public void fillRegistrationForm(User user) {
        type(By.cssSelector("input#name"), user.getName());
        type(By.cssSelector("input#lastName"),user.getLastName());
        type(By.cssSelector("input#email"),user.getEmail());
        type(By.cssSelector("input#password"),user.getPassword());

    }

    public void checkPolicy() {
        click(By.className("checkbox-container"));
    }

    public String getErrorRegistrationText() {
        return wd.findElement(By.xpath("//div[text()='Wrong email format'][last()]")).getText();
    }

    public String getErrorPasswordText() {
        return wd.findElement(By.xpath("//div[contains(text(),'Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]')]")).getText();
    }

    public String getErrorShortPasswordText() {
        return wd.findElement(By.xpath("//div[contains(text(),'Password must contain minimum 8 symbols')]")).getText();
    }
}