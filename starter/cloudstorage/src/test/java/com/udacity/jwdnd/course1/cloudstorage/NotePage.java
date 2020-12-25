package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class NotePage {

    private final WebDriverWait driverWait;

    // Modal Controls
    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(xpath = "//div[@id='noteModal']" +
            "//div[@class='modal-dialog']" +
            "//div[@class='modal-footer']" +
            "//button[@class='btn btn-primary']")
    private WebElement saveButton;

    // Tab Controls
    @FindBy(xpath = "//table[@id='noteTable']//tbody//tr")
    private List<WebElement> notes;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(xpath = "//div[@id='nav-notes']//button")
    private WebElement addNoteButton;

    @FindBy(xpath = "//table[@id='noteTable']//tbody//tr//td//button")
    private List<WebElement> editNoteButtons;

    @FindBy(xpath = "//table[@id='noteTable']//tbody//tr//td//a")
    private WebElement deleteNoteButton;

    public NotePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        driverWait = new WebDriverWait(driver, 500);
    }

    public int notesSize(){
        return notes.size();
    }

    public void openNoteTab(){
        // loading notes tab
        driverWait.until(ExpectedConditions.elementToBeClickable(noteTab)).click();
    }

    public void createNote(String title, String description){
        // clicking add note button
        driverWait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();

        // here note modal should be presented and intractable to set the title and description for the note
        driverWait.until(ExpectedConditions.elementToBeClickable(noteTitle)).click();
        noteTitle.sendKeys(title);
        driverWait.until(ExpectedConditions.elementToBeClickable(noteDescription)).click();
        noteDescription.sendKeys(description);

        driverWait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    /**
     * Edits note at a specific row
     *
     * @param noteIndex
     * @param title
     * @param description
     */
    public void editNote(int noteIndex, String title, String description) {

        // TODO get edit button for note by noteIndex
        WebElement editNoteButton = editNoteButtons.get(noteIndex);

        // clicking edit note button
        driverWait.until(ExpectedConditions.elementToBeClickable(editNoteButton)).click();

        // here note modal should be presented and intractable to update the title and description for the note
        driverWait.until(ExpectedConditions.elementToBeClickable(noteTitle)).click();
        noteTitle.clear();
        noteTitle.sendKeys(title);

        driverWait.until(ExpectedConditions.elementToBeClickable(noteDescription)).click();
        noteDescription.clear();
        noteDescription.sendKeys(description);

        driverWait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }


    /**
     * Deletes last added note
     */
    public void deleteNote() {
        driverWait.until(ExpectedConditions.elementToBeClickable(deleteNoteButton)).click();
    }

    public String[] getEditedValues(int rowIndex) {
        List<WebElement> tds = notes.get(rowIndex).findElements(By.xpath("td//span"));
        String [] values = new String [2];
        values[0] = tds.get(0).getAttribute("innerHTML"); // Note Title
        values[1] = tds.get(1).getAttribute("innerHTML"); // Note Description
        return values;
    }
}