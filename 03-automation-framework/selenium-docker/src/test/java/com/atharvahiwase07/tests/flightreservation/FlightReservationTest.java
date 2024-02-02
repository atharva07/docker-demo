package com.atharvahiwase07.tests.flightreservation;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.atharvahiwase07.pages.flightregistration.FlightConfirmationPage;
import com.atharvahiwase07.pages.flightregistration.FlightSearchPage;
import com.atharvahiwase07.pages.flightregistration.FlightsSelectionPage;
import com.atharvahiwase07.pages.flightregistration.RegistrationConfirmationPage;
import com.atharvahiwase07.pages.flightregistration.RegistrationPage;
import com.atharvahiwase07.tests.AbstractTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;

public class FlightReservationTest extends AbstractTest{
    
    private String noOfPassengers;
    private String expectedPrice;

    // Once we are done with scripting the test file execute the test with the folllowing commands.
    // Open the terminal.
    // go to the location of the pom.xml file
    // hit the command mvn clean test
    // check the reports in the target folder of the project.
    // dont forget to push the code.
    @BeforeTest
    @Parameters({"noOfPassengers" ,"expectedPrice"})
    public void setParameters(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;

        // this is the driver setup
        //WebDriverManager.chromedriver().driverVersion("119.0.6045.105").setup();
        //this.driver = new ChromeDriver();
    }
    
    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        driver.manage().window().maximize();
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("Selenium", "Docker");
        registrationPage.enterUserCredentials("selenium@docker.com", "docker");
        registrationPage.enterAddress("123 non main street", "Newe York", "17223");
        registrationPage.register();   
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.gotoflightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.selectPassengers(noOfPassengers);
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightSelectionTest() {
        FlightsSelectionPage flightSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt());
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmflightsButton();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightReservationConfirmationTest() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), expectedPrice);
    }
}
