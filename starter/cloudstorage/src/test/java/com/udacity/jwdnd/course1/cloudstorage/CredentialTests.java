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
	private CredentialTab credentialTab;

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

//		homePage.logout();
	}

	// _TODO Write a test that creates a set of credentials,
	//  verifies that they are displayed, and
	//  verifies that the displayed password is encrypted.
	@Test
	public void createCredential(){
		// _TODO create a new credential

		// CredentialModal initiation
		credentialTab = new CredentialTab(driver);

		// navigate to notes tab and create a new credential
		credentialTab.openCredentialTab();
		String expectedUrl = "uuuuuuuuuu";
		String expectedUsername = "sssssssssss";
		String expectedBlankPassword = "ppppppppppp";
		credentialTab.createCredential(expectedUrl, expectedUsername, expectedBlankPassword);

		driver.get(LOCALHOST + port);

		// TODO verify display
		credentialTab.openCredentialTab();
		String [] actualCredentialArray = credentialTab.getLastAddedCredential();

		Integer credentialId = Integer.valueOf(actualCredentialArray[3]);
		String actualCredential = actualCredentialArray[0]+actualCredentialArray[1]+actualCredentialArray[2];
		String expectedCredential = credentialTab.getExpectedCredential(credentialId, credentialService);
		Assertions.assertEquals(expectedCredential, actualCredential);

		// TODO verify the displayed password is encrypted

	}

	// TODO Write a test that views an existing set of credentials,
	//  verifies that the viewable password is unencrypted,
	//  edits the credentials, and
	//  verifies that the changes are displayed.
	@Test
	public void editCredential(){
		// NoteModal initiation
		credentialTab = new CredentialTab(driver);

		// navigate to notes tab
		credentialTab.openCredentialTab();

		String expectedUrl = "uuuuuuuuuu";
		String expectedUsername = "sssssssssss";
		String expectedBlankPassword = "ppppppppppp";

		// edit the last note in the note's list
		int credentialsCounter = credentialTab.credentialsListSize();
		credentialTab.editCredential( credentialsCounter - 1,
				expectedUrl, expectedUsername, expectedBlankPassword);


		// loading home page and navigating to notes tab
		driver.get(LOCALHOST + port);
		credentialTab.openCredentialTab();

		String[] editedValues = credentialTab.getLastAddedCredentials();
		String actualUrl = editedValues[0];
		String actualUsername = editedValues[1];
		String actualBlankPassword = editedValues[2];

		Assertions.assertEquals(expectedUrl, actualUrl);
		Assertions.assertEquals(expectedUsername, actualUsername);
		Assertions.assertEquals(expectedBlankPassword, actualBlankPassword);

	}

	// TODO Write a test that deletes an existing set of credentials
	//  and verifies that the credentials are no longer displayed.
	@Test
	public void deleteCredential(){

		// Credential Page initiation
		credentialTab = new CredentialTab(driver);

		// navigate to notes tab
		credentialTab.openCredentialTab();

		int sizeBeforeSaving = credentialTab.credentialsListSize();
		credentialTab.deleteCredential();
		int sizeAfterSaving = credentialTab.credentialsListSize();

		Assertions.assertEquals(-1, sizeAfterSaving - sizeBeforeSaving);

	}

}
