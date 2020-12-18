package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class NoteModal {

    private WebDriverWait driverWait;

    @FindBy(id = "note-id")
    private WebElement noteId;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "btn-save-note")
    private WebElement saveButton;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;

    @FindBy(id = "btn-new-note")
    private WebElement addNoteButton;

    @FindBy(id = "noteModal")
    private WebElement noteModal;

    @FindBy(xpath = "//table[@id='noteTable']//tbody//tr//td//button")
    private WebElement editNoteButton;

    @FindBy(id = "")
    private WebElement deleteNoteButton;

    public NoteModal(WebDriver driver){
        PageFactory.initElements(driver, this);
        driverWait = new WebDriverWait(driver, 500);
    }

    public WebElement getNoteId() {
        return noteId;
    }

    public WebElement getNoteTitle() {
        return noteTitle;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getNoteTab() {
        return noteTab;
    }

    public WebElement getAddNoteButton() {
        return addNoteButton;
    }

    public WebElement getEditNoteButton() {
        return editNoteButton;
    }

    public WebElement getDeleteNoteButton() {
        return deleteNoteButton;
    }

    public int notesSize(WebDriver driver){
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='noteTable']//tr"));
        return elements.size();
    }

    public void openNoteTab(){

        // loading notes tab
        driverWait.until(ExpectedConditions.elementToBeClickable(getNoteTab())).click();
    }


    public void createNote(String title, String description){
        // clicking add note button
        driverWait.until(ExpectedConditions.elementToBeClickable(getAddNoteButton())).click();

        // here note modal should be presented and intractable to set the title and description for the note
        driverWait.until(ExpectedConditions.elementToBeClickable(getNoteTitle())).click();
        getNoteTitle().sendKeys(title);
        driverWait.until(ExpectedConditions.elementToBeClickable(getNoteDescription())).click();
        getNoteDescription().sendKeys(description);

        System.out.println("Note modal ---> Before Saving");
        driverWait.until(ExpectedConditions.elementToBeClickable(getSaveButton())).click();
    }

    public void editNote(String title, String description) {
        // clicking add note button
        driverWait.until(ExpectedConditions.elementToBeClickable(getEditNoteButton())).click();

        // here note modal should be presented and intractable to set the title and description for the note
        driverWait.until(ExpectedConditions.elementToBeClickable(getNoteTitle())).click();
        getNoteTitle().clear();
        getNoteTitle().sendKeys(title);
        driverWait.until(ExpectedConditions.elementToBeClickable(getNoteDescription())).click();
        getNoteDescription().clear();
        getNoteDescription().sendKeys(description);

        System.out.println("Note modal ---> Before Saving");
        driverWait.until(ExpectedConditions.elementToBeClickable(getSaveButton())).click();
    }
}