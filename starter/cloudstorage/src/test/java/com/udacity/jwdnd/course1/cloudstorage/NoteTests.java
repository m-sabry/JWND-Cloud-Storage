package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NoteTests {

	@LocalServerPort
	private int port;
	private WebDriver driver;

	private HomePage homePage;
	private LoginPage loginPage;
	private SignUpPage signUpPage;
	private NoteTab noteTab;

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
		driver = new ChromeDriver();

		signup(driver);
		// login
		login(driver);

		// Loading home page
		driver.get(BASE_URL + this.port);
		homePage = new HomePage(driver);

		// NoteModal initiation
		noteTab = new NoteTab(driver);

		// navigate to notes tab
		noteTab.openNoteTab();

	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	@Order(1)
	public void createNote(){
		// NoteModal initiation
		noteTab = new NoteTab(driver);
		// navigate to notes tab
		noteTab.openNoteTab();

		int sizeBeforeSaving = noteTab.notesSize();
		noteTab.createNote("Title: " + new Date().toString(), "Description: " + new Date().toString());
		driver.get(BASE_URL + port);
		int sizeAfterSaving = noteTab.notesSize();

		Assertions.assertEquals(1, sizeAfterSaving - sizeBeforeSaving);
	}

	@Test
	@Order(2)
	public void editNote(){
		// _TODO Write a test that edits an existing note and verifies that the changes are displayed.
		String expectedTitle = "Edited title";
		String expectedDescription = "Edited description";

		// edit the last note in the note's list
		int noteCounter = noteTab.notesSize();
		noteTab.editNote(noteCounter - 1, expectedTitle, expectedDescription);

		// loading home page and navigating to notes tab
		driver.get(BASE_URL + port);
		noteTab.openNoteTab();

		String[] editedValues = noteTab.getEditedValues(noteCounter -1 );
		String actualTitle = editedValues[0];
		String actualDescription = editedValues[1];
		Assertions.assertEquals(expectedTitle, actualTitle);
		Assertions.assertEquals(expectedDescription, actualDescription);
	}

	@Test
	@Order(3)
	public void deleteNote(){
		// _TODO Write a test that deletes a note and verifies that the note is no longer displayed.
		// NoteModal initiation
		noteTab = new NoteTab(driver);

		// navigate to notes tab
		noteTab.openNoteTab();

		int sizeBeforeSaving = noteTab.notesSize();
		noteTab.deleteNote();
		int sizeAfterSaving = noteTab.notesSize();

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
