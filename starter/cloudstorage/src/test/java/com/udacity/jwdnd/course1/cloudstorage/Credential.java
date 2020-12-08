package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Credential {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    // TODO Write a test that creates a set of credentials,
    //  verifies that they are displayed, and verifies that the displayed password is encrypted.
    public void createCredential(){

    }

    // TODO Write a test that views an existing set of credentials,
    //  verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
    public void editCredential(){

    }

    // TODO Write a test that deletes an existing set of credentials
    //  and verifies that the credentials are no longer displayed.
    public void deleteCredential(){

    }

}
