package com.zullcrm.step_definitions;

import com.github.javafaker.Faker;
import com.zullcrm.pages.BasePage;
import com.zullcrm.pages.LogInPage;
import com.zullcrm.utilities.BrowserUtils;
import com.zullcrm.utilities.ConfigurationReader;
import com.zullcrm.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class Zull1095_Mentions {

    LogInPage logInPage = new LogInPage();
    BasePage basePage = new BasePage();
    Faker faker = new Faker();

    /**
     * Pre-Conditions
     */
    @Given("User is on homepage")
    public void user_is_on_homepage() {

        Driver.getDriver().get(ConfigurationReader.getProperty("loginUrl"));

        logInPage.inputEmail.sendKeys(ConfigurationReader.getProperty("inputEmail"));
        logInPage.inputPassword.sendKeys(ConfigurationReader.getProperty("inputPassword"));
        logInPage.logInButton.click();

    }

    @Given("User navigates to messages tab")
    public void userNavigatesToMessagesTab() {

        basePage.messagesTab.click();
    }


    /**
     * Acceptance Criteria 1
     */
    @When("User clicks Add Mention button")
    public void userClicksAddMentionButton() {

        basePage.mentionButton.click();

    }

    @And("User clicks Employee and departments tab")
    public void userClicksEmployeeAndDepartmentsTab() {

        basePage.employeesAndDepartmentsButton.click();

    }

    @When("User clicks on first Department Employee username")
    public void userClicksOnFirstDepartmentEmployeeUsername(String employeeUsername) {

        for (WebElement each : basePage.employeeUsernames) {

            if(each.getText().equalsIgnoreCase(employeeUsername))
                each.click();

        }
        //basePage.firstEmployeeUsername.click();
    }

    @Then("User sees mentioned Employee username on Activity Stream")
    public void userSeesMentionedEmployeeUsernameOnActivityStream(String employeeUsername) {

       String expectedResult = employeeUsername;
       String actualResult = basePage.verifyMention.getText();
       Assert.assertEquals(expectedResult, actualResult);

    }


    /**
     * Acceptance Criteria 2
     */
    @When("User clicks Link button")
    public void userClicksLinkButton() {

        basePage.linkButton.click();
    }

    @When("User fills out the text box with {string} and link ribbon with {string}")
    public void userFillsOutTheTextBoxWithAndLinkRibbonWith(String text, String link) {

        basePage.inputText.sendKeys(text);
        basePage.inputUrl.sendKeys(link);

    }

    @And("User clicks Save button")
    public void userClicksSaveButton() {
        basePage.saveButton.click();
    }

    @And("User clicks Send button at Messages Tab")
    public void userClicksSendButtonAtMessagesTab() {
        basePage.sendButton.click();
    }

    @Then("User sees the {string} attached to a specific {string}")
    public void userSeesTheAttachedToASpecific(String link, String text) {

        BrowserUtils.sleep(5);
        Assert.assertEquals(text, basePage.actualLinkText.getText());
    }

    /**
     * Acceptance Criteria 3
     */
    @When("User clicks Insert Video button")
    public void userClicksInsertVideoButton() {

        basePage.insertVideoButton.click();

    }

    @When("User enters {string} of the Video from {string}")
    public void userEntersOfTheVideoFrom(String url) {

        basePage.videoInputBox.sendKeys(url);

    }

    @And("User clicks Save button at Video popup")
    public void userClicksSaveButtonAtVideoPopup() {

        basePage.videoSaveButton.click();

    }

    @Then("User sees the video URL at Activity Stream")
    public void userSeesTheVideoURLAtActivityStream() {

        Driver.getDriver().switchTo().frame(basePage.messagesIframe);
        Assert.assertTrue(basePage.actualInputInMessagesBox.isDisplayed());

    }

    /**
     * Acceptance Criteria 4
     */
    @When("User clicks Quote Text button")
    public void userClicksQuoteTextButton() {

        basePage.quoteTextButton.click();

    }

    @When("User enters a {string} in yellow quote ribbon")
    public void userEntersAInYellowQuoteRibbon(String quote) {

        Driver.getDriver().switchTo().frame(basePage.messagesIframe);
        basePage.getQuoteTextRibbon.sendKeys(quote);
        Driver.getDriver().switchTo().defaultContent();

    }

    @Then("User sees the {string} in Activity Stream")
    public void userSeesTheInActivityStream(String quote) {

        BrowserUtils.sleep(2);
        Assert.assertEquals(quote, basePage.actualQuote.getText());

    }

    /**
     * Acceptance Criteria 5
     */
    @When("User clicks add tag button")
    public void userClicksAddTagButton() {

        basePage.addTagButton.click();

    }

    @When("User enter a message input in message box")
    public void userEnterAMessageInputInMessageBox() {

        Driver.getDriver().switchTo().frame(basePage.messagesIframe);
        basePage.messagesInput.sendKeys(faker.chuckNorris().fact());
        Driver.getDriver().switchTo().defaultContent();


    }

    @When("User enters {string} in input box")
    public void userEntersInInputBox(String tag) {

        basePage.tagTextInput.sendKeys(tag);

    }

    @And("User clicks add button")
    public void userClicksAddButton() {

        basePage.tagAddButton.click();

    }

    @Then("User sees {string} in Activity Stream")
    public void userSeesInActivityStream(String tag) {

        BrowserUtils.sleep(2);
        Assert.assertEquals(tag, basePage.actualTag.getText());

    }

    /**
     * Acceptance Criteria 6
     */
    @When("User clicks delete sign next to the tag")
    public void userClicksDeleteSignNextToTheTag() {

        basePage.tagDeleteButton.click();

    }

    @Then("User removes the {string}")
    public void userRemovesThe(String tag) {

        BrowserUtils.sleep(2);
        Assert.assertFalse(basePage.tagLocator.getText().contains(tag));

    }



}

