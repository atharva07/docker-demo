package com.atharvahiwase07.tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setDriver(String browser) {
        if(Boolean.getBoolean("selenium.grid.enabled"))
        {
            try {
                this.driver = getRemoteDriver(browser);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else {
            this.driver = getLocalDriver();
        }
    }

    private WebDriver getRemoteDriver(String browser) throws MalformedURLException {
        Capabilities capabilities;
        //if(System.getProperty("browser").equalsIgnoreCase("chrome"))
        if(browser.equalsIgnoreCase("chrome"))
        {
            capabilities = new ChromeOptions();
        } else {
            capabilities = new FirefoxOptions();
        }
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}
