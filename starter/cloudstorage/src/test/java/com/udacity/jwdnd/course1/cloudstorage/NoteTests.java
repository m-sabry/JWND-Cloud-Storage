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
	private NoteTab noteTab;

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
		driver.get(LOCALHOST + port + "/login");
		loginPage.getUsername().sendKeys(USERNAME);
		loginPage.getPassword().sendKeys(PASSWORD);
		loginPage.getLoginSubmit().submit();

		// Loading home page
		driver.get(LOCALHOST + this.port);
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

//		homePage.logout();
	}


	// _TODO Write a test that creates a note, and verifies it is displayed.
	@Test
	@Order(1)
	public void createNote(){
		// NoteModal initiation
		noteTab = new NoteTab(driver);
		// navigate to notes tab
		noteTab.openNoteTab();

		int sizeBeforeSaving = noteTab.notesSize();
		noteTab.createNote("Title: " + new Date().toString(), "Description: " + new Date().toString());
		driver.get(LOCALHOST + port);
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
		driver.get(LOCALHOST + port);
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

}
