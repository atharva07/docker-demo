package com.atharvahiwase07.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
    protected WebDriver driver;

    @BeforeTest
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }
}
