package wrappers;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrappers {
    //Creating ChromeDriver instance variable
    ChromeDriver driver = (ChromeDriver) DriverFactory.getDriver();


    //Creating WebDriverWait instance variable
    protected WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    public void scroll(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior : 'smooth', block : 'center', inline : 'center'});", element);
    }

    //To click an WebElement
    public boolean click(WebElement element) {
        try {
            scroll(element);
            Thread.sleep(500);
            element.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //To enter text in a WebElement
    public boolean sendKeys(WebElement element, String text) {
        try {
            scroll(element);
            Thread.sleep(500);
            //Clearing the WebElement
            element.clear();

            Thread.sleep(500);
            //Sending text to the WebElement
            element.sendKeys(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //To open url
    public boolean openUrl(String url) {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sendkeysToNameInputBox(String name) {
        try {
            //Locating textbox having "Name" text
            WebElement nameTxtBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Name']/ancestor::div[3]/following-sibling::div//input")));

            //Entering Name
            return sendKeys(nameTxtBox, name);
        } catch (Exception e) {
            return false;
        }
    }

    //To return epoch time
    public long getEpochTime() {
        //returning epoch time
        return System.currentTimeMillis();
    }

    public boolean sendkeysToPracticingAutomationInputBox(String text) {
        try {
            //Locating textbox having "Why are you practicing Automation?" text
            WebElement practAuto = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Why are you practicing Automation?']/ancestor::div[3]/following-sibling::div//textarea")));
            return sendKeys(practAuto, text);
        } catch (Exception e) {
            return false;
        }
    }

    //Selecting radio button for given experience
    public boolean experienceRadioBtn(String exp) {
        try {
            //Locating Radio Button for given experience
            WebElement expRadioBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='How much experience do you have in Automation Testing?']/ancestor::div[3]/following-sibling::div//span[text()='" + exp + "']/ancestor::div[2]/preceding-sibling::div")));

            //Clicking on radio button
            click(expRadioBtn);

            System.out.println(exp + " experience selected");
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Selecting Checkbox for given option text
    public boolean checkBoxSelect(String option) {
        try {
            //Locating the checkbox for given option text
            WebElement optionChkBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Which of the following have you learned in Crio.Do for Automation Testing?']/ancestor::div[3]/following-sibling::div//span[text()='" + option + "']/ancestor::div[2]/preceding-sibling::div")));

            //Selecting the checkbox
            return click(optionChkBox);

        } catch (Exception e) {
            return false;
        }
    }

    //To select option from the dropdown of "How should you be addressed"
    public boolean clickAddressedDropDown() {
        try {
            //Locating the dropdown button
            WebElement dropDown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='How should you be addressed?']/ancestor::div[4]//span[text()='Choose']")));

            //Clicking on the dropdown option
            return click(dropDown);
        } catch (Exception e) {
            return false;
        }
    }

    //To select option from the dropdown of "How should you be addressed"
    public boolean dropDownOptionClick(String option) {
        try {
            //Locating the dropdown option with given option
            WebElement optionSelect = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='How should you be addressed?']/ancestor::div[4]//span[text()='" + option + "'])[2]")));

            //Clicking on the dropdown option
            click(optionSelect);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='vRMGwf oJeWuf' and normalize-space()='" + option + "'])[1]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Selecting the date before today
    public boolean day7Before(int minusDay) {
        try {
            //Getting today's date in the "yyy-MM-dd" pattern
            LocalDate currentDate = LocalDate.now();

            //Getting given number of days before today's date
            LocalDate newDate = currentDate.minusDays(minusDay);

            //Formate the stored date into "dd-MM-yyyy" pattern
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            //Converting formated date into String
            String date = newDate.format(dtf);

            //Locating the input box
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='What was the date 7 days ago?']/ancestor::div[4]//input")));

            //Entering date to the input box
            return sendKeys(input, date);

        } catch (Exception e) {
            return false;
        }
    }

    //Entering the given time
    public boolean enterGivenTime(String timeToEnter) {
        try {
            //Locating the hour input box
            WebElement hourInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='What is the time right now?']/ancestor::div[4]//input)[1]")));

            //Locating the minute input box
            WebElement minInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='What is the time right now?']/ancestor::div[4]//input)[2]")));

            //Entering the hour part and Entering the minutes part
            return sendKeys(hourInput, timeToEnter.substring(0, 2)) && sendKeys(minInput, timeToEnter.substring(3, 5));
        } catch (Exception e) {
            return false;
        }
    }

    //Entering the current time
    public boolean enterCurrentTime() {
        try {
            Calendar c = Calendar.getInstance();
            String hr = String.valueOf(c.get(Calendar.HOUR));
            String mm = String.valueOf(c.get(Calendar.MINUTE));
            int amPm = c.get(Calendar.AM_PM);

            //Locating hour input box
            WebElement hourInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='What is the time right now?']/ancestor::div[4]//input)[1]")));

            //Locating the minute input box
            WebElement minInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[text()='What is the time right now?']/ancestor::div[4]//input)[2]")));

            //Entering hour part in the hour input box and Entering the minute part in the minute input box
            boolean status = sendKeys(hourInput, hr) && sendKeys(minInput, mm);
            if (!status) return false;

            // AM PM dropdown does not render always
            try {
                WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='vRMGwf oJeWuf' and normalize-space()='AM'])[1]")));
                click(dropdown);

                if (amPm == 0) {
                    return click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='vRMGwf oJeWuf' and normalize-space()='AM'])[2]"))));
                } else {
                    return click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='vRMGwf oJeWuf' and normalize-space()='PM'])[2]"))));
                }
            } catch (Exception e) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean clickSubmitBtn() {
        try {
            return click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Submit']"))));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyThanksMessageIsDisplayed() {
        try {
            //Waiting and locating for the successful WebElement
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[normalize-space()='Thanks for your response, Automation Wizard!']")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
} 