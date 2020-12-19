package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CredentialModal {

    @FindBy(id = "nav-credentials")
    private WebElement credentialsTab;
    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    public CredentialModal(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

}