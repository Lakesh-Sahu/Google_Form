import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import wrappers.DriverFactory;
import wrappers.Wrappers;

import java.util.Arrays;

public class TestCases extends Wrappers {
    // Creating instance variable of WebDriverWait class
    WebDriverWait wait;

    // Verify url is opening
    @Test
    public void testCase01() {
        try {
            System.out.println("Start Test case: testCase01");

            // Opening the required url
            Assert.assertTrue(openUrl("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform"), "User is unable to open url");

            System.out.println("testCase01: Passed");

        } catch (Exception e) {
            System.out.println("testCase01 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase01");
    }

    // Verify user is able to enter name in the Name textbox
    @Test
    public void testCase02() {
        try {
            System.out.println("Start Test case: testCase02");

            Assert.assertTrue(sendkeysToNameInputBox("Crio Learner"), "User is unable to enter name in name input box");

            System.out.println("testCase02: Passed");
        } catch (Exception e) {
            System.out.println("testCase02 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase02");
    }

    // Verify user is able to enter details and epoch time in "Why are you practicing Automation?" textbox
    @Test
    public void testCase03() {
        try {
            System.out.println("Start Test case: testCase03");

            //Storing the text to enter + epoch time
            String text = "I want to be the best QA Engineer! " + getEpochTime();

            //Entering the text to the textbox
            Assert.assertTrue(sendkeysToPracticingAutomationInputBox(text), "Unable to enter text in practicing automation input box");

            System.out.println("testCase03: Passed");
        } catch (Exception e) {
            System.out.println("testCase03 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase03");
    }

    // Selecting radio button for "How much experience do you have in Automation Testing?"
    @Test
    public void testCase04() {
        try {
            System.out.println("Start Test case: testCase04");

            //Storing experience between options: 0 - 2, 3 - 5, 6 - 10, > 10
            String experience = "0 - 2";

            //Selecting radio button for above experience
            Assert.assertTrue(experienceRadioBtn(experience), "User is unable to select experience for given option");

            System.out.println("testCase04: Passed");
        } catch (Exception e) {
            System.out.println("testCase04 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase04");
    }

    // Selecting checkbox for "Which of the following have you learned in Crio.Do for Automation Testing?"
    @Test
    public void testCase05() {
        try {
            System.out.println("Start Test case: testCase05");

            //Storing options to be selected
            String[] optionsToSelect = {"Java", "Selenium", "TestNG"};

            for (String option : optionsToSelect) {
                Assert.assertTrue(checkBoxSelect(option), "User is unable to check the " + option + " option");
            }

            System.out.println("testCase05: Passed");
        } catch (Exception e) {
            System.out.println("testCase05 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase05");
    }

    // Selecting How should you be addressed?
    @Test
    public void testCase06() {
        try {
            System.out.println("Start Test case: testCase06");

            //Clicking on the dropdown
            Assert.assertTrue(clickAddressedDropDown(), "User is unable to click \"How should you be addressed dropdown\"");

            //Storing the option to select
            String optionToSelect = "Mr";

            //Selecting the option
            Assert.assertTrue(dropDownOptionClick(optionToSelect), "User is unable to select the " + optionToSelect + "option for \"How should you be addressed dropdown\"");

            System.out.println("testCase06: Passed");

        } catch (Exception e) {
            System.out.println("testCase06 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase06");
    }

    // Entering date before 7 days from today
    @Test
    public void testCase07() {
        try {
            System.out.println("Start Test case: testCase07");

            int numberOfDaysBefore = 7;

            //Selecting the date (today - number of days before)
            Assert.assertTrue(day7Before(numberOfDaysBefore), "User is unable to select " + numberOfDaysBefore + " days before today");

            System.out.println("testCase07: Passed");
        } catch (Exception e) {
            System.out.println("testCase07 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase07");
    }

    // Entering current time and clicking submit button
    @Test
    public void testCase08() {
        try {
            System.out.println("Start Test case: testCase08");

            //Enter the current time in "HH:mm" format and click on the submit button
            Assert.assertTrue(enterCurrentTime(), "User is unable to enter current time");
            Assert.assertTrue(clickSubmitBtn(), "User is unable to click submit button");

            System.out.println("testCase08: Passed");
        } catch (Exception e) {
            System.out.println("testCase08 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase08");
    }

    // Checking successful message and printing
    @Test
    public void testCase09() {
        try {
            System.out.println("Start Test case: testCase09");
            Assert.assertTrue(verifyThanksMessageIsDisplayed(), "User is unable to see Thanks message after the submission");
            System.out.println("testCase09: Passed");
        } catch (Exception e) {
            System.out.println("testCase09 Failed\n" + Arrays.toString(e.getStackTrace()));
        }
        System.out.println("End Test case: testCase09");
    }

    //Quitting the browser window
    @AfterTest
    public void endTest() {
        System.out.println("Quiting");
        DriverFactory.getDriver().quit();
    }
}