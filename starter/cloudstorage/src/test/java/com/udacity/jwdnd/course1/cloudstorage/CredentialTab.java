package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class CredentialTab {

    private final WebDriverWait driverWait;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    // Credential tab controls
    @FindBy(xpath = "//table[@id='credentialTable']//tbody//tr")
    private List<WebElement> credentialTable;
    @FindBy(xpath = "//div[@id='nav-credentials']//button")
    private WebElement newCredentialButton;
    @FindBy(xpath = "//table[@id='credentialTable']//tbody//tr//td//button")
    private List<WebElement> editButtons;
    @FindBy(xpath = "//table[@id='credentialTable']//tbody//tr//td//a")
    private WebElement deleteButton;
    @FindBy(xpath = "//table[@id='credentialTable']//tbody//tr")
    private List<WebElement> credentialRows;

    // modal controls
    @FindBy(id = "credential-url")
    private WebElement urlInput;
    @FindBy(id = "credential-username")
    private WebElement usernameInput;
    @FindBy(id = "credential-password")
    private WebElement passwordInput;
    @FindBy(xpath = "//div[@id='credentialModal']" +
            "//div[@class='modal-dialog']" +
            "//div[@class='modal-footer']" +
            "//button[@class='btn btn-primary']")
    private WebElement saveButton;


    public CredentialTab(WebDriver driver){
        PageFactory.initElements(driver, this);
        driverWait = new WebDriverWait(driver, 500);
    }

    public void openCredentialTab(){
        driverWait.until(ExpectedConditions.elementToBeClickable(credentialsTab)).click();
    }

    public void createCredential(String url, String username, String password){
        driverWait.until(ExpectedConditions.elementToBeClickable(newCredentialButton)).click();

        driverWait.until(ExpectedConditions.elementToBeClickable(urlInput)).click();
        urlInput.sendKeys(url);
        driverWait.until(ExpectedConditions.elementToBeClickable(usernameInput)).click();
        usernameInput.sendKeys(username);
        driverWait.until(ExpectedConditions.elementToBeClickable(passwordInput)).click();
        passwordInput.sendKeys(username);

        driverWait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void editCredential(int rowIndex, String url, String username, String password) {
        driverWait.until(ExpectedConditions.elementToBeClickable(
                editButtons.get(rowIndex))).click();

        driverWait.until(ExpectedConditions.elementToBeClickable(urlInput)).click();
        urlInput.clear();
        urlInput.sendKeys(url);
        driverWait.until(ExpectedConditions.elementToBeClickable(usernameInput)).click();
        usernameInput.clear();
        usernameInput.sendKeys(username);
        driverWait.until(ExpectedConditions.elementToBeClickable(passwordInput)).click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        driverWait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void deleteCredential() {
        driverWait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public int credentialsListSize() {
        return credentialTable.size();
    }

    public String [] getLastAddedCredential() {
        WebElement row = credentialRows.get(credentialRows.size() - 1);
        List<WebElement> tds = row.findElements(By.xpath("td//span"));

        String [] details = new String[4];
        details[0] = tds.get(0).getAttribute("innerHTML"); // URL
        details[1] = tds.get(1).getAttribute("innerHTML"); // username
        details[2] = tds.get(2).getAttribute("innerHTML"); // password
        details[3] = row.findElement(By.xpath("//table[@id='credentialTable']//tbody//tr//td//a"))
                .getAttribute("href").split("=")[1]; // credential Id

        return details;
    }

    public String [] getLastAddedCredentials() {
        WebElement row = credentialRows.get(credentialRows.size() - 1);
        List<WebElement> tds = row.findElements(By.xpath("td//span"));

        String [] details = new String[4];
        details[0] = tds.get(0).getAttribute("innerHTML"); // URL
        details[1] = tds.get(1).getAttribute("innerHTML"); // username
        details[2] = tds.get(2).getAttribute("innerHTML"); // password
        details[3] = row.findElement(By.xpath("//table[@id='credentialTable']//tbody//tr//td//a"))
                .getAttribute("href").split("=")[1]; // credential Id

        return details;
    }

    public String getExpectedCredential(Integer credentialId, CredentialService credentialService) {
        Credential credential = credentialService.getOne(credentialId);
        return credential.getUrl()+credential.getUsername()+credential.getPassword();
    }
}