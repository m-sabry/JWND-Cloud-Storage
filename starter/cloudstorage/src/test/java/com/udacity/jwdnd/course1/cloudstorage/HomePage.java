package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {

    @FindBy(id = "nav-notes")
    private WebElement notesTab;
    @FindBy(id = "noteTable")
    private WebElement notesTable;

    @FindBy(id = "nav-credentials")
    private WebElement credentialsTab;
    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    @FindBy(xpath = "//div[@id='logoutDiv']//form//button")
    private WebElement logoutButton;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void logout(){
        logoutButton.submit();
    }

}