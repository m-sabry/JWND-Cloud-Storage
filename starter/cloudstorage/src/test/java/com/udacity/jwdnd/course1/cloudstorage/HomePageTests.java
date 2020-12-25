package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomePageTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	private LoginPage loginPage;
	private SignUpPage signUpPage;
	private HomePage homePage;

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
		homePage = new HomePage(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}


	/*
		Authorization Tests
	 */

	@Test
	public void successfulLogin() {
		loginPage = new LoginPage(driver);

		driver.get("http://localhost:" + this.port + "/login");

		loginPage.getUsername().sendKeys(USERNAME);
		loginPage.getPassword().sendKeys(PASSWORD);
		loginPage.getLoginSubmit().submit();

		Assertions.assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());
	}

	@Test
	public void unAuthorizedUser() throws InterruptedException{
		// _TODO Write a test that verifies that an unauthorized user can only access the login and signup pages.
		String currentURL = null;
		// first scenario, unauthorized user visit home page and redirected to the login page
		driver.get("http://localhost:" + this.port);
		currentURL = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:" + port + "/login", currentURL);

		// second scenario, unauthorized user try to visit the signup page and successfully reach it
		driver.get("http://localhost:" + this.port + "/signup");
		currentURL = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:" + port + "/signup", currentURL);

		// third scenario, unauthorized user try to visit the login page and successfully reach it
		driver.get("http://localhost:" + this.port + "/login");
		currentURL = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:" + port + "/login", currentURL);
	}

	// _TODO Write a test that signs up a new user, logs in, verifies that the home page is accessible,
	//  logs out, and verifies that the home page is no longer accessible.
	@Test
	public void fullCycleTest(){
		//Signup
		driver.get("http://localhost:" + this.port + "/signup");
		signUpPage = new SignUpPage(driver);
		signUpPage.getUsername().sendKeys(USERNAME);
		signUpPage.getPassword().sendKeys(PASSWORD);
		signUpPage.signup();

		//login
		loginPage = new LoginPage(driver);
		driver.get(LOCALHOST + this.port + "/login");
		loginPage.getUsername().sendKeys(USERNAME);
		loginPage.getPassword().sendKeys(PASSWORD);
		loginPage.getLoginSubmit().submit();
//		Assertions.assertEquals("http://localhost:" + port + "/", driver.getCurrentUrl());

		// go to home
		driver.get("http://localhost:" + this.port);
		Assertions.assertEquals(LOCALHOST + port + "/", driver.getCurrentUrl());

		// logout
		homePage.logout();

		// check home again
		driver.get("http://localhost:" + this.port);
		Assertions.assertEquals(LOCALHOST + port + "/login", driver.getCurrentUrl());

	}

}
