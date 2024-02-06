package com.atharvahiwase07.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.atharvahiwase07.utils.Config;
import com.atharvahiwase07.utils.Constants;
import com.google.common.util.concurrent.Uninterruptibles;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;

    @BeforeSuite
    public void setUpConfig() {
        Config.initialize();
    }

    @BeforeTest
    public void setDriver() throws MalformedURLException {
        if(Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)))
        {
            this.driver = getRemoteDriver();
        } else {
            this.driver = getLocalDriver();
        }
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities = new ChromeOptions();
        if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("gird url: {}", url);
        return new RemoteWebDriver(new URL(url), capabilities);
    }

    private WebDriver getLocalDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }

    // this method will add 5 senonds in execution.
    @SuppressWarnings("null")
    @AfterMethod
    public void sleep() {
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }
}
