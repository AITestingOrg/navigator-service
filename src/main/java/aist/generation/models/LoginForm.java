package aist.generation.models;

import org.openqa.selenium.WebElement;

/**
 * Created by Bryan on 9/14/17.
 */
public class LoginForm implements Form{
    private WebElement loginBox;
    private WebElement passwordBox;
    private WebElement submitButton;

    public LoginForm() {

    }

    public LoginForm(WebElement login, WebElement password) {
        this.loginBox = login;
        this.passwordBox = password;
    }

    public WebElement getLoginBox() {
        return loginBox;
    }

    public WebElement getPasswordBox() {
        return passwordBox;
    }

    public void setLoginBox(WebElement loginBox) {
        this.loginBox = loginBox;
    }

    public void setPasswordBox(WebElement passwordBox) {
        this.passwordBox = passwordBox;
    }

    public void setSubmitButton(WebElement submitButton) {
        this.submitButton = submitButton;
    }

    @Override
    public void fillForm() {

    }
}
