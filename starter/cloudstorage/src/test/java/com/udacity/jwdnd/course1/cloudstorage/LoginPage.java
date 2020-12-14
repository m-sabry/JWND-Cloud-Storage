package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement username;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(className = "btn-primary")
    private WebElement loginSubmit;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsername() {
        return username;
    }

    public void setUsername(WebElement username) {
        this.username = username;
    }

    public WebElement getPassword() {
        return password;
    }

    public void setPassword(WebElement password) {
        this.password = password;
    }

    public WebElement getLoginSubmit() {
        return loginSubmit;
    }

    public void setLoginSubmit(WebElement loginSubmit) {
        this.loginSubmit = loginSubmit;
    }
}
