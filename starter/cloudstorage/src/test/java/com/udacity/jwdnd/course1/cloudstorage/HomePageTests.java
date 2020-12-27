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
public class HomePageTests {

	private static final String BASE_URL = "http://localhost:";
	private static final String LOGIN = "/login";
	private static final String SIGNUP = "/signup";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	@LocalServerPort
	private int port;
	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private SignUpPage signUpPage;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();

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



	public void signup() {
		//Signup
		driver.get(BASE_URL + port + SIGNUP);
		signUpPage = new SignUpPage(driver);
		signUpPage.getUsername().sendKeys(USERNAME);
		signUpPage.getPassword().sendKeys(PASSWORD);
		signUpPage.signup();
	}

	public void login() {
		//login
		loginPage = new LoginPage(driver);
		driver.get(BASE_URL + port + LOGIN);
		loginPage.getUsername().sendKeys(USERNAME);
		loginPage.getPassword().sendKeys(PASSWORD);
		loginPage.getLoginSubmit().submit();
	}

	// _TODO Write a test that signs up a new user, logs in, verifies that the home page is accessible,
	//  logs out, and verifies that the home page is no longer accessible.
	@Test
	@Order(1)
	public void fullCycleTest(){
		signup();

		login();

		//		Assertions.assertEquals(BASE_URL + port + "/", driver.getCurrentUrl());

		// go to home
		driver.get(BASE_URL+ this.port);
		Assertions.assertEquals(BASE_URL + port + "/", driver.getCurrentUrl());

		// logout
		homePage.logout();

		// check home again
		driver.get(BASE_URL + this.port);
		Assertions.assertEquals(BASE_URL + port + LOGIN, driver.getCurrentUrl());
	}

	/*
		Authorization Tests
	 */

	@Test
	@Order(2)
	public void successfulLogin() {
		login();
		Assertions.assertEquals(BASE_URL + port + "/", driver.getCurrentUrl());
	}

	@Test
	@Order(3)
	public void unAuthorizedUser() throws InterruptedException{
		// _TODO Write a test that verifies that an unauthorized user can only access the login and signup pages.
		String currentURL = null;
		// first scenario, unauthorized user visit home page and redirected to the login page
		driver.get(BASE_URL + this.port);
		currentURL = driver.getCurrentUrl();
		Assertions.assertEquals(BASE_URL + port + LOGIN, currentURL);

		// second scenario, unauthorized user try to visit the signup page and successfully reach it
		driver.get(BASE_URL + this.port + SIGNUP);
		currentURL = driver.getCurrentUrl();
		Assertions.assertEquals(BASE_URL + port + SIGNUP, currentURL);

		// third scenario, unauthorized user try to visit the login page and successfully reach it
		driver.get(BASE_URL + this.port + LOGIN);
		currentURL = driver.getCurrentUrl();
		Assertions.assertEquals(BASE_URL + port + LOGIN, currentURL);
	}

}
