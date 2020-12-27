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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CredentialTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	@Autowired
	private CredentialService credentialService;

	private HomePage homePage;
	private LoginPage loginPage;
	private SignUpPage signUpPage;
	private CredentialTab credentialTab;


	private static final String BASE_URL = "http://localhost:";
	private static final String LOGIN = "/login";
	private static final String SIGNUP = "/signup";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();

		signup(driver);

		// logs in an existing user
		login(driver);

		// Loading home page
		driver.get(BASE_URL + this.port);
		homePage = new HomePage(driver);


	}

	@AfterEach
	public void afterEach() {

		if (this.driver != null) {
			driver.quit();
		}

	}

	@Test
	@Order(1)
	public void createCredential(){
		credentialTab = new CredentialTab(driver);

		// _TODO create a credential
		// navigate to notes tab and create a new credential
		credentialTab.openCredentialTab();
		String expectedUrl = "url";
		String expectedUsername = "username";
		String expectedBlankPassword = "password";
		credentialTab.createCredential(expectedUrl, expectedUsername, expectedBlankPassword);

		// Navigating to home page
		driver.get(BASE_URL + port);

		// _TODO verify display
		credentialTab.openCredentialTab();
		String [] actualCredentialArray = credentialTab.getLastAddedCredential();

		Integer credentialId = Integer.valueOf(actualCredentialArray[3]);
		String actualCredential = actualCredentialArray[0]+actualCredentialArray[1]+actualCredentialArray[2];
		// expected credential loaded from database
		String expectedCredential = credentialTab.getExpectedCredential(credentialId, credentialService);

		Assertions.assertEquals(expectedCredential, actualCredential);
	}

	@Test
	@Order(2)
	public void editCredential(){
		// NoteModal initiation
		credentialTab = new CredentialTab(driver);

		// navigate to notes tab
		credentialTab.openCredentialTab();

		String expectedUrl = "url";
		String expectedUsername = "username";
		String expectedBlankPassword = "password";

		// edit the last note in the note's list
		int credentialsCounter = credentialTab.credentialsListSize();
		credentialTab.editCredential( credentialsCounter - 1,
				expectedUrl, expectedUsername, expectedBlankPassword);


		// loading home page and navigating to notes tab
		driver.get(BASE_URL + port);
		credentialTab.openCredentialTab();

		String[] editedValues = credentialTab.getLastAddedCredentials(credentialService);
		String actualUrl = editedValues[0];
		String actualUsername = editedValues[1];
		String actualBlankPassword = editedValues[2];

		// _TODO verifies that the changes appear in the credential list and password unencrypted
		Assertions.assertEquals(expectedUrl, actualUrl);
		Assertions.assertEquals(expectedUsername, actualUsername);
		Assertions.assertEquals(expectedBlankPassword, actualBlankPassword);

	}

	@Test
	@Order(3)
	public void deleteCredential(){

		// Credential Page initiation
		credentialTab = new CredentialTab(driver);

		// navigate to notes tab
		credentialTab.openCredentialTab();

		// _TODO delete credential
		int sizeBeforeSaving = credentialTab.credentialsListSize();
		credentialTab.deleteCredential();
		int sizeAfterSaving = credentialTab.credentialsListSize();

		// _TODO verifies that the changes appear in the credential list
		Assertions.assertEquals(-1, sizeAfterSaving - sizeBeforeSaving);

	}



	private void signup(WebDriver driver) {
		//Signup
		driver.get(BASE_URL + port + SIGNUP);
		signUpPage = new SignUpPage(driver);
		signUpPage.getUsername().sendKeys(USERNAME);
		signUpPage.getPassword().sendKeys(PASSWORD);
		signUpPage.signup();
	}

	private void login(WebDriver driver) {
		//login
		loginPage = new LoginPage(driver);
		driver.get(BASE_URL + port + LOGIN);
		loginPage.getUsername().sendKeys(USERNAME);
		loginPage.getPassword().sendKeys(PASSWORD);
		loginPage.getLoginSubmit().submit();
	}


}
