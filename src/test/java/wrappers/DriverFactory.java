package wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.logging.Level;

public class DriverFactory {
    private static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            System.setProperty("java.util.logging.config.file", "logging.properties");

            //Creating the ChromeOptions class object
            ChromeOptions options = new ChromeOptions();
            LoggingPreferences logs = new LoggingPreferences();

            logs.enable(LogType.BROWSER, Level.ALL);
            logs.enable(LogType.DRIVER, Level.ALL);
            options.setCapability("goog:loggingPrefs", logs);
            options.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(options);

            //Maximizing the browser window
            driver.manage().window().maximize();
            System.out.println("starting browser");
        }
        return driver;
    }
}