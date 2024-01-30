package com.atharvahiwase07.pages.vendorPortal;

import org.openqa.selenium.WebDriver;

import com.atharvahiwase07.pages.AbstractPage;

public class LoginPage extends AbstractPage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt(){
        return false;
    }

    
}
