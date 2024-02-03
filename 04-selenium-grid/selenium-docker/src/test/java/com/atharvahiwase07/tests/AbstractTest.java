package com.atharvahiwase07.tests;

import java.security.DrbgParameters.Capability;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    public void setDriver(){
        if(Boolean.getBoolean("selenium.grid.enabled"))
        {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }
        this.driver = new ChromeDriver();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {

        Capabilities capabilities;
        if(System.getProperty("browser").equalsIgnoreCase("chrome"))
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
