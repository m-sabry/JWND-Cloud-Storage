package com.udacity.jwdnd.course1.cloudstorage;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.web.server.LocalServerPort;


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

}
