package com.atharvahiwase07.pages.flightregistration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationConfirmationPage {
    @FindBy(id = "go-to-flight-search")
    private WebElement goToFlightSearchButton;

    public RegistrationConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void gotoflightSearch() {
        this.goToFlightSearchButton.click();
    }

}
