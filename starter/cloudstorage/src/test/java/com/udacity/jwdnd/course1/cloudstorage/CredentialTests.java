package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	@Autowired
	private CredentialService credentialService;

	private HomePage homePage;
	private CredentialPage credentialPage;

	private static final String LOCALHOST = "http://localhost:";
	private static final String USERNAME = "msabry";
	private static final String PASSWORD = "111111";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();

	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();

		// login
		LoginPage loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.getUsername().sendKeys(USERNAME);
		loginPage.getPassword().sendKeys(PASSWORD);
		loginPage.getLoginSubmit().submit();

		// Loading home page
		driver.get(LOCALHOST + this.port);
		homePage = new HomePage(driver);
	}

	@AfterEach
	public void afterEach() {

		if (this.driver != null) {
			driver.quit();
		}

		// logout
		if(homePage != null) homePage.logout();
		else System.out.println("HOME PAGE IS NULL");
	}

	// _TODO Write a test that creates a set of credentials,
	//  verifies that they are displayed, and
	//  verifies that the displayed password is encrypted.
	@Test
	public void createCredential(){
		// _TODO create a new credential

		// CredentialModal initiation
		credentialPage = new CredentialPage(driver);

		// navigate to notes tab and create a new credential
		credentialPage.openCredentialTab();
		String expectedUrl = "uuuuuuuuuu";
		String expectedUsername = "sssssssssss";
		String expectedBlankPassword = "ppppppppppp";
		credentialPage.createCredential(expectedUrl, expectedUsername, expectedBlankPassword);

		driver.get(LOCALHOST + port);

		// TODO verify display
		credentialPage.openCredentialTab();
		String [] actualCredentialArray = credentialPage.getLastAddedCredential();

		Integer credentialId = Integer.valueOf(actualCredentialArray[3]);
		String actualCredential = actualCredentialArray[0]+actualCredentialArray[1]+actualCredentialArray[2];
		String expectedCredential = credentialPage.getExpectedCredential(credentialId, credentialService);
		Assertions.assertEquals(expectedCredential, actualCredential);

		// TODO verify the displayed password is encrypted
//		credentialPage.openCredentialTab();
//		Assertions.assertEquals(expectedEncryptedPassword, actualEncryptedPassword);

	}

	// TODO Write a test that views an existing set of credentials,
	//  verifies that the viewable password is unencrypted,
	//  edits the credentials, and
	//  verifies that the changes are displayed.
	@Test
	public void editCredential(){
		// NoteModal initiation
		credentialPage = new CredentialPage(driver);

		// navigate to notes tab
		credentialPage.openCredentialTab();

		String expectedUrl = "uuuuuuuuuu";
		String expectedUsername = "sssssssssss";
		String expectedBlankPassword = "ppppppppppp";

		credentialPage.editCredential(expectedUrl, expectedUsername, expectedBlankPassword);
		String actualUrl = "";
		String actualUsername = "";
		String actualBlankPassword = "";

		Assertions.assertEquals(expectedUrl, actualUrl);
		Assertions.assertEquals(expectedUsername, actualUsername);
		Assertions.assertEquals(expectedBlankPassword, actualBlankPassword);

	}

	// TODO Write a test that deletes an existing set of credentials
	//  and verifies that the credentials are no longer displayed.
	@Test
	public void deleteCredential(){

		// Credential Page initiation
		credentialPage = new CredentialPage(driver);

		// navigate to notes tab
		credentialPage.openCredentialTab();

		int sizeBeforeSaving = credentialPage.credentialsListSize();
		credentialPage.deleteCredential();
		int sizeAfterSaving = credentialPage.credentialsListSize();

		Assertions.assertEquals(-1, sizeAfterSaving - sizeBeforeSaving);

	}

}
