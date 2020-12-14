package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;


public class HomePage {

    @FindBy(id = "nav-notes")
    private WebElement notesTab;
    @FindBy(id = "noteTable")
    private WebElement notesTable;

    @FindBy(id = "nav-credentials")
    private WebElement credentialsTab;
    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    @FindBy(css = "btn btn-secondary float-right")
    private WebElement logoutButton;

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public List<Note> loadNotes(){
        return null;
    }

    public List<Note> loadCredentials(){
        return null;
    }

    public void logout(){
        logoutButton.submit();
    }

}