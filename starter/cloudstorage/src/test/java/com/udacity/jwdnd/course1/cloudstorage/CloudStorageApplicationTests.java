package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	private LoginPage loginPage;
	private HomePage homePage;
	private SignUpPage signUpPage;

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


	/*
		Authorization Test
	 */

	@Test
	public void authorization() {
		loginPage = new LoginPage(driver);

		driver.get("http://localhost:" + this.port + "/login");

		loginPage.getUsername().sendKeys("msabry");
		loginPage.getPassword().sendKeys("111111");
		loginPage.getLoginSubmit().submit();

		Assertions.assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());
	}

	@Test
	public void unAuthorizedUser() throws InterruptedException{
		// _TODO Write a test that verifies that an unauthorized user can only access the login and signup pages.

		driver.get("http://localhost:" + this.port);
		Assertions.assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());
	}

	// TODO Write a test that signs up a new user, logs in, verifies that the home page is accessible,
	//  logs out, and verifies that the home page is no longer accessible.
	@Test
	public void fullCycleTest(){

		String username = "mostafa";
		String password = "password";

		//Signup
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage = new SignUpPage(driver);
		signUpPage.getUsername().sendKeys(username);
		signUpPage.getPassword().sendKeys(password);
		signUpPage.signup();

//		Assertions.assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());

		//login
		loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.getUsername().sendKeys(username);
		loginPage.getPassword().sendKeys(password);
		loginPage.getLoginSubmit().submit();
//		Assertions.assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());

		// go to home
		driver.get("http://localhost:" + this.port);
		Assertions.assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());

		// logout
		homePage.logout();

		// check home again
		driver.get("http://localhost:" + this.port);
		Assertions.assertEquals("http://localhost:" + port + "/login", driver.getCurrentUrl());

	}


	/*
		Home Page - Note Testing
	 */

	// TODO Write a test that creates a note, and verifies it is displayed.
	public void createNote(){

	}
	// TODO Write a test that edits an existing note and verifies that the changes are displayed.

	public void editNote(){

	}
	// TODO Write a test that deletes a note and verifies that the note is no longer displayed.
	public void deleteNote(){

	}



	/*
		Home Page - Credential Testing
	 */
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
