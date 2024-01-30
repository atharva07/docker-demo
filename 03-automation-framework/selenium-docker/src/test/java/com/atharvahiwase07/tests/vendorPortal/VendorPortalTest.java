package com.atharvahiwase07.tests.vendorPortal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.atharvahiwase07.pages.vendorPortal.DashboardPage;
import com.atharvahiwase07.pages.vendorPortal.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VendorPortalTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @BeforeTest
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }
    
    @Test
    public void loginTest() {
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        driver.manage().window().maximize();
        Assert.assertTrue(loginPage.isAt());
        loginPage.login("sam", "sam");
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest() {
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), "$40,000");
        Assert.assertEquals(dashboardPage.getAnnualEarning(), "$215,000");
        Assert.assertEquals(dashboardPage.getProfitMargin(), "50%");
        Assert.assertEquals(dashboardPage.getAvailableInventory(), "18");


        // order history search
        dashboardPage.searchOrderHistoryBy("adams");
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), 8);
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest() {
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }
}