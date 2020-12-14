package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

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


	/*
		Authorization Test
	 */

	@Test
	public void authorization() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void unAuthorizedUser() throws InterruptedException{
		// TODO Write a test that verifies that an unauthorized user can only access the login and signup pages.
		driver = new ChromeDriver();

		// visit home redirect to login page
		driver.get("http://localhost:8080");
		Thread.sleep(10000);

		// visiting signup page
		driver.get("http://localhost:8080/signup");
		Thread.sleep(10000);

//        driver.quit();
	}

	// TODO Write a test that signs up a new user, logs in, verifies that the home page is accessible,
	//  logs out, and verifies that the home page is no longer accessible.
	public void fullCycleTest(){
		//Signup

		//login

		// go to home

		// logout

		// check home again
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
